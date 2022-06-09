#include <Wire.h>
#include <FastLED.h>
#define DATA_PIN 2

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
        bool can_move_to(Point position);
        CellType get_cell(int x, int y);
        bool move(int dx, int dy);

        bool move_up();
        bool move_down();
        bool move_left();
        bool move_right();

        CRGB get_color(CellType type);

        int snake_index(int x, int y);

        void draw();

        void set_initial_player_position();
        CRGB colors[WIDTH * HEIGHT];
        
    private:
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

        // const char win[9][10] = {
        //     {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        //     {'#', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', ' '},
        //     {'#', ' ', '#', ' ', '#', ' ', ' ', ' ', ' ', ' '},
        //     {'#', ' ', '#', ' ', '#', ' ', ' ', ' ', ' ', ' '},
        //     {'#', ' ', '#', ' ', '#', ' ', ' ', ' ', ' ', ' '},
        //     {'#', ' ', '#', ' ', '#', ' ', ' ', ' ', ' ', ' '},
        //     {'#', ' ', '#', ' ', '#', ' ', ' ', ' ', ' ', ' '},
        //     {' ', '#', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' '},
        //     {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}};
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
        
        Point player_pos;
        int target_reached = false;
};