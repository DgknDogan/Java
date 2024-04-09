package dataLayer;

import java.util.ArrayList;
import java.util.List;

import businessLayer.Temperature;

public class Location {
    private String name;

    public Location(String name) {
        this.name = name;
    }

    public ArrayList<Temperature> createTemperatureList() {
        ArrayList<Temperature> temperatureList = new ArrayList<>();
        for (int i = 2020; i <= 2022; i++) {
            for (int j = 1; j <= 12; j++) {
                Temperature temperature = new Temperature();
                temperature.setYear(i);
                temperature.setMonth(j);
                temperatureList.add(temperature);
            }
        }
        return temperatureList;
    }

    public double calculateAverageTemp(int option, int year, ArrayList<Temperature> temperatureList) {
        double sum = 0;
        List<Temperature> sublist = temperatureList.subList(0 + 12 * (year - 1), 12 + 12 * (year - 1));
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

    public String getName() {
        return name;
    }
}
