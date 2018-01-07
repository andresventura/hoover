package com.yoti.domain.instruction;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.yoti.domain.instruction.DrivingInstructions.*;

public class DrivingInstructionFactoryTest {
    @Test
    public void createsInstructionsFromString() {
        DrivingInstructionFactory factory = new DrivingInstructionFactory();

        List<DrivingInstruction> instructions = factory.getInstructions("NSEW");

        Assert.assertEquals(Arrays.asList(NORTH, SOUTH, EAST, WEST), instructions);
    }

    @Test(expected = IllegalArgumentException.class)
    public void failsWhenStringContainsInvalidCharacters() {
        DrivingInstructionFactory factory = new DrivingInstructionFactory();

        List<DrivingInstruction> instructions = factory.getInstructions("NR");
    }
}
