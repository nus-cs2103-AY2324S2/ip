package lelu;

import java.time.format.DateTimeParseException;
import java.util.LinkedList;
import java.util.Scanner;

import exceptions.LeluException;
import storage.Storage;
import tasks.TaskList;
import commands.ByeCommand;
import commands.Command;
import ui.Ui;



public class Lelu {
    private static TaskList tasks = new TaskList();
    private Ui ui = new Ui();
    private Storage store = new Storage("./data/lelu.txt");;

    public Lelu() {
        store.load(Lelu.tasks);
    }


    /**
     * Returns a response to the user's input
     *
     * @param input User's input
     * @return Response based on user's input
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input, this.ui);
            return command.execute(tasks, ui, store, input);
        } catch (DateTimeParseException e) {
            return ui.dateFormatInstructions();
        } catch (LeluException e) {
            return e.getMessage();
        }
    }




}

