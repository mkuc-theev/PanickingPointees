package com.mkuc.pbgame.model;

/**
 * Represents a Pointee that jumps between squares on the game board
 */
public class Pointee {
    private Boolean hasJumped;

    public Pointee() {
        hasJumped = false;
    }

    /**
     * @return Whether the Pointee has jumped this round already
     */
    public Boolean hasJumped() {
        return hasJumped;
    }

    /**
     * @param hasJumped Whether the Pointee has jumped this round already
     */
    public void setHasJumped(Boolean hasJumped) {
        this.hasJumped = hasJumped;
    }
}
