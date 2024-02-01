package duke;
import java.io.IOException;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class test {
    public static void main(String[] args) throws IOException {
        timetest();
    }

    public static void timetest() {
        DateTimeFormatter DATEFORMAT = DateTimeFormatter.ofPattern("d-M-yy");
        String s = "1-2-24";
        LocalDate l = LocalDate.parse(s, DATEFORMAT);
        String a = l.format(DateTimeFormatter.ofPattern("MMM-d-yy"));
        System.out.println(l);
        System.out.println(a);
    }
}
