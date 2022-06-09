#include <nfc.h>

NFC::NFC() : nfc(PN532_SS) {}

NFC::~NFC() {}

void NFC::init() {
    Serial.println("Looking for NFC module ...");

    // Initialise NFC
    this->nfc.begin();

    uint32_t nfc_version = nfc.getFirmwareVersion();

    if (!nfc_version) {
        Serial.println("Could not find NFC module! Stopping");
        while (1);
    }

    this->nfc.SAMConfig();
}

uint8_t NFC::read() {
    return nfc.readPassiveTargetID(PN532_MIFARE_ISO14443A, this->uid, &this->uidLength);
}

String NFC::getCardId() {
    return (String)uid[0] + (String)uid[1] + (String)uid[2] + (String)uid[3] + (String)uid[4] + (String)uid[5] + (String)uid[6];
}