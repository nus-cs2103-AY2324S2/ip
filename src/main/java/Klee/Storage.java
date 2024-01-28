package Klee;

import Klee.task.Deadline;
import Klee.task.Event;
import Klee.task.Task;
import Klee.task.ToDo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static String path = "data";
    private static String fileName = "data/Klee.txt";

    public Storage () {}

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

    public void saveTasks (TaskList tasks) {
        try {
            FileWriter file = new FileWriter("data/Klee.txt");
            for (int i = 0; i < tasks.size(); i++) {
                Task currentTask = tasks.get(i);
                String line = currentTask.toText();
                file.write(line + "\n");
            }
            file.close();
        } catch (IOException e) {
            System.out.println("Klee has run out of ink! I cannot write this down!");
        }
    }

    public TaskList retrieveTasks (Ui ui) {
        TaskList tasks = new TaskList();

        //Create /data directory only if it does not exist
        new File(path).mkdirs();
        try {
            File data = new File(fileName);
            if (!data.exists()) {
                data.createNewFile();
            } else {
                Scanner readFile = new Scanner(data);
                while (readFile.hasNext()) {
                    String read = readFile.nextLine();
                    String[] line = read.split(" / ");
                    switch (line[0]) {
                    case "T":
                        tasks.add(ToDo.fromText(line[2], line[1]));
                        break;
                    case "D":
                        tasks.add(Deadline.fromText(line[2], line[1], parseDateTimeTxt(line[3])));
                        break;
                    case "E":
                        tasks.add(Event.fromText(line[2], line[1], parseDateTimeTxt(line[3]), parseDateTimeTxt(line[4])));
                        break;
                    }
                }
            }
        } catch (IOException e) {
            ui.showError("Klee could not find the book we wrote on last time... I'm sorry...");
        }
        return tasks;
    }
}
