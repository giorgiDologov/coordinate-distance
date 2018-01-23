package com.ernest.giorgi;

import java.awt.*;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * Created by ernest.giorgi on 08/01/18.
 */
public class Action {

    //reading 2 bytes
    private static final int STARTER = 0;
    private static final int LENGTH = 2;

    public static FixSizePriorityQueue<Point>[] getCloseAndFarPoints(String FILELOC,
                                                                     final Point closestPoint,
                                                                     int nClosest,
                                                                     final Point farestPoint,
                                                                     int nFurther) throws IOException, ClassCastException {

        //********DEFINING COMPARATORS FOR CUSTOM PQ********

        //comparator for closest custom PriorityQueue
        FixSizePriorityQueue<Point> closeQueue = new FixSizePriorityQueue<>(nClosest, (Point pointOne, Point pointTwo) ->
                //bigger distance, bigger priority (FIFO)
                pointOne.distance(closestPoint) > pointTwo.distance(closestPoint) ? -1 : 1);

        //comparator for farest custom PriorityQueue
        FixSizePriorityQueue<Point> farQueue = new FixSizePriorityQueue<>(nFurther, (Point pointOne, Point pointTwo) ->
                //smaller distance bigger priority (FIFO)
                pointOne.distance(farestPoint) < pointTwo.distance(farestPoint) ? -1 : 1);

        //********READING IN BINARY FILE********

        Point currPoint;
        BufferedInputStream stream = null; //faster than DataInputStream

        try {

            stream = new BufferedInputStream(new FileInputStream(FILELOC));

            //creation of Point-s
            try {
                byte[] byteArray = new byte[2];
                int numberOfBytes;
                //read bytes, create shorts
                short x;
                short y;

                do {
                        //16 bit short stored in 2 bytes
                        numberOfBytes = stream.read(byteArray,STARTER,LENGTH);
                        x = ByteBuffer.wrap(byteArray).getShort();
                        numberOfBytes = stream.read(byteArray,STARTER,LENGTH);
                        y = ByteBuffer.wrap(byteArray).getShort();
                        //create Point object
                        currPoint = new Point(x,y);

                        //opposite priorities will dequeue in opposite order
                        closeQueue.add(currPoint);
                        farQueue.add(currPoint);
                    } while (numberOfBytes > 0);

                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                //flushing stream
                if (stream != null) {
                    stream.close();
                }
            }

        //return queue of closest and farest points (in a bigger infrastructure, I would create an Object for this)
        return new FixSizePriorityQueue[]{
                closeQueue,
                farQueue
        };
    }

}
