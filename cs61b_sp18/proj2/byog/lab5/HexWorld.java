package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {

    private final int WIDTH;
    private final int HEIGHT;

    private final long SEED = System.currentTimeMillis();
    private final Random RANDOM = new Random(SEED);

    private final TETile[][] world;

    // the size of the hexagon
    private final int size;

    public HexWorld(int size) {
        this.WIDTH = 60;
        this.HEIGHT = 50;
        this.world = new TETile[WIDTH][HEIGHT];
        this.size = size;
    }

    public HexWorld(int size, int width, int height) {
        this.WIDTH = width;
        this.HEIGHT = height;
        this.world = new TETile[width][height];
        this.size = size;
    }

    /**
     * determine whether {@code p} is in the {@code world}.
     * @param p the position of the tile
     * @return  boolean
     */
    private boolean isInWorld(Position p) {
        int x = p.getX();
        int y = p.getY();
        return x >= 0 && x < WIDTH && y >= 0 && y < HEIGHT;
    }

    /**
     * @param i The row number e.g. when i = 0 is the bottom row.
     * @return  The width of row {@code i}.
     */
    private int hexRowWidth(int i) {
        int effectiveI = i;
        if (i >= size) {
             effectiveI = size * 2 - 1 - effectiveI;
        }
        return size + effectiveI * 2;
    }

    /**
     * @param i row num of the hexagon e.g. when i = 0 is the bottom row
     * @return  The offset of row {@code i}
     */
    private int hexRowOffset(int i) {
        int effectiveI = i;
        if (i >= size) {
            effectiveI = 2 * size - 1 - effectiveI;
        }
        return -effectiveI;
    }

    /**
     * Adds a row of the same tile.
     * @param p     the leftmost position of the row
     * @param width the number of tiles wide to draw
     * @param t     the tile to draw
     */
    private void addRow(Position p, int width, TETile t) {
        for (int xi = 0; xi < width; xi++) {
            int x = p.getX() + xi;
            int y = p.getY();
            world[x][y] = t;
        }
    }

    /**
     * Adds a hexagon to the world
     * @param p the bottom left coordinate of the hexagon
     * @param t the tile to draw
     */
    public void addHexagon(Position p, TETile t) {
        if (size < 2) {
            throw new IllegalArgumentException("Hexagon must be at least size 2.");
        }
        for (int yi = 0; yi < size * 2; ++yi) {
            int thisRowY = p.getY() + yi;
            int startX = p.getX() + hexRowOffset(yi);

            Position rowStartPos = new Position(startX, thisRowY);
            if (isInWorld(rowStartPos)) {
                int rowWidth = hexRowWidth(yi);
                addRow(rowStartPos, rowWidth, t);
            }
        }
    }

    /**
     * returns the RANDOM tile.
     */
    public TETile randomTile() {
        int tileNum = RANDOM.nextInt(5);
        return switch (tileNum) {
            case 0 -> Tileset.FLOWER;
            case 1 -> Tileset.GRASS;
            case 2 -> Tileset.MOUNTAIN;
            case 3 -> Tileset.TREE;
            default -> Tileset.SAND;
        };
    }

    /**
     * initialize the background using tile {@code t}.
     * @param t the background tile
     */
    public void setBackground(TETile t) {
        for (int x = 0; x < WIDTH; ++x) {
            for (int y = 0; y < HEIGHT; ++y) {
                world[x][y] = t;
            }
        }
    }

    /** returns the bottom left coordinate of the right hexagon */
    private Position rightPos(Position p) {
        return new Position(p.getX() + 4 * size - 2, p.getY());
    }

    /** returns the bottom left coordinate of the top left hexagon */
    private Position topLeftPos(Position p) {
        return new Position(p.getX() - 2 * size + 1, p.getY() + size);
    }

    /** returns the bottom left coordinate of the top right hexagon */
    private Position topRightPos(Position p) {
        return new Position(p.getX() + 2 * size - 1, p.getY() + size);
    }

    /**
     * Adds the hexagons in a line.
     * @param p     the bottom left coordinate of the first hexagon.
     * @param num   the number of the hexagons in a line.
     * @return      the position of the top left of the first hexagon.
     */
    private Position addHexInLine(Position p, int num) {
        Position pos = p;
        for (int i = 0; i < num; i++) {
            addHexagon(pos, randomTile());
            pos = rightPos(pos);
        }
        return topLeftPos(p);
    }

    /**
     * Adds the small trapezium inside the {@code HexWorld}.
     * @param p the coordinate of the bottom left hexagon of the trapezium
     * @return  the coordinate of the top right of the top left hexagon.
     */
    private Position addTrapezium(Position p) {
        Position pos = addHexInLine(p, size - 1);
        addHexInLine(pos, size);
        return topRightPos(pos);
    }

    /**
     * Adds the small triangle inside the {@code HexWorld}.
     * @param p     the coordinate of the bottom left hexagon of the triangle.
     * @param num   the number of the layers.
     */
    private void addTriangle(Position p, int num) {
        Position pos = p;
        for (int i = num; i > 0; --i) {
            pos = addHexInLine(pos, i);
            pos = rightPos(pos);
        }
    }

    /**
     * Adds the small inverted triangle inside the {@code HexWorld}.
     * @param p     the coordinate of the bottom hexagon of the triangle.
     * @param num   the number of the layers.
     * @return      the coordinate of the top right of the top left hexagon.
     */
    private Position addInvertedTriangle(Position p, int num) {
        Position pos = p;
        for (int i = 1; i <= num; i++) {
            pos = addHexInLine(pos, i);
        }
        return rightPos(pos);
    }

    /**
     * Adds the large hexagon
     * @param p the coordinate of the top of the large hexagon
     */
    public void addBigHexagon(Position p) {
        Position pos = p;
        pos = addInvertedTriangle(pos, size);
        for (int i = 0; i < size - 1; i++) {
            pos = addTrapezium(pos);
        }
        addTriangle(pos, size - 1);
    }

    /**
     * Display the {@code HexWorld} in a window.
     * @param t the tile of the background
     */
    public void displayHexWorld(Position p, TETile t) {
        // initialize the tile rendering engine with a window of size WIDTH x HEIGHT
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        // initialize the background
        setBackground(t);

        // Adds the large hexagon with several blocks with different colors
        addBigHexagon(p);

        // draws the world to the screen
        ter.renderFrame(world);
    }

    public static void main(String[] args) {
        HexWorld hexWorld = new HexWorld(3);
        Position pos = new Position(30, 10);
        hexWorld.displayHexWorld(pos, Tileset.NOTHING);
    }
}
