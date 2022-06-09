#include "maze.h"

Maze::Maze() {
    
}

Maze::~Maze() {

}

void Maze::init() {
    FastLED.addLeds<WS2811, DATA_PIN, GRB>(this->colors, WIDTH * HEIGHT);
	FastLED.setBrightness(40);
    this->displayOff();

    pinMode(32, INPUT);
	pinMode(33, INPUT);
	pinMode(34, INPUT);
	pinMode(35, INPUT);
}

void Maze::start() {
    this->setInitialPlayerPosition();
    this->draw();
}

bool Maze::canMoveTo(Point position) {
    if (position.x < 0 || position.x > WIDTH || position.y < 0 || position.y > HEIGHT) {
        return false;
    }

    return maze[position.y][position.x] == ' ' || maze[position.y][position.x] == '?' || maze[position.y][position.x] == 'P';  
}

CellType Maze::getCell(int x, int y) {
    if (x < 0 || x > WIDTH || y < 0 || y > HEIGHT) {
        return CellType::Nothing;
    }

    if (x == this->playerPosition.x && y == this->playerPosition.y) {
        return CellType::Player;
    }

    if (maze[y][x] == '?') {
        return CellType::Target;
    }

    if (maze[y][x] == '#') {
        return CellType::Wall;
    }

    return CellType::Nothing;
}

bool Maze::move(int dx, int dy) {
    Point newPosition = {.x = this->playerPosition.x + dx, .y = this->playerPosition.y + dy};
    bool canMove = canMoveTo(newPosition);

    if (this->getCell(newPosition.x, newPosition.y) == CellType::Target) {
        this->targetReached = true;
        this->displayOff();
    }

    if (canMove) {
        this->playerPosition = newPosition;
    }

    return canMove;
}

bool Maze::moveUp() {
    bool isMoved = this->move(0, -1);
    this->draw();
    // TODO: Please fix this line properly, instead of using delays...
    delay(BUTTON_DELAY);
    return isMoved;
}

bool Maze::moveDown() {
    bool isMoved = this->move(0, 1);
    this->draw();
    // TODO: Please fix this line properly, instead of using delays...
    delay(BUTTON_DELAY);
    return isMoved;
}

bool Maze::moveLeft() {
    bool isMoved = this->move(-1, 0);
    this->draw();
    // TODO: Please fix this line properly, instead of using delays...
    delay(BUTTON_DELAY);
    return isMoved;
}

bool Maze::moveRight() {
    bool isMoved = this->move(1, 0);
    this->draw();
    // TODO: Please fix this line properly, instead of using delays...
    delay(BUTTON_DELAY);
    return isMoved;
}

CRGB Maze::getColor(CellType type) {
    switch (type) {
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

int Maze::snakeIndex(int x, int y) {
    int offset = y * WIDTH;

    if (y % 2 == 0) {
        return x + offset;
    } else {
        return WIDTH - x - 1 + offset;
    }
}

void Maze::draw() {
    for (int x = 0; x < WIDTH; x++) {
        for (int y = 0; y < HEIGHT; y++) {
            CellType type = this->getCell(x, y);
            colors[this->snakeIndex(x, y)] = this->getColor(type);
        }
    }

    FastLED.show();
}

void Maze::setInitialPlayerPosition() {
    for (int x = 0; x < WIDTH; x++) {
        for (int y = 0; y < HEIGHT; y++) {
            if (maze[y][x] == 'P') {
                Serial.println(x);
                Serial.println(y);
                this->playerPosition = {
                                .x = x,
                                .y = y
                            };
            }
        }
    }
}

void Maze::displayOff() {
    for (int i = 0; i < WIDTH * HEIGHT; i++) {
        colors[i] = CRGB::Black;
    }
    FastLED.show();
}

bool Maze::hasReachedTarget() {
    return this->targetReached;
}