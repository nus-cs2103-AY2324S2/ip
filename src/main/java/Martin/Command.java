package Martin;

import java.io.IOException;
import java.time.LocalDate;

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
     * @param storage The storage to save and load task data.
     * @param ui The user interface for displaying messages.
     * @param parser The parser for parsing user input.
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
     * @param command The keyword representing the command.
     * @param remainingWords The remaining words in the user input.
     * @throws IOException If an I/O error occurs while accessing the storage.
     */
    public void handleCommand(ChatbotKeyword command, String remainingWords) throws IOException {
        switch (command) {
            case LIST:
                taskList.printList();
                break;
            case MARK:
                int doneIndex = Integer.parseInt(remainingWords);
                taskList.markAsDone(doneIndex);
                storage.rewriteFile(taskList);
                break;
            case UNMARK:
                int undoneIndex = Integer.parseInt(remainingWords);
                taskList.unmarkAsDone(undoneIndex);
                storage.rewriteFile(taskList);
                break;
            case DELETE:
                int deleteIndex = Integer.parseInt(remainingWords);
                taskList.remove(deleteIndex);
                storage.rewriteFile(taskList);
                break;
            case TODO:
                Todo todo = new Todo(remainingWords);
                taskList.add(todo);
                storage.appendToFile(todo.toFileString());
                break;
            case DEADLINE:
                String[] deadlineArray = parser.deadlineParse(remainingWords);
                LocalDate deadlineTime = LocalDate.parse(deadlineArray[1]);
                Deadline deadline = new Deadline(deadlineArray[0], deadlineTime);
                taskList.add(deadline);
                storage.appendToFile(deadline.toFileString());
                break;
            case EVENT:
                String[] eventArray = parser.eventParse(remainingWords);
                Event event = new Event(eventArray[0], eventArray[1], eventArray[2]);
                taskList.add(event);
                storage.appendToFile(event.toFileString());
                break;
            case BYE:
                ui.sayBye();
                break;
            default:
                throw new IllegalArgumentException("I'm sorry, but I don't know what that means :-(");
        }
    }
}
