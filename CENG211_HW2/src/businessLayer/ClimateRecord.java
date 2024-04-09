package businessLayer;

import dataLayer.City;
import dataLayer.Country;
import dataLayer.FileIO;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

import businessLayer.WindSpeed.WindEnum;



public class ClimateRecord {
    private FileIO fileIO = new FileIO();
    private ArrayList<Country> countries = fileIO.getCountries();
    private ArrayList<City> citites = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public ClimateRecord() {
        createCitiesList();
    }

    public void createCitiesList() {
        for (int i = 0; i < countries.size(); i++) {
            citites.addAll(countries.get(i).getCitiesInCountry());
        }
    }

    public void menuOperations() throws InterruptedException {
        while (true) {
            displayMenu();

            System.out.print("Please select an option: ");
            String menuSelection = scanner.nextLine();
            while (true) {
                if (1 > Integer.parseInt(menuSelection) || Integer.parseInt(menuSelection) > 7) {
                    System.out.print("Incorrect option input! Please reenter another option input: ");
                    menuSelection = scanner.nextLine();
                } else {
                    break;
                }
            }
            System.out.println();
            if (menuSelection.equals("1")) {
                firstQuerry();
            } else if (menuSelection.equals("2")) {
                secondQuerry();
            } else if (menuSelection.equals("3")) {
                thirdQuerry();
            } else if (menuSelection.equals("4")) {
                fourthQuerry();
            } else if (menuSelection.equals("5")) {
                fifthQuerry();
            } else if (menuSelection.equals("6")) {
                sixthQuerry();
            } else if (menuSelection.equals("7")) {
                break;
            }
            Thread.sleep(1000);
        }
        scanner.close();
    }

    private void displayMenu() {
        System.out
                .println("[1] Calculate average temperature for a country according to temperature unit and year.");
        System.out.println("[2] Calculate average temperature for a city according to temperature unit and year.");
        System.out.println("[3] Calculate average wind speed for a city according to speed unit and year.");
        System.out.println("[4] Calculate average humidity of a city for every year.");
        System.out.println("[5] Count how many times a year a specific radiation intensity value appears.");
        System.out.println("[6] Calculate the 'felt temperature' value for a specific month.");
        System.out.println("[7] Exit the application.");
    }

    private City getSelectedCity() {
        System.out.print("Enter the name of the city: ");

        City selectedCity;
        while (true) {
            String city = scanner.nextLine();
            selectedCity = new City(city);
            for (int i = 0; i < citites.size(); i++) {
                if (citites.get(i).getName().equals(city)) {
                    selectedCity = citites.get(i);
                }
            }
            if (citites.contains(selectedCity)) {
                break;
            }
            System.out.print("Incorrect option input! Please reenter another option input: ");
        }
        return selectedCity;
    }

    private int selectedYear() {
        System.out.println();
        System.out.println("[1] 2020 [2] 2021 [3] 2022");
        System.out.print("Please select the year: ");
        int year;
        while (true) {
            year = Integer.parseInt(scanner.nextLine());
            if (1 <= year && year <= 3) {
                break;
            }

            System.out.print("Incorrect option input! Please reenter another option input:");
        }
        return year;
    }

    private int selectTempUnit() {
        System.out.println();
        System.out.println("[1] Celsius [2] Fahrenheit [3] Kelvin ");
        System.out.print("Please select the temperature unit: ");

        int option;
        while (true) {
            option = Integer.parseInt(scanner.nextLine());
            if (1 <= option && option <= 3) {
                break;
            }
            System.out.print("Incorrect option input! Please reenter another option input:");
        }
        return option;
    }

    private Country getSelectedCountry() {
        System.out.print("Enter the name of the country: ");

        Country selectedCountry;
        while (true) {
            String country = scanner.nextLine();
            selectedCountry = new Country(country, citites);
            for (int i = 0; i < countries.size(); i++) {
                if (countries.get(i).getName().equals(country)) {
                    selectedCountry = countries.get(i);
                }
            }
            if (countries.contains(selectedCountry)) {
                break;
            }
            System.out.print("Incorrect option input! Please reenter another option input: ");
        }
        return selectedCountry;
    }

    private int selectedMonth() {
        System.out.println();
        System.out.print("Please enter the month from 1 to 12: ");
        int month;
        while (true) {
            month = Integer.parseInt(scanner.nextLine());
            if (1 <= month && month <= 12) {
                break;
            }
            System.out.print("Incorrect option input! Please reenter another option input:");
        }
        return month;
    }

    private int selectWindUnit() {
        System.out.println();
        System.out.println("[1] m/s [2] km/h");
        System.out.print("Please select the wind speed unit: ");
        int option;
        while (true) {
            option = Integer.parseInt(scanner.nextLine());
            if (1 <= option && option <= 3) {
                break;
            }
            System.out.print("Incorrect option input! Please reenter another option input:");
        }
        return option;
    }

    private String selectedIntensity() {
        System.out.println();
        System.out.println("['Low'] Low ['Medium'] Medium ['High'] High ");
        System.out.print("Please select the radiation intensity unit: ");

        String option;
        while (true) {
            option = scanner.nextLine();
            if (option.equals("Low") || option.equals("Medium") || option.equals("High")) {
                break;
            }
            System.out.print("Incorrect option input! Please reenter another option input:");
        }
        return option;
    }

    // Queries
    public void firstQuerry() {
        Country selectedCountry = getSelectedCountry();

        int option = selectTempUnit();

        int year = selectedYear();

        System.out.println();
        System.out.println(
                "==> Average temperature of " + selectedCountry.getName() + " in " + YearEnum.getYearEnum(year)
                        + " "
                        + TempEnum.getTempEnum(option) + " "
                        + String.format(Locale.US, "%.2f",
                                selectedCountry.calculateAverageTemp(option, year)));
        System.out.println("*****************************************************************************");

    }

    public void secondQuerry() {
        City selectedCity = getSelectedCity();

        int option = selectTempUnit();

        int year = selectedYear();

        System.out.println();
        System.out.println(
                "==> Average temperature of " + selectedCity.getName() + " in " + YearEnum.getYearEnum(year) + " "
                        + TempEnum.getTempEnum(option) + " "
                        + String.format(Locale.US, "%.2f",
                                selectedCity.calculateAverageTemp(option, year)));
        System.out.println("*****************************************************************************");
    }

    private void thirdQuerry() {
        City selectedCity = getSelectedCity();

        int option = selectWindUnit();

        int month = selectedMonth();

        System.out.println();
        System.out.println(
                "==> Average wind speed of " + selectedCity.getName() + " in " + MonthEnum.getMonthEnum(month)
                        + " of the given three years: "
                        + String.format(Locale.US, "%.2f",
                                selectedCity.calculateAverageWindSpeed(option, month))
                        + " "
                        + WindEnum.getWindEnum(option));
        System.out.println("*****************************************************************************");

    }

    private void fourthQuerry() {
        City selectedCity = getSelectedCity();

        System.out.println(
                "==> Average humidity of a city for given 3 years is "
                        + String.format(Locale.US, "%.2f", selectedCity.calculateAverageHumidity()));
        System.out.println("*****************************************************************************");
    }

    private void fifthQuerry() {
        City selectedCity = getSelectedCity();

        String option = selectedIntensity();

        int year = selectedYear();

        System.out.println();
        System.out.println("==> The intensity value has become " + option + " in " + YearEnum.getYearEnum(year)
                + " for "
                + selectedCity.getName() + " " + selectedCity.countRadiationIntensity(option, year) + " times.");
        System.out.println("*****************************************************************************");
    }

    private void sixthQuerry() {
        City selectedCity = getSelectedCity();

        int month = selectedMonth();

        int year = selectedYear();

        System.out.println("==> Felt temperature of " + MonthEnum.getMonthEnum(month) + "/" + YearEnum.getYearEnum(year)
                + " for "
                + selectedCity.getName()
                + " is: " + String.format(Locale.US, "%.2f",
                        selectedCity.getFeltTemperatureList().get((month - 1) + (12 * (year - 1)))));
        System.out.println("*****************************************************************************");
    }
}