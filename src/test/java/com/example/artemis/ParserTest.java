package com.example.artemis;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
public class ParserTest {
    @Test
    public void testHandleTodoTask() {
        // Arrange
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("./data/test.txt");
        Parser parser = new Parser();

        try {
            parser.parseInput("todo", tasks, ui, storage);
        } catch (IndexOutOfBoundsException | ArtemisException e) {
            String expectedMessage = "OOPS!!! The description of a todo cannot be empty.";
            assertEquals(expectedMessage, e.getMessage());
        }
    }

    @Test
    public void testHandleMarkAsDone() {
        // Arrange
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("./data/test.txt");
        Parser parser = new Parser();

        try {
            parser.parseInput("mark 5", tasks, ui, storage);
        } catch (NumberFormatException | IndexOutOfBoundsException | ArtemisException e) {
            String expectedMessage = "OOPS!!! Please provide a valid task number.";
            assertEquals(expectedMessage, e.getMessage());
        }
    }
}
