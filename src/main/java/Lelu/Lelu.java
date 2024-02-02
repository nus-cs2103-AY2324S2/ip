package Lelu;

import TasksStorage.Storage;
import TasksStorage.TaskList;
import UI.Ui;
import Exceptions.*;
import Commands.ByeCommand;
import Commands.Command;


import java.time.format.DateTimeParseException;
import java.util.Scanner;


public class Lelu {

    private static TaskList tasks;


    private Lelu(String filePath) {
        Ui ui = new Ui();
        ui.greet();
        Lelu.tasks = new TaskList();

        Storage store = new Storage(filePath);
        while (true) {
            try {
                store.load(Lelu.tasks);
                this.listen(ui, store);
                store.save(Lelu.tasks);
                break;
            } catch (DateTimeParseException e) {
                ui.dateFormatInstructions();
            } catch (LeluException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void listen(Ui ui, Storage store) throws LeluException {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String message = sc.nextLine();
            Command command = Parser.parse(message, ui);
            command.execute(tasks, ui, store, message);
            if (command instanceof ByeCommand) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        new Lelu("./data/lelu.txt");
        }

}

