package com.mygdx.game;

public abstract class Achievement {
    private String name;
    private String description;
    private boolean conditionSatisfied;

    public Achievement(String name, String description) {
        this.name = name;
        this.description = description;
        conditionSatisfied = false;
    }

    public abstract void checkCondition();

    public void unlockAchievement() {
        if (!conditionSatisfied)
            conditionSatisfied = true;
    }
}
