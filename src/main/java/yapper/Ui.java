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
    private boolean hasEnded;
    private final TaskList mainTasks;

    public Ui(TaskList mainTasks) {
        this.mainTasks = mainTasks;
        hasEnded = false;
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
    public static void hello() {
        String logo =
                "       :::   :::           :::        :::::::::       :::::::::       ::::::::::       :::::::::\n"
                + "      :+:   :+:         :+: :+:      :+:    :+:      :+:    :+:      :+:              :+:    :+:\n"
                + "      +:+ +:+         +:+   +:+     +:+    +:+      +:+    +:+      +:+              +:+    +:+\n"
                + "      +#++:         +#++:++#++:    +#++:++#+       +#++:++#+       +#++:++#         +#++:++#:\n"
                + "      +#+          +#+     +#+    +#+             +#+             +#+              +#+    +#+\n"
                + "     #+#          #+#     #+#    #+#             #+#             #+#              #+#    #+#\n"
                + "    ###          ###     ###    ###             ###             ##########       ###    ###\n\n";
        System.out.print(LINE + "    What's poppin' fam, it's ya boi\n\n" + logo
                + "    Hit me up with those deets and let's vibe together!\n" + LINE);
    }

    /**
     * Sends a message for terminating the program.
     * Terminates the program by setting a flag hasEnded.
     */
    public void bye() {
        System.out.print(INDENT + "Peace out, fam! Stay lit and keep those good vibes rollin'!\n");
        hasEnded = true;
    }

    public boolean hasEnded() {
        return hasEnded;
    }

    public static void listMessage() {
        System.out.println(INDENT + "I gotchu bruv. Here's your list:");
    }

    public void markMessage(Task task) {
        System.out.println(INDENT + "Yasss King/Queen! This task is officially slayed:\n  " + INDENT + task);
    }

    public void unmarkMessage(Task task) {
        System.out.println(INDENT + "You forgor:\n  " + INDENT + task);
    }

    public void deleteMessage(Task task) {
        System.out.println(INDENT + "Zamn! This task never happened:\n  " + INDENT + task);
    }

    /**
     * Prints message when a {@link Todo} is added to the {@link TaskList}.
     * Displays the task added and total number of task in the task list.
     *
     * @param todo Todo task that was added into the {@link TaskList}.
     */
    public  void addTodoMessage(Todo todo) {
        System.out.println(INDENT + "Ayo new task just dropped:\n  " + INDENT + todo);
        System.out.println(INDENT + "Yo, we're " + mainTasks.listSize()
                + " task(s) deep! Let's keep this SIGMA GRINDSET!");
    }

    /**
     * Prints message when a {@link Deadline} is added to the {@link TaskList}.
     * Displays the task added and total number of task in the task list.
     *
     * @param deadline Deadline task that was added into the {@link TaskList}.
     */
    public void addDeadlineMessage(Deadline deadline) {
        System.out.println(INDENT + "Ayo new task just dropped:\n  " + INDENT + deadline);
        System.out.println(INDENT + "Yo, we're " + mainTasks.listSize()
                + " task(s) deep! Let's keep this SIGMA GRINDSET!");
    }

    /**
     * Prints message when a {@link Event} is added to the {@link TaskList}.
     * Displays the task added and total number of task in the task list.
     *
     * @param event Event task that was added into the {@link TaskList}.
     */
    public void addEventMessage(Event event) {
        System.out.println(INDENT + "Ayo new task just dropped:\n  " + INDENT + event);
        System.out.print(INDENT + "Yo, we're " + mainTasks.listSize()
                + " task(s) deep! Let's keep this SIGMA GRINDSET!\n");
    }

    public static void findMessage(String searchedString) {
        System.out.println(INDENT + "Aight imma look for: " + searchedString);
        System.out.println(INDENT + ".");
        System.out.println(INDENT + ".");
        System.out.println(INDENT + ".");
    }

    public static void foundNothingMessage() {
        System.out.println(INDENT + "My bad G, I ain't found nothing");
    }
}
