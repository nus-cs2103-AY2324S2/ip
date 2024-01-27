import java.util.ArrayList;
import java.util.Arrays;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    public static ArrayList<Task> readTodoData(File f) {
        ArrayList<Task> todoList = new ArrayList<>();

        try {
            Scanner s = new Scanner(f);
            int count = 0;
            while (s.hasNext()) {
                try {
                    todoList.add(taskFromSave(s.nextLine()));
                    count++;
                } catch (TaskCreationException e) {
                    System.out.println("Error in reading task: " + e.getMessage());
                }
            }
            Duke.botPrint(count + " tasks loaded from save");
            s.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return todoList;

    }

    public static void saveTodoData(ArrayList<Task> data, File f) throws IOException {
        FileWriter fw = new FileWriter(f);
        String dataString = "";
        for (int i = 1; i <= data.size(); i++) {
            dataString = dataString + data.get(i - 1).toSave() + "\n";
        }
        
        Duke.botPrint(data.size() + " tasks saved");
        fw.write(dataString);
        fw.close();
    }

    public static Task taskFromSave(String task) throws TaskCreationException {
        String[] taskSplit = task.split("\\|");
        boolean isDone;
        if (taskSplit[1].equals("[X]")) {
            isDone = true;
        } else if (taskSplit[1].equals("[ ]")) {
            isDone = false;
        } else {
            throw new TaskCreationException("Unable to determine if task (" + task + ") is done or not");
        }
        switch (taskSplit[0]) {
        case "[T]":
            return new Todo(isDone, taskSplit[2]);
        case "[D]":
            return new Deadline(isDone, taskSplit[2], taskSplit[3]);
        case "[E]":        
            return new Event(isDone, taskSplit[2], taskSplit[3], taskSplit[4]);
        default:
            throw new TaskCreationException("No such task: " + taskSplit[0] + " for " + task);
        }
    }

}
