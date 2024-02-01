import java.time.LocalDateTime;

public class DateTimePrinter {

    private String[] dates = new String[] {
            "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
    };
    public String print(LocalDateTime dateTime) {
        int day = dateTime.getDayOfMonth();
        int month = dateTime.getMonthValue();
        int year = dateTime.getYear();
        int hour = dateTime.getHour();
        int minute = dateTime.getMinute();
        String suffix = "AM";
        if (hour >= 12) {
            suffix = "PM";
            hour -= 12;
        }
        if (hour == 0) {
            hour = 12;
        }
        return day + " " + dates[month - 1] + " " + year + " " + hour + ":" + minute + " " + suffix;
    }
}
