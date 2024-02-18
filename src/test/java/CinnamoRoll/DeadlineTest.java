package CinnamoRoll;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

class DeadlineTest {
    @Test
    void toStringTest() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime time = LocalDateTime.parse("2024-02-05 14:00", format);
        Deadlines deadline = new Deadlines("MA5211 Assignment 1", time, true);
        assertEquals("[D][X] MA5211 Assignment 1 (by: Feb 05 2024 14:00)", deadline.toString(),
                "Testing toString method of deadline class");
    }
    @Test
    void getStatusIcon() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime time = LocalDateTime.parse("2024-02-05 14:00", format);
        Deadlines deadline1 = new Deadlines("MA5211 Assignment 1", time, true);
        Deadlines deadline2 = new Deadlines("MA5211 Assignment 1", time, false);
        assertEquals("[D][X]", deadline1.getStatusIcon(),
                "Testing whether deadline class marks the event correctly");
        assertEquals("[D][ ]", deadline2.getStatusIcon(),
                "Testing whether deadline class unmarks the event correctly");
    }
}
