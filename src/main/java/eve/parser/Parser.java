package eve.parser;

import java.time.LocalDateTime;

public class Parser {
    public static LocalDateTime stringToDateTime(String s) {
        String[] temp = s.split(" ");
        int hour = Integer.parseInt(temp[1].substring(0, 2));
        int min = Integer.parseInt(temp[1].substring(2));
        String[] dateTemp = temp[0].split("/");
        int day = Integer.parseInt(dateTemp[0]);
        int month = Integer.parseInt(dateTemp[1]);
        int year = Integer.parseInt(dateTemp[2]);

        LocalDateTime newDateTime = LocalDateTime.of(year, month, day, hour, min);

        return newDateTime;
    }
}
