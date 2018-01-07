package com.yoti.service;

import com.yoti.domain.Coordinates;

import java.util.Set;

public class HooverRequest {
    private final Coordinates roomSize;
    private final Set<Coordinates> patches;
    private final Coordinates coords;
    private final String instructions;


    public HooverRequest(Coordinates roomSize, Set<Coordinates> patches, Coordinates coords,
                         String instructions) {
        this.roomSize = roomSize;
        this.patches = patches;
        this.coords = coords;
        this.instructions = instructions;
    }

    public Coordinates getRoomSize() {
        return roomSize;
    }

    public Coordinates getCoords() {
        return coords;
    }

    public String getInstructions() {
        return instructions;
    }

    public Set<Coordinates> getPatches() {
        return patches;
    }

}
