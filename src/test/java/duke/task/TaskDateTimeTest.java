package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaskDateTimeTest {
    @Test
    public void parse_ValidDateTime_ReturnsTaskDateTime() throws DukeDateTimeParseException {
        // Arrange
        String dateTimeString = "2020-08-25 18:00";

        // Act
        TaskDateTime dateTime = new TaskDateTime(dateTimeString);

        // Assert
        assertEquals("Tue, Aug 25 2020 18:00", dateTime.toString());
    }

    @Test
    public void parse_InvalidDateTime_ThrowsDukeDateTimeParseException() {
        // Arrange
        String dateTimeString = "2020-08-25 18:00:00";

        // Act & Assert
        assertThrows(DukeDateTimeParseException.class, () -> new TaskDateTime(dateTimeString));
    }

    @Test
    public void serialize_ValidTaskDateTime_ReturnsSerializedString() throws DukeDateTimeParseException {
        // Arrange
        TaskDateTime dateTime = new TaskDateTime("2020-08-25 18:00");

        // Act
        String serializedString = dateTime.serialize();

        // Assert
        assertEquals("2020-08-25 18:00", serializedString);
    }
}
