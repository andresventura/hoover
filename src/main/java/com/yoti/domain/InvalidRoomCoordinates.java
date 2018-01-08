package com.yoti.domain;

public class InvalidRoomCoordinates extends RuntimeException {
    public InvalidRoomCoordinates(Coordinates coordinates) {
        super(String.format("Invalid room coordinates: %s", coordinates));
    }
}
