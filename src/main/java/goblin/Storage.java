package goblin;

import goblin.task.Deadlines;
import goblin.task.Events;
import goblin.task.Task;
import goblin.task.ToDos;
import goblin.exception.OrkException;

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.File;
import java.util.ArrayList;
import java.io.IOException;


public class Storage {
    protected static String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public static ArrayList<Task> readFile() throws OrkException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File myObj = new File(filePath);
            Scanner reader = new Scanner(myObj);
            while (reader.hasNextLine()) {
                String taskInfo = reader.nextLine();
                String[] info = taskInfo.split(" ", 3);
                boolean isDone = info[0].equals("true");
                String type = info[1];
                String apple = info[2];
                if (type.equals("deadline")) {
                    String[] split = apple.split(" /by ");
                    String description = split[0];
                    String DateAndTime = split[1];
                    Deadlines deadline = new Deadlines(description, DateAndTime);
                    if (isDone) {
                        deadline.done();
                    }
                    tasks.add(deadline);
                } else if (type.equals("todo")) {
                    ToDos todo = new ToDos(apple);
                    if (isDone) {
                        todo.done();
                    }
                    tasks.add(todo);
                } else {
                    String[] split = apple.split(" /from ");
                    String description = split[0];
                    String StartAndEnd = split[1];
                    String[] split2 = StartAndEnd.split((" /to "));
                    String start = split2[0];
                    String end = split2[1];
                    Events event = new Events(description, start, end);
                    if (isDone) {
                        event.done();
                    }
                    tasks.add(event);
                }
            }
            return tasks;
        }
        catch (FileNotFoundException e) {
            throw new OrkException("Storage not found");
        }
    }

    public static void writeToDisk(TaskList list) throws OrkException {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            ArrayList<Task> taskList = TaskList.getList();
            for (int i = 0; i < TaskList.list.size(); i++) {
                Task task = taskList.get(i);
                String type = task.type();
                int isDone = task.isDone() ? 1 : 0;
                String description = task.getDescription();
                String taskInfo;
                if (type.equals("[D]")) {
                    Deadlines deadline = (Deadlines) task;
                    taskInfo = deadline.toString();
                } else if (type.equals("[T]")) {
                    ToDos todo = (ToDos) task;
                    taskInfo = todo.toString();
                } else {
                    Events event = (Events) task;
                    taskInfo = event.toString();
                }
                fileWriter.write(taskInfo.toString() + "\n");
            }
            fileWriter.close();
        } catch (IOException exception) {
            throw new OrkException("Something went wrong :" + exception.getMessage());
        }
    }
}
