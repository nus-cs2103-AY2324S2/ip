package harper;

import harper.commands.Command;
import harper.exceptions.HarperException;
import harper.utils.Parser;
import harper.utils.Storage;
import harper.utils.TaskList;
import harper.utils.Ui;

/**
 * A chatbot called Harper.
 */
public class Harper {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    //CHECKSTYLE.OFF: MissingJavadocMethod
    public Harper() {
        this.ui = new Ui();
        try {
            this.storage = new Storage("data", "harper.txt");
            this.taskList = new TaskList(this.storage.load());
        } catch (HarperException e) {
            ui.showError(e);
            this.taskList = new TaskList();
        }
        assert this.ui != null;
        assert this.storage != null;
    }

    /**
     * Responds to user's input.
     *
     * @param input User's input.
     * @return Chatbot's response.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input.trim());
            return c.execute(this.taskList, this.ui, this.storage);
        } catch (HarperException e) {
            return ui.showError(e);
        }
    }

    /**
     * Greets the user.
     *
     * @return Greeting message.
     */
    public String greet() {
        return this.ui.greet();
    }

    /**
     * Returns exit message.
     *
     * @return Exit message.
     */
    public String exit() {
        return this.ui.exit();
    }
}
