package com.yoti.domain;

import org.junit.Test;

import java.util.Collections;

import static com.yoti.domain.Coordinates.fromXY;
import static java.util.Collections.emptySet;
import static java.util.Collections.singleton;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RoomTest {

    @Test
    public void returnsTrueWhenPositionExists() {
        Room room = new Room(fromXY(5, 5), emptySet());
        assertTrue(room.positionExists(fromXY(0,0)));
    }

    @Test
    public void returnsFalseWhenPositionDoesNotExist() {
        Room room = new Room(fromXY(5, 5), emptySet());
        assertFalse(room.positionExists(fromXY(10,10)));
    }

    @Test
    public void returnsTrueWhenRemovingPatch() {
        Room room = new Room(fromXY(5, 5), singleton(fromXY(1,1)));
        assertTrue(room.removePatchOfDirtAt(fromXY(1,1)));
    }

    @Test
    public void returnsFalseWhenTryingToRemovePatchAtAnEmptyPosition() {
        Room room = new Room(fromXY(5, 5), singleton(fromXY(1,1)));
        assertFalse(room.removePatchOfDirtAt(fromXY(1,2)));
    }
}
