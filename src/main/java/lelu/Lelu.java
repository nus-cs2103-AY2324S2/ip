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

//    private Lelu(String filePath) {
//        this.store = new Storage("./data/lelu.txt");
//        store.load(Lelu.tasks);
//
////        while (true) {
////            try {
////                store.load(Lelu.tasks);
////                this.listen(ui, store);
////                break;
////            } catch (DateTimeParseException e) {
////                ui.dateFormatInstructions();
////            } catch (LeluException e) {
////                System.out.println(e.getMessage());
////            }
////        }
//    }
//
//    public void listen(Ui ui, Storage store) throws LeluException {
//        Scanner sc = new Scanner(System.in);
//        while (true) {
//            String message = sc.nextLine();
//            Command command = Parser.parse(message, ui);
//            command.execute(tasks, ui, store, message);
//            if (command instanceof ByeCommand) {
//                break;
//            }
//        }
//    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
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

