package businessLayer;

public class ClimateMeasurement {
    private int year;
    private int month;

    public ClimateMeasurement() {
        this.year = 2020;
        this.month = 1;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }
}

enum YearEnum {
    YEAR2020(1, 2020), YEAR2021(2, 2021), YEAR2022(3, 2022);

    private int option;
    private int year;

    private YearEnum(int option, int year) {
        this.option = option;
        this.year = year;
    }

    public static int getYearEnum(int option) {
        for (YearEnum yearEnum : values()) {
            if (yearEnum.option == option) {
                return yearEnum.year;
            }
        }
        return -1;
    }
}

enum MonthEnum {
    January(1),
    February(2),
    March(3),
    April(4),
    May(5),
    June(6),
    July(7),
    August(8),
    September(9),
    October(10),
    November(11),
    December(12);

    private int option;

    private MonthEnum(int option) {
        this.option = option;
    }

    public static MonthEnum getMonthEnum(int option) {
        for (MonthEnum monthEnum : values()) {
            if (monthEnum.option == option) {
                return monthEnum;
            }
        }
        return null;
    }
}