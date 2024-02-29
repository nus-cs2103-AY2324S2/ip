package damon;

import damon.command.Command;
import damon.command.ExitCommand;
import damon.exceptions.DamonExceptions;
import damon.exceptions.NoDescriptionException;
import damon.exceptions.WrongInputException;
import damon.response.Response;
import damon.storage.Storage;
import damon.tasklist.TaskList;
import damon.ui.Ui;
import damon.parser.Parser;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.awt.*;
import java.util.concurrent.Callable;

/**
 * Creates a chatbot called Damon. A Damon object responds to user's input
 * by adding, deleting, marking, and un-marking Tasks in TaskList object.
 * Damon also store Tasks in TaskList in storage file of its filepath using Storage object.
 */
public class Damon {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Response response;
    private boolean isExit = false;

    /**
     * Constructs a new Damon object with String filePath parameter.
     *
     * @param filePath Path of storage file of this Damon object.
     */
    public Damon(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        response = new Response();
        response.showWelcome();
        try {
            tasks = new TaskList(storage.load());
        } catch (DamonExceptions e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs chatbot Damon.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage, response);
                isExit = c.isExit();
            } catch (DamonExceptions e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public void run(String fullCommand) {
        try {
            assert Parser.parse(fullCommand) instanceof Command;
            Command c = Parser.parse(fullCommand);
            if (c.isExit()) {
                assert !isExit;
                isExit = true;
            }
            c.execute(tasks, ui, storage, response);
        } catch (DamonExceptions e) {
            response.showError(e.getMessage());
        }
    }

    public boolean getIsExit() {
        return isExit;
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse() {
        return response.getResponseMessage();
    }


    public static void main(String[] args) {
        new Damon("..\\Damon.txt").run();
    }
}

