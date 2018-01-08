package com.yoti.domain.instruction;

import com.yoti.domain.Coordinates;

import java.util.function.Function;

import static com.yoti.domain.Coordinates.fromXY;

public enum DrivingInstruction {
    N(c -> fromXY(c.getX(), c.getY() + 1)),
    S(c -> fromXY(c.getX(), c.getY() - 1)),
    E(c -> fromXY(c.getX() + 1, c.getY())),
    W(c -> fromXY(c.getX() - 1, c.getY()));

    DrivingInstruction(Function<Coordinates, Coordinates> fn) {
        this.fn = fn;
    }

    private final Function<Coordinates, Coordinates> fn;

    public Function<Coordinates, Coordinates> getFn() {
        return fn;
    }
}
