package com.mkuc.pbgame.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GridSquareTest {

    GridSquare testSquare;

    @BeforeEach
    void setup() {
        testSquare = new GridSquare();
    }

    @Test
    void hasCouponOnInit() {
        assertTrue(testSquare.hasCoupon());
    }

    @Test
    void setHasCouponWorks() {
        testSquare.setHasCoupon(false);
        assertFalse(testSquare.hasCoupon());
    }

    @Test
    void getPointeesWorks() {
        testSquare.getPointees().add(new Pointee());
        assertEquals(2, testSquare.getPointees().size());
    }

    @Test
    void valueWorks() {
        assertEquals(1, testSquare.value());
    }

    @Test
    void killPointeesWorks() {
        testSquare.killPointees();
        assertEquals(0, testSquare.getPointees().size());
    }

    @Test
    void resetPointeesWorks() {
        testSquare.getPointees().get(0).setHasJumped(true);
        testSquare.resetPointees();
        assertEquals(false, testSquare.getPointees().get(0).hasJumped());
    }
}