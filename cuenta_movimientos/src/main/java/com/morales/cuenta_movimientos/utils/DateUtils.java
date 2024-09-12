package com.morales.cuenta_movimientos.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static Date convertStringToDate(String date) throws ParseException{
        SimpleDateFormat formatter = new SimpleDateFormat(MessageUtil.getMessage(Messages.DATE_FORMAT));
        return formatter.parse(date);
    }

}
