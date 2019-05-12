package com.mygdx.game.menu.achievements;

public abstract class Achievement {
    protected String name;
    protected String description;
    protected boolean conditionSatisfied;
    protected static int totalKnots = 0;

    /**
     * This is the constructor for the Achievements object initialises name, description, and conditionSatisfied.
     * @param name is the name of the Achievement.
     * @param description is the content of the Achievement.
     */
    public Achievement(String name, String description) {
        this.name = name;
        this.description = description;
        conditionSatisfied = false;
    }

    /**
     * This method returns the name of the Achievement.
     * @return The name of the Achievement.
     */
    public String getName() {
        return name;
    }

    /**
     * This method sets the name of the Achievement to the given name.
     * @param name The name of the Achievement to set to.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method returns the description of the Achievement.
     * @return The description of the Achievement.
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method sets the description of the Achievement to the given name.
     * @param description The description of the Achievement to set to.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * This method indicates whether the condition is satisfied or not.
     * @return true if condition is satisfied, false otherwise
     */
    public boolean isConditionSatisfied() {
        return conditionSatisfied;
    }

    /**
     * This method sets the conditionSatisfied value to the given boolean value.
     * @param conditionSatisfied The boolean value to set conditionSatisfied to.
     */
    public void setConditionSatisfied(boolean conditionSatisfied) {
        this.conditionSatisfied = conditionSatisfied;
    }

    /**
     * This method returns the number of total knots.
     * @return The number of total knots.
     */
    public static int getTotalKnots() { return totalKnots; }

    /**
     * This method sets the number of total knots to the given value.
     * @param setTo The number of total knots to set to.
     */
    public static void setTotalKnots(int setTo) { totalKnots = setTo; }

    /**
     * This method increments the number of total knots by one.
     */
    public static void incrementTotalKnots() { totalKnots++; }

    /**
     * An abstract method that checks is the conditions are met.
     */
    public abstract void checkCondition();

    /**
     * This method checks if the achievement is unlocked by
     * looking at the conditionSatisfied variable.
     */
    public void unlockAchievement() {
        if (!conditionSatisfied)
            conditionSatisfied = true;
    }
}
