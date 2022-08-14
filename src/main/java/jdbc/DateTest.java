package jdbc;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;

public class DateTest {
    public static void main(String[] args) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.add(Calendar.MONTH,0);
//        calendar.set(Calendar.DAY_OF_MONTH,1);
//        Date time = calendar.getTime();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now.getDayOfMonth());
        LocalDateTime firstDayOfMonth = LocalDateTime.of(LocalDate.from(LocalDateTime.now().with(TemporalAdjusters.firstDayOfMonth())), LocalTime.MIN);
        System.out.println(firstDayOfMonth.format(dateFormat));
        for(int i = 0;i<31;i++) {

            System.out.println(firstDayOfMonth.format(dateFormat));
            firstDayOfMonth = firstDayOfMonth.plusDays(1);
        }
        System.out.println(firstDayOfMonth);

    }
}
