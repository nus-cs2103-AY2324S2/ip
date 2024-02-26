package duke.main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

import duke.exception.DateFormatException;
import duke.exception.FileNotFoundException;
import duke.exception.UnknownInputException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Class representing the storage system of the chatbot.
 * Stores tasks entered by user on hard drive and loads them when the programme is started
 */
public class Storage {
    private static TaskList taskList;

    private static NotesList notesList;
    private static final String PATH = "data/Storage.txt";
    public Storage(TaskList taskList, NotesList notesList) throws FileNotFoundException {
        Storage.taskList = taskList;
        Storage.notesList = notesList;
        if (!(new File(PATH).exists())) {
            throw new FileNotFoundException();
        }
    }

    public static void store() {
        try {
            FileWriter writer = new FileWriter(PATH);
            writer.write("-----TASKS-----\n");
            for (int i = 0; i < taskList.getSize(); i++) {
                writer.write(i == 0 ? (i + 1) + ": " + taskList.get(i).toString()
                        : System.lineSeparator() + (i + 1) + ": " + taskList.get(i).toString());
            }
            writer.write("\n\n-----NOTES-----\n");
            for (int i = 0; i < notesList.getSize(); i++) {
                writer.write(i == 0 ? (i + 1) + ": " + notesList.get(i)
                        : System.lineSeparator() + (i + 1) + ": " + notesList.get(i));
            }
            writer.close();
        } catch (IOException e) {
            Ui.printException(e);
        }
    }
    public static ArrayList<Task> readTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Scanner file = new Scanner(new File(PATH));
            file.nextLine();
            boolean endTasks = false;
            while (file.hasNext() && !endTasks) {
                String task = file.nextLine();
                if (!task.isEmpty() && !task.contains("NOTES")) {
                    String taskType = task.substring(4, 5);
                    System.out.println(taskType);
                    boolean isComplete = task.charAt(8) != ' ';
                    try {
                        switch (taskType) {
                        case "T":
                            String todoName = task.substring(11);
                            tasks.add(new ToDo(todoName));
                            break;
                        case "D":
                            String deadlineName = task.substring(11, task.indexOf("("));
                            String deadlineDueDate = timeParser(task.substring(task.indexOf("(") + 1, task.indexOf(")")));
                            tasks.add(new Deadline(deadlineName, deadlineDueDate));
                            break;
                        case "E":
                            String eventName = task.substring(11, task.indexOf("("));
                            String eventStartTime = timeParser(task.substring(task.indexOf("(") + 1, task.indexOf(" to ")));
                            String eventEndTime = timeParser(task.split(" to ")[1].replace(")", ""));
                            tasks.add(new Event(eventName, eventStartTime, eventEndTime));
                            break;
                        default:
                            Ui.printException(new UnknownInputException());
                        }
                        if (isComplete) {
                            tasks.get(tasks.size() - 1).setComplete();
                        }
                    } catch (DateFormatException d) {
                        Ui.printException(d);
                    }
                } else {
                    endTasks = true;
                }
            }
            file.close();
        } catch (java.io.FileNotFoundException e) {
            Ui.printException(new FileNotFoundException());
        }
        return tasks;
    }

    public static ArrayList<String> readNotes() {
        ArrayList<String> notes = new ArrayList<>();
        try {
            Scanner fileReader = new Scanner(new File(PATH));
            boolean isNotes = false;
            while (fileReader.hasNext() && !isNotes) {
                String curr = fileReader.nextLine();
                if (curr.contains("NOTES")) {
                    isNotes = true;
                }
            }
            while (fileReader.hasNext()) {
                String curr = fileReader.nextLine();
                int start = curr.indexOf(" ");
                notes.add(curr.substring(start));
            }
            fileReader.close();
        } catch (java.io.FileNotFoundException e) {
            Ui.printException(new FileNotFoundException());
        }
        return notes;
    }
    private static String timeParser(String date) {
        String[] input = date.split(", ");
        String[] dateString = input[0].split(" ");
        DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("MMM")
                .withLocale(Locale.ENGLISH);
        TemporalAccessor temporalAccessor = dtFormatter.parse(dateString[1]);
        int month = temporalAccessor.get(ChronoField.MONTH_OF_YEAR);
        String[] timeString = input[1].split(":");
        int time = (Integer.parseInt(timeString[0]) + (timeString[2].contains("pm") ? 12 : 0)) * 100;
        return dateString[0] + '/' + month + '/' + dateString[2] + " " + time;
    }

}
