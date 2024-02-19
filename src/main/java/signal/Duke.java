package signal;

import signal.task.*;
import signal.util.Parser;
import signal.util.Storage;
import signal.util.Ui;

import java.io.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class Duke {

    public static String formatDate(LocalDate date) {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        String formattedDate = date.format(outputFormatter);
        return formattedDate;
    }

    public static String formatTime(LocalTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a");
        String formattedTime = time.format(formatter);
        return formattedTime;
    }

    private static final String FILE_PATH = "../data/signal.txt";
    private static Storage fileManager = new Storage(FILE_PATH);
    public static Parser parser;
    private static ArrayList<Task> taskList = fileManager.loadTasks();
    private static int index = 0; // index of the next task to be filled
    private static Ui ui = new Ui(taskList);
    public Storage getStorage() {
        return fileManager;
    }



    public static void main(String[] args) {
        ui.intro();


        while(true) {
            String userInput = ui.scan();
            String[] inputArray = userInput.split(" ");

            if (userInput.equals("bye")) {
                // Exit program
                break;
            }

            parser.read(userInput);
//            try {
//                parser.read(userInput);
//            } catch (DukeException e) {
//                ui.signalSays(e.getMessage());
//            }


//            else if (userInput.equals("")) {
//                // input is blank
//                ui.emptyInput();
//            } else if (inputArray.length == 2 && inputArray[0].equals("mark")) {
//                // Mark item at index as done
//                int itemIndex = Integer.parseInt(inputArray[1]) - 1;
//                ui.signalSays(ui.commandMark(itemIndex));
//            } else if (inputArray.length == 2 && inputArray[0].equals("unmark")) {
//                // Mark item at index as done
//                int itemIndex = Integer.parseInt(inputArray[1]) - 1;
//                ui.signalSays(ui.commandUnmark(itemIndex));
//            } else if (inputArray.length == 2 && (ui.isPermutationMatch(inputArray[0], "mark") || ui.isPermutationMatch(inputArray[0], "unmark"))) {
//                // check typo of "mark" or "unmark"
//                if (ui.checkCommandTypo(inputArray[0], "mark")) { // command mark typo
//                    int itemIndex = Integer.parseInt(inputArray[1]) - 1;
//                    ui.signalSays(ui.commandMark(itemIndex));
//                } else if (ui.checkCommandTypo(inputArray[0], "unmark")) { // command unmark typo
//                    int itemIndex = Integer.parseInt(inputArray[1]) - 1;
//                    ui.signalSays(ui.commandUnmark(itemIndex));
//                } else {
//                    ui.signalSays("Do you want to add '" + userInput + "'? (y/n)");
//                    String addCommandCheck = ui.scan();
//                    if (addCommandCheck.equals("n")) {
//                        ui.signalSays("What else can I help you with?");
//                    } else if (addCommandCheck.equals("y")) {
//                        ui.taskAdded(userInput);
//                    }
//                }
//            } else if (userInput.equals("list")) {
//                // command list
//                if (taskList.size() == 0) {
//                    ui.signalSays("Oops, looks like you haven't added any tasks!");
//                } else {
//                    ui.commandList();
//                }
//            }
////            else if (inputArray[0].equals("date")) {
////                if (taskList.size() == 0) {
////                    signalSays("Oops, looks like you haven't added any tasks!");
////                } else {
////                    commandListDate(inputArray[1]);
////                }
////            }
//            else if (ui.isPermutationMatch(userInput, "list")) {
//                // check typo of command list
//                if (ui.checkCommandTypo(userInput, "list")) {
//                    ui.commandList();
//                } else {
//                    ui.signalSays("Do you want to add " + userInput + "? (y/n)");
//                    String addCommandCheck = ui.scan();
//                    if(addCommandCheck.equals("n")) {
//                        ui.signalSays("What else can I help you with?");
//                    } else if(addCommandCheck.equals("y")) {
//                        ui.taskAdded(userInput);
//                    }
//                }
//            } else if (inputArray[0].equals("todo") || inputArray[0].equals("deadline") || inputArray[0].equals("event")) {
//                String task = String.join(" ", Arrays.copyOfRange(inputArray, 1, inputArray.length));
//                try {
//                    ui.commandAddTask(inputArray[0], task);
//                } catch (DukeException e) {
//                    ui.signalSays("WHOOPSIE! " + e.getMessage());
//                }
//            } else if (userInput.equals("blah")) {
//                try {
//                    ui.commandBlah();
//                } catch (DukeException e) {
//                    ui.signalSays(e.getMessage());
//                }
//            } else if (userInput.equals("something else")) {
//                try {
//                    ui.commandSomethingelse();
//                } catch (DukeException e) {
//                    ui.signalSays(e.getMessage());
//                }
//            } else if (inputArray.length == 2 && inputArray[0].equals("delete")) {
//                try {
//                    int itemIndex = Integer.parseInt(inputArray[1]) - 1;
//                    if (index == 0) {
//                        ui.commandDeleteInvalid(0);
//                    }
//                    if (itemIndex > index - 1) {
//                        ui.commandDeleteInvalid(101);
//                    }
//                    if (itemIndex < 0) {
//                        ui.commandDeleteInvalid(-1);
//                    }
//                    ui.commandDelete(itemIndex);
//                } catch (DukeException e) {
//                    ui.signalSays(e.getMessage());
//                }
//            } else if (userInput.equals("help")) {
//                ui.commandHelp();
//            } else {
//                ui.taskAdded(userInput);
//            }
        }

        ui.leave();

    }
}

