package slaybot;

public class Slaybot {

    private TaskList tasks;
    private Ui ui;

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
