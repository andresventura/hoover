package com.yoti.domain;

import org.junit.Test;

import static com.yoti.domain.Coordinates.fromXY;
import static com.yoti.domain.instruction.DrivingInstruction.*;
import static java.util.Arrays.asList;
import static java.util.Collections.singleton;
import static org.junit.Assert.assertEquals;

public class HooverTest {

    @Test
    public void hooversInitialPositionEvenWhenNoInstructionsAreGiven() {
        Room room = new Room(fromXY(2, 2), singleton(fromXY(1, 1)));

        Hoover hoover = new Hoover(fromXY(1, 1), room);

        assertEquals(1, hoover.getHooveredPatchCount());
    }

    @Test(expected = InvalidRoomCoordinates.class)
    public void failsWhenInitialPositionIsOutsideOfRoomBoundaries() {
        Room room = new Room(fromXY(2, 2), singleton(fromXY(1, 1)));

        new Hoover(fromXY(10, 10), room);
    }

    @Test
    public void whenInstructionsTakeHooverOutOfBoundariesItSkids() {
        Room room = new Room(fromXY(2, 2), singleton(fromXY(1, 1)));
        Hoover hoover = new Hoover(fromXY(0, 0), room);

        hoover.follow(asList(E, E, E, N, N, N));

        assertEquals(fromXY(1, 1), hoover.getPosition());
    }

    @Test
    public void whenInstructionsTakeHooverTwoTimesOverPatchTheCountShouldBeOne() {
        Room room = new Room(fromXY(2, 2), singleton(fromXY(1, 1)));
        Hoover hoover = new Hoover(fromXY(0, 0), room);

        hoover.follow(asList(N, E, S, W, N, E));

        assertEquals(1, hoover.getHooveredPatchCount());
    }
}
