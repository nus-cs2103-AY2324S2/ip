package Martin;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.time.format.DateTimeParseException;

/**
 * Represents a command that can be executed by the chatbot.
 * Each command is associated with a specific keyword and performs a specific action.
 */
/**
 * Represents a command that can be executed by the chatbot.
 * Each command is associated with a specific keyword and performs
 * a corresponding action on the task list.
 */
public class Command {
    private TaskList taskList;
    private Storage storage;
    private Ui ui;
    private Parser parser;

    /**
     * Constructs a Command object with the specified dependencies.
     *
     * @param taskList The task list to be operated on.
     * @param storage  The storage to save and load task data.
     * @param ui       The user interface for displaying messages.
     * @param parser   The parser for parsing user input.
     */
    public Command(TaskList taskList, Storage storage, Ui ui, Parser parser) {
        this.taskList = taskList;
        this.storage = storage;
        this.ui = ui;
        this.parser = parser;
    }

    /**
     * Handles the specified command by performing the corresponding action.
     *
     * @param command        The keyword representing the command.
     * @param remainingWords The remaining words in the user input.
     * @throws IOException If an I/O error occurs while accessing the storage.
     */
    public String handleCommand(ChatbotKeyword command, String remainingWords) throws IOException {
        String response = "";
        switch (command) {
            case LIST:
                response = taskList.printList();
                break;
            case MARK:
                int doneIndex = Integer.parseInt(remainingWords);
                response = taskList.markAsDone(doneIndex);
                storage.rewriteFile(taskList);
                break;
            case UNMARK:
                int undoneIndex = Integer.parseInt(remainingWords);
                response = taskList.unmarkAsDone(undoneIndex);
                storage.rewriteFile(taskList);
                break;
            case DELETE:
                int deleteIndex = Integer.parseInt(remainingWords);
                Task removed = taskList.remove(deleteIndex);
                response = ui.sayDeleted(removed);
                storage.rewriteFile(taskList);
                break;
            case HI:
                response = ui.sayGreeting();
                break;
            case TODO:
                Todo todo = new Todo(remainingWords);
                response = taskList.add(todo);
                storage.appendToFile(todo.toFileString());
                break;
            case DEADLINE:
                String[] deadlineArray = parser.parseDeadline(remainingWords);
                LocalDate deadlineTime;
                try {
                    deadlineTime = LocalDate.parse(deadlineArray[1]);
                } catch (DateTimeParseException e) {
                    return "Invalid date format. Please use yyyy-mm-dd";
                }
                Deadline deadline = new Deadline(deadlineArray[0], deadlineTime);
                response = taskList.add(deadline);
                storage.appendToFile(deadline.toFileString());
                break;
            case EVENT:
                String[] eventArray = parser.parseEvent(remainingWords);
                Event event = new Event(eventArray[0], eventArray[1], eventArray[2]);
                response = taskList.add(event);
                storage.appendToFile(event.toFileString());
                break;
            case BYE:
                response = ui.sayBye();
                break;
            case FIND:
                ArrayList<Task> foundTasks = taskList.find(remainingWords);
                response = ui.printFoundTasks(foundTasks);
                break;
            case HELP:
                response = ui.sayHelp();
                break;
            default:
                response = "I'm sorry, but I don't know what that means :-(";
        }
        return response;
    }
}
