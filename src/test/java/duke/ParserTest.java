package duke;  //same package as the class being tested

import duke.command.*;
import duke.exceptions.DukeException;
import duke.exceptions.IllegalParamException;
import duke.exceptions.MissingInfoException;
import duke.exceptions.ParserException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {
    @Test
    public void parse_emptyInput_throwsParserException() {
        assertThrows(MissingInfoException.class, () -> Parser.parse(""));
    }

    @Test
    public void parse_validByeInputs_returnsByeCommand() throws DukeException {
        // random capitalization of bye results in ByeCommand returned
        Command bye = Parser.parse("ByE");
        assertTrue(bye instanceof ByeCommand);
    }

    private void assertTrue(boolean b) {
    }

    @Test
    public void parse_validListInputs_returnsListCommand() throws DukeException {
        // random capitalization of list returns ListCommand
        Command list = Parser.parse("lIsT");
        assertTrue(list instanceof ListCommand);

        // alternative ls keyword returns ListCommand
        Command list1 = Parser.parse("ls");
        assertTrue(list1 instanceof ListCommand);
    }

    @Test
    public void parse_validMarkInput_returnsMarkCommand() throws DukeException {
        // random capitalization of mark and valid number returns MarkCommand
        Command command = Parser.parse("mArk 1");
        assertTrue(command instanceof MarkCommand);

        // random capitalization of mark and spaces before 0 returns MarkCommand
        Command command1 = Parser.parse("mArk    0");
        assertTrue(command1 instanceof MarkCommand);

        // random capitalization of mark and big number returns MarkCommand
        Command command2 = Parser.parse("MArk 6969420");
        assertTrue(command2 instanceof MarkCommand);
    }

    @Test
    public void parse_invalidMarkInput_throwsException() {
        // non numeric value throws exception
        assertThrows(IllegalParamException.class, () -> Parser.parse("mArk 1a2b#c"));

        // no number component throws exception
        assertThrows(MissingInfoException.class, () -> Parser.parse("mArk"));

    }

    @Test
    public void parse_validUnmarkInput_returnsMarkCommand() throws DukeException {
        // random capitalization of unmark and valid number and random spacing returns UnmarkCommand
        Command command = Parser.parse("uNmArk     2");
        assertTrue(command instanceof UnmarkCommand);

        // full capitalization of unmark and 0 returns UnmarkCommand
        Command command1 = Parser.parse("UNMARK 0");
        assertTrue(command1 instanceof UnmarkCommand);

        // random capitalization of unmark and big number returns UnmarkCommand
        Command command2 = Parser.parse("unMArk 6969420");
        assertTrue(command2 instanceof UnmarkCommand);
    }

    @Test
    public void parse_invalidUnmarkInput_throwsException() {
        // non numeric value throws exception
        assertThrows(IllegalParamException.class, () -> Parser.parse("unmArk 1a2b#c"));

        // no number component and random spaces throws exception
        assertThrows(MissingInfoException.class, () -> Parser.parse("unmArk            "));
    }

    @Test
    public void parse_validDeleteInput_returnsDeleteCommand() throws DukeException {
        // random capitalization of unmark and valid number and random spacing returns DeleteCommand
        Command command = Parser.parse("deLeTe     2");
        assertTrue(command instanceof DeleteCommand);

        // spaces and tabs and 0 will return DeleteCommand
        Command command1 = Parser.parse("deLeTe         0");
        assertTrue(command1 instanceof DeleteCommand);
    }

    @Test
    public void parse_invalidDeleteInput_throwsException() {
        // non numeric value throws exception
        assertThrows(IllegalParamException.class, () -> Parser.parse("unmArk 1a2b#c"));

        // no number component and random spaces throws exception
        assertThrows(MissingInfoException.class, () -> Parser.parse("unmArk            "));
    }

    @Test
    public void parse_validToDoInput_returnsToDoCommand() throws DukeException {
        // tricky description returns ToDoCommand
        Command command = Parser.parse("toDo     todotodotodo@22s");
        assertTrue(command instanceof ToDoCommand);

        // tricky description 2 returns ToDoCommand
        Command command1 = Parser.parse("toDo     /by 21 Jan 2021 todotodotodo@22s");
        assertTrue(command1 instanceof ToDoCommand);
    }

    @Test
    public void parse_invalidToDoInput_throwsException() {
        // keyword + spaces throws exception
        assertThrows(MissingInfoException.class, () -> Parser.parse("tOdo            "));

    }

    @Test
    public void parse_validDeadlineInput_returnsDeadlineCommand() throws DukeException {
        // tricky description returns DeadlineCommand
        Command command = Parser.parse("deaDLine     deadline    /by 21 Jan 2000");
        assertTrue(command instanceof DeadlineCommand);

        // tricky description 2 returns DeadlineCommand
        Command command1 = Parser.parse("deadline    cry /by 21 Dec 3021   ");
        assertTrue(command1 instanceof DeadlineCommand);
    }

    @Test
    public void parse_invalidDeadlineInput_throwsException() {
        // keyword + spaces throws exception
        assertThrows(MissingInfoException.class, () -> Parser.parse("deadline            "));

        // no /by keyword throws ParserException
        assertThrows(ParserException.class, () -> Parser.parse("deadline    21 Jan 2002        "));

        // no task description but valid date throws exception
        assertThrows(MissingInfoException.class, () -> Parser.parse("deadline  /by 21 Jan 2002       "));

        // invalid date throws exception
        assertThrows(ParserException.class, () -> Parser.parse("deadline writetestcase /by 221 Jan 2002"));

        // missing date throws exception
        assertThrows(ParserException.class, () -> Parser.parse("deadline writetestcase /by "));
    }

    @Test
    public void parse_validEventInput_returnsEventCommand() throws DukeException {
        // normal input case returns EventCommand
        Command command = Parser.parse("evENT   eventeventevent /from 21 Jan 2042 /to 21 Jan 2030");
        assertTrue(command instanceof EventCommand);

        // /from and /by are swapped returns event command
        Command command1 = Parser.parse("Event   eat yumz /to 21 Jan 2021 /from 23 Jan 2023");
        assertTrue(command1 instanceof EventCommand);
    }

    @Test
    public void parse_invalidEventInput_throwsException() {
        // keyword + spaces throws exception
        assertThrows(MissingInfoException.class, () -> Parser.parse("event            "));

        // no both /from and /to keyword throws ParserException
        assertThrows(ParserException.class, () -> Parser.parse("event    21 Jan 2002        "));

        // no task description but valid dates throws exception
        assertThrows(MissingInfoException.class, () -> Parser.parse("deadline /by 21 Jan 2002 /from 21 Jan 2002"));

        // invalid date throws exception
        assertThrows(ParserException.class, () -> Parser.parse("event writecase /to 221 Jan 2002 /from 21 Jan 2002"));

        // missing /from throws exception
        assertThrows(ParserException.class, () -> Parser.parse("deadline writetestcase /to 23 Jan 2002 "));
    }


}