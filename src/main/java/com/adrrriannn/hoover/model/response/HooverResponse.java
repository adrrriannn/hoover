package com.adrrriannn.hoover.model.response;

import com.adrrriannn.hoover.model.Position;

/**
 * Created by adrian on 11/09/17.
 */
public class HooverResponse {

    private Position finalPosition;
    private int patches;

    private HooverResponse() {}

    public HooverResponse(Position finalPosition, int patches) {
        this.finalPosition = finalPosition;
        this.patches = patches;
    }

    public Position getFinalPosition() {
        return finalPosition;
    }

    public void setFinalPosition(Position finalPosition) {
        this.finalPosition = finalPosition;
    }

    public int getPatches() {
        return patches;
    }

    public void setPatches(int patches) {
        this.patches = patches;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HooverResponse that = (HooverResponse) o;

        if (patches != that.patches) return false;
        return finalPosition != null ? finalPosition.equals(that.finalPosition) : that.finalPosition == null;
    }

    @Override
    public int hashCode() {
        int result = finalPosition != null ? finalPosition.hashCode() : 0;
        result = 31 * result + patches;
        return result;
    }
}
