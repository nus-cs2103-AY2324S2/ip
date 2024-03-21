package duke;

import duke.task.*;
import duke.exceptions.FileIOException;


import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Acts as file handling class which is responsible for storing and reading tasks.
 */
public class Storage {
    private File FILE;

    /**
     * Constructs a storage object.
     * @param filePath Consists of a filepath for storing and reading tasks.
     */
    public Storage(String filePath) {
        this.FILE = new File(filePath);
    }

    /**
     * Constructs a Storage Object.
     */
    public Storage() {}

    /**
     * Creates a File if it is not there
     * @throws FileIOException If unsuccessful in creating directory.
     */
    private void create() throws FileIOException {
        try {
            File parent = FILE.getParentFile();
            if (parent != null && !parent.exists() && !parent.mkdirs()) {
                throw new IOException("Couldn't create dir: " + parent);
            }
            FILE.createNewFile();
        } catch (IOException e) {
            throw new FileIOException(e.getMessage());
        }
    }

        /**
     * Saves the tasks in the file from the list of tasks.
     * @param list List of tasks.
     * @throws FileIOException if unsuccessful in creating file.
     */
        protected void saveInFile(TaskList list) throws FileIOException {
            try {
                if (!FILE.exists()) {
                    create();
                }
                FileWriter fw = new FileWriter(FILE);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
            for (int i = 0; i < list.size(); i++) {
                Task task = list.get(i);
                    if (task instanceof Deadline) {
                        Deadline deadline = (Deadline) task;
                        fw.write("D| " + (deadline.checkDone() ? "1" : "0") + "| " +
                                deadline.getDescription() + "| " + deadline.getBy().format(formatter) + "\n");
                    } else if (task instanceof Event) {
                        Event event = (Event) task;
                        fw.write("E| " + (event.checkDone() ? "1" : "0") + "| " +
                                event.getDescription() + "| " + event.getStart().format(formatter) +
                                "-" + event.getEnd().format(formatter) + "\n");
                    } else if (task instanceof Todo) {
                        Todo todo = (Todo) task;
                        fw.write("T| " + (todo.checkDone() ? "1" : "0") + "| " +
                                todo.getDescription() + "\n");
                    }
                }
                fw.close();
            } catch (IOException e) {
                throw new FileIOException(e.getMessage());
            }
        }



    /**
     * Loads tasks from a file and returns them as a list of Task objects.
     *
     * @return A List of Task objects loaded from the file.
     */
    public List<Task> readFromFile() {
        List<Task> list = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        try {
            if (!FILE.exists()) {
                return list;
            }
            FileReader fr = new FileReader(FILE);
            Scanner sc = new Scanner(fr);
            while(sc.hasNext()) {
                String line = sc.nextLine();
                String[] split = line.split("\\| ");
                switch (split[0]) {
                    case "D":
                        LocalDateTime deadlineDateTime = LocalDateTime.parse(split[3], formatter);
                        Deadline tempDeadline = new Deadline(split[2], deadlineDateTime);
                        if ("1".equals(split[1])) {
                            tempDeadline.markAsDone();
                        }
                        list.add(tempDeadline);
                        break;
                    case "E":
                        String[] eventTimes = split[3].split("-");
                        LocalDateTime startEventDateTime = LocalDateTime.parse(eventTimes[0], formatter);
                        LocalDateTime endEventDateTime = LocalDateTime.parse(eventTimes[1], formatter);
                        Event tempEvent = new Event(split[2], startEventDateTime, endEventDateTime);
                        if ("1".equals(split[1])) {
                            tempEvent.markAsDone();
                        }
                        list.add(tempEvent);
                        break;
                    case "T":
                        Todo tempToDo = new Todo(split[2]);
                        if ("1".equals(split[1])) {
                            tempToDo.markAsDone();
                        }
                        list.add(tempToDo);
                        break;
                }
            }
            sc.close();
        } catch (IOException e) {
            // Handle IOException as needed
        }
        return list;
    }
}