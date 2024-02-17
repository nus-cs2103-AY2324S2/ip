import Commands.Command;
import Commands.CommandParser;
import Commands.DeleteCommand;
import Exceptions.*;
import Exceptions.TaskListFullException;
import Tasks.Deadline;
import Tasks.Event;
import Tasks.TaskList;
import Tasks.Todo;
import Utils.CommandTypes;
import Utils.Storage;
import Utils.Ui;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class  Dude {

    private static TaskList taskList = null;
    private static Storage storage = new Storage("data/tasklist.ser");

    static final String[] supported_commands = {"bye", "list", "mark", "unmark", "todo", "event", "deadline", "delete"};

    public static void main(String[] args) {

//        String logo =   "888888ba                 dP          \n" +
//                        "88    `8b                88          \n" +
//                        "88     88 dP    dP .d888b88 .d8888b. \n" +
//                        "88     88 88    88 88'  `88 88ooood8 \n" +
//                        "88    .8P 88.  .88 88.  .88 88.  ... \n" +
//                        "8888888P  `88888P' `88888P8 `88888P'";
//
//        System.out.println("--------------------------------------\n");
//        System.out.println(logo + "\n");
//        System.out.println("--------------------------------------");
//        System.out.println("Dude v1.0 by Tahsin Hasem.\n");

        Ui ui = new Ui();

        try {
            taskList = storage.loadTasks();
        } catch (Exception e) { //Thrown when file gets corrupted
            ui.showMessage("An error occurred while loading the tasks. Deleting the storage and starting with an empty task list.");
            storage.deleteStorage();
            taskList = new TaskList();
        }

        ui.showWelcome();

        // Initialise Scanner to reach command line input
        Scanner sc = new Scanner(System.in);
        while(true){

            String msg = "";
            try {
                msg = sc.nextLine();
            }catch(NoSuchElementException e){
                //this will not be handled.
                break;
            }

            Command command = CommandParser.parseCommand(msg, taskList);

            try {
                String response = command.execute();
                ui.showMessage(response);
            } catch (DudeException e) {
                ui.showMessage(e.getMessage());
            }

            if (command.getCommandType() == CommandTypes.BYE) {
                break;
            }
        }

    }

}
