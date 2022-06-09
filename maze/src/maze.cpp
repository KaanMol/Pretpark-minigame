#include "maze.h"

Maze::Maze() {

}

Maze::~Maze() {

}

bool Maze::can_move_to(Point position) {
    if (position.x < 0 || position.x > WIDTH || position.y < 0 || position.y > HEIGHT) {
        return false;
    }

    return maze[position.y][position.x] == ' ' || maze[position.y][position.x] == '?' || maze[position.y][position.x] == 'P';  
}

CellType Maze::get_cell(int x, int y) {
    if (x < 0 || x > WIDTH || y < 0 || y > HEIGHT) {
        return CellType::Nothing;
    }

    if (x == player_pos.x && y == player_pos.y) {
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
    Point new_pos = {.x = player_pos.x + dx, .y = player_pos.y + dy};
    bool can_move = can_move_to(new_pos);

    if (get_cell(new_pos.x, new_pos.y) == CellType::Target) {
        target_reached = true;
        this->set_black();
    }

    if (can_move) {
        player_pos = new_pos;
    }

    return can_move;
}

bool Maze::move_up() {
    return move(0, -1);
}

bool Maze::move_down() {
    return move(0, 1);
}

bool Maze::move_left() {
    return move(-1, 0);
}

bool Maze::move_right() {
    return move(1, 0);
}

CRGB Maze::get_color(CellType type) {
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

int Maze::snake_index(int x, int y) {
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
            CellType type = get_cell(x, y);
            colors[snake_index(x, y)] = get_color(type);
        }
    }
}

void Maze::set_initial_player_position() {
    for (int x = 0; x < WIDTH; x++) {
        for (int y = 0; y < HEIGHT; y++) {
            if (maze[y][x] == 'P') {
                Serial.println(x);
                Serial.println(y);
                player_pos = {
                                .x = x,
                                .y = y
                            };
            }
        }
    }
}

void Maze::set_black() {
    for (int i = 0; i < WIDTH * HEIGHT; i++) {
        colors[i] = CRGB::Black;
    }
    FastLED.show();
}
