package Commands;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import Duke.Commands.DateFormat;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class DateFormatTest {
    @Test
    public void testFormat() {
        // Arrange
        String dateStr = "2022-02-22";

        // Act
        LocalDate formattedDate = DateFormat.format(dateStr);

        // Assert
        assertEquals(LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd")), formattedDate);
    }

    @Test
    public void testCompareDate() {
        // Arrange
        LocalDate date1 = LocalDate.parse("2022-02-22");
        LocalDate date2 = LocalDate.parse("2022-02-23");

        // Act
        boolean result = DateFormat.compareDate(date1, date2);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testReformatDate() {
        // Arrange
        LocalDate date = LocalDate.parse("2022-02-22");

        // Act
        String reformattedDate = DateFormat.reformatDate(date);

        // Assert
        assertEquals(date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")), reformattedDate);
    }

}
