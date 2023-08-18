package com.example.login;

public class ModelActivity {
    String holidayName;
    String holiday;
    String dateHoliday;

    public ModelActivity(String holidayName, String holiday, String dateHoliday) {
        this.holidayName = holidayName;
        this.holiday = holiday;
        this.dateHoliday = dateHoliday;
    }

    public String getHolidayName() {
        return holidayName;
    }

    public void setHolidayName(String holidayName) {
        this.holidayName = holidayName;
    }

    public String getHoliday() {
        return holiday;
    }

    public void setHoliday(String holiday) {
        this.holiday = holiday;
    }

    public String getDateHoliday() {
        return dateHoliday;
    }

    public void setDateHoliday(String dateHoliday) {
        this.dateHoliday = dateHoliday;
    }
}
