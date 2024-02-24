package bmo.util;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.IOException;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import bmo.command.Command;
import bmo.command.DefaultCommand;
import bmo.command.DoneCommand;
import bmo.command.DueCommand;
import bmo.command.EventCommand;
import bmo.command.ExitCommand;
import bmo.command.GreetCommand;
import bmo.command.ToDoCommand;

class ParserTest {

    @Test
    void testParse() throws IOException {
        // Test parsing a "hi" command
        Command hiCommand = Parser.parse("hi");
        assertInstanceOf(GreetCommand.class, hiCommand, "Expected GreetCommand for 'hi'");

        // Test parsing a "bye" command
        Command byeCommand = Parser.parse("bye");
        assertInstanceOf(ExitCommand.class, byeCommand, "Expected ExitCommand for 'bye'");

        // Test parsing a "done" command with an index
        Command doneCommand = Parser.parse("done  1");
        assertInstanceOf(DoneCommand.class, doneCommand, "Expected DoneCommand for 'done  1'");

        // Test parsing a "todo" command with a description
        Command todoCommand = Parser.parse("todo task");
        assertInstanceOf(ToDoCommand.class, todoCommand, "Expected ToDoCommand for 'todo task'");

        // Test parsing a "due" command with a description and due date
        Command dueCommand = Parser.parse("due task /by 01/01/2024 1000");
        assertInstanceOf(DueCommand.class, dueCommand, "Expected DueCommand for 'due task /by 01/01/2024 1000'");

        // Test parsing an "event" command with a description, start, and end dates
        Command eventCommand = Parser.parse("event event /from 01/01/2024 1000 /to 02/01/2024 1000");
        assertInstanceOf(EventCommand.class, eventCommand, "Expected EventCommand for 'event event /from "
                + "01/01/2024 1000 /to 02/01/2024 1000'");

        // Test parsing an "invalid" command
        Command invalidCommand = Parser.parse("invalid");
        assertInstanceOf(DefaultCommand.class, invalidCommand, "Expected DefaultCommand for 'invalid'");
    }

    @Test
    void testParseDateTime() {
        LocalDateTime dateTime = Parser.formatDateTime("01/01/2024 1000");
        assertNotNull(dateTime, "Expected non-null LocalDateTime for valid input");

        dateTime = Parser.formatDateTime("invalid date");
        assertNull(dateTime, "Expected null LocalDateTime for invalid input");
    }
}

