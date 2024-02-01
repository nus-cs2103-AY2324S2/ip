package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void dateParsing_dateParsedCorrectlyForSaving(){
        Event event = new Event("test description", "2024-02-01 1800", "2024-02-01 1900");
        assertEquals(event.fileSavingString(), "E | 0 | test description | 2024-02-01 1800 | 2024-02-01 1900");
    }

    @Test
    public void dateParsing_dateParsedCorrectlyForPrinting(){
        Event event = new Event("test description", "2024-02-01 1800", "2024-02-01 1900");
        assertEquals(event.toString(), "[E][ ] test description (from: Feb 01 2024 1800 to: Feb 01 2024 1900)");
    }
}





