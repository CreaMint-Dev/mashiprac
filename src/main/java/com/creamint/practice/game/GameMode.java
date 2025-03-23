package com.creamint.practice.game;

public enum GameMode {
    NODEBUFF("NoDebuff"),
    BOXING("Boxing"),
    SUMO("Sumo");

    private final String name;

    GameMode(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}