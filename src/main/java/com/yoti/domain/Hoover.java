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
        if (!this.room.positionExists(this.position)) {
            throw new IllegalArgumentException("Invalid room position: " + this.position);
        }
    }

    public void follow(List<DrivingInstruction> instructions) {
        for (DrivingInstruction instruction : instructions) {
            Coordinates nextPosition = instruction.apply(this.position);
            if (room.positionExists(nextPosition)) {
                this.position = nextPosition;
                hoover();
            }
        }
    }

    private void hoover() {
        if(room.removePatchOfDirtAt(this.position)) {
            this.hooveredPatchCount++;
        }
    }

    public Coordinates getPosition() {
        return position;
    }

    public int getHooveredPatchCount() {
        return hooveredPatchCount;
    }
}
