package campus.infrastructure;

import campus.exceptions.CampusException;
import org.junit.jupiter.api.Test;

import java.util.Objects;

public class ParserTest {
    @Test
    public void parseMessageTest() {
        Ui ui = new Ui();
        TaskList taskList = new TaskList();
        Storage storage = new Storage("src/main/java/campus/dataTest.txt", true);
        Parser parser = new Parser(ui, taskList, storage);

        String msg = "help";
        String res;
        res = parser.parseMessage(msg);
        String message = "CampusBot's Full Command List\n"
                + "The following commands are used to manipulate entries in the list of tasks:\n"
                + "'mark' - marks a particular task as complete\n"
                + "'unmark' - unmarks a particular task as incomplete\n"
                + "'delete' - deletes a particular task\n"
                + "The following commands are used to create a new entry in the list of tasks:\n"
                + "'todo' - creates a new todo task in the list\n"
                + "'deadline' - creates a new deadline task in the list\n"
                + "'event' - creates a new event task in the list\n"
                + "The following commands are admin related commands used to navigate the ChatBot\n"
                + "'list' - displays the current list of tasks\n"
                + "'find' - searches for a task in the list by the keyword that proceeds the find command\n"
                + "'help' - displays the help page, the one you are seeing now :)\n"
                + "'bye' - exits the ChatBot\n";
        assert(Objects.equals(res, message));

        msg = "asdf";
        res = parser.parseMessage(msg);
        message = "Sorry, I don't understand that command, please check for potential spelling errors\n";
        assert(Objects.equals(res, message));

        msg = "list";
        res = parser.parseMessage(msg);
        message = "\n";
        assert(Objects.equals(res, message));
    }

    @Test
    public void handleTodoTest() {
        Ui ui = new Ui();
        TaskList taskList = new TaskList();
        Storage storage = new Storage("src/main/java/campus/dataTest.txt", true);
        Parser parser = new Parser(ui, taskList, storage);

        String res = parser.parseMessage("todo eat");
        String message = "Your directive has been duly noted. The tas has been successfully expunged fromour records. Should there be any further matters requiring attention or if new tasksarise, do not hesitate to relay them, and they shall be handled with utmost careand efficiency.\n"
            + "added: [T] [ ] eat\n"
            + "Now you have 1 task(s) in the list.\n";
        assert(Objects.equals(res, message));
    }

    @Test
    public void handleTodoTest2() {
        Ui ui = new Ui();
        TaskList taskList = new TaskList();
        Storage storage = new Storage("src/main/java/campus/dataTest.txt", true);
        Parser parser = new Parser(ui, taskList, storage);

        String res = parser.parseMessage("todo");
        String message = "Error! A todo task must have a name, please follow the following syntax: todo <task name>\n\n";
        System.out.println(res);
        assert(Objects.equals(res, message));
    }

    @Test
    public void handleDeadlineTest() {
        Ui ui = new Ui();
        TaskList taskList = new TaskList();
        Storage storage = new Storage("src/main/java/campus/dataTest.txt", true);
        Parser parser = new Parser(ui, taskList, storage);

        String res = parser.parseMessage("deadline finish work /by 2359 08/02/2001");
        String message = "Your directive has been duly noted. The tas has been successfully expunged fromour records. Should there be any further matters requiring attention or if new tasksarise, do not hesitate to relay them, and they shall be handled with utmost careand efficiency.\n"
                + "added: [D] [ ] finish work (by: 2359 08/02/2001)\n"
                + "Now you have 1 task(s) in the list.\n";
        assert(Objects.equals(res, message));
    }

    @Test
    public void handleDeadlineTest2() {
        Ui ui = new Ui();
        TaskList taskList = new TaskList();
        Storage storage = new Storage("src/main/java/campus/dataTest.txt", true);
        Parser parser = new Parser(ui, taskList, storage);

        String res = parser.parseMessage("deadline");
        String message = "Error! A deadline task must have the correct number of parameters, "
                + "please follow the following syntax: deadline <deadline name> "
                + "/by <endDateTime (HHmm dd/MM/yyyy)>\n\n";
        assert(Objects.equals(res, message));
    }

    @Test
    public void handleEventTest() {
        Ui ui = new Ui();
        TaskList taskList = new TaskList();
        Storage storage = new Storage("src/main/java/campus/dataTest.txt", true);
        Parser parser = new Parser(ui, taskList, storage);

        String res = parser.parseMessage("event floor hotpot /from 1900 08/02/2001 /to 2200 08/02/2001");
        String message = "Your directive has been duly noted. The tas has been successfully expunged fromour records. Should there be any further matters requiring attention or if new tasksarise, do not hesitate to relay them, and they shall be handled with utmost careand efficiency.\n"
                + "added: [E] [ ] floor hotpot (from: 1900 08/02/2001 to: 2200 08/02/2001)\n"
                + "Now you have 1 task(s) in the list.\n";
        System.out.println(res);
        assert(Objects.equals(res, message));
    }

    @Test
    public void handleEventTest2() {
        Ui ui = new Ui();
        TaskList taskList = new TaskList();
        Storage storage = new Storage("src/main/java/campus/dataTest.txt", true);
        Parser parser = new Parser(ui, taskList, storage);

        String res = parser.parseMessage("event");
        String message = "Error! An event task must have the correct number of parameters, "
                + "please follow the following syntax: event <event name> "
                + "/from <startDateTime (HHmm dd/MM/yyyy)> /to <endDateTime (HHmm dd/MM/yyyy)>\n\n";
        System.out.println(res);
        assert(Objects.equals(res, message));
    }

    @Test
    public void markUnmarkTest() {
        Ui ui = new Ui();
        TaskList taskList = new TaskList();
        Storage storage = new Storage("src/main/java/campus/dataTest.txt", true);
        Parser parser = new Parser(ui, taskList, storage);

        String res = parser.parseMessage("todo eat");
        String message = "Your directive has been duly noted. The task has been successfully expunged from our records. Should there be any further matters requiring attention or if new tasks arise, do not hesitate to relay them, and they shall be handled with utmost care and efficiency.\n"
                + "added: [T] [ ] eat\n"
                + "Now you have 1 task(s) in the list.\n";
        assert(Objects.equals(res, message));

        res = parser.parseMessage("mark 1");
        message = "Splendid news! Your accomplishment is a testament to your diligence and skill. Should you require further assistance or have additional tasks to undertake, do not hesitate to summon me.\n"
            + "[T] [X] eat\n";
        assert(Objects.equals(res, message));

        res = parser.parseMessage("unmark 1");
        message = "Splendid news! Your accomplishment is a testament to your diligence and skill. Should you require further assistance or have additional tasks to undertake, do not hesitate to summon me.\n"
                + "[T] [ ] eat\n";
        assert(Objects.equals(res, message));
    }
}
