#define PN532_SCK (18)
#define PN532_MOSI (23)
#define PN532_SS (5)
#define PN532_MISO (19)

#include <SPI.h>
#include <UNIT_PN532.h>

class NFC {
    public:
        NFC();
        ~NFC();
        void init();
        uint8_t read();
        String getCardId();
    private:
        UNIT_PN532 nfc;
        uint8_t uid[7] = {0, 0, 0, 0, 0, 0, 0};
		uint8_t uidLength;
};