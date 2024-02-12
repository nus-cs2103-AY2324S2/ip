import Exceptions.DukeException;
import Storage.Storage;
import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.Todo;
import UI.Parser;
import UI.Ui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Duke {
    public static void main(String[] args) throws DukeException {

        //Initialising packages
        Storage storage = new Storage();
        Ui ui = new Ui();
        Parser parser = new Parser();
        List<Task> tasks = storage.loadTasks();

        //Print welcome
        ui.printWelcome();

        //Execute Inputs
        boolean isExit = false;
        while (!isExit) {
            int index;
            String[] inputs = ui.readCommand();
            Parser.Command command = parser.parse(inputs[0]);
            try {
                switch (command) {
                    case BYE:
                        ui.printGoodbye();
                        isExit = true;
                        break;
                    case LIST:
                        ui.printList(tasks);
                        break;
                    case MARK:
                        index = Integer.parseInt(inputs[1]) - 1;
                        tasks.get(index).mark();
                        ui.printMark(tasks.get(index));
                        storage.saveTasks(tasks);
                        break;
                    case UNMARK:
                        index = Integer.parseInt(inputs[1]) - 1;
                        tasks.get(index).unMark();
                        ui.printUnmark(tasks.get(index));
                        storage.saveTasks(tasks);
                        break;
                    case TODO:
                        Todo newTodo = new Todo(inputs[1]);
                        tasks.add(newTodo);
                        ui.printTodo(newTodo, tasks);
                        storage.saveTasks(tasks);
                        break;
                    case DEADLINE:
                        String[] deadlineSplit = inputs[1].split(" /by ");
                        Deadline newDeadline = new Deadline(deadlineSplit[0], deadlineSplit[1]);
                        tasks.add(newDeadline);
                        ui.printDeadline(newDeadline, tasks);
                        storage.saveTasks(tasks);
                        break;
                    case EVENT:
                        String[] eventSplit = inputs[1].split(" /from | /to ");
                        Event newEvent = new Event(eventSplit[0], eventSplit[1], eventSplit[2]);
                        tasks.add(newEvent);
                        ui.printEvent(newEvent, tasks);
                        storage.saveTasks(tasks);
                        break;
                    case DELETE:
                        index = Integer.parseInt(inputs[1]) - 1;
                        Task toDelete = tasks.get(index);
                        tasks.remove(toDelete);
                        ui.printDelete(toDelete, tasks);
                        storage.saveTasks(tasks);
                        break;
                    case FIND:
                        ui.findTasks(inputs[1], tasks);
                        break;
                    default:
                        ui.printError("Command not found!");
                        break;
                }

            } catch (ArrayIndexOutOfBoundsException e) {
                ui.printError("Error! Description to command not found!");
            }

        }



    }
    }


