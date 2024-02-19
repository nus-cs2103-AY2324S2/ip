package yapper;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

/**
 * Handles the printing of messages in response to the input form the user.
 */
public class Ui {
    private static final String LINE = "    ____________________________________________________________\n";
    private static final String INDENT = "    ";
    private final TaskList mainTasks;

    /**
     * Initialises a Ui that corresponds to a main {@link TaskList}.
     * Main {@link TaskList} will also contain a reference to this instance.
     *
     * @param mainTasks Main {@link TaskList} to read from.
     */
    public Ui(TaskList mainTasks) {
        this.mainTasks = mainTasks;
        mainTasks.setUi(this);
    }

    public static String indent() {
        return INDENT;
    }

    public static String line() {
        return LINE;
    }

    /**
     * Greets the user with an introductory message.
     */
    public static String helloMessage() {
        String logo =
                "       :::   :::           :::        :::::::::       :::::::::       ::::::::::       :::::::::\n"
                + "      :+:   :+:         :+: :+:      :+:    :+:      :+:    :+:      :+:              :+:    :+:\n"
                + "      +:+ +:+         +:+   +:+     +:+    +:+      +:+    +:+      +:+              +:+    +:+\n"
                + "      +#++:         +#++:++#++:    +#++:++#+       +#++:++#+       +#++:++#         +#++:++#:\n"
                + "      +#+          +#+     +#+    +#+             +#+             +#+              +#+    +#+\n"
                + "     #+#          #+#     #+#    #+#             #+#             #+#              #+#    #+#\n"
                + "    ###          ###     ###    ###             ###             ##########       ###    ###\n\n";
        return LINE + "    What's poppin' fam, it's ya boi\n\n" + logo
                + "    Hit me up with those deets and let's vibe together!\n" + LINE;
    }

    /**
     * Sends a message for terminating the program.
     * Terminates the program by setting a flag hasEnded.
     */
    public String byeMessage() {
        return "Peace out, fam! Stay lit and keep those good vibes rollin'!\n";
    }

    public static String helpMessage() {
        return "Here are the list of commands and their formats:\n"
                + "help, list, todo, deadline, event, mark, unmark, \ndelete, find, bye\n" + INDENT
                + "list - lists all tasks\n" + INDENT
                + "todo - add a task to be done (todo <description>)\n" + INDENT
                + "deadline - add a task that has a deadline (deadline <description> /by <yyyy-mm-dd>)\n" + INDENT
                + "event - add an event with a start and end date\n"
                + "(event <description> /from <yyyy-mm-dd> /to <yyyy-mm-dd>)\n" + INDENT
                + "mark - mark your task/event as done (mark <index number>)\n" + INDENT
                + "unmark - mark your task/event as not done (unmark <index number>)\n" + INDENT
                + "delete - delete your task/event from your list (delete <index number>)\n" + INDENT
                + "find - search for a task in your list by its description (find <description>)\n" + INDENT
                + "bye - saves your changes to your list\n" + INDENT
                + "help - brings up the available commands\n";
    }

    public static String listMessage() {
        return "I gotchu bruv. Here's your list:\n";
    }

    public String markMessage(Task task) {
        return "Yasss King/Queen! This task is officially slayed:\n  " + task + "\n";
    }

    public String unmarkMessage(Task task) {
        return "You forgor:\n  " + task + "\n";
    }

    public String deleteMessage(Task task) {
        return "Zamn! This task never happened:\n  " + task + "\n";
    }

    /**
     * Prints message when a {@link Todo} is added to the {@link TaskList}.
     * Displays the task added and total number of task in the task list.
     *
     * @param todo Todo task that was added into the {@link TaskList}.
     */
    public String addTodoMessage(Todo todo) {
        return "Ayo new task just dropped:\n  " + todo
                + "\nYo, we're " + mainTasks.listSize() + " task(s) deep! Let's keep this SIGMA GRINDSET!\n";
    }

    /**
     * Prints message when a {@link Deadline} is added to the {@link TaskList}.
     * Displays the task added and total number of task in the task list.
     *
     * @param deadline Deadline task that was added into the {@link TaskList}.
     */
    public String addDeadlineMessage(Deadline deadline) {
        return "Ayo new task just dropped:\n  " + deadline
                + "\nYo, we're " + mainTasks.listSize() + " task(s) deep! Let's keep this SIGMA GRINDSET!\n";
    }

    /**
     * Prints message when a {@link Event} is added to the {@link TaskList}.
     * Displays the task added and total number of task in the task list.
     *
     * @param event Event task that was added into the {@link TaskList}.
     */
    public String addEventMessage(Event event) {
        return "Ayo new task just dropped:\n  " + event
                + "\nYo, we're " + mainTasks.listSize() + " task(s) deep! Let's keep this SIGMA GRINDSET!\n";
    }

    /**
     * Prints message when user searches for a {@link String}
     *
     * @param searchedString {@link String} that user is finding.
     */
    public static String findMessage(String searchedString) {
        return "Aight imma look for: " + searchedString + " ... ...\n";
    }

    public static String foundNothingMessage() {
        return "My bad G, I ain't found nothing\n";
    }
}
