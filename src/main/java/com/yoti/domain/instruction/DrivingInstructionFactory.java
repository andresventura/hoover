package com.yoti.domain.instruction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.yoti.domain.instruction.DrivingInstructions.*;

public class DrivingInstructionFactory {

    private static final Map<Character, DrivingInstruction> DRIVING_INSTRUCTIONS = createDrivingInstructions();

    private static Map<Character, DrivingInstruction> createDrivingInstructions() {
        Map<Character, DrivingInstruction> map = new HashMap<>();
        map.put('N', NORTH);
        map.put('S', SOUTH);
        map.put('E', EAST);
        map.put('W', WEST);
        return map;
    }
    public List<DrivingInstruction> getInstructions(String instructions) {
        List<DrivingInstruction> list = new ArrayList<>(instructions.length());
        for(int i = 0;i < instructions.length();i++) {
            DrivingInstruction instruction = DRIVING_INSTRUCTIONS.get(instructions.charAt(i));
            if (instruction == null) {
                throw new IllegalArgumentException(String.format("Unrecognized instruction %s", instructions.charAt(i)));
            }
            list.add(instruction);
        }
        return list;
    }
}
