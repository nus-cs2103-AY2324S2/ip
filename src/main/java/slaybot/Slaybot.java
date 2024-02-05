package slaybot;

/**
 * The Slaybot class combines the various classes to provide an interactive task tracking bot
 */
public class Slaybot {

    private TaskList tasks;
    private Ui ui;

    /**
     * Constuctor for Slaybot class
     */
    public Slaybot() {
        this.tasks = new TaskList();
        this.ui = new Ui(tasks);
    }

    public void run() {
        this.ui.start();
    }
    public static void main(String[] args) {
        new Slaybot().run();
    }
}
