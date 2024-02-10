package duke.tasks;

import duke.ui.Ui;

import java.time.format.DateTimeFormatter;


public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean status) {
        this.description = description;
        this.isDone = status;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public void markDone(Ui ui) {
        isDone = true;
        ui.showLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(this.toString());
        ui.showLine();
    }

    public void unmark(Ui ui) {
        isDone = false;
        ui.showLine();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(this.toString());
        ui.showLine();
    }

    public void displayTask(int size) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + this.toString());
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }
    public String convertToFileFormat(DateTimeFormatter storeFormatter) {
        return (isDone ? "1" : "0") + " | " + description;
    }
}
