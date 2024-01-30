import entity.*;
import java.util.*;

public class SlayBot {

    private TaskList tasks;
    private Ui ui;

    public SlayBot() {
        this.tasks = new TaskList();
        this.ui = new Ui(tasks);
    }

    public void run() {
        this.ui.start();
    }

    public static void main(String[] args) {
        new SlayBot().run();
    }
}
