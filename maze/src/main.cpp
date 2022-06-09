#define GAME_ID 1

#include <Wire.h>
#include <SPI.h>
#include <UNIT_PN532.h>
#include <vector>
#include <Arduino.h>
#include "maze.h"
#include <WiFi.h>
#include <HTTPClient.h>

#define SSID "Kaan’s iPhone"
#define PASSWORD "Lucas123"

#define PN532_SCK (18)
#define PN532_MOSI (23)
#define PN532_SS (5)
#define PN532_MISO (19) 

UNIT_PN532 nfc(PN532_SS);

Maze maze;

std::vector<String> players;

String uid_to_char(uint8_t uid[])
{
  return (String)uid[0] + (String)uid[1] + (String)uid[2] + (String)uid[3] + (String)uid[4] + (String)uid[5] + (String)uid[6];
}



void award_points()
{
  for (String &player : players)
  {
    HTTPClient http;
    String url = "https://mobiele-beleving-dev.herokuapp.com/points?nfcId=" + player + "&gameId=" + GAME_ID;
    http.begin(url.c_str());
    int response = http.POST("");

    if (response >= 200 && response < 300)
    {
      Serial.println("Success (" + String(response) + "): " + http.getString());
      // beep?
    }
    else
    {
      Serial.println("Error (" + String(response) + "): " + http.getString());
    }

    http.end();
  }
}

void setup()
{
	ledcSetup(0, 2000, 8);
	ledcAttachPin(4, 0);

  Serial.println("Starting serial connection ...");

  Serial.begin(115200);
  while (!Serial)
    delay(10);

  Serial.println("Looking for NFC module ...");

  // Initialise NFC
  nfc.begin();
  uint32_t nfc_version = nfc.getFirmwareVersion();
  if (!nfc_version)
  {
    Serial.println("Could not find NFC module! Stopping");
    while (1)
      ;
  }
  nfc.SAMConfig();

  // Connect to WiFi
  Serial.print("Connecting to WiFi ");
  WiFi.begin(SSID, PASSWORD);
  while (WiFi.status() != WL_CONNECTED)
  {
    delay(500);
    Serial.print(".");
  }

  Serial.println(" CONNECTED");

  Serial.println("Ready");

	FastLED.addLeds<WS2811, DATA_PIN, GRB>(maze.colors, WIDTH * HEIGHT);
	FastLED.setBrightness(40);


	pinMode(32, INPUT);
	pinMode(33, INPUT);
	pinMode(34, INPUT);
	pinMode(35, INPUT);
}

void loop()
{
	if (players.size() != 2) {
		// Read the NFC card
		uint8_t uid[7] = {0, 0, 0, 0, 0, 0, 0};
		uint8_t uidLength;
		uint8_t success = nfc.readPassiveTargetID(PN532_MIFARE_ISO14443A, uid, &uidLength);

		if (success)
		{
			String id = uid_to_char(uid);

			if (!std::count(players.begin(), players.end(), id))
			{
				Serial.println("Adding " + id);
				players.push_back(id);

				ledcWriteTone(0, 1000);
				delay(200);
				ledcWriteTone(0, 0);
			}
		}

		if (players.size() == 2) {
			maze.set_initial_player_position();
			maze.draw();
			FastLED.show();
		}
		
		return;
	}



  	int leftButtonState = digitalRead(34);
	int upButtonState = digitalRead(35);
	int downButtonState = digitalRead(32);
	int rightButtonState = digitalRead(33);

	if (leftButtonState == HIGH) {
		Serial.println("left");
		maze.move_left();
		maze.draw();
		FastLED.show();
		delay(500);
	} else if (upButtonState == HIGH) {
		Serial.println("up");
		maze.move_up();
		maze.draw();
		FastLED.show();
		delay(500);
	} else if (downButtonState == HIGH) {
		Serial.println("down");
		maze.move_down();
		maze.draw();
		FastLED.show();
		delay(500);
	} else if (rightButtonState == HIGH) {
		Serial.println("right");
		maze.move_right();
		maze.draw();
		FastLED.show();
		delay(500);
	}

	if (maze.target_reached) {
		for (int i = 0; i < 3; i ++) {
			ledcWriteTone(0, 1000);
			delay(200);
			ledcWriteTone(0, 0);
			delay(300);
		}
		award_points();
		ESP.restart();
	}
}