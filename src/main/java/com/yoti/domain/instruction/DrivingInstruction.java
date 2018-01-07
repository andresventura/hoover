package com.yoti.domain.instruction;

import com.yoti.domain.Coordinates;

import java.util.function.Function;

public interface DrivingInstruction extends Function<Coordinates, Coordinates> {}
