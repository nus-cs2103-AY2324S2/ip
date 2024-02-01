package duke;

import duke.task.*;
import exceptions.FileIOException;


import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private File FILE;

    public Storage(String filePath) {
        this.FILE = new File(filePath);
    }

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

//    public void saveInFile(TaskList list) throws FileIOException {
//        try {
//            if (!FILE.exists()) {
//                create();
//            }
//            FileWriter fw = new FileWriter(FILE);
//            for (int i = 0; i < list.size(); i++) {
//                Task task = list.get(i);
//                switch (task.type()) {
//                    case "D":
//                        Deadline deadline = (Deadline) task;
//                        fw.write(deadline.type() + " | " + (deadline.getStatusIcon().isBlank() ? "0" : "1") + " | " +
//                                deadline.getDescription() + " | " +
//                                deadline.getBy());
//                        break;
//                    case "E":
//                        Event Event = (Event) task;
//                        fw.write(Event.type() + " | " + (Event.getStatusIcon().isBlank() ? "0" : "1") + " | " +
//                                Event.getDescription() + " | " +
//                                Event.getDate());
//                        break;
//                    case "T":
//                        Todo toDo = (Todo) task;
//                        fw.write(toDo.type() + " | " + (toDo.getStatusIcon().isBlank() ? "0" : "1") + " | " +
//                                toDo.getDescription());
//                        break;
//                }
//                fw.write("\n");
//            }
//            fw.close();
//        } catch (IOException e) {
//            throw new FileIOException(e.getMessage());
//        }

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
                        fw.write("D| " + (deadline.isDone ? "1" : "0") + "| " +
                                deadline.description + "| " + deadline.getBy().format(formatter) + "\n");
                    } else if (task instanceof Event) {
                        Event event = (Event) task;
                        fw.write("E| " + (event.isDone ? "1" : "0") + "| " +
                                event.description + "| " + event.getStart().format(formatter) +
                                "-" + event.getEnd().format(formatter) + "\n");
                    } else if (task instanceof Todo) {
                        Todo todo = (Todo) task;
                        fw.write("T| " + (todo.isDone ? "1" : "0") + "| " +
                                todo.description + "\n");
                    }
                }
                fw.close();
            } catch (IOException e) {
                throw new FileIOException(e.getMessage());
            }
        }


    public List<Task> load() {
        List<Task> list = new ArrayList<>();
        try {
            if (!FILE.exists()) {
                return list;
            }
            FileReader fr = new FileReader(FILE);
            Scanner sc = new Scanner(fr);
            while(sc.hasNext()) {
                String line = sc.nextLine();
                String[] split = line.split(" \\| ");
                switch (split[0]) {
                    case "D":
                        Deadline tempDeadline = new Deadline(split[2], LocalDateTime.parse(split[3], DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")));
                        if (split[1].equals("1")) {
                            tempDeadline.markAsDone();
                        }
                        list.add(tempDeadline);
                        break;
                    case "E":
                        String[] start_end = split[3].split("-");
                        Event tempEvent = new Event(split[2],LocalDateTime.parse(start_end[0], DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")), LocalDateTime.parse(start_end[1], DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")));
                        if (split[1].equals("1")) {

                        }
                        list.add(tempEvent);
                        break;
                    case "T":
                        Todo tempToDo = new Todo(split[2]);
                        if (split[1].equals("1")) {
                            tempToDo.markAsDone();
                        }
                        list.add(tempToDo);
                        break;
                }
            }
        } catch (IOException e) {
        }
        return list;
    }


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