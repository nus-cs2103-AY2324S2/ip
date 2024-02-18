package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class ParserTest {

    @Test
    void getCommandWord_emptyInput() {
        Parser parser = new Parser("");
        assertThrows(DukeException.class, () -> parser.getCommandWord(),
            "Command cannot be empty.");
    }

    @Test
    void getCommandWord_spaceInput() {
        Parser parser = new Parser(" ");
        assertThrows(DukeException.class, () -> parser.getCommandWord(),
            "Command cannot be empty.");
    }

    @Test
    void getCommandWord_success() throws DukeException {
        Parser parser = new Parser("todo test");
        assertEquals(parser.getCommandWord(), "todo");
    }

    @Test
    void getDescription_emptyInput() {
        Parser parser = new Parser("todo");
        assertThrows(DukeException.class, () -> parser.getDescription(),
            "The description of a task cannot be empty. \n\t"
                + "Please use the following format: description <description>");
    }

    @Test
    void getDescription_success() throws DukeException {
        Parser parser = new Parser("todo test");
        assertEquals(parser.getDescription(), "test");
    }

    @Test
    void getBy_wrongByFormat() {
        Parser parser = new Parser("deadline test by 23-12-2032");
        assertThrows(DukeException.class, () -> parser.getBy(),
            "Invalid command for deadline. \n\t"
                + "Please use the following format: deadline <description> /by <dd-mm-yyyy>");
    }

    @Test
    void getBy_wrongDateFormat() {
        Parser parser = new Parser("deadline test /by 2032/12/13");
        assertThrows(java.time.format.DateTimeParseException.class, () -> parser.getBy(),
            "Invalid date format. Please use dd-mm-yyyy");
    }

    @Test
    void getBy_emptyInput() {
        Parser parser = new Parser("deadline test /by");
        assertThrows(DukeException.class, () -> parser.getBy(),
            "The deadline and description for a task cannot be empty. \n\t"
                + "Please use the following format: deadline <description> /by <dd-mm-yyyy>");
    }

    @Test
    void getBy_multipleBy() {
        Parser parser = new Parser("deadline test /by 23-12-2032 /by 2-2-2022");
        assertThrows(DukeException.class, () -> parser.getBy(),
            "Invalid command for deadline. \n\t"
                + "Please use the following format: deadline <description> /by <dd-mm-yyyy>");
    }

    @Test
    void getBy_success() throws DukeException {
        Parser parser = new Parser("deadline test /by 23-12-2032 0000");
        assertEquals(parser.getBy(), Task.getLocalDateTimeInput("23-12-2032 0000"));
    }


}
