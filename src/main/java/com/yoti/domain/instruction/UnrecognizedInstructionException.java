package com.yoti.domain.instruction;

public class UnrecognizedInstructionException extends RuntimeException {
    public UnrecognizedInstructionException(char instruction) {
        super(String.format("Unrecognized instruction: %s", instruction));
    }
}
