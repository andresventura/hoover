package com.yoti.domain.instruction;

import org.junit.Test;

import static com.yoti.domain.Coordinates.fromXY;
import static com.yoti.domain.instruction.DrivingInstruction.*;
import static org.junit.Assert.assertEquals;

public class DrivingInstructionsTest {

    @Test
    public void northIncreasesYByOne() {
        assertEquals(fromXY(0,1), N.getFn().apply(fromXY(0,0)));
    }

    @Test
    public void southDecreasesYByOne() {
        assertEquals(fromXY(0,0), S.getFn().apply(fromXY(0,1)));
    }

    @Test
    public void eastIncreasesXByOne() {
        assertEquals(fromXY(1,0), E.getFn().apply(fromXY(0,0)));
    }

    @Test
    public void westDecreasesXByOne() {
        assertEquals(fromXY(0,0), W.getFn().apply(fromXY(1,0)));
    }
}
