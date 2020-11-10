package org.example.models;

public class Thermostat extends  Thing{
    private int temperature;
    private final int minTemperature;
    private final int maxTemperature;

    public int getTemperature() {
        return temperature;
    }

    public boolean setTemperature(int temperature) {
        if (temperature < minTemperature || temperature > maxTemperature)
            return false;
        this.temperature = temperature;
        return true;
    }

    public int getMinTemperature() {
        return minTemperature;
    }

    public int getMaxTemperature() {
        return maxTemperature;
    }

    public Thermostat(int minTemperature, int maxTemperature) {
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
        this.temperature = this.minTemperature;
    }

    @Override
    public String getTypeName() {
        return "Thermostat";
    }

    @Override
    public String getDescription() {
        return "temperature="+this.temperature;
    }
}
