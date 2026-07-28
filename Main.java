//Rediet Bezabih

import java.util.Scanner;

/**
 * Main class: reads keyboard input, runs the game loop,
 * enforces movement rules, and detects win / lose.
 *
 * Controls: W = up, S = down, A = left, D = right, Q = quit
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Configurable maze size (e.g. 8 for an 8x8 grid)
        System.out.print("Enter maze size (e.g. 8): ");
        int size = scanner.nextInt();

        // Starting energy scales with maze size so the game is playable
        int startingEnergy = size * 3;

        Maze maze = new Maze(size);
        Player player = new Player(startingEnergy);
        boolean gameOver = false;

        while (!gameOver) {
            System.out.println("Player position: (" + player.getX() + ", " + player.getY() + ")");
            System.out.println("Energy: " + player.getEnergy());
            System.out.print("Move (W/A/S/D/Q): ");

            String input = scanner.next().toUpperCase();
            char move = input.charAt(0);

            if (move == 'Q') {
                gameOver = true;
                continue;
            }

            // Calculate the target position without moving yet
            int newX = player.getX();
            int newY = player.getY();

            if (move == 'W') {
                newY++;
            } else if (move == 'S') {
                newY--;
            } else if (move == 'A') {
                newX--;
            } else if (move == 'D') {
                newX++;
            } else {
                System.out.println("Invalid move! Use W, A, S, D, or Q.");
                continue;
            }

            // Player cannot leave the grid
            if (newX < 0 || newX >= maze.getSize() || newY < 0 || newY >= maze.getSize()) {
                System.out.println("You cannot leave the maze!");
                continue;
            }

            // Player cannot move through walls
            Tile targetTile = maze.getTile(newX, newY);
            if (targetTile.isWall()) {
                System.out.println("You hit a wall!");
                continue;
            }

            // Valid move: update player position and decrease energy by 1
            if (move == 'W') {
                player.moveUp();
            } else if (move == 'S') {
                player.moveDown();
            } else if (move == 'A') {
                player.moveLeft();
            } else if (move == 'D') {
                player.moveRight();
            }

            // Win: player reached the exit
            if (maze.getTile(player.getX(), player.getY()).isExit()) {
                System.out.println("Player position: (" + player.getX() + ", " + player.getY() + ")");
                System.out.println("Energy: " + player.getEnergy());
                System.out.println("You escaped the maze!");
                gameOver = true;
                continue;
            }

            // Lose: energy reached 0
            if (player.getEnergy() <= 0) {
                System.out.println("Player position: (" + player.getX() + ", " + player.getY() + ")");
                System.out.println("Energy: " + player.getEnergy());
                System.out.println("You ran out of energy!");
                gameOver = true;
            }
        }

        scanner.close();
    }
}
