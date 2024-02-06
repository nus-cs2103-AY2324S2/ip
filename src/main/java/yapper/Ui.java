package yapper;

import exception.YapperException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

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

    public void addTodoMessage(Todo todo) {
        System.out.println(INDENT + "Ayo new task just dropped:\n  " + INDENT + todo);
        System.out.println(INDENT + "Yo, we're " + mainTasks.listSize()
                + " task(s) deep! Let's keep this SIGMA GRINDSET!");
    }

    public void addDeadlineMessage(Deadline deadline) {
        System.out.println(INDENT + "Ayo new task just dropped:\n  " + INDENT + deadline);
        System.out.println(INDENT + "Yo, we're " + mainTasks.listSize()
                + " task(s) deep! Let's keep this SIGMA GRINDSET!");
    }
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
