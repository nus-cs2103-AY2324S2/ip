package checkbot.task;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class TimeStringTest {
    @Test
    public void parse_success() {
        final String[] dateStrings = {"01-02-2024",
                                      "1-2-2024",
                                      "1/2/2024",
                                      "01/02/2024",
                                      "1 Feb 2024",
                                      "1 February 2024",
                                      "01 Feb 2024",
                                      "01 February 2024",
                                      "Feb 1 2024",
                                      "February 1 2024",
                                      "Feb 01 2024",
                                      "February 01 2024"};

        LocalDate fixedDate = LocalDate.of(2024, 2, 1);

        for (String date : dateStrings) {
            TimeString dateTime = new TimeString(date);
            assertTrue(dateTime.timeEquals(fixedDate));
        }
    }

    @Test
    public void parse_fail() {
        final String[] dateStrings = {"invalid",
                                      "30 23 2303",
                                      "2 f 24"};

        for (String date : dateStrings) {
            TimeString dateTime = new TimeString(date);
            assertTrue(dateTime.timeEquals(null));
        }
    }
}
