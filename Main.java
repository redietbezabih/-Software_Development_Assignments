//Rediet Bezabih

import java.util.Scanner;

/**
 * Main class: reads keyboard input, runs the game loop,
 * checks movement rules, and detects win / lose.
 *
 * Controls: W = up, S = down, A = left, D = right, Q = quit
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Maze size is configurable – ask the user
        System.out.print("Enter maze size (e.g. 8 for an 8x8 maze): ");
        int size = scanner.nextInt();

        // Enough energy for a few tries; scale with maze size
        int startingEnergy = size * 3;

        Maze maze = new Maze(size);
        Player player = new Player(startingEnergy);

        boolean gameOver = false;

        System.out.println("Welcome to the Maze Game!");
        System.out.println("Reach the exit (E) before your energy runs out.");
        System.out.println("W=up, S=down, A=left, D=right, Q=quit");

        // Show the maze once at the start so the player can see walls and exit
        maze.printMaze(player);

        // Main game loop – keep going until win, lose, or quit
        while (!gameOver) {
            System.out.println("Player position: (" + player.getX() + ", " + player.getY() + ")");
            System.out.println("Energy: " + player.getEnergy());
            System.out.print("Move (W/A/S/D/Q): ");

            String input = scanner.next().toUpperCase();
            char move = input.charAt(0);

            if (move == 'Q') {
                System.out.println("You quit the game.");
                gameOver = true;
                continue;
            }

            // Figure out where the player wants to go (without moving yet)
            int newX = player.getX();
            int newY = player.getY();

            if (move == 'W') {
                newY = newY + 1; // up
            } else if (move == 'S') {
                newY = newY - 1; // down
            } else if (move == 'A') {
                newX = newX - 1; // left
            } else if (move == 'D') {
                newX = newX + 1; // right
            } else {
                System.out.println("Invalid key! Use W, A, S, D or Q.");
                continue;
            }

            // Check if the new position is outside the grid
            if (newX < 0 || newX >= maze.getSize() || newY < 0 || newY >= maze.getSize()) {
                System.out.println("You cannot leave the maze!");
                continue; // energy is NOT decreased
            }

            // Check if there is a wall on the target tile
            Tile target = maze.getTile(newX, newY);
            if (target.isWall()) {
                System.out.println("You hit a wall!");
                continue; // energy is NOT decreased
            }

            // Movement is allowed – actually move the player (this costs 1 energy)
            if (move == 'W') {
                player.moveUp();
            } else if (move == 'S') {
                player.moveDown();
            } else if (move == 'A') {
                player.moveLeft();
            } else if (move == 'D') {
                player.moveRight();
            }

            // Optional: show maze after each successful move
            maze.printMaze(player);

            // Win: player reached the exit
            if (maze.getTile(player.getX(), player.getY()).isExit()) {
                System.out.println("Player position: (" + player.getX() + ", " + player.getY() + ")");
                System.out.println("Energy: " + player.getEnergy());
                System.out.println("You escaped the maze!");
                gameOver = true;
                continue;
            }

            // Lose: energy reached 0 and player is not on the exit
            if (player.getEnergy() <= 0) {
                System.out.println("Player position: (" + player.getX() + ", " + player.getY() + ")");
                System.out.println("Energy: " + player.getEnergy());
                System.out.println("You ran out of energy! Game over.");
                gameOver = true;
            }
        }

        scanner.close();
    }
}
