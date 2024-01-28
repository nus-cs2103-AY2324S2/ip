package duke;

import duke.task.Task;
import duke.task.TaskList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Ui {
    private final BufferedReader reader;
    private final PrintWriter writer;

    public Ui() {
        reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new PrintWriter(System.out, true);
    }

    public String readCommand() throws IOException {
        return reader.readLine();
    }

    public void showLine() {
        writer.println("____________________________________________________________");
    }

    public void showMessage(String message) {
        writer.println(message);
    }

    public void showLoadingError() {
        showMessage("Error loading tasks from file.");
    }

    public void showGoodbyeMessage() {
        showMessage("Bye. Hope to see you again soon!");
    }

    public void showError(String error) {
        showMessage(error);
    }

    public void showDeletedMessage(TaskList tasks, Task removedTask) {
        String taskWord = tasks.size() == 1 ? " task" : " tasks";
        writer.println("Noted. I've removed this task:\n  " + removedTask);
        writer.println("Now you have " + tasks.size() + taskWord + " in the list.");
    }

    public void close() {
        writer.close();
    }

    public void showWelcome() {
        showLine();
        showMessage("Hello! I'm Nicky!");
        showMessage("What can I do for you?");
        showLine();
    }

    public void showAddedTask(TaskList tasks) {
        String taskWord = tasks.size() == 1 ? " task" : " tasks";
        writer.println("Got it. I've added this task:\n  " + tasks.get(tasks.size() - 1));
        writer.println("Now you have " + tasks.size() + taskWord + " in the list.");
    }

    public PrintWriter getWriter() {
        return this.writer;
    }

    public void showMarkedMessage(Task task) {
        if(task.isDone()) {
            writer.println("This task is already marked as done:\n  " + task);
        } else {
            task.markAsDone();
            writer.println("Nice! I've marked this task as done:\n  " + task);
        }
    }

    public void showUnmarkedMessage(Task task) {
        if(task.isDone()) {
            task.markAsNotDone();
            writer.println("OK, I've marked this task as not done yet:\n  " + task);
        } else {
            writer.println("This task is already marked as not done:\n  " + task);
        }
    }
}
