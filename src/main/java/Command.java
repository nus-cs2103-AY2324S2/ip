import task.Task;
import task.taskList;
import task.Event;
import task.Todo;
import task.Deadline;
import util.DukeException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Command {
//    public static final String FILE_LOCATION = "data/duke.txt";

    public static Task parse(String inputCommand) {
        String[] tokens = inputCommand.split(" \\| ");
//        for (int i = 0; i < tokens.length; i++) {
//            tokens[i].trim();
//        }
        String taskType = tokens[0];
        boolean isDone = tokens[1].equals("O");
        String description = tokens[2];
        Task task;

        switch(taskType) {
            case "T":
                task = new Todo(description, isDone);
                break;
            case "D":
                String deadlineBy = tokens[3];
                task = new Deadline(description, deadlineBy, isDone);
                break;
            case "E":
                String eventAt = tokens[3];
                task = new Event(description, eventAt, isDone);
                break;
            default:
                throw new IllegalStateException("I cannot recognize this task type!" + taskType);
        }
        return task;
    }

    public static void readFile(String filePath, taskList tasklist) {
        try {
            File file = new File(filePath);
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String input = sc.nextLine();
                tasklist.addTask(parse(input));
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    public static void process(String inputCommand, taskList tasklist) {
        if (inputCommand.equals("list")) {
            tasklist.listDownTask();
        } else if (inputCommand.startsWith("mark")) {
            char pos = inputCommand.charAt(5);
            int index = Integer.parseInt(String.valueOf(pos));
            tasklist.markTask(index);
        } else if (inputCommand.startsWith("unmark")) {
            char pos = inputCommand.charAt(7);
            int index = Integer.parseInt(String.valueOf(pos));
            tasklist.unmarkTask(index);
        } else if (inputCommand.startsWith("todo")) {
            try {
                String des = inputCommand.substring(5);
                Task todo = new Todo(des);
                tasklist.addTask(todo);
            } catch (StringIndexOutOfBoundsException e) {
                DukeException error = new DukeException(e);
                System.out.println(error);
            }
        } else if (inputCommand.startsWith("deadline")) {
            String[] separate = inputCommand.substring(9).split("\\|");
            for (int i = 0; i < separate.length; i++) {
                separate[i] = separate[i].trim();
            }
            Task deadline = new Deadline(separate[0], separate[1]);
            tasklist.addTask(deadline);
        } else if (inputCommand.startsWith("event")) {
            String[] separate = inputCommand.substring(6).split("\\|");
            for (int i = 0; i < separate.length; i++) {
                separate[i] = separate[i].trim();
            }
            Task event = new Event(separate[0], separate[1]);
            tasklist.addTask(event);
        } else if(inputCommand.startsWith("delete")) {
            char pos = inputCommand.charAt(7);
            int index = Integer.parseInt(String.valueOf(pos));
            tasklist.deleteTask(index);
        } else {
            System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    public static void writeFile(String filePath, taskList tasklist) {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (int i = 0; i < tasklist.getSize(); i++) {
                Task task = tasklist.getTask(i);
                fw.write(task.outputString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error detected: " + e.getMessage());
        }
    }
}
