package paimon.parser;

import paimon.exception.ChatBotParameterException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class ParserTest {

    @Test
    void parseDateTime_normalFormat_success() throws ChatBotParameterException {
        assertEquals(LocalDateTime.of(2024,1,30,12,1),
                Parser.parseDateTime("30/1/2024 1201"));
    }

    @Test
    void parseDateTime_zeroMonthFormat_success() throws ChatBotParameterException {
        assertEquals(LocalDateTime.of(2024,1,30,12,1),
                Parser.parseDateTime("30/01/2024 1201"));
    }

    @Test
    void parseDateTime_zeroDayFormat_success() throws ChatBotParameterException {
        assertEquals(LocalDateTime.of(2024,1,1,12,1),
                Parser.parseDateTime("01/1/2024 1201"));
    }

    @Test
    void parseDateTime_storageDateTime_success() throws ChatBotParameterException {
        assertEquals(LocalDateTime.of(2024,2,2,6,0),
                Parser.parseDateTime("2024-02-02T06:00"));
    }

    @Test
    void parseDateTime_noTime_exceptionThrown() {
        try {
            assertEquals(LocalDateTime.of(2024,1,1,12,0),
                    Parser.parseDateTime("1/1/2024"));
            fail();
        } catch (ChatBotParameterException e) {
            assertEquals("Invalid Date Time format, try d/M/yyy Hmm", e.getMessage());
        }
    }

    @Test
    void parseInteger_normalInt_success() throws ChatBotParameterException {
        assertEquals(1, Parser.parseInteger("1"));

        assertEquals(-1, Parser.parseInteger("-1"));

        assertEquals(1, Parser.parseInteger("01"));
    }

    @Test
    void parseInteger_invalidString_exceptionThrown() {
        try {
            assertEquals(1234, Parser.parseInteger("123-4"));
            fail();
        } catch (ChatBotParameterException e) {
            assertEquals("Invalid task number \n" +
                    "try: mark/unmark/delete <task_number>", e.getMessage());
        }
    }
}