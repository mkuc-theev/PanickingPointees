package com.mkuc.pbgame.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class GridTest {

    Grid testGrid;

    @BeforeEach
    void setup() {
        testGrid = new Grid();
    }

    @Test
    void squaresAsListContainsAllSquares() {
        boolean isCorrect = true;
        List<GridSquare> squareList = testGrid.getSquaresAsList();
        for (int row = 0; row < 15; row++) {
            for (int col = 0; col < 15; col++) {
                if (!squareList.contains(testGrid.getSquares()[col][row])) {
                    isCorrect = false;
                }
            }
        }
        assertTrue(isCorrect);
    }

    @Test
    void pointeesNotRemovedOrCopiedWhenJump() {
        List<GridSquare> beforeJump = testGrid.getSquaresAsList();
        List<Pointee> beforeJumpPointees = new ArrayList<>();
        for (GridSquare square : beforeJump) {
            beforeJumpPointees.addAll(square.getPointees());
        }
        testGrid.scareAllPointees();
        List<GridSquare> afterJump = testGrid.getSquaresAsList();
        List<Pointee> afterJumpPointees = new ArrayList<>();
        for (GridSquare square : afterJump) {
            afterJumpPointees.addAll(square.getPointees());
        }
        assertAll(
                () -> afterJumpPointees.containsAll(beforeJumpPointees),
                () -> Objects.equals(beforeJumpPointees.size(), afterJumpPointees.size())
        );
    }

    @Test
    void valueWorks() {
        assertEquals(1, testGrid.value(0, 0));
    }

    @Test
    void totalValueWorks() {
        assertEquals(225, testGrid.totalValue());
    }

    @Test
    void redeemCouponWorks() {
        assertAll(
                () -> Objects.equals(1, testGrid.redeemCoupon(0, 0)),
                () -> Objects.equals(224, testGrid.totalValue())
        );
    }


}