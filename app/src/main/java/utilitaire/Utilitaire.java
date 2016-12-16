package utilitaire;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import business.Person;

/**
 * Created by dimitri.mella on 07.12.2016.
 */

public class Utilitaire implements Serializable {

    public static Long nextId = new Long(0);
    public static List<Person> people = new ArrayList<>();

    public static Date convertStringToDate(String date) {
        DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        try {
            return formatter.parse(date);
        } catch (ParseException e) {

            e.printStackTrace();
        }
        return null;
    }

    public static String convertDateToString(Date date){
        Format formatter;
        formatter = new SimpleDateFormat("dd.MM.yyyy");
        return formatter.format(date);
    }

    public static Long getNextId(){
        nextId++;
        return nextId;
    }
}
