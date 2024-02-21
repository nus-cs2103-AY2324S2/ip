package kwuntalk;

import kwuntalk.command.Command;
import kwuntalk.exception.KwunTalkException;
import kwuntalk.task.Task;


/**
 * Represents the Chatbot object.
 */

public class KwunTalk {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private boolean isExit = false;
    private static final String FILE_PATH = "./data/kwuntalk.txt";

    /**
     * Constructor for KwunTalk.
     */
    public KwunTalk() {
        this.ui = new Ui();
        this.storage = new Storage(FILE_PATH);

        try {
            this.tasks = new TaskList(this.storage.readTasksFile()
                    .toArray(new Task[0])
            );
        } catch (KwunTalkException e) {
            this.ui.showError(e);

        }
    }

    private void run() {
        System.out.println(this.getGreeting());

        while (!isExit) {
            String input = this.ui.readInput();
            System.out.println(this.getResponse(input));
        }
    }

    protected String getGreeting() {
        return ui.showGreet();
    }

    /**
     * Returns the response to the user's input.
     *
     * @param input The user's input.
     * @return The response to the user's input
     */
    protected String getResponse(String input) {
        try {
            Command cmd = Parser.parse(input);
            String response = cmd.generateReply(this.tasks, this.ui, this.storage);
            this.isExit = cmd.isExit();
            return response;
        } catch (KwunTalkException e) {
            return ui.showError(e);
        }
    }


    public static void main(String[] args) {
        new KwunTalk().run();
    }
}
