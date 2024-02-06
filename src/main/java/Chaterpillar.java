import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.IOException;

import java.time.LocalDate;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Main class for Chaterpillar chatbot.
 */
public class Chaterpillar {

    public Storage storage;
    public TaskList tasks;
    public Ui ui;

    public Chaterpillar() {
        ui = new Ui();
        storage = new Storage();
        tasks = new TaskList();
    }

    public void run() {
        ui.greet();
        boolean hasExited = false;

        while (!hasExited) {
            try {
                String fullCommand = ui.readCommand();
                Command command = Parser.parse(fullCommand);
                command.execute(tasks, ui, storage);
                hasExited = command.hasExited();
            } catch (IOException e) {
                ui.echo("IOException occurred.");
            } finally {
                ui.printHorizontalLine();
            }
        }
    }

    public static void main(String[] args) {
        new Chaterpillar().run();
    }
}
