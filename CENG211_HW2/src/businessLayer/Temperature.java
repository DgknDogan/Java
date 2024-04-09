package businessLayer;

import java.util.Random;

public class Temperature extends ClimateMeasurement {
    private double celciusMeasurement;
    private double fahrenheitMeasurement;
    private double kelvinMeasurement;

    public Temperature() {
        Random random = new Random();
        this.celciusMeasurement = (random.nextDouble() * (90.0)) - 40.0;
        this.fahrenheitMeasurement = (celciusMeasurement * 9 / 5) + 32;
        this.kelvinMeasurement = celciusMeasurement + 273;
    }

    public double getCelciusMeasurement() {
        return celciusMeasurement;
    }

    public double getFahrenheitMeasurement() {
        return fahrenheitMeasurement;
    }

    public double getKelvinMeasurement() {
        return kelvinMeasurement;
    }
}

enum TempEnum {
    CELCIUS(1, "celcius"), FAHRENHEIT(2, "fahrenheit"), KELVIN(3, "kelvin");

    private int option;
    private String value;

    private TempEnum(int option, String value) {
        this.value = value;
        this.option = option;
    }

    public static String getTempEnum(int option) {
        for (TempEnum tempEnum : values()) {
            if (tempEnum.option == option) {
                return tempEnum.value;
            }
        }
        return null;
    }
}