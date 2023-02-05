package com.mkuc.pbgame.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PointeeTest {

    Pointee testPointee;

    @BeforeEach
    void setup() {
        testPointee = new Pointee();
    }

    @Test
    void hasJumpedFalseOnInit() {
        assertFalse(testPointee.hasJumped());
    }

    @Test
    void setHasJumpedWorks() {
        testPointee.setHasJumped(true);
        assertTrue(testPointee.hasJumped());
    }
}