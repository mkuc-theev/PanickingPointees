package com.mkuc.pbgame.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Represents the game grid and provides methods to interact with it
 */
public class Grid {
    private final GridSquare[][] squares = new GridSquare[15][15];

    public Grid() {
        for (int row = 0; row < 15; row++) {
            for (int col = 0; col < 15; col++) {
                squares[col][row] = new GridSquare();
            }
        }
    }

    private List<Direction> getValidDirections(Integer column, Integer row) {
        List<Direction> validDirections = new java.util.ArrayList<>(Arrays.stream(Direction.values()).toList());
        if (row.equals(0)) {
            validDirections.remove(Direction.N);
            validDirections.remove(Direction.NE);
            validDirections.remove(Direction.NW);

        }
        if (row.equals(14)) {
            validDirections.remove(Direction.S);
            validDirections.remove(Direction.SE);
            validDirections.remove(Direction.SW);
        }
        if (column.equals(0)) {
            validDirections.remove(Direction.W);
            validDirections.remove(Direction.NW);
            validDirections.remove(Direction.SW);
        }
        if (column.equals(14)) {
            validDirections.remove(Direction.E);
            validDirections.remove(Direction.NE);
            validDirections.remove(Direction.SE);
        }
        return validDirections;
    }

    private void resetHasJumped() {
        for (int row = 0; row < 15; row++) {
            for (int col = 0; col < 15; col++) {
                squares[col][row].resetPointees();
            }
        }
    }

    private void scarePointeesOnSquare(Integer col, Integer row) {
        Random rng = new Random();
        List<Direction> validDirections = getValidDirections(col, row);
        List<Pointee> tempList = List.copyOf(squares[col][row].getPointees());
        for (Pointee pointee : tempList) {
            if (pointee.hasJumped()) {
                continue;
            }
            Direction randomDir = validDirections.get(rng.nextInt(validDirections.size()));
            squares[col + randomDir.value()[0]][row + randomDir.value()[1]].getPointees().add(pointee);
            squares[col][row].getPointees().remove(pointee);
            pointee.setHasJumped(true);
        }
    }

    /**
     * Causes all Pointees on the board to jump to a random adjacent square on the board.
     * The Pointees will never jump off the board.
     */
    public void scareAllPointees() {
        resetHasJumped();
        for (int row = 0; row < 15; row++) {
            for (int col = 0; col < 15; col++) {
                scarePointeesOnSquare(col, row);
            }
        }
    }

    /**
     * @param col The column coordinate of the coupon to be picked up
     * @param row The row coordinate of the coupon to be picked up
     * @return The value of the coupon on the square
     * Removes the coupon from the square it's on and provides its value
     */
    public Integer redeemCoupon(Integer col, Integer row) {
        if (squares[col][row].hasCoupon()) {
            squares[col][row].setHasCoupon(false);
            Integer value = value(col, row);
            squares[col][row].killPointees();
            return value;
        }
        return -1;
    }

    /**
     * @param col The column coordinate of the square
     * @param row The row coordinate of the square
     * @return The value of the square specified
     */
    public Integer value(Integer col, Integer row) {
        return squares[col][row].value();
    }

    public Integer totalValue() {
        Integer total = 0;
        for (int row = 0; row < 15; row++) {
            for (int col = 0; col < 15; col++) {
                total += value(col, row);
            }
        }
        return total;
    }

    /**
     * @return The 2D array of square objects
     */
    public GridSquare[][] getSquares() {
        return squares;
    }

    /**
     * @return A list representation of the square objects, in "reading" order
     */
    public List<GridSquare> getSquaresAsList() {
        List<GridSquare> squareList = new ArrayList<>();
        for (GridSquare[] row : squares) {
            squareList.addAll(Arrays.stream(row).toList());
        }
        return squareList;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (int row = 0; row < 15; row++) {
            for (int col = 0; col < 15; col++) {
                builder.append(squares[col][row].hasCoupon() ? String.format("[(%d)]", value(col, row)) : String.format("[%d]", value(col, row)));
            }
            builder.append("\n");
        }
        builder.append(String.format("Total value: %d", totalValue()));
        return builder.toString();
    }
}
