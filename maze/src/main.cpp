#include <Wire.h>
#include <FastLED.h>

// // How many leds in your strip?
// #define NUM_LEDS 90
// // For led chips like WS2812, which have a data line, ground, and power, you just
// // need to define DATA_PIN.  For led chipsets that are SPI based (four wires - data, clock,
// // ground, and power), like the LPD8806 define both DATA_PIN and CLOCK_PIN
// // Clock pin only needed for SPI based chipsets when not using hardware SPI
#define DATA_PIN 2

// // Define the array of leds
// CRGB leds[NUM_LEDS];

// void setup()
// {
//   FastLED.addLeds<WS2811, DATA_PIN, GRB>(leds, NUM_LEDS);
// }

// void loop()
// {
//   FastLED.setBrightness(100);

//   for (int i = 0; i < NUM_LEDS; i++)
//   {
//     leds[i] = CRGB::Green;
//   }
//   FastLED.show();
//   delay(500);

//   for (int i = 0; i < NUM_LEDS; i++)
//   {
//     leds[i] = CRGB::Red;
//   }

//   FastLED.show();
//   delay(500);

//   for (int i = 0; i < NUM_LEDS; i++)
//   {
//     leds[i] = CRGB::Blue;
//   }

//   FastLED.show();
//   delay(500);
// }

// //Declaración de Librerías
// #include <Wire.h>
// #include <SPI.h> //Librería para comunicación SPI
// #include <UNIT_PN532.h> //Librería Modificada

// //Conexiones SPI del ESP32
// #define PN532_SCK  (18)
// #define PN532_MOSI (23)
// #define PN532_SS   (5)
// #define PN532_MISO (19) //Para almacenar los datos

// UNIT_PN532 nfc(PN532_SS);// Línea enfocada para la comunicación por SPI

// void setup() {
//   Serial.begin(115200);
//   while (!Serial) delay(10); // for Leonardo/Micro/Zero

//   nfc.begin();

//   uint32_t versiondata = nfc.getFirmwareVersion();
//   if (! versiondata) {
//     Serial.print("Didn't find PN53x board");
//     while (1); // halt
//   }
//   // Got ok data, print it out!
//   Serial.print("Found chip PN5"); Serial.println((versiondata>>24) & 0xFF, HEX);
//   Serial.print("Firmware ver. "); Serial.print((versiondata>>16) & 0xFF, DEC);
//   Serial.print('.'); Serial.println((versiondata>>8) & 0xFF, DEC);

//   // configure board to read RFID tags
//   nfc.SAMConfig();

//   Serial.println("Waiting for an ISO14443A Card ...");
// }

// void loop(void) {
//   uint8_t success;
//   uint8_t uid[] = { 0, 0, 0, 0, 0, 0, 0 };  // Buffer to store the returned UID
//   uint8_t uidLength;                        // Length of the UID (4 or 7 bytes depending on ISO14443A card type)

//   // Wait for an ISO14443A type cards (Mifare, etc.).  When one is found
//   // 'uid' will be populated with the UID, and uidLength will indicate
//   // if the uid is 4 bytes (Mifare Classic) or 7 bytes (Mifare Ultralight)
//   success = nfc.readPassiveTargetID(PN532_MIFARE_ISO14443A, uid, &uidLength);

//   if (success) {
//     // Display some basic information about the card
//     Serial.println("Found an ISO14443A card");
//     Serial.print("  UID Length: ");Serial.print(uidLength, DEC);Serial.println(" bytes");
//     Serial.print("  UID Value: ");
//     nfc.PrintHex(uid, uidLength);
//     Serial.println("");

//     if (uidLength == 4)
//     {
//       // We probably have a Mifare Classic card ...
//       Serial.println("Seems to be a Mifare Classic card (4 byte UID)");

//       // Now we need to try to authenticate it for read/write access
//       // Try with the factory default KeyA: 0xFF 0xFF 0xFF 0xFF 0xFF 0xFF
//       Serial.println("Trying to authenticate block 4 with default KEYA value");
//       uint8_t keya[6] = { 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF };

//       // Start with block 4 (the first block of sector 1) since sector 0
//       // contains the manufacturer data and it's probably better just
//       // to leave it alone unless you know what you're doing
//       success = nfc.mifareclassic_AuthenticateBlock(uid, uidLength, 4, 0, keya);

//       if (success)
//       {
//         Serial.println("Sector 1 (Blocks 4..7) has been authenticated");
//         uint8_t data[16];

//         // If you want to write something to block 4 to test with, uncomment
//         // the following line and this text should be read back in a minute
//         //memcpy(data, (const uint8_t[]){ 'a', 'd', 'a', 'f', 'r', 'u', 'i', 't', '.', 'c', 'o', 'm', 0, 0, 0, 0 }, sizeof data);
//         // success = nfc.mifareclassic_WriteDataBlock (4, data);

//         // Try to read the contents of block 4
//         success = nfc.mifareclassic_ReadDataBlock(4, data);

//         if (success)
//         {
//           // Data seems to have been read ... spit it out
//           Serial.println("Reading Block 4:");
//           nfc.PrintHexChar(data, 16);
//           Serial.println("");

//           // Wait a bit before reading the card again
//           delay(1000);
//         }
//         else
//         {
//           Serial.println("Ooops ... unable to read the requested block.  Try another key?");
//         }
//       }
//       else
//       {
//         Serial.println("Ooops ... authentication failed: Try another key?");
//       }
//     }

//     if (uidLength == 7)
//     {
//       // We probably have a Mifare Ultralight card ...
//       Serial.println("Seems to be a Mifare Ultralight tag (7 byte UID)");

//       // Try to read the first general-purpose user page (#4)
//       Serial.println("Reading page 4");
//       uint8_t data[32];
//       success = nfc.mifareultralight_ReadPage (4, data);
//       if (success)
//       {
//         // Data seems to have been read ... spit it out
//         nfc.PrintHexChar(data, 4);
//         Serial.println("");

//         // Wait a bit before reading the card again
//         delay(1000);
//       }
//       else
//       {
//         Serial.println("Ooops ... unable to read the requested page!?");
//       }
//     }
//   }
// }

#include <Arduino.h>

enum CellType
{
  Player,
  Wall,
  Target,
  Nothing
};

typedef struct
{
  int x;
  int y;
} Point;

#define WIDTH 10
#define HEIGHT 9
#define BUTTON_FORWARD 4
/*
  #: wall
  P: player
  ?: target

  note: this is only the initial state.
        the P char does not update positions during playing
*/
const char maze[9][10] = {
    {'#', '#', '#', ' ', '#', '#', '#', '#', '#', ' '},
    {'P', ' ', '#', ' ', ' ', ' ', '#', ' ', '#', ' '},
    {'#', ' ', '#', '#', '#', ' ', ' ', ' ', ' ', ' '},
    {' ', ' ', ' ', ' ', ' ', ' ', '#', ' ', '#', '#'},
    {'#', ' ', '#', '#', '#', '#', '#', ' ', '#', ' '},
    {' ', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
    {'#', '#', ' ', ' ', '#', ' ', '#', '#', '#', '#'},
    {'#', '#', '#', ' ', '#', ' ', ' ', ' ', ' ', ' '},
    {'#', ' ', ' ', ' ', '#', '#', '#', ' ', '#', '?'}};

// const char maze[9][10] = {
//     {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
//     {'#', 'P', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
//     {'#', ' ', '#', ' ', '#', '#', ' ', '#', ' ', '#'},
//     {'#', ' ', '#', ' ', '#', '#', ' ', '#', ' ', '#'},
//     {'#', ' ', '#', ' ', ' ', ' ', ' ', '#', ' ', '#'},
//     {'#', ' ', '#', ' ', ' ', ' ', ' ', '#', ' ', '#'},
//     {'#', ' ', '#', '#', '#', '#', '#', '#', ' ', '#'},
//     {'#', '?', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
//     {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'}};

CRGB colors[WIDTH * HEIGHT];
Point player_pos;

bool can_move_to(Point position)
{
  if (position.x < 0 || position.x > WIDTH || position.y < 0 || position.y > HEIGHT)
  {
    return false;
  }

  return maze[position.y][position.x] == ' ';
}

bool move(int dx, int dy)
{
  Point new_pos = {.x = player_pos.x + dx, .y = player_pos.y + dy};
  bool can_move = can_move_to(new_pos);

  if (can_move)
  {
    player_pos = new_pos;
  }

  return can_move;
}

bool move_up()
{
  return move(0, 1);
}

bool move_down()
{
  return move(0, -1);
}

bool move_left()
{
  return move(-1, 0);
}

bool move_right()
{
  return move(1, 0);
}

CellType get_cell(int x, int y)
{
  if (x < 0 || x > WIDTH || y < 0 || y > HEIGHT)
  {
    return CellType::Nothing;
  }

  if (x == player_pos.x && y == player_pos.y)
  {
    return CellType::Player;
  }

  if (maze[y][x] == '?')
  {
    return CellType::Target;
  }

  if (maze[y][x] == '#')
  {
    return CellType::Wall;
  }

  return CellType::Nothing;
}

CRGB get_color(CellType type)
{
  switch (type)
  {
  case CellType::Player:
    return CRGB::Blue;

  case CellType::Wall:
    return CRGB::White;

  case CellType::Target:
    return CRGB::Red;

  default:
    return CRGB::Black;
  }
}

int snake_index(int x, int y)
{
  int offset = y * WIDTH;

  if (y % 2 == 0)
  {
    return x + offset;
  }
  else
  {
    return WIDTH - x - 1 + offset;
  }
}

void draw_maze()
{

  for (int x = 0; x < WIDTH; x++)
  {
    for (int y = 0; y < HEIGHT; y++)
    {

      CellType type = get_cell(x, y);
      colors[snake_index(x, y)] = get_color(type);
    }
  }
}

void set_initial_player_position()
{
  for (int x = 0; x < WIDTH; x++)
  {
    for (int y = 0; y < HEIGHT; y++)
    {
      if (maze[y][x] == 'P')
      {
        Serial.println(x);
        Serial.println(y);
        player_pos = {.x = x,
                      .y = y};
      }
    }
  }
}

int led = 0;

void setup()
{
  Serial.begin(115200);
  FastLED.addLeds<WS2811, DATA_PIN, GRB>(colors, WIDTH * HEIGHT);
  FastLED.setBrightness(40);
  set_initial_player_position();
  draw_maze();
  FastLED.show();
}

void loop()
{
  // colors[led] = CRGB::Red;
  // FastLED.show();

  // delay(500);

  // colors[led] = CRGB::Black;
  // delay(500);
  // led++;
  // led %= 90;
}