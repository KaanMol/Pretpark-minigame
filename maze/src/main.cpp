#include <Arduino.h>
#include "maze.h"

Maze maze;

void setup() {
	Serial.begin(115200);
	FastLED.addLeds<WS2811, DATA_PIN, GRB>(maze.colors, WIDTH * HEIGHT);
	FastLED.setBrightness(40);
	maze.set_initial_player_position();
	maze.draw();
	FastLED.show();

	pinMode(32, INPUT);
	pinMode(33, INPUT);
	pinMode(34, INPUT);
	pinMode(35, INPUT);
}

void loop() {
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
}