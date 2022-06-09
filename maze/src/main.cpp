#define GAME_ID 1
#define DATA_PIN 2

// //Declaración de Librerías
#include <Wire.h>
#include <SPI.h>        //Librería para comunicación SPI
#include <UNIT_PN532.h> //Librería Modificada
#include <vector>
#include <Arduino.h>
#include <WiFi.h>
#include <HTTPClient.h>

#define SSID "Kaan’s iPhone"
#define PASSWORD "Lucas123"

// //Conexiones SPI del ESP32
#define PN532_SCK (18)
#define PN532_MOSI (23)
#define PN532_SS (5)
#define PN532_MISO (19) // Para almacenar los datos

UNIT_PN532 nfc(PN532_SS); // Línea enfocada para la comunicación por SPI

std::vector<String> players;

String uid_to_char(uint8_t uid[])
{
  return (String)uid[0] + (String)uid[1] + (String)uid[2] + (String)uid[3] + (String)uid[4] + (String)uid[5] + (String)uid[6];
}

void setup()
{
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
}

void loop()
{
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
      // beep on register?
    }
  }
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