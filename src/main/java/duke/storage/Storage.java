package duke.storage;

import duke.parser.Parser;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;
import duke.task.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDate;
import java.time.LocalDateTime;

import java.util.Scanner;

public class Storage {
    Parser parser = new Parser();
    public String filePath = "./src/main/";
    public String directoryPath = "./src/main/";
    public Storage(String filePath) {
        this.directoryPath += filePath.substring(0, filePath.lastIndexOf("/"));
        this.filePath += filePath;
    }
    public void loadFile(TaskList tasks) throws IOException {
        File f = new File(filePath);
        Scanner sc = new Scanner(f);
        while (sc.hasNext()) {
            String[] taskDescriptions = sc.nextLine().split("\\|");
            String taskName = taskDescriptions[1];
            boolean done = Boolean.parseBoolean(taskDescriptions[2]);
            switch (taskDescriptions[0]) {
                case "todo" :
                    tasks.addTask(new ToDo(taskName, done));
                    break;
                case "deadline" :
                    if (taskDescriptions[3].split("T").length > 1) {
                        LocalDateTime time = LocalDateTime.parse(taskDescriptions[3]);
                        tasks.addTask(new Deadline(taskName, done, time));
                    } else {
                        LocalDate time = LocalDate.parse(taskDescriptions[3]);
                        tasks.addTask(new Deadline(taskName, done, time));
                    }
                    break;
                case "event" :
                    tasks.addTask(new Event(taskName, done, taskDescriptions[3], taskDescriptions[4]));
                    break;
                default:
                    break;
            }
        }
    }

    public void writeFile(TaskList tasks) throws IOException {
        File f = new File(this.filePath);
        FileWriter writer = new FileWriter(f);
        writer.write(tasks.storeTasks());
        writer.close();
    }
}
