package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;


/**
 * Created by Blander on 9/9/23.
 */
public class Room {
    // the width of the inner space in the room
    final int width;
    // the height of the inner space in the room
    final int height;

    final TETile wallTile;
    final TETile floorTile;

    final Postion p;

    private final long seed = System.currentTimeMillis();
    final Random rand = new Random(seed);

    public Room(int x, int y) {
        this.width = RandomUtils.uniform(rand, 3, 16);
        this.height = RandomUtils.uniform(rand, 3, 16);
        this.wallTile = Tileset.WALL;
        this.floorTile = Tileset.FLOOR;
        this.p = new Postion(x, y);
    }

    public boolean isIntersectedTo(Room room) {
        return p.x >= room.p.x && p.x < room.p.x + width
                && p.y >= room.p.y && p.y < room.p.y + height + 2;
    }

    public Room randomNeighbor() {
        double r = RandomUtils.uniform(rand);
        if (r < 0.5) {
            int x = RandomUtils.uniform(rand, p.x, p.x + width - 3);
            return new Room(x, p.y);
        }
        int y = RandomUtils.uniform(rand, p.y, p.y + height - 3);
        return new Room(p.x, y);
    }


}
