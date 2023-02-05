package com.mkuc.pbgame.model;


/**
 * Represents a direction on a square grid, with north being "up" and so forth, as a set of coordinate offsets.
 */
public enum Direction {
    N, E, S, W, NE, NW, SE, SW;

    public Integer[] value() {
        return switch (this) {
            case N -> new Integer[]{0, -1};
            case E -> new Integer[]{1, 0};
            case S -> new Integer[]{0, 1};
            case W -> new Integer[]{-1, 0};
            case NE -> new Integer[]{1, -1};
            case NW -> new Integer[]{-1, -1};
            case SE -> new Integer[]{1, 1};
            case SW -> new Integer[]{-1, 1};
        };
    }
}
