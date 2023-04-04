
#define GAME_ID 1

#include <Wire.h>
#include <wireless.h>
#include <nfc.h>
#include <vector>
#include <Arduino.h>
#include <maze.h>
#include <WiFi.h>
#include <HTTPClient.h>

#define SSID "Kaan’s iPhone"
#define PASSWORD "Lucas123"

std::vector<String> players;

Maze maze;
NFC nfc;
Wireless wifi;
bool started = false;

void awardPoints()
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

	maze.init();
	nfc.init();
	wifi.init();
	Serial.println("Ready");
}

void loop()
{
	if (players.size() < 4)
	{
		if (nfc.read())
		{
			String id = nfc.getCardId();

			if (!std::count(players.begin(), players.end(), id))
			{
				Serial.println("Adding " + id);
				players.push_back(id);

				ledcWriteTone(0, 1000);
				delay(200);
				ledcWriteTone(0, 0);
			}
		}
		if (players.size() == 2 && started == false)
		{
			maze.start();
			started = true;
		}
	}

	int leftButtonState = digitalRead(34);
	int upButtonState = digitalRead(35);
	int downButtonState = digitalRead(32);
	int rightButtonState = digitalRead(33);

	if (leftButtonState == HIGH)
	{
		Serial.println("left");
		maze.moveLeft();
	}
	else if (upButtonState == HIGH)
	{
		Serial.println("up");
		maze.moveUp();
	}
	else if (downButtonState == HIGH)
	{
		Serial.println("down");
		maze.moveDown();
	}
	else if (rightButtonState == HIGH)
	{
		Serial.println("right");
		maze.moveRight();
	}

	if (maze.hasReachedTarget())
	{
		for (int i = 0; i < 3; i++)
		{
			ledcWriteTone(0, 1000);
			delay(200);
			ledcWriteTone(0, 0);
			delay(300);
		}
		awardPoints();
		ESP.restart();
	}
}