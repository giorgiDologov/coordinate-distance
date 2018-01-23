package com.ernest.giorgi;

import java.awt.*;
import java.io.IOException;

/**
 * Created by ernest.giorgi on 08/01/18.
 */
public class Main {
    //src/main/resources/points.dat
    private static String FILELOC = "src/resources/points.dat";

    // coordinates we compare to (feel free to change it)
    private static Point CLOSE_POINT = new Point(-200,300);
    private static Point FAR_POINT = new Point(1000,25);
    // size for arrays
    private static int CLOSEST_LIST_MAX = 10;
    private static int FAREST_LIST_MAX = 20;


    public static void main(String[] args) throws IOException {
        System.out.println("Started execution...");
        System.out.println();
        long now = System.currentTimeMillis()/1000;

        //action
        FixSizePriorityQueue<Point>[] sortedPoints = Action.getCloseAndFarPoints(FILELOC,
                CLOSE_POINT,
                CLOSEST_LIST_MAX,
                FAR_POINT,
                FAREST_LIST_MAX);
        //time elapsed
        long elapsed = System.currentTimeMillis()/1000 - now;
        System.out.println();
        System.out.printf("Execution finished in " + elapsed + " seconds.");
        System.out.println();
        System.out.println();

        //print close points
        FixSizePriorityQueue<Point> closeQueue = sortedPoints[0];
        System.out.println("These points are the closest to point (" + CLOSE_POINT.x + "," + CLOSE_POINT.y + ")");
        System.out.println();
        for (int i = 1; i < CLOSEST_LIST_MAX + 1; i++) {
            Point point = closeQueue.poll();
            System.out.println(i + ". close point is (" + point.x + "," + point.y + ")," +
                    " distance = " + point.distance(FAR_POINT));
        }

        System.out.println();

        //print far points
        FixSizePriorityQueue<Point> farQueue = sortedPoints[1];
        System.out.println("These points are the farest to point (" + FAR_POINT.x + "," + FAR_POINT.y + ")");
        System.out.println();
        for (int i = FAREST_LIST_MAX; i > 0; i--) {
            Point point = farQueue.poll();
            System.out.println(i + ". far point is (" + point.x + "," + point.y + ")," +
                    " distance = " + point.distance(FAR_POINT));
        }
    }
}
