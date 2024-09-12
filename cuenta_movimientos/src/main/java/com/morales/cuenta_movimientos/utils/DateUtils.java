package com.morales.cuenta_movimientos.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    public static Date convertStringToDate(String date, Boolean startDate) throws ParseException{
        SimpleDateFormat formatter = new SimpleDateFormat(MessageUtil.getMessage(Messages.DATE_FORMAT));
        Date dateResult = formatter.parse(date);
        Calendar calendar = Calendar.getInstance();
        if (startDate) {
            calendar.setTime(dateResult);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            return calendar.getTime();
        } else {
            calendar.setTime(dateResult);
            calendar.set(Calendar.HOUR_OF_DAY, 23);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 59);
            calendar.set(Calendar.MILLISECOND, 999);
            return calendar.getTime();
        }
    }

}
