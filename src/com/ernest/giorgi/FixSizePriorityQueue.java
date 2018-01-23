package com.ernest.giorgi;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by ernest.giorgi on 08/01/18.
 */

public class FixSizePriorityQueue<T> extends PriorityQueue<T> {

    private int mMaximumSize;

    public FixSizePriorityQueue(int maximumSize, Comparator<T> comparator) {
        super(comparator);
        mMaximumSize = maximumSize;
    }

    private boolean isMaximumSize() {
        return this.size() > mMaximumSize;
    }

    @Override
    public boolean add(T t) {
        super.add(t);

        if (isMaximumSize()) {

            this.remove();
            return true;

        }

        return false;
    }

}
