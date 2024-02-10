package duke.util;

import java.util.ArrayList;

import duke.task.Task;

/**
 * Represents the User Interface.
 * Messages are displayed to inform users of the effects of their command.
 * Messages are displayed through the console.
 */
public class Ui {
//    private String logo = "\t  __   __    ____  ____  ____  ____\n"
//            + "\t / _\\ (  )  (  __)(  _ \\(  __)(    \\\n"
//            + "\t/    \\/ (_/\\ ) _)  )   / ) _)  ) D (\n"
//            + "\t\\_/\\_/\\____/(__)  (__\\_)(____)(____/\n";
    private final String name = "Alfred";

    private String message = "";
    /**
     * Displays the given content between two lines to separate the output message.
     *
     * @param content the content to be printed.
     */
//    private static void printMessageWithLines(String content) {
//        System.out.println("\t____________________________________________________________");
//        System.out.println("\t" + content);
//        System.out.println("\t____________________________________________________________\n");
//    }

    /**
     * Displays a greeting message with the logo and the name of the chatbot and,
     * prompts the user to enter commands.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Displays a farewell message from the chatbot.
     */
    public void leave() {
        String message = "Farewell. Wishing for the opportunity to meet you again soon.";
//        printMessageWithLines(message);
        this.message = message;
    }

    /**
     * Displays a message to inform user of invalid command and,
     * shows the details of valid commands.
     */
    public void informInvalidCommand() {
        String message = "I regret to inform you that I currently lack an understanding of the intended meaning behind"
                + "that statement.";
        this.message = message;
//        informWrongInputFormat();
    }

    /**
     * Informs the user that the given task has been marked.
     *
     * @param task the given task to be marked.
     */
    public void informListMarked(Task task) {
        String message = "It is my pleasure to inform you that I have officially marked this particular task as"
                + " 'completed':" + "\n" + task;
//        printMessageWithLines(message);
        this.message = message;
    }

    /**
     * Informs the user that the given task has been unmarked.
     *
     * @param task the given task to be unmarked.
     */
    public void informListUnmarked(Task task) {
        String message = "I wish to communicate that I have marked this particular task as 'incomplete' at this "
                + "juncture:" + "\n" + task;
//        printMessageWithLines(message);
        this.message = message;
    }

    /**
     * Informs the user that the given task is added into the given list and,
     * displays the size of the resulting list.
     *
     * @param task the given task to be added.
     * @param taskList the list from which the task will be added.
     */
    public void informItemAdded(Task task, TaskList taskList) {
        String message = "I am pleased to convey that the following task has been added to the outlined list:\n"
                + task + "\n " + taskList;
//        printMessageWithLines(message);
        this.message = message;
    }

    /**
     * Displays the given list fully with all the task details.
     *
     * @param taskList the given list to be displayed.
     */
    public void displayFullList(TaskList taskList) {
        String message = "";
        if (taskList.getSize() > 0) {
            message = taskList.showList();
        } else {
            message = "I would like to inform you that the task list is empty.";
        }
        this.message = message;
    }

    /**
     * Informs the user that the given task is removed from the given list and,
     * displays the size of the resulting list.
     *
     * @param task the given task to be removed.
     * @param size the list from which the task will be removed.
     */
    public void informItemRemoved(Task task, int size) {
        String message = "I acknowledge your update. The specified task has been duly removed:\n" + task
                + "\nCurrently, " + "the list comprises " + size + " tasks.";
//        printMessageWithLines(message);
        this.message = message;
    }
    /**
     * Display the input task list.
     *
     * @param taskList the task list to be displayed.
     */
    public void displaySelectedList(ArrayList<Task> taskList) {
//        System.out.println("\t____________________________________________________________");
//        System.out.println("\tHere are the corresponding tasks that align with your criteria:");
//        for (Task t: taskList) {
//            System.out.println("\t" + t);
//        }
//        System.out.println("\t____________________________________________________________\n");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Here are the corresponding tasks that align with your criteria:\n");
        for (Task t : taskList) {
            stringBuilder.append("").append(t).append("");
        }
        this.message = stringBuilder.toString();;
    }
    /**
     * Informs the user of the correct format to enter a date and time.
     */
    public void informWrongDateFormat() {
        String message = "Please entered the date and time in the (yyyy/mm/dd HHmm) format";
//        printMessageWithLines(message);
        this.message = message;
    }

    /**
     * Informs the user of incorrect input format by,
     * displaying the correct format of all the valid commands.
     */
    public void informWrongInputFormat() {
        String message = "Please follow the correct format for adding tasks:\n"
                + "To add todos: todo <Task name>\n"
                + "To add deadlines: deadline <Task name> /by <deadline in yyyy-mm-dd HHmm format>\n"
                + "To add events: event <Task name> /from <start time in yyyy-mm-dd HHmm format> "
                + "to <end time in yyyy-mm-dd HHmm format>\n"
                + "Other commands: \n"
                + "list : to list the full list\n"
                + "mark <index>: to mark the task at index no <index> in the list as complete\n"
                + "unmark <index>: to mark the task at index no <index> in the last as incomplete\n"
                + "delete <index>: to remove the taks at index no <index> in the last\n"
                + "bye: to leave the program\n"
                + "find <keyword>: to find tasks with description that contains the keyword";
        this.message = message;
    }

}
