package com.mygdx.game.menu.achievements;

public abstract class Achievement {
    private String name;
    private String description;
    private boolean conditionSatisfied;
    private static int totalKnots = 0;

    /**
     * This is the constructor for the Achievements object initialises name, description, and conditionSatified.
     * @param name is the name of the Achievement.
     * @param description is the content of the Achievement.
     */
    public Achievement(String name, String description) {
        this.name = name;
        this.description = description;
        conditionSatisfied = false;
    }

    public static int getTotalKnots() { return totalKnots; }

    public static void setTotalKnots(int setTo) { totalKnots = setTo; }

    public static void incrementTotalKnots() { totalKnots++; }

    /**
     * An abstract method that checks is the conditions are met.
     */
    public abstract void checkCondition();

    /**
     * This method checks if the achievement is unlocked by
     * looking at the condition Satisfied variable
     */
    public void unlockAchievement() {
        if (!conditionSatisfied)
            conditionSatisfied = true;
    }
}
