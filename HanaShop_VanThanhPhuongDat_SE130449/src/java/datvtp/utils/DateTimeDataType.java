package datvtp.utils;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeDataType implements Serializable {
    public static String getTimeNow() {
        Date date = new Date();
        SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timeStr = spf.format(date);

        return timeStr;
    }
    
    public static String getDateFuture() {
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, 10);
        date = c.getTime();
        SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd");
        String timeStr = spf.format(date);

        return timeStr;
    }
    
    public static String getDatePast() {
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, -10);
        date = c.getTime();
        SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd");
        String timeStr = spf.format(date);

        return timeStr;
    }
}
