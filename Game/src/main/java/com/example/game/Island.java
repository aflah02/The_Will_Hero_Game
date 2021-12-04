package com.example.game;

import java.io.Serializable;

public class Island implements Serializable {
    private Position position;
    private int size;

    Island(){

    }

    public Position getPosition() {
        return position;
    }

    public int getSize() {
        return size;
    }
}
