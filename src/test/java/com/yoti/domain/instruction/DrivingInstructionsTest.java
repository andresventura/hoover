package com.yoti.domain.instruction;

import org.junit.Test;

import static com.yoti.domain.Coordinates.fromXY;
import static com.yoti.domain.instruction.DrivingInstructions.*;
import static org.junit.Assert.assertEquals;

public class DrivingInstructionsTest {

    @Test
    public void northIncreasesYByOne() {
        assertEquals(fromXY(0,1), NORTH.apply(fromXY(0,0)));
    }

    @Test
    public void southDecreasesYByOne() {
        assertEquals(fromXY(0,0), SOUTH.apply(fromXY(0,1)));
    }

    @Test
    public void eastIncreasesXByOne() {
        assertEquals(fromXY(1,0), EAST.apply(fromXY(0,0)));
    }

    @Test
    public void westDecreasesXByOne() {
        assertEquals(fromXY(0,0), WEST.apply(fromXY(1,0)));
    }
}
