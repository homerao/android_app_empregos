package br.com.util;

import android.widget.Toast;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;

public class DateConverterUtil {






    public static Date stringToDate(String source) throws ParseException{
        Date date = null;


            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            date = (Date)formatter.parse(source);


        return  date;
    }


    public static String dateToString(Date date){
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String dataStr = format.format(date);
        return dataStr;
    }
}
