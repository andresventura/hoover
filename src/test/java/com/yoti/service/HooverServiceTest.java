package com.yoti.service;

import com.yoti.domain.Coordinates;
import com.yoti.domain.instruction.DrivingInstructionFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static com.yoti.domain.Coordinates.fromXY;
import static org.junit.Assert.assertEquals;

public class HooverServiceTest {
    private HooverService hooverService;

    private HooverRequest createHoverRequest(String instructions) {
        return createHoverRequest(instructions, Collections.emptySet());
    }

    private HooverRequest createHoverRequest(String instructions, Set<Coordinates> patches) {
        return new HooverRequest(fromXY(5, 5), patches, fromXY(0, 0), instructions);
    }

    private HooverResponse createHoverResponse(Coordinates position) {
        return createHoverResponse(position, 0);
    }

    private HooverResponse createHoverResponse(Coordinates position, int patches) {
        return new HooverResponse(position, patches);
    }

    @Before
    public void setUp() {
        this.hooverService = new HooverService(new DrivingInstructionFactory());
    }

    @Test
    public void whenNoInstructionsHooverStaysAtTheInitialPosition() {
        HooverRequest request = createHoverRequest("");

        HooverResponse response = hooverService.hoover(request);

        assertEquals(createHoverResponse(fromXY(0, 0)), response);
    }

    @Test
    public void whenMovingOnePositionNorthHooverPositionChanges() {
        HooverRequest request = createHoverRequest("N");

        HooverResponse response = hooverService.hoover(request);

        assertEquals(createHoverResponse(fromXY(0, 1)), response);
    }

    @Test
    public void whenMovingMultiplePositionsHooverPositionChanges() {
        HooverRequest request = createHoverRequest("NNEESW");

        HooverResponse response = hooverService.hoover(request);

        assertEquals(createHoverResponse(fromXY(1, 1)), response);
    }

    @Test
    public void whenHooveringOverPatchesTheyGetCounted() {
        HooverRequest request = createHoverRequest("NNEESW",
                new HashSet<>(Arrays.asList(fromXY(0, 0), fromXY(2, 2))));

        HooverResponse response = hooverService.hoover(request);

        assertEquals(createHoverResponse(fromXY(1, 1), 2), response);
    }
}
