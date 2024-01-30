package chatbot;

import chatbot.exceptions.DukeException;
import chatbot.exceptions.InvalidArgumentException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Plana {
    private final TaskList taskList;
    private final Storage store;
    private final Ui view;
    private boolean shouldExit;

    public Plana() throws IOException, ClassNotFoundException {
        this.view = new Ui();
        this.shouldExit = false;
        this.store = new Storage();
        this.taskList = new TaskList(this.store);
    }


    public void init() throws IOException {
        this.view.greet();

        while (!shouldExit) {
            String userInput = this.view.getInput();
            try {
                Command cmd = Parser.toCommand(userInput);
                cmd.execute(view, taskList);
                this.shouldExit = cmd.shouldExit();
            } catch (DukeException e) {
                this.view.displayError(e);
            }
        }

        this.taskList.saveToStore(this.store);
        this.view.bye();
    }

    private void parseInput(String in) throws DukeException {

    }


}
