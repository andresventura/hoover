package com.yoti.domain;

import java.util.HashSet;
import java.util.Set;

public class Room {
    private final Coordinates topRight;
    private Set<Coordinates> patchesOfDirt;

    public Room(Coordinates topRight, Set<Coordinates> patchesOfDirt) {
        this.topRight = topRight;
        this.patchesOfDirt = new HashSet<>(patchesOfDirt);
    }

    boolean removePatchOfDirtAt(Coordinates position) {
        return patchesOfDirt.remove(position);
    }

    boolean positionExists(Coordinates position) {
        return position.getX() >= 0 && position.getY() >= 0 && position.getX() < topRight.getX()
                && position.getY() < topRight.getY();
    }
}
