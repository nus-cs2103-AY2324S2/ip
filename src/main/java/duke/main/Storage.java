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
        Scanner file = new Scanner(PATH);
        file.nextLine();
        while (file.hasNext()) {
            String task = file.nextLine();
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
                    String deadlineDueDate = timeParser(task.substring(task.indexOf("(" + 1), task.indexOf(")")));
                    tasks.add(new Deadline(deadlineName, deadlineDueDate));
                    break;
                case "E":
                    String eventName = task.substring(11, task.indexOf("("));
                    String eventStartTime = timeParser(task.substring(task.indexOf("(" + 1), task.indexOf(" to ")));
                    String eventEndTime = timeParser(task.split(" to ")[0].substring(0, task.split(" to ")[0].indexOf(")")));
                    tasks.add(new Event(eventName, eventStartTime, eventEndTime));
                    break;
                default:
                    Ui.printException(new UnknownInputException());
                }
                if (isComplete) {
                    tasks.get(tasks.size()).setComplete();
                }
            } catch (DateFormatException d) {
                Ui.printException(d);
            }
        }
        return tasks;
    }

    public static ArrayList<String> readNotes() {
        Scanner file = new Scanner(PATH);
        ArrayList<String> notes = new ArrayList<>();
        boolean isNotes = false;
        while (file.hasNext() && !isNotes) {
            String curr = file.nextLine();
            if (curr.equals("-----NOTES-----")) {
                isNotes = true;
            }
        }
        while (file.hasNext()) {
            String curr = file.nextLine();
            int start = curr.indexOf(" ");
            notes.add(curr.substring(start));
        }
        return notes;
    }
    private static String timeParser(String date) {
        String[] dateString = date.split(" ");
        DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("MMM")
                .withLocale(Locale.ENGLISH);
        TemporalAccessor temporalAccessor = dtFormatter.parse(dateString[1]);
        int month = temporalAccessor.get(ChronoField.MONTH_OF_YEAR);
        String[] timeString = dateString[3].split(":");
        int time = (Integer.parseInt(timeString[0]) + (dateString[3].contains("pm") ? 12 : 0)) * 100;
        StringBuilder dueDate = new StringBuilder();
        dueDate.append(dateString[0] + '/' + month + '/' + dateString[2] + " " + time);
        return dueDate.toString();
    }

}
