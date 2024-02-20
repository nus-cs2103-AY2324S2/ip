package javassist;

import java.io.IOException;

import javassist.command.Command;
import javassist.exception.JavAssistException;
import javassist.util.ExpenseList;
import javassist.util.Parser;
import javassist.util.Storage;
import javassist.util.TaskList;
import javassist.util.Ui;


/**
 * Represents a ChatBot.
 */
public class JavAssist {

    private Storage storage;
    private TaskList tasks;
    private ExpenseList expenses;
    private Ui ui;
    private boolean isExit = false;

    /**
     * Constructs a new JavAssist instance to run.
     * Reads from file to set up chatbot.
     *
     * @param file Name of file to save data and read from.
     * @param name Name of chatbot.
     * @param logo Logo of chatbot.
     */
    public JavAssist(String file, String expenseFile, String name, String logo) {
        ui = new Ui(name, logo, System.in);
        try {
            storage = new Storage(file, expenseFile);
        } catch (IOException e) {
            ui.showError(e.getMessage());
        }
        try {
            tasks = new TaskList(storage.readFromFile());
        } catch (JavAssistException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
        try {
            expenses = storage.readFromExpenseFile();
        } catch (JavAssistException e) {
            ui.showLoadingError();
            expenses = new ExpenseList();
        }
    }

    /**
     * Starts up the chatbot with welcome message and start taking in commands until bye command is inputted.
     */
    public void run() {
        ui.showWelcome();
        isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parseCommand(fullCommand);
                if (c.isExpenseCommand()) {
                    c.execute(expenses, ui, storage);
                } else {
                    c.execute(tasks, ui, storage);
                }
                isExit = c.isExit();
            } catch (JavAssistException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Returns response corresponding to input.
     *
     * @param input User input.
     * @return Message from chatbot.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parseCommand(input);
            isExit = c.isExit();
            if (c.isExpenseCommand()) {
                return c.execute(expenses, ui, storage);
            } else {
                return c.execute(tasks, ui, storage);
            }
        } catch (JavAssistException e) {
            return ui.showError(e.getMessage());
        }
    }

    /**
     * Returns true if current command is bye.
     *
     * @return Value of isExit.
     */
    public boolean isExit() {
        return isExit;
    }

    public String getLogo() {
        return ui.showWelcome();
    }

    /**
     * Starts Duke chatbot on command line. Entry point of application.
     *
     * @param args Not used.
     */
    public static void main(String[] args) {
        String logo = "\t  ____   __  _  _  __    ___  ___  ____  ___  ____\n\t"
                + " (_  _) /__\\( \\/ )/__\\  / __)/ __)(_  _)/ __)(_  _)\n\t"
                + " .-_)( /(__)\\\\  //(__)\\ \\__ \\\\__ \\ _)(_ \\__ \\  )(\n\t"
                + "\\____)(__)(__)\\/(__)(__)(___/(___/(____)(___/ (__)\n";

        new JavAssist("./data/JavAssist.txt", "./data/JavAssistExpense.txt", "JavAssist", logo).run();
    }
}
