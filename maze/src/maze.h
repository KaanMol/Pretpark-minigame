#include <Wire.h>
#include <FastLED.h>
#define DATA_PIN 2
#define BUTTON_DELAY 500
#define WIDTH 10
#define HEIGHT 9
#define BUTTON_FORWARD 4

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

class Maze {
    public:
        Maze();
        ~Maze();
        void init();
        void start();

        bool moveUp();
        bool moveDown();
        bool moveLeft();
        bool moveRight();

        bool hasReachedTarget();
        
    private:
        void displayOff();
        CellType getCell(int x, int y);

        bool move(int dx, int dy);
        bool canMoveTo(Point position);
        int snakeIndex(int x, int y);
        
        CRGB getColor(CellType type);
        void draw();
        void setInitialPlayerPosition();
        CRGB colors[WIDTH * HEIGHT];

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
        
        Point playerPosition;
        bool targetReached = false;
};