package dataLayer;

import businessLayer.Humidity;
import businessLayer.RadiationAbsorbtion;
import businessLayer.Temperature;
import businessLayer.WindSpeed;
import businessLayer.WindSpeed.WindEnum;

import java.util.ArrayList;
import java.util.List;

public class City extends Location {
    private ArrayList<Temperature> cityTemperatureList = new ArrayList<>();
    private ArrayList<Humidity> cityHumidityList = new ArrayList<>();
    private ArrayList<WindSpeed> cityWindSpeedList = new ArrayList<>();
    private ArrayList<RadiationAbsorbtion> cityRadiationAbsorbtionList = new ArrayList<>();
    private ArrayList<Double> feltTemperatureList = new ArrayList<>();

    public City(String cityName) {
        super(cityName);

        cityTemperatureList = createTemperatureList();
        cityHumidityList = createHumidityList();
        cityWindSpeedList = createWindSpeedList();
        cityRadiationAbsorbtionList = createRadiationAbsortionList();
        feltTemperatureList = createFeltTemperature();
    }

    private ArrayList<Humidity> createHumidityList() {
        ArrayList<Humidity> humidityList = new ArrayList<>();
        for (int i = 2020; i <= 2022; i++) {
            for (int j = 1; j <= 12; j++) {
                Humidity humidity = new Humidity();
                humidity.setYear(i);
                humidity.setMonth(j);
                humidityList.add(humidity);
            }
        }
        return humidityList;
    }

    private ArrayList<WindSpeed> createWindSpeedList() {
        ArrayList<WindSpeed> windSpeedList = new ArrayList<>();
        for (int i = 2020; i <= 2022; i++) {
            for (int j = 1; j <= 12; j++) {
                WindSpeed windSpeed = new WindSpeed();
                windSpeed.setYear(i);
                windSpeed.setMonth(j);
                windSpeedList.add(windSpeed);
            }
        }
        return windSpeedList;
    }

    private ArrayList<RadiationAbsorbtion> createRadiationAbsortionList() {
        ArrayList<RadiationAbsorbtion> radiationAbsorbtionsList = new ArrayList<>();
        for (int i = 2020; i <= 2022; i++) {
            for (int j = 1; j <= 12; j++) {
                RadiationAbsorbtion radiationAbsorbtion = new RadiationAbsorbtion();
                radiationAbsorbtion.setYear(i);
                radiationAbsorbtion.setMonth(j);
                radiationAbsorbtionsList.add(radiationAbsorbtion);
            }
        }
        return radiationAbsorbtionsList;
    }

    private ArrayList<Double> createFeltTemperature() {
        ArrayList<Double> tempList = new ArrayList<>();
        double feltTemperature = 0;
        for (int i = 0; i < cityTemperatureList.size(); i++) {
            double temperature = cityTemperatureList.get(i).getCelciusMeasurement();
            double humidity = cityHumidityList.get(i).getHumidityPercentage() / 100;
            double windSpeed = cityWindSpeedList.get(i).getMetersPerSecond();
            double radiationAbsorbtion = cityRadiationAbsorbtionList.get(i).getUnitAbsorbtionValue();
            feltTemperature = temperature + 0.3 * humidity - 0.7 * (radiationAbsorbtion /
                    (windSpeed + 10));
            feltTemperatureList.add(feltTemperature);
            tempList.add(feltTemperature);
        }
        return tempList;
    }

    //
    public int countRadiationIntensity(String option, int year) {
        int count = 0;
        List<RadiationAbsorbtion> sublist = cityRadiationAbsorbtionList.subList(0 + 12 * (year - 1),
                12 + 12 * (year - 1));
        for (int i = 0; i < sublist.size(); i++) {
            if (sublist.get(i).getRadiationIntensity().toString().equals(option)) {
                count++;
            }
        }
        return count;
    }

    public double calculateAverageTemp(int option, int year) {
        double sum = 0;
        List<Temperature> sublist = cityTemperatureList.subList(0 + 12 * (year - 1), 12 + 12 * (year - 1));
        for (int i = 0; i < sublist.size(); i++) {
            switch (option) {
                case 1:
                    sum += sublist.get(i).getCelciusMeasurement();
                    break;
                case 2:
                    sum += sublist.get(i).getFahrenheitMeasurement();
                    break;
                case 3:
                    sum += sublist.get(i).getKelvinMeasurement();
                    break;
            }
        }
        return sum / sublist.size();
    }

    public double calculateAverageWindSpeed(int option, int month) {
        double sum = 0;
        for (int i = 0; i < 3; i++) {
            if (WindEnum.getWindEnum(option).equals("m/s")) {
                sum += cityWindSpeedList.get(month + (12 * i) - 1).getMetersPerSecond();
            } else if (WindEnum.getWindEnum(option).equals("km/h")) {
                sum += cityWindSpeedList.get(month + (12 * i) - 1).getKmPerHour();
            }
        }
        return sum / cityWindSpeedList.size();
    }

    public double calculateAverageHumidity() {
        double totalHumidity = 0;
        for (int i = 0; i < cityHumidityList.size(); i++) {
            totalHumidity += (cityHumidityList.get(i).getHumidityPercentage() / cityHumidityList.size());
        }
        return totalHumidity;
    }

    public ArrayList<Double> getFeltTemperatureList() {
        return feltTemperatureList;
    }

    public ArrayList<Temperature> getCityTemperatureList() {
        return cityTemperatureList;
    }

    public ArrayList<Humidity> getCityHumidityList() {
        return cityHumidityList;
    }

    public ArrayList<WindSpeed> getCityWindSpeedList() {
        return cityWindSpeedList;
    }
}