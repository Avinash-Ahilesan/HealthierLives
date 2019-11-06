package model;

import java.io.Serializable;
import java.util.Calendar;

public class TimeStamp implements Serializable {

    private Calendar calendar;

    public TimeStamp(int year, int month, int date) {
        calendar = new Calendar.Builder().build();
        calendar.set(year, month, date);
    }

    @Override
    public String toString() {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return day + "/" + month + "/" + year;
    }

    public TimeStamp(TimeStamp timeStamp) {
        calendar = new Calendar.Builder().build();
        calendar.set(timeStamp.calendar.get(Calendar.YEAR), timeStamp.calendar.get(Calendar.MONTH),
                timeStamp.calendar.get(Calendar.DAY_OF_MONTH));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        TimeStamp obj1 = (TimeStamp) obj;
        if (obj1.calendar.equals(this.calendar)) {
            return true;
        }
        return false;
    }

}
