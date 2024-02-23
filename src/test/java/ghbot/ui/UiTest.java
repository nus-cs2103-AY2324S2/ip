package ghbot.ui;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.DateTimeException;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

import ghbot.exception.UiException;

/**
 * UiTest Class.
 */
class UiTest {

    @Test
    public void validateInput_validTodoInstruction_success() throws UiException {
        Ui todo = new Ui("todo gym");
        String[] todoArr = new String[2];
        todoArr[0] = "todo";
        todoArr[1] = "gym";
        assertArrayEquals(todoArr, todo.validateInput());
    }

    @Test
    public void validateInput_validDeadlineInstruction_success() throws UiException {
        Ui deadline = new Ui("deadline assignment /by 2024-03-23 0900");
        String[] deadlineArr = new String[2];
        deadlineArr[0] = "deadline";
        deadlineArr[1] = "assignment /by 2024-03-23 0900";
        assertArrayEquals(deadlineArr, deadline.validateInput());
    }

    @Test
    public void validateInput_validEventInstruction_success() throws UiException {
        Ui event = new Ui("event project meeting /from 2024-03-24 0700 /to 2024-03-24 0900");
        String[] eventArr = new String[2];
        eventArr[0] = "event";
        eventArr[1] = "project meeting /from 2024-03-24 0700 /to 2024-03-24 0900";
        assertArrayEquals(eventArr, event.validateInput());
    }

    @Test
    public void checkEmptyInstruction_emptyInstruction_uiExceptionThrown() {
        Ui ui = new Ui("");
        UiException uiException = assertThrows(UiException.class, () -> {
            ui.validateInput();
        });
        assertEquals("Helloooo!! please type something and follow the format below!!\n"
                + "todo 'task description'\n"
                + "deadline 'task description' /by 'time'\n"
                + "event 'task description' /from 'start time' /to 'end time'", uiException.getMessage());
    }

    @Test
    public void checkInvalidInstruction_invalidInstruction_uiExceptionThrown() {
        Ui ui = new Ui("add");
        UiException uiException = assertThrows(UiException.class, ()-> {
            ui.validateInput();
        });
        assertEquals("Sorry! I don't get the instruction!\n"
                    + "please use either todo, deadline, event, list, mark, "
                    + "unmark, bye, find or delete!", uiException.getMessage());
    }

    @Test
    public void checkMissingDescription_todoInstruction_uiExceptionThrown() {
        Ui ui = new Ui("todo");
        UiException uiException = assertThrows(UiException.class, ()-> {
            ui.validateInput();
        });
        assertEquals("Sorry! You need to include a description for your task!", uiException.getMessage());
    }

    @Test
    public void checkMissingDescription_deadlineInstruction_uiExceptionThrown() {
        Ui ui = new Ui("deadline");
        UiException uiException = assertThrows(UiException.class, ()-> {
            ui.validateInput();
        });
        assertEquals("Sorry! You need to include a description and deadline for your task!", uiException.getMessage());

    }

    @Test
    public void checkMissingDescription_eventInstruction_uiExceptionThrown() {
        Ui ui = new Ui("event");
        UiException uiException = assertThrows(UiException.class, ()-> {
            ui.validateInput();
        });
        assertEquals("Sorry! You need to include a description, "
                    + "start time and end time for your task!", uiException.getMessage());
    }

    @Test
    public void checkMissingDescription_markInstruction_uiExceptionThrown() {
        Ui ui = new Ui("mark");
        UiException uiException = assertThrows(UiException.class, ()-> {
            ui.validateInput();
        });
        assertEquals("Sorry! You need to include a number to mark your task!", uiException.getMessage());
    }

    @Test
    public void checkMissingDescription_unmarkInstruction_uiExceptionThrown() {
        Ui ui = new Ui("unmark");
        UiException uiException = assertThrows(UiException.class, ()-> {
            ui.validateInput();
        });
        assertEquals("Sorry! You need to include a number to unmark your task!", uiException.getMessage());
    }

    @Test
    public void checkMissingDescription_deleteInstruction_uiExceptionThrown() {
        Ui ui = new Ui("delete");
        UiException uiException = assertThrows(UiException.class, ()-> {
            ui.validateInput();
        });
        assertEquals("Sorry! You need to include a number to delete your task!", uiException.getMessage());
    }

    @Test
    public void checkMissingDescription_findInstruction_uiExceptionThrown() {
        Ui ui = new Ui("find");
        UiException uiException = assertThrows(UiException.class, ()-> {
            ui.validateInput();
        });
        assertEquals("Sorry! You need to include a partial word"
                + " or keyword that you want to find!", uiException.getMessage());

    }

    @Test
    public void checkIntegerDescription_invalidMarkIntegerDescription_numberFormatExceptionThrown() {
        Ui ui = new Ui("mark gym");
        NumberFormatException numberFormatException = assertThrows(NumberFormatException.class, ()-> {
            ui.validateInput();
        });
        assertEquals("Sorry! Please key in a valid list number for the task!\n"
                + "E.g. mark 1, unmark 2, delete 10", numberFormatException.getMessage());

    }

    @Test
    public void checkIntegerDescription_invalidUnmarkIntegerDescription_numberFormatExceptionThrown() {
        Ui ui = new Ui("unmark gym");
        NumberFormatException numberFormatException = assertThrows(NumberFormatException.class, ()-> {
            ui.validateInput();
        });
        assertEquals("Sorry! Please key in a valid list number for the task!\n"
                + "E.g. mark 1, unmark 2, delete 10", numberFormatException.getMessage());

    }

    @Test
    public void checkIntegerDescription_invalidDeleteIntegerDescription_numberFormatExceptionThrown() {
        Ui ui = new Ui("delete gym");
        NumberFormatException numberFormatException = assertThrows(NumberFormatException.class, ()-> {
            ui.validateInput();
        });
        assertEquals("Sorry! Please key in a valid list number for the task!\n"
                + "E.g. mark 1, unmark 2, delete 10", numberFormatException.getMessage());

    }

    @Test
    public void additionalCheckForDeadlineTask_wrongFormat_uiExceptionThrown() {
        Ui ui = new Ui("deadline wrong format");
        UiException uiException = assertThrows(UiException.class, ()-> {
            ui.validateInput();
        });
        assertEquals("Sorry! Please follow the following format! \n"
                + "deadline 'task description' /by 'time'", uiException.getMessage());
    }

    @Test
    public void checkDateTimeForDeadlineTask_wrongDateTimeFormat_uiExceptionThrown() {
        Ui ui = new Ui("deadline assignment /by 2359");
        DateTimeParseException dateTimeParseException = assertThrows(DateTimeParseException.class, ()-> {
            ui.validateInput();
        });
        assertEquals("Your date/time format is wrong.\n"
                + "Please use yyyy-MM-dd HHmm format (e.g. 2024-01-01 1500).", dateTimeParseException.getMessage());
    }

    @Test
    public void additionalCheckForEventTask_wrongFormat_uiExceptionThrown() {
        Ui ui = new Ui("event wrong format");
        UiException uiException = assertThrows(UiException.class, ()-> {
            ui.validateInput();
        });
        assertEquals("Sorry! Please follow the following format! \n"
                + "event 'task description' /from 'start time' /to 'end time'", uiException.getMessage());
    }

    @Test
    public void additionalCheckForEventTask_secondWrongFormat_uiExceptionThrown() {
        Ui ui = new Ui("event wrong format /from 2030-12-23 0900 /to");
        UiException uiException = assertThrows(UiException.class, ()-> {
            ui.validateInput();
        });
        assertEquals("Sorry! Please follow the following format! \n"
                + "event 'task description' /from 'start time' /to 'end time'", uiException.getMessage());
    }

    @Test
    public void checkDateTimeForEventTask_wrongStartDateTime_dateTimeParseExceptionThrown() {
        Ui ui = new Ui("event assessment /from 2359 /to 2024-12-12 2359");
        DateTimeParseException dateTimeParseException = assertThrows(DateTimeParseException.class, ()-> {
            ui.validateInput();
        });
        assertEquals("Your start date/time format is wrong.\n"
                + "Please use yyyy-MM-dd HHmm format (e.g. 2024-01-01 1500).", dateTimeParseException.getMessage());
    }
    @Test
    public void checkDateTimeForEventTask_wrongEndDateTime_dateTimeParseExceptionThrown() {
        Ui ui = new Ui("event assessment /from 2024-12-23 2359 /to 2359");
        DateTimeParseException dateTimeParseException = assertThrows(DateTimeParseException.class, ()-> {
            ui.validateInput();
        });
        assertEquals("Your end date/time format is wrong.\n"
                    + "Please use yyyy-MM-dd HHmm format (e.g. 2024-01-01 1500).", dateTimeParseException.getMessage());
    }

    @Test
    public void checkDateTimeForEventTask_startLaterThanEndDateTime_dateTimeExceptionThrown() {
        Ui ui = new Ui("event assessment /from 2024-12-23 2359 /to 2024-12-12 2359");
        DateTimeException dateTimeException = assertThrows(DateTimeException.class, ()-> {
            ui.validateInput();
        });
        assertEquals("Sorry! Your start time should be earlier than your end time.", dateTimeException.getMessage());
    }
}
