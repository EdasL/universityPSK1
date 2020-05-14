package services;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeService {

    public String getDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return formatter.format(date);
    }

}
