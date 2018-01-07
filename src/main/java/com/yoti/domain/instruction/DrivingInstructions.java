package com.yoti.domain.instruction;

import static com.yoti.domain.Coordinates.fromXY;

public class DrivingInstructions {

    private DrivingInstructions() {}

    public static final DrivingInstruction NORTH = c -> fromXY(c.getX(), c.getY() + 1);
    public static final DrivingInstruction SOUTH = c -> fromXY(c.getX(), c.getY() - 1);
    public static final DrivingInstruction EAST  = c -> fromXY(c.getX() + 1, c.getY());
    public static final DrivingInstruction WEST  = c -> fromXY(c.getX() - 1, c.getY());
}
