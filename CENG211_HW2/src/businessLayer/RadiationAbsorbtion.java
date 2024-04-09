package businessLayer;

import java.util.Random;

public class RadiationAbsorbtion extends ClimateMeasurement {
    private double unitAbsorbtionValue;
    private RadiationIntensity radiationIntensity = RadiationIntensity.selectRandomEnum();

    public RadiationAbsorbtion() {
        Random random = new Random();
        this.unitAbsorbtionValue = random.nextDouble(16) + 5;
    }

    public double getUnitAbsorbtionValue() {
        return unitAbsorbtionValue;
    }

    public RadiationIntensity getRadiationIntensity() {
        return radiationIntensity;
    }
    public enum RadiationIntensity {
        LOW("Low"), MEDIUM("Medium"), HIGH("High");

        private String value;

        private RadiationIntensity(String value) {
            this.value = value;
        }

        public static RadiationIntensity selectRandomEnum() {
            Random random = new Random();
            return values()[random.nextInt(values().length)];
        }

        public String toString() {
            return value;
        }
    };
}


