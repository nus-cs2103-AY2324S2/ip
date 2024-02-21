package duke;

import java.time.format.DateTimeParseException;

import javafx.util.Pair;

/**
 * Represent the chatbot class to be used for interaction with the user
 * CS2103T
 * AY23/24 Semester 2
 * Author: Chua Zen Khoon
 */
public class Duke {

    protected static final int BYE_COMMAND = 1;
    protected static final int LIST_COMMAND = 2;
    protected static final int MARK_COMMAND = 3;
    protected static final int UNMARK_COMMAND = 4;
    protected static final int DELETE_COMMAND = 5;
    protected static final int FIND_COMMAND = 6;
    protected static final int SNOOZE_COMMAND = 7;
    protected static final int TASK_COMMAND = 8;

    protected static final int STORAGE_ADD_COMMAND = 0;
    protected static final int STORAGE_DELETE_COMMAND = 1;
    protected static final int STORAGE_SNOOZE_COMMAND = 2;


    private Ui ui;
    private Storage storage;
    private TaskList tasks;


    /**
     * Constructor for a Duke instance (Different chatbot instances for different users)
     *
     * @param directoryPath directory for Storage to check
     * @param fileName name of file for Storage to check
     */
    public Duke(String directoryPath, String fileName) {
        ui = new Ui();
        storage = new Storage(directoryPath, fileName);

        try {
            tasks = new TaskList(storage.setUpDirAndFile());
        } catch (DukeException e) {
            ui.showLoadingError(e);
            tasks = new TaskList();
        }

    }

    /**
     * Activates once Chatbot is booted up
     *
     * @return a greeting message
     */
    public String greet() {
        return "Hello! I'm Balom.\nWhat can I do for you today?\n\n"
                + "---Start by entering a todo, deadline or event with the relevant details!\n"
                + "Todo: todo + task ;\n"
                + "Event: event + task + /from yyyy-MM-dd HH:mm + /to yyyy-MM-dd HH:mm;\n"
                + "Deadline: deadline + task + /by yyyy-MM-dd HH:mm;\n"
                + "View the task list with List/list, or close the chat with Bye/bye!\n"
                + "Mark/Unmark a task in the list with mark (number) or unmark (number)\n"
                + "Delete a task in the list with delete (number)\n"
                + "Find a task in the list with find (keyword)\n"
                + "Snooze a task by 5 minutes with snooze (number)\n"
                + "Records will be remembered if you close me and reopen me!---\n";
    }

    /**
     * Activates once Chatbot is called to shut down
     *
     * @return a goodbye message
     */
    private String bye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Gets the result of processing the user input from the text box
     *
     * @param echo
     * @return String to be added to dialog box
     */
    public String getResponse(String echo) {
        String output = " ";
        try {
            //Ui grabs echo, tells parser to analyse and returns a result to Ui.
            //Ui then knows what command to execute, and passes back here for taskList + storage to execute

            int[] result = ui.analyseUserInput(echo);
            switch (result[0]) {
            case BYE_COMMAND:
                output = bye();
                break;
            case LIST_COMMAND:
                output = tasks.showTasks();
                break;
            case MARK_COMMAND:
                output = tasks.markMechanism(result[1]);
                break;
            case UNMARK_COMMAND:
                output = tasks.unmarkMechanism(result[1]);
                break;
            case DELETE_COMMAND:
                output = tasks.deleteMechanism(result[1]);
                if (result[1] <= tasks.getSize() + 1) {
                    storage.updateFile(new Task("To Delete"), STORAGE_DELETE_COMMAND, result[1]);
                }
                break;
            case FIND_COMMAND:
                String keywordToFind = ui.analyseFind(echo);
                output = tasks.findMechanism(keywordToFind);
                break;
            case SNOOZE_COMMAND:
                Pair<String, Task> taskToSnooze = tasks.snoozeMechanism(result[1]);
                output = taskToSnooze.getKey();
                if (result[1] <= tasks.getSize() + 1) {
                    storage.updateFile(taskToSnooze.getValue(), STORAGE_SNOOZE_COMMAND, result[1]);
                }
                break;
            case TASK_COMMAND:
                //tell ui to parse and return task to make
                //pass to tasklist to add the task only, no other computation needed
                Task taskToAdd = ui.analyseTask(echo);
                output = tasks.taskMechanism(taskToAdd);
                storage.updateFile(taskToAdd, STORAGE_ADD_COMMAND, 0);
                break;
            default:
                assert false : "Should not reach this point";
                break;
            }
        } catch (DukeException e) {
            output = e.getMessage();
        } catch (DateTimeParseException e) {
            output = "Make sure your datetime format is correct! ";
        } catch (IndexOutOfBoundsException e) {
            output = "There are only: " + tasks.getSize()
                    + " task(s) in the list to delete.\n";
        }

        return output;
    }

}
