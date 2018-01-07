package com.yoti.service;

import com.yoti.domain.Hoover;
import com.yoti.domain.Room;
import com.yoti.domain.instruction.DrivingInstructionFactory;

public class HooverService {
    private DrivingInstructionFactory drivingInstructionFactory;

    public HooverService(DrivingInstructionFactory drivingInstructionFactory) {
        this.drivingInstructionFactory = drivingInstructionFactory;
    }

    public HooverResponse hoover(HooverRequest hooverRequest) {
        Room room = new Room(hooverRequest.getRoomSize(), hooverRequest.getPatches());
        Hoover hoover = new Hoover(hooverRequest.getCoords(), room);
        hoover.follow(drivingInstructionFactory.getInstructions(hooverRequest.getInstructions()));
        return new HooverResponse(hoover.getPosition(), hoover.getHooveredPatchCount());
    }
}
