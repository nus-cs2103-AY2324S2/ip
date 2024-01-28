package Klee;

import Klee.command.Bye;
import Klee.command.Command;

import Klee.task.Task;

import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

import java.io.FileWriter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Klee {
    enum Instruction {
        todo,
        deadline,
        event,
        mark,
        unmark,
        delete
    }

    public static String checkToDo(String input) throws KleeException {
        String[] description = input.split("todo ");
        if (description.length == 2) return description[1];
        else throw new KleeException("We should think of a name for the Klee.task!");
    }

    public static String[] checkDeadline(String input) throws KleeException {
        String[] command = input.split("deadline ");
        String[] output = new String[2];
        if (command.length == 2) {
            command = command[1].split(" /by ");
            if (command.length == 2) {
                output[0] = command[0];
                output[1] = command[1];
                return output;
            } else throw new KleeException("We should indicate when this Klee.task is due with `/by`");
        }
        else throw new KleeException("We should think of a name for the Klee.task!");
    }

    public static String[] checkEvent(String input) throws KleeException {
        String[] command = input.split("event ");
        String[] output = new String[3];
        if (command.length == 2) {
            command = command[1].split(" /from ");
            if (command.length == 2) {
                output[0] = command[0];
                command = command[1].split(" /to ");
                if (command.length == 2) {
                    output[1] = command[0];
                    output[2] = command[1];
                    return output;
                } else throw new KleeException("We should indicate when this event ends with `/to`");
            } else throw new KleeException("We should indicate when this event starts with `/from`");
        } else throw new KleeException("We should think of a name for the Klee.task!");
    }

    public static void saveData(ArrayList<Task> tasks) {
        try {
            FileWriter data = new FileWriter("data/Klee.Klee.txt");
            for (int i = 0; i < tasks.size(); i++) {
                Task currentTask = tasks.get(i);
                String line = currentTask.toText();
                data.write(line + "\n");
            }
            data.close();
        } catch (IOException e) {
            System.out.println("Klee.Klee has run out of ink! I cannot write this down!");
        }
    }

    public static LocalDateTime parseDateTime(String dateTime) throws KleeException {
        String[] splitDateTime = dateTime.split(" ");
        int year = 0;
        int month = 0;
        int day = 0;
        int hour = 0;
        int minute = 0;
        boolean hasTime = false;
        if (splitDateTime.length > 2) {
            // The input does not fit the syntax
            throw new KleeException("Time and date should be written with only 1 space between them.");
        } else if (splitDateTime.length == 2) {
            //There is a space which indicates that time is given
            hour = Integer.parseInt(splitDateTime[1].substring(0, 2));
            minute = Integer.parseInt(splitDateTime[1].substring(2, 4));
            hasTime = true;
        }

        // Test which syntax of date was used
        String[] splitDate = splitDateTime[0].split("-");
        if (splitDate.length == 3) {
            year = Integer.parseInt(splitDate[0]);
            month = Integer.parseInt(splitDate[1]);
            day = Integer.parseInt(splitDate[2]);
        } else if (splitDate.length == 1) {
            splitDate = splitDateTime[0].split("/");
            if (splitDate.length == 3) {
                year = Integer.parseInt(splitDate[2]);
                month = Integer.parseInt(splitDate[1]);
                day = Integer.parseInt(splitDate[0]);
            } else {
                throw new KleeException("Dates should only be written like 27/1/2024 or 2024-1-27");
            }
        } else {
            throw new KleeException("Dates should only be written like 27/1/2024 or 2024-1-27");
        }

        LocalDateTime returnVariable = LocalDateTime.of(year, month, day, hour, minute);
        return returnVariable;
    }

    public static LocalDateTime parseDateTimeTxt (String txt) {
        String[] dateTime = txt.split(" ");
        int year = Integer.parseInt(dateTime[0]);
        int month = Integer.parseInt(dateTime[1]);
        int day = Integer.parseInt(dateTime[2]);
        int hour = Integer.parseInt(dateTime[3]);
        int minute = Integer.parseInt(dateTime[4]);
        LocalDateTime returnVariable = LocalDateTime.of(year, month, day, hour, minute);
        return returnVariable;
    }

    public static String parseTxtDateTime (LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy MM dd H m"));
    }

    public static String dateTimeString (LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy h:ma"));
    }

    public static void run (Ui ui, Parser parser, Storage storage, TaskList tasks) {
        Scanner getInput = new Scanner(System.in);
        while (true) {
            String input = getInput.nextLine();
            try {
                Command command = parser.parseInput(input);
                command.runCommand(ui, storage, tasks);
                if (command.getClass() == Bye.class) {
                    break;
                }
            } catch (KleeException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Ui ui = new Ui();
        Parser parser = new Parser();
        Storage storage = new Storage();

        TaskList tasks = storage.retrieveTasks(ui);

        ui.showWelcome();

        run(ui, parser, storage, tasks);
    }
}
