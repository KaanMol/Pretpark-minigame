#include <wireless.h>

Wireless::Wireless() {}

Wireless::~Wireless() {}

void Wireless::init() {
    Serial.print("Connecting to WiFi ");

    WiFi.begin(SSID, PASSWORD);

    while (WiFi.status() != WL_CONNECTED)
    {
        delay(500);
        Serial.print(".");
    }

    Serial.println(" CONNECTED");
}