//Rediet Bezabih

import java.util.Random;

/**
 * Represents the maze as a 2D grid of tiles.
 * Walls and the exit are placed randomly when the maze is created.
 */
public class Maze {
    private Tile[][] grid;
    private int size;

    public Maze(int size) {
        this.size = size;
        grid = new Tile[size][size];

        // Fill the grid with empty floor tiles
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                grid[x][y] = new Tile(false, false);
            }
        }

        Random random = new Random();

        // Place the exit at a random position that is not (0, 0)
        int exitX;
        int exitY;
        do {
            exitX = random.nextInt(size);
            exitY = random.nextInt(size);
        } while (exitX == 0 && exitY == 0);

        grid[exitX][exitY] = new Tile(false, true);

        // Place walls on random cells (about 20% of the grid)
        int wallsToPlace = (size * size) / 5;
        int wallsPlaced = 0;

        while (wallsPlaced < wallsToPlace) {
            int wallX = random.nextInt(size);
            int wallY = random.nextInt(size);

            boolean isStart = wallX == 0 && wallY == 0;
            boolean isExit = grid[wallX][wallY].isExit();
            boolean isAlreadyWall = grid[wallX][wallY].isWall();

            if (!isStart && !isExit && !isAlreadyWall) {
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
}
