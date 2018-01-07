package com.yoti.service;

import com.yoti.domain.Coordinates;

import java.util.Objects;

public class HooverResponse {
    private final Coordinates coords;
    private final int patches;

    HooverResponse(Coordinates coords, int patches) {
        this.coords = coords;
        this.patches = patches;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HooverResponse that = (HooverResponse) o;
        return patches == that.patches &&
                Objects.equals(coords, that.coords);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coords, patches);
    }
}
