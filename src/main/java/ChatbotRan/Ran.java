package ChatbotRan;

import javafx.scene.layout.VBox;

import java.util.Scanner;

/**
 * Runs the entire chatbot. Reads from standard input.
 */
public class Ran {
    Parser parser;
    TaskList taskList;
    TaskIo taskIo;
    RanUi ui;

    /**
     * Constructs the object using given input
     *
     * @param taskIo Storage of tasks
     */
    public Ran(TaskIo taskIo, RanUi ranUi) {
        this.taskList = new TaskList(taskIo);
        this.ui = ranUi;
        this.taskIo = taskIo;
        this.parser = new Parser();
    }

    public void respond(String input) {
        try {
            parser.exec(input, taskList, ui);
        } catch (TaskException e) {
            ui.displayError(e);
        }
        if (!parser.running()) {
            ui.bye();
        }
    }

    public void setContainer(VBox dialogContainer) {
        this.ui.setContainer(dialogContainer);
    }
    public boolean running() {
        return this.parser.running();
    }
}

