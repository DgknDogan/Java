package businessLayer;

import java.util.Random;

public class Humidity extends ClimateMeasurement {
    private double humidityPercentage;

    public Humidity() {
        Random random = new Random();
        this.humidityPercentage = random.nextDouble() * 100;
    }

    public double getHumidityPercentage() {
        return humidityPercentage;
    }
}
