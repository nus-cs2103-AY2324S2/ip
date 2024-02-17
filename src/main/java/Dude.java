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


    public void run() {

    }



    public static void main(String[] args) {

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
            } catch (DudeException | IndexOutOfBoundsException e) {
                ui.showMessage(e.getMessage());
            }

            boolean isListCommand = command.getCommandType() == CommandTypes.LIST;
            boolean isByeCommand = command.getCommandType() == CommandTypes.BYE;

            if (!(isListCommand || isByeCommand)) {
                try {
                    storage.saveTasks(taskList);
                } catch (Exception e) {
                    ui.showMessage("An error occurred while saving the tasks.");
                }
            }

            if (command.getCommandType() == CommandTypes.BYE) {
                break;
            }
        }

    }

}
