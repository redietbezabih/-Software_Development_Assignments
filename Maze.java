//Rediet Bezabih
import java.util.Random;

/**
 * Represents the maze as a 2D grid of Tile objects.
 * Walls and the exit are placed randomly when the maze is created.
 */
public class Maze {
    private Tile[][] grid;
    private int size;

    /**
     * Creates a maze of the given size.
     * About 20% of the tiles become walls (not on start or exit).
     * The exit is placed at a random position that is not (0, 0).
     */
    public Maze(int size) {
        this.size = size;
        // grid[x][y] so we can look up a tile by player coordinates directly
        grid = new Tile[size][size];

        // First fill the whole grid with empty tiles (no walls, not exit)
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                grid[x][y] = new Tile(false, false);
            }
        }

        Random random = new Random();

        // Place the exit at a random cell that is not the starting position
        int exitX;
        int exitY;
        do {
            exitX = random.nextInt(size);
            exitY = random.nextInt(size);
        } while (exitX == 0 && exitY == 0);

        // Replace that tile with an exit tile (not a wall)
        grid[exitX][exitY] = new Tile(false, true);

        // Place walls on some random cells (roughly 20% of the maze)
        int numberOfWalls = (size * size) / 5;
        int wallsPlaced = 0;

        while (wallsPlaced < numberOfWalls) {
            int wallX = random.nextInt(size);
            int wallY = random.nextInt(size);

            // Do not put a wall on the start, on the exit, or on an existing wall
            boolean isStart = (wallX == 0 && wallY == 0);
            boolean isExit = grid[wallX][wallY].isExit();
            boolean alreadyWall = grid[wallX][wallY].isWall();

            if (!isStart && !isExit && !alreadyWall) {
                grid[wallX][wallY] = new Tile(true, false);
                wallsPlaced++;
            }
        }
    }

    public Tile getTile(int x, int y) {
        return grid[x][y];
    }

    public int getSize() {
        return size;
    }

    /**
     * Prints a simple view of the maze.
     * P = player, E = exit, # = wall, . = empty.
     * Top of the printout is high y, so (0,0) appears at the bottom-left.
     */
    public void printMaze(Player player) {
        System.out.println();
        // Print from top (size-1) down to bottom (y = 0)
        for (int y = size - 1; y >= 0; y--) {
            for (int x = 0; x < size; x++) {
                if (player.getX() == x && player.getY() == y) {
                    System.out.print("P ");
                } else if (grid[x][y].isExit()) {
                    System.out.print("E ");
                } else if (grid[x][y].isWall()) {
                    System.out.print("# ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}
