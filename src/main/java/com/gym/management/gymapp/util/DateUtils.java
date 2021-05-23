package com.gym.management.gymapp.util;

import java.util.Date;

public class DateUtils {

    public static boolean between(Date date, Date dateStart, Date dateEnd) {
        if (date != null && dateStart != null && dateEnd != null) {
            if ((date.after(dateStart) && date.before(dateEnd)) || date.equals(dateStart) || date.equals(dateEnd)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public static long difference(Date dateStart, Date dateEnd) {
        long days_difference = (((dateEnd.getTime() - dateStart.getTime()) / (1000 * 60 * 60 * 24)) % 365) + 1;
        return days_difference;
    }
}
