//Rediet Bezabih

/**
 * Represents the player in the maze.
 * Position (0, 0) is the bottom-left corner of the grid.
 */
public class Player {
    private int x;
    private int y;
    private int energy;

    // Player always starts at (0, 0)
    public Player(int energy) {
        this.x = 0;
        this.y = 0;
        this.energy = energy;
    }

    // Moving up increases y (towards the top of the grid)
    public void moveUp() {
        y++;
        energy--;
    }

    // Moving down decreases y (towards the bottom of the grid)
    public void moveDown() {
        y--;
        energy--;
    }

    // Moving left decreases x
    public void moveLeft() {
        x--;
        energy--;
    }

    // Moving right increases x
    public void moveRight() {
        x++;
        energy--;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getEnergy() {
        return energy;
    }
}
