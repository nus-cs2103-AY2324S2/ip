import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Testing {
    public static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy HHmm");
        LocalDateTime ldt = LocalDateTime.parse("27 08 2001 1800", formatter);
    }
}
