package signal.util;

//import signal.DukeException;
import signal.task.Task;

import java.util.ArrayList;

public class Parser {
    private ArrayList<Task> tasks;
    private Ui ui;

    public Parser(ArrayList<Task> taskList, Ui ui) {
        this.tasks = taskList;
        this.ui = ui;
    }

    public void read(String userInput) {
        String[] inputParts = userInput.split(" ");
        if (userInput.equals("")) {
            // input is blank
            ui.emptyInput();
        } else if (userInput.startsWith("mark")) {
            // mark item as done
            ui.markTask(inputParts);
        } else if (ui.isPermutationMatch(inputParts[0], "mark")) {
            markTypo(inputParts);
        } else if (userInput.startsWith("unmark")) {
            // mark item as undone
            ui.unMarkTask(inputParts);
        } else if (ui.isPermutationMatch(inputParts[0], "unmark")) {
            unmarkTypo(inputParts);
        } else if (userInput.equals("list")) {
            // show list of tasks
            ui.commandList();
        } else if (ui.isPermutationMatch(userInput, "list")) {
            // check if user made a typo of 'list'
            listTypo(userInput);
        } else if (userInput.startsWith("delete")) {
            // remove a task
            listDelete(inputParts);
        } else if (userInput.equals("help")) {
            // show help message
            ui.commandHelp();
        } else {
            taskCommands(userInput);
        }
    }

    public void markTypo(String[] inputParts) {
        if (ui.checkCommandTypo(inputParts[0], "mark")) {
            ui.markTask(inputParts);
        } else {
            ui.signalSays("What else can I help you with?");
        }
    }

    public void unmarkTypo(String[] inputParts) {
        if (ui.checkCommandTypo(inputParts[0], "unmark")) {
            ui.unMarkTask(inputParts);
        } else {
            ui.signalSays("What else can I help you with?");
        }
    }

    public void listTypo(String userInput) {
        if (ui.checkCommandTypo(userInput, "list")) {
            ui.commandList();
        } else {
            ui.signalSays("What else can I help you with?");

//            ui.signalSays("Do you want to add " + userInput + "? (y/n)");
//            String addCommandCheck = ui.scan();
//            if (addCommandCheck.equals("n")) {
//                ui.signalSays("What else can I help you with?");
//            } else if(addCommandCheck.equals("y")) {
//                taskCommands(userInput);
//            }
        }
    }




    public void listDelete(String[] inputParts) {
        int index = Integer.parseInt(inputParts[1]);
        ui.commandDelete(index);
    }

    public void taskCommands(String userInput) {
        // create tasks
        String[] inputParts = userInput.split(" ");
        if (userInput.startsWith("todo")) {
            ui.commandToDo(inputParts);
        } else if (userInput.startsWith("deadline")) {
            ui.commandDeadline(inputParts);
        } else if (userInput.startsWith("event")) {
            ui.commandEvent(inputParts);
        } else {
            otherInputs(userInput);
        }

    }

    public void otherInputs(String userInput) {
        if (userInput.equals("blah")) {
            ui.commandBlah();
//            try {
//                ui.commandBlah();
//            } catch (DukeException e) {
//                ui.signalSays(e.getMessage());
//            }
        } else if (userInput.equals("something else")) {
            ui.commandSomethingelse();
//            try {
//                ui.commandSomethingelse();
//            } catch (DukeException e) {
//                ui.signalSays(e.getMessage());
//            }
        } else {
            ui.signalSays("Sorry, I don't know what you're talking about. " +
                    "Enter 'help' to see what commands you can use!");
        }
    }
}
