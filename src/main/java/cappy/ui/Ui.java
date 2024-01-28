package cappy.ui;

import cappy.util.Logger;
import cappy.task.Task;
import cappy.task.TaskList;

public class Ui {
    private static final String HORIZONTAL_LINE =
            "____________________________________________________________";
    private static final String INDENT = "    ";

    public void showAddedTask(Task task, TaskList tasks) {
        String[] messages = {
            "Got it. I've added this task:",
            task.toString(),
            "Now you have " + tasks.size() + " tasks in the list."
        };
        show(messages);
    }

    public void showBanner() {
        Logger.print(" ██████╗ █████╗ ██████╗ ██████╗ ██╗   ██╗");
        Logger.print("██╔════╝██╔══██╗██╔══██╗██╔══██╗╚██╗ ██╔╝");
        Logger.print("██║     ███████║██████╔╝██████╔╝ ╚████╔╝ ");
        Logger.print("██║     ██╔══██║██╔═══╝ ██╔═══╝   ╚██╔╝  ");
        Logger.print("╚██████╗██║  ██║██║     ██║        ██║   ");
        Logger.print(" ╚═════╝╚═╝  ╚═╝╚═╝     ╚═╝        ╚═╝   ");
    }

    public void show(String message) {
        Logger.print(INDENT + HORIZONTAL_LINE);
        Logger.print(INDENT + " " + message);
        Logger.print(INDENT + HORIZONTAL_LINE + "\n");
    }

    public void show(String[] messages) {
        Logger.print(INDENT + HORIZONTAL_LINE);
        for (String msg : messages) {
            Logger.print(INDENT + " " + msg);
        }
        Logger.print(INDENT + HORIZONTAL_LINE + "\n");
    }

    public void showError(String message) {
        Logger.error(message);
    }

    public void showError(Exception exception) {
        Logger.error(exception.getMessage());
    }

    public void showGreetings() {
        String[] messages = {"Hello! I'm Cappy", "What can I do for you?"};
        show(messages);
    }

    public void showExit() {
        show("Bye. Hope to see you again soon!");
    }
}
