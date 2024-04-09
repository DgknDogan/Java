package dataLayer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileIO {

    public ArrayList<Country> getCountries() {
        ArrayList<Country> countryList = new ArrayList<Country>();

        try (BufferedReader br = new BufferedReader(new FileReader("./countries_and_cities.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                ArrayList<City> cityList = new ArrayList<City>();
                String splittedLine[] = line.split(",");

                City city1 = new City(splittedLine[1].stripLeading());
                cityList.add(city1);
                City city2 = new City(splittedLine[2].stripLeading());
                cityList.add(city2);
                City city3 = new City(splittedLine[3].stripLeading());
                cityList.add(city3);
                Country country = new Country(splittedLine[0], cityList);
                countryList.add(country);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return countryList;
    }
}
