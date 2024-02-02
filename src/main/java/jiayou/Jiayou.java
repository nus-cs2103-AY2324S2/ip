package jiayou;

import jiayou.task.*;

/** Represents a chatbot named Jiayou.
 * @author Liu Jiayao
 */
public class Jiayou {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Returns a new Jiayou instance.
     *
     * @param filePath The path of the text file which stores the task list.
     */
    public Jiayou(String filePath) {
        this.tasks = new TaskList();
        this.ui = new Ui(this.tasks);
        this.storage = new Storage(filePath, this.tasks);
        this.tasks.linkStorage(this.storage);
        this.storage.load();
    }

    /**
     * Runs the chatbot Jiayou to send the greeting message.
     */
    public void run() {
        this.ui.greet();
    }

    /**
     * Initializes the chatbot and runs it.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        new Jiayou("./data/tasks.txt").run();
    }
}
