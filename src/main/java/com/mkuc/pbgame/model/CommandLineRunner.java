package com.mkuc.pbgame.model;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

/**
 * An alternative runtime for when the application is meant to run in no-GUI mode
 */
public class CommandLineRunner {
    private final Grid grid;

    public CommandLineRunner() {
        grid = new Grid();
    }

    /**
     * Performs a predetermined "run" of the game, where the highest value coupon is redeemed whenever allowed.
     * Displays the information about the state of the game after every coupon redemption.
     */
    public void run() {
        for (int i = 1; i <= Config.REDEEMABLE_ROUNDS[Config.REDEEMABLE_ROUNDS.length - 1]; i++) {
            grid.scareAllPointees();
            if (Arrays.stream(Config.REDEEMABLE_ROUNDS).anyMatch(Predicate.isEqual(i))) {
                GridSquare max = grid.getSquaresAsList().stream().max(Comparator.comparingInt(GridSquare::value)).get();
                System.out.printf("=== Iteration %d ===%n", i);
                System.out.printf("Highest value coupon: %d points.%n", max.value());
                max.killPointees();
                max.setHasCoupon(false);
                System.out.println("All present coupons: " + grid.getSquaresAsList().stream().filter(p -> p.value() > 0).map(p -> p.value()).toList());
                System.out.printf("Total value of coupons: %d points.%n", grid.totalValue());
            }
        }
    }
}
