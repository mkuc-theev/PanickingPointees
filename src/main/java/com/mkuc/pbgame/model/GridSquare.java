package com.mkuc.pbgame.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a square on the game grid, contains the Pointee objects
 */
public class GridSquare {

    private final List<Pointee> pointees;
    private Boolean hasCoupon;


    public GridSquare() {
        hasCoupon = true;
        pointees = new ArrayList<>();
        pointees.add(new Pointee());
    }

    /**
     * @return Whether the square has a coupon on it
     */
    public Boolean hasCoupon() {
        return hasCoupon;
    }

    /**
     * @param hasCoupon The state of the coupon, true if present and false if claimed
     */
    public void setHasCoupon(Boolean hasCoupon) {
        this.hasCoupon = hasCoupon;
    }

    /**
     * @return A list of the Pointees standing on the square
     */
    public List<Pointee> getPointees() {
        return pointees;
    }

    /**
     * @return The value of the square, determined by the number of Pointees standing on it.
     */
    public Integer value() {
        return pointees.size();
    }

    /**
     * Removes all pointees currently on the square from the board completely
     */
    public void killPointees() {
        pointees.clear();
    }

    /**
     * Sets the hasJumped value of all the Pointees on the square to false, indicating they haven't jumped this round.
     */
    public void resetPointees() {
        for (Pointee pointee : pointees) {
            pointee.setHasJumped(false);
        }
    }
}
