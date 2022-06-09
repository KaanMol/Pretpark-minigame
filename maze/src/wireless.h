#include <WiFi.h>
#include <HTTPClient.h>

#define SSID "Kaan’s iPhone"
#define PASSWORD "Lucas123"

class Wireless {
    public:
        Wireless();
        ~Wireless();
        void init();
};