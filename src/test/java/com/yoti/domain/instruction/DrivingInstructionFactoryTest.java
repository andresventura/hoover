package com.yoti.domain.instruction;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.yoti.domain.instruction.DrivingInstruction.*;
import static org.junit.Assert.assertEquals;


public class DrivingInstructionFactoryTest {
    @Test
    public void createsInstructionsFromString() {
        DrivingInstructionFactory factory = new DrivingInstructionFactory();

        List<DrivingInstruction> instructions = factory.createInstructions("NSEW");

        assertEquals(Arrays.asList(N, S, E, W), instructions);
    }

    @Test(expected = UnrecognizedInstructionException.class)
    public void failsWhenStringContainsInvalidCharacters() {
        DrivingInstructionFactory factory = new DrivingInstructionFactory();

        factory.createInstructions("NR");
    }
}
