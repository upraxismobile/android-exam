package com.vj.myapplication;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 *
 * Created by VJ on 12/31/2017.
 */

public class Util {

    public static int calculateAge(String datetime, String sourceformat) {

        DateTimeFormatter formatter = DateTimeFormat.forPattern(sourceformat);
        DateTime dateTime = DateTime.parse(datetime, formatter.withZone(DateTimeZone.forID("Asia/Manila")));

        LocalDate birthdate = new LocalDate (dateTime.getYear(), dateTime.getMonthOfYear(), dateTime.getDayOfMonth());          //Birth date
        LocalDate now = new LocalDate();                    //Today's date
        Period period = new Period(birthdate, now, PeriodType.yearMonthDay());
        return period.getYears();
    }

    public static String translateBirthDate(String datetime, String sourceformat, String outputformat) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern(sourceformat);
        DateTime utcTime = DateTime.parse(datetime, formatter.withZone(DateTimeZone.forID("Asia/Manila")));
        return utcTime.toString(outputformat);
    }
}
