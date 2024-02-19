package guanguan;

/**
 * Extension of Ui class that is responsible for the outputs on GUI.
 * Solution below adapted from https://github.com/lordidiot/ip/blob/master/src/main/java/duke/ui/TextUi.java
 */
public class Gui extends Ui {
    private final StringBuilder buffer;

    /**
     * Constructor for Gui.
     */
    public Gui() {
        this.buffer = new StringBuilder();
    }

    /**
     * Get accumulated output from the UI.
     * @return Text output accumulated so far.
     */
    @Override
    public String getTextOutput() {
        String out = this.buffer.toString();
        this.buffer.delete(0, this.buffer.length());
        return out;
    }

    @Override
    public void welcome() {
        this.buffer.append("Hello! I'm Guan Guan\n");
        this.buffer.append("What can I do for you?\n");
        line();
    }

    @Override
    public void bye() {
        this.buffer.append("Bye. Hope to see you again soon!\n");
    }

    @Override
    public void println(String message) {
        this.buffer.append(String.format("%s\n", message));
    }

    @Override
    public void addTask() {
        this.buffer.append("Got it. I've added this task:\n");
    }

    @Override
    public void deleteTask() {
        this.buffer.append("Noted. I've removed this task:\n");
    }

    @Override
    public void countTasks(int i) {
        this.buffer.append(String.format("Now you have %s tasks in the list.\n", i));
    }

    @Override
    public void tasks(TaskList items) throws GgException {
        this.buffer.append("Here are the tasks in your list:\n");
        for (int i = 0; i < items.size(); i++) {
            this.buffer.append(String.format("%s. %s\n", i + 1, items.get(i)));
        }
    }

    @Override
    public void markTask() {
        this.buffer.append("Nice! I've marked this task as done:\n");
    }

    @Override
    public void unmarkTask() {
        this.buffer.append("OK, I've marked this task as not done yet:\n");
    }

    @Override
    public void line() {
        this.buffer.append("____________________________________________________________\n");
    }

    @Override
    public void emptyLine() {
        this.buffer.append("\n");
    }

    @Override
    public void error(String message) {
        this.buffer.append(String.format("[!] %s\n", message));
    }

}

