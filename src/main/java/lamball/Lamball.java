package lamball;

import javafx.scene.image.Image;
import lamball.command.Command;
import lamball.exception.LamballParseException;
import lamball.memo.Memo;
import lamball.memo.MemoList;


/**
 * Main chat bot class that is tasked with initializing and relaying user inputs to the various classes.
 *
 * @author ongzhili
 */
public class Lamball {
    private static final String USER_PROMPT = "    You:";
    private Image user = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/Lamball.png"));
    private TaskList tasks;
    private MemoList memos;
    private Ui ui;
    private String lastDoneTask;


    /**
     * Constructor for Lamball chatbot.
     *
     */
    public Lamball() {
        tasks = new TaskList(this);
        memos = new MemoList(this);
        ui = new Ui();
    }

    String initialize() {
        ui.greetingMessage();
        String guiText = Storage.obtainSavedFile(this);
        return guiText;
    }

    public void addToMemList(Memo mem, boolean isInit) {
        this.memos.addMemo(mem, isInit);
    }

    /**
     * Parse for initial list of commands
     *
     * @param msg Command to parse.
     * @throws LamballParseException if invalid command is provided.
     */
    public void initParse(String msg) throws LamballParseException {
        Command parsed = Parser.parse(msg, tasks, memos, true);
        parsed.run();
    }





    /**
     * Generates response to user input.
     *
     * @return Responds appropriately to given input.
     */
    public String getResponse(String input) {
        String response = "";
        try {
            Command comd = Parser.parse(input, tasks, memos, false);
            boolean isActive = comd.run();
            if (!isActive) {
                response = ui.goodbyeMessage();
            } else {
                response = ui.displayAction(this.lastDoneTask);
            }
        } catch (LamballParseException e) {
            response = ui.displayError(e);
        }
        assert response != "" : "response should not be empty";
        return response;
    }

    /**
     * Updates last done task (either in memo or tasklist)
     *
     * @param task Formatted string of last done task.
     */
    public void updateLastDoneTask(String task) {
        this.lastDoneTask = task;
    }
}


