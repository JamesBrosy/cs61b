package byog.Core;

import byog.TileEngine.TETile;

import javax.xml.crypto.KeySelector;


/**
 * Created by Blander on 9/9/23.
 */
public class GameWorld {
    private static TETile[][] world;

    public static void addRoom(Room room) {
        int topWallY = room.p.y + room.height;
        int bottomWallY = room.p.y - 1;
        int rightWallX = room.p.x + room.width;
        int leftWallX = room.p.x - 1;
        for (int x = room.p.x; x < rightWallX; x++) {
            world[x][bottomWallY] = room.wallTile;
            world[x][topWallY] = room.wallTile;
            for (int y = room.p.y; y < topWallY; y++) {
                world[x][y] = room.floorTile;
            }
        }
        for (int y = bottomWallY; y <= topWallY; y++) {
            world[leftWallX][y] = room.wallTile;
            world[rightWallX][y] = room.wallTile;
        }
    }

    public static void addBridgeBetween(Room room1, Room room2) {

    }
}
