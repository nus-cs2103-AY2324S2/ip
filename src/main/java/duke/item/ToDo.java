package duke.item;

import java.io.Serializable;

/**
 * Represents a To-do, which differs from an Event and a Deadline
 * in that it contains no DateTime members. A To-do represents a
 * task that is not tied to any period or moment in time.
 */
public class ToDo implements Item, Serializable {
    private boolean isDone = false;
    private String name = "";

    /**
     * Creates a new deadline object. The name and isDone
     * field values are obtained from parsing the name argument.
     *
     * @param name a string array obtained from splitting command with
     *             the whitespace regex.
     */
    public ToDo(String[] name) {
        for (int i = 1; i < name.length; i++) {
            this.name += name[i] + " ";
        }
        this.name = this.name.trim();
    }
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String doneMessage() {
        return "Nice! I've marked this task as done:\n"
                + this.toString();
    }

    @Override
    public String undoneMessage() {
        return "OK, I've marked this task as not done yet:\n"
                + this.toString();
    }

    @Override
    public String printChecked(boolean b) {
        return b ? "X" : " ";
    }

    @Override
    public String addMessage(int num) {
        return "Got it. I've added this task:\n"
                + this.toString()
                + "\nNow you have " + num + " tasks in the list.";
    }

    @Override
    public String removeMessage(int num) {
        return "Noted. I've removed this task:\n"
                + this.toString()
                + "\nNow you have " + num + " tasks in the list.";
    }

    @Override
    public void markDone() {
        this.isDone = true;
    }

    @Override
    public void markUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[T][" + printChecked(this.isDone) + "] " + this.name;
    }
}
