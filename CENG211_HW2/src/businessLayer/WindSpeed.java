package businessLayer;

import java.util.Random;

public class WindSpeed extends ClimateMeasurement {
    private double metersPerSecond;
    private double kmPerHour;

    public WindSpeed() {
        Random random = new Random();
        this.metersPerSecond = random.nextDouble() * 113.2;
        this.kmPerHour = (metersPerSecond * 3600 / 1000);
    }

    public double getMetersPerSecond() {
        return metersPerSecond;
    }

    public double getKmPerHour() {
        return kmPerHour;
    }
    public enum WindEnum {
        METER(1, "m/s"), KILOMETER(2, "km/h");

        private int option;
        private String value;

        private WindEnum(int option, String value) {
            this.option = option;
            this.value = value;
        }

        public static String getWindEnum(int option) {
            for (WindEnum windEnum : values()) {
                if (windEnum.option == option) {
                    return windEnum.value;
                }
            }
            return null;
        }
    }
}