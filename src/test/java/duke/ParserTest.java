package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    Parser parser = new Parser();
    Duke duke = new Duke();
    TaskList taskList = new TaskList();
    @Test
    public void listCommand_success() {
        parser.checkInput("todo Cook", this.duke, this.taskList);
        parser.checkInput("todo bake", this.duke, this.taskList);
        assertEquals("These are the tasks we currently have: \n"
                + "1. [T][] Cook\n" + "2. [T][] bake\n" + "We have 2 tasks.\n",
                parser.checkInput("list", this.duke, this.taskList));
    }

    @Test
    public void invalidCommand() {
        assertEquals("I don't understand :/"
                , parser.checkInput("bla", this.duke,this.taskList));
    }

    @Test
    public void findCommand_taskExist_success() {
        parser.checkInput("todo Cook", this.duke, this.taskList);
        assertEquals("1. [T][] Cook\n"
                        +"We have 1 matching tasks with the word Cook.\n",
                parser.checkInput("find Cook", this.duke, this.taskList));
    }

    @Test
    public void findCommand_taskNoExist_success() {
        parser.checkInput("todo Cook", this.duke, this.taskList);
        assertEquals("We have 0 matching tasks with the word aaa.\n",
                parser.checkInput("find aaa", this.duke, this.taskList));
    }

    @Test
    public void markCommand_success() {
        parser.checkInput("todo Cook", this.duke, this.taskList);
        assertEquals("We have completed this task!\n"
                        + "[T][X] Cook\n",
                parser.checkInput("mark 1", this.duke, this.taskList));
    }

    @Test
    public void markCommand_invalidNumber_exceptionThrown() {
        parser.checkInput("todo Cook", this.duke, this.taskList);
        parser.checkInput("todo bake", this.duke, this.taskList);
        assertEquals("You only have 2 tasks!\n"
                + "Select a number from 1 to 2.\n",
                parser.checkInput("mark 7", this.duke, this.taskList));
    }

    @Test
    public void markCommand_invalidFormat_exceptionThrown() {
        assertEquals("Please input a number.\n",
                parser.checkInput("mark abc", this.duke, this.taskList));
    }

    @Test
    public void unmarkCommand_success() {
        parser.checkInput("todo Cook", this.duke, this.taskList);
        assertEquals("Oops, task unmarked!\n" +
                "[T][] Cook\n",
                parser.checkInput("unmark 1", this.duke, this.taskList));
    }

    @Test
    public void unmarkCommand_invalidNumber_exceptionThrown() {
        parser.checkInput("todo Cook", this.duke, this.taskList);
        parser.checkInput("todo bake", this.duke, this.taskList);
        assertEquals("You only have 2 tasks!\n"
                        + "Select a number from 1 to 2.\n",
                parser.checkInput("unmark 7", this.duke, this.taskList));
    }

    @Test
    public void unmarkCommand_invalidFormat_exceptionThrown() {
        assertEquals("Please input a number.\n",
                parser.checkInput("unmark abc", this.duke, this.taskList));
    }

    @Test
    public void deleteCommand_success() {
        parser.checkInput("todo Cook", this.duke, this.taskList);
        assertEquals("Task has been deleted!\n" +
                        "[T][] Cook\n",
                parser.checkInput("delete 1", this.duke, this.taskList));
    }

    @Test
    public void deleteCommand_invalidNumber_exceptionThrown() {
        parser.checkInput("todo Cook", this.duke, this.taskList);
        parser.checkInput("todo bake", this.duke, this.taskList);
        assertEquals("You only have 2 tasks!\n"
                        + "Select a number from 1 to 2.\n",
                parser.checkInput("delete 7", this.duke, this.taskList));
    }

    @Test
    public void deleteCommand_invalidFormat_exceptionThrown() {
        assertEquals("Please input a number.\n",
                parser.checkInput("delete abc", this.duke, this.taskList));
    }

    @Test
    public void deleteCommand_missingArgument_exceptionThrown() {
        parser.checkInput("todo Cook", this.duke, this.taskList);
        parser.checkInput("todo Cook", this.duke, this.taskList);
        assertEquals("You only have 2 tasks!\n"
                        + "Select a number from 1 to 2.\n",
                parser.checkInput("delete", this.duke, this.taskList));
    }

    @Test
    public void deleteCommand_emptyList_success() {
        assertEquals("You have no task to delete!\n",
                parser.checkInput("delete", this.duke, this.taskList));
    }

    @Test
    public void todoCommand_success() {
        parser.checkInput("todo Cook", this.duke, this.taskList);
        assertEquals("New ToDo created!\n"
                        + "[T][] Cook\n",
                parser.checkInput("todo Cook", this.duke, taskList));
    }
    @Test
    public void todoCommand_missingDescription() {
        assertEquals("Please input a Task Description.\n",
                parser.checkInput("todo", this.duke, taskList));
    }

    @Test
    public void deadlineCommand_missingDate() {
        assertEquals("Please input a date with a / in front.\n",
                parser.checkInput("deadline cook", this.duke, taskList));
    }

    @Test
    public void deadlineCommand_invalidDate() {
        assertEquals("Invalid DateTime Format. Please input as follows:\n"
                        + "dd-mm-yyyy hh:mm\n",
                parser.checkInput("deadline cook /3feb2001", this.duke, taskList));
    }

    @Test
    public void eventCommand_missingDate() {
        assertEquals("Please input a start and end date with a / in front.\n",
                parser.checkInput("event cook", this.duke, taskList));
    }

    @Test
    public void eventCommand_invalidDate() {
        assertEquals("Invalid DateTime Format. Please input as follows:\n"
                        + "dd-mm-yyyy hh:mm\n",
                parser.checkInput("event cook /3feb2001 /4feb2001", this.duke, taskList));
    }
}
