package com.yoti.domain.instruction;

import java.util.ArrayList;
import java.util.List;

public class DrivingInstructionFactory {

    public List<DrivingInstruction> createInstructions(String instructions) {
        List<DrivingInstruction> list = new ArrayList<>(instructions.length());
        for(int i = 0;i < instructions.length();i++) {
            try {
                list.add(DrivingInstruction.valueOf(String.valueOf(instructions.charAt(i))));
            } catch (IllegalArgumentException e) {
                throw new UnrecognizedInstructionException(instructions.charAt(i));
            }
        }
        return list;
    }
}
