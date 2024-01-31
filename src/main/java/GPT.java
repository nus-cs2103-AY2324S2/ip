import java.util.Scanner;
import java.util.ArrayList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


import java.io.IOException;

enum TaskType {
    T, D, E
}

public class GPT {
    //private static final DateTimeFormatter DATE_FORMATT_OUTPUT = DateTimeFormatter.ofPattern("MMM dd yyyy");
    private static final String FILE_PATH = "./data/GPT.txt";


    public static void main(String[] args) {
        Ui ui = new Ui();
        Storage storage = new Storage(FILE_PATH);
        TaskList tl = new TaskList(storage.loadTasks());

        ui.printWelcomeMsg();

        Scanner scn = new Scanner(System.in);

        while (true) {
            String userInput = scn.nextLine().trim();
            if (userInput.equals("bye")) {
                ui.printGoodbyeMessage();
                break;
            }

            try {
                Parser.parseCommand(userInput, tl, ui, storage);
            } catch (GPTException e) {
                ui.printErrorMessage(e.getMessage());
            }
        }

    }
}

