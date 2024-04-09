package dataLayer;

import java.util.ArrayList;
import java.util.List;

import businessLayer.Temperature;

public class Country extends Location {
    private ArrayList<City> citiesInCountry;
    private ArrayList<Temperature> countyTemperatureList = new ArrayList<>();

    public Country(String countryName, ArrayList<City> citiesInCountry) {
        super(countryName);
        this.citiesInCountry = citiesInCountry;

        countyTemperatureList = createTemperatureList();
    }

    public double calculateAverageTemp(int option, int year) {
        double sum = 0;
        List<Temperature> sublist = countyTemperatureList.subList(0 + 12 * (year - 1), 12 + 12 * (year - 1));
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

    public ArrayList<City> getCitiesInCountry() {
        return citiesInCountry;
    }

    public ArrayList<Temperature> getCountyTemperatureList() {
        return countyTemperatureList;
    }
}