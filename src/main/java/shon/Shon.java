package shon;

import java.time.format.DateTimeParseException;

import shon.command.Command;

import shon.exception.CommandException;
import shon.exception.ParameterException;

public class Shon {

    private Ui ui;
    private TaskList list;
    private Storage storage;

    public Shon() {
        this.ui = new Ui();
        this.storage = new Storage("./data/Shon.txt");
        this.list = storage.loadList();
    }

    public static void main(String[] args) {
        new Shon().run();
    }

    private void run() {
        this.ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = this.ui.readInput();
                Command command = Parser.parse(input);
                command.execute(this.list, this.ui);
                this.storage.updateData(this.list.formatData());
                isExit = command.isExit();
            } catch (ParameterException | CommandException e) {
                this.ui.print(e.getMessage());
            } catch (DateTimeParseException e) {
                this.ui.print(e.getParsedString() + " is not a valid date/time",
                        "Please enter the date/time in \"dd/mm/yyyy hhmm\" format with valid values.");
            }
        }
    }
}
