package byog.lab6;

import edu.princeton.cs.introcs.StdDraw;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;

public class MemoryGame {
    private int width;
    private int height;
    private int round;
    private Random rand;
    private boolean gameOver;
    private boolean playerTurn;
    private static final char[] CHARACTERS = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final String[] ENCOURAGEMENT = {"You can do this!", "I believe in you!",
                                                   "You got this!", "You're a star!", "Go Bears!",
                                                   "Too easy for you!", "Wow, so impressive!"};

    public static void main(String[] args) {
        MemoryGame game = new MemoryGame(40, 40);
        game.startGame();
    }

    public MemoryGame(int width, int height) {
        /* Sets up StdDraw so that it has a width by height grid of 16 by 16 squares as its canvas
         * Also sets up the scale so the top left is (0,0) and the bottom right is (width, height)
         */
        this.width = width;
        this.height = height;
        StdDraw.setCanvasSize(this.width * 16, this.height * 16);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, this.width);
        StdDraw.setYscale(0, this.height);
        StdDraw.clear(Color.BLACK);
        StdDraw.enableDoubleBuffering();

        this.rand = new Random(System.currentTimeMillis());
    }

    public String generateRandomString(int n) {
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < n; ++i) {
            buffer.append(CHARACTERS[rand.nextInt(CHARACTERS.length)]);
        }
        return buffer.toString();
    }

    public void drawFrame(String s) {
        int midWidth = width >> 1;
        int midHeight = height >> 1;

        StdDraw.clear(Color.black);
        if (!gameOver) {
            StdDraw.setFont(new Font("MesloLGM Nerd Font", Font.PLAIN, 20));
            StdDraw.textLeft(1, height - 1, "Round: " + round);
            StdDraw.text(midWidth, height - 1, playerTurn ? "Type!" : "Watch!");
            StdDraw.textRight(width - 1, height - 1, ENCOURAGEMENT[rand.nextInt(ENCOURAGEMENT.length)]);
            StdDraw.line(0, height - 2, width, height - 2);
        }
        StdDraw.setFont(new Font("MesloLGM Nerd Font", Font.PLAIN, 30));
        StdDraw.setPenColor(Color.white);
        StdDraw.text(midWidth, midHeight, s);
        StdDraw.show();
    }

    public void flashSequence(String letters) {
        for (int i = 0; i < letters.length(); ++i) {
            drawFrame(letters.substring(i, i + 1));
            StdDraw.pause(750);
            drawFrame("");
            StdDraw.pause(750);
        }
    }

    public String solicitNCharsInput(int n) {
        StringBuilder input = new StringBuilder(n);
        drawFrame("");
        while (input.length() < n) {
            if (!StdDraw.hasNextKeyTyped()) {
                continue;
            }
            input.append(StdDraw.nextKeyTyped());
            drawFrame(input.toString());
        }
        StdDraw.pause(500);
        return input.toString();
    }

    public void startGame() {
        gameOver = false;
        playerTurn = false;
        round = 1;

        while (!gameOver) {
            drawFrame("Round: " + round + "! Good luck!");
            StdDraw.pause(1500);

            String randString = generateRandomString(round);
            flashSequence(randString);

            playerTurn = true;
            String input = solicitNCharsInput(round);

            if (!input.equals(randString)) {
                gameOver = true;
                drawFrame("Game Over! You made it to round: " + round);
            } else {
                drawFrame("Correct, well done!");
                StdDraw.pause(1500);
                round++;
                playerTurn = false;
            }
        }
    }

}
