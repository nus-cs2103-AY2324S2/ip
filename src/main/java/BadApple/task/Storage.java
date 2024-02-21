package BadApple.task;

import BadApple.main.BadAppleException;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Storage {
    /**
     * After each request by the user, erases previous contents of the file
     * and rebuilds the file based on task list
     * @param file the file to write tasks to
     * @throws IOException if file writing fails for any reason
     */
    public static void updateTasks(File file) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));

        // write to file
        int index = 1;
        for (Task t : TaskList.tasks) {
            String task = index + " " + t.toString() + "\n";
            bufferedWriter.write(task);
            index++;
        }

        bufferedWriter.close();
    }

    /**
     * given a File and its contents, add it into the Tracker's taskList
     * @param file the file to read from
     * @throws IOException when a file can't be read from
     * @throws BadAppleException when the file contents are in the wrong format (i.e. non-command)
     */
    public static void loadSave(File file) throws IOException, BadAppleException {
        // check the file to see what tasks are already available.
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

        int taskIndex = 1;
        while (bufferedReader.ready()) {
            String line = bufferedReader.readLine();
            Command c;
            // deconstruct the line:
            ArrayList<String> args = new ArrayList<>(Arrays.asList(line.split(" ")));

            String command;
            boolean status = args.get(2).equals("[X]");
            command = args.get(1);

            switch (command.toLowerCase()) {
                case "todo":
                    c = Todo.parseTodoFromFile(args);
                    break;
                case "deadline":
                    c = Deadline.parseDeadlineFromReader(args);
                    break;
                case "event":
                    c = Event.parseEventFromReader(args);
                    break;
                default:
                    System.out.println("unrecognizable command detected");
                    throw new BadAppleException("The sun shined brighter when your files weren't corrupted");
            }

            c.execute();
            if (status) {
                // if this task is already complete, mark it.
                Parser.ProcessQuery("mark " + taskIndex);
            }
            taskIndex++;
        }
    }
}
