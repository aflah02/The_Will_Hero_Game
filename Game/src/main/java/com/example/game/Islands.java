package com.example.game;

public class Islands {
    Position position;
    int size;

    public Islands(Position position, int size) {
        this.position = position;
        this.size = size;
    }

    public Position getPosition() {
        return position;
    }

    public int getSize() {
        return size;
    }
}
