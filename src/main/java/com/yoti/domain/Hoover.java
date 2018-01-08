package com.yoti.domain;

import com.yoti.domain.instruction.DrivingInstruction;

import java.util.List;

public class Hoover {
    private Coordinates position;
    private Room room;
    private int hooveredPatchCount = 0;

    public Hoover(Coordinates position, Room room) {
        this.position = position;
        this.room = room;
        validatePosition();
        hoover();
    }

    private void validatePosition() {
        if (!room.positionExists(position)) {
            throw new InvalidRoomCoordinates(position);
        }
    }

    public void follow(List<DrivingInstruction> instructions) {
        for (DrivingInstruction instruction : instructions) {
            Coordinates nextPosition = instruction.getFn().apply(position);
            if (room.positionExists(nextPosition)) {
                position = nextPosition;
                hoover();
            }
        }
    }

    private void hoover() {
        if(room.removePatchOfDirtAt(position)) {
            hooveredPatchCount++;
        }
    }

    public Coordinates getPosition() {
        return position;
    }

    public int getHooveredPatchCount() {
        return hooveredPatchCount;
    }
}
