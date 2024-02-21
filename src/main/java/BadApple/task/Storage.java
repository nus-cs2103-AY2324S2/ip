package BadApple.task;

import BadApple.main.BadAppleException;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

        StringBuilder stringBuilder = new StringBuilder();
        int taskIndex = 1;
        while (bufferedReader.ready()) {
            String line = bufferedReader.readLine();
            // deconstruct the line:
            ArrayList<String> args = new ArrayList<>(Arrays.asList(line.split(" ")));

            String command = "";
            String query = "";
            StringBuilder taskName;
            boolean status = args.get(2).equals("[X]");

            if (!(args.size() < 2)) {
                command = args.get(1);
            } else {
                return;
            }

            switch (command.toLowerCase()) {
                case "todo":
                    // the fourth token should be the task name for this command.
                    if (args.size() < 4) {
                        throw new BadAppleException("Todo Task in wrong format, " +
                                "should be <number> todo <status> <taskName>");
                    }
                    taskName = new StringBuilder();
                    for (int i = 3; i < args.size(); i++) {
                        taskName.append(args.get(i));
                    }

                    query = "todo " + taskName;
                    break;

                case "deadline":
                    if (args.size() < 6 || !args.contains("(by:")) {
                        throw new BadAppleException("Deadline in wrong format" +
                                "should be <number> 'deadline' <status> <description> '(by: ' <ByValue>");
                    }
                    taskName = new StringBuilder();
                    StringBuilder by = new StringBuilder();
                    int separator = args.indexOf("(by:");
                    for (int i = 3; i < separator; i++) {
                        taskName.append(args.get(i)).append(" ");
                    }
                    for (int i = separator + 1; i < args.size() - 1; i++) {
                        by.append(args.get(i)).append(" ");
                    }
                    by.deleteCharAt(by.length() - 1);
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM uuuu");
                    LocalDate byValue = LocalDate.parse(by, formatter);
                    query = "deadline " + taskName + "/by " + byValue;
                    break;
                case "event":
                    if (args.size() < 8 || !(args.contains("(from:") && args.contains("to:"))) {
                        throw new BadAppleException("Event in wrong format" +
                                "should be <no.> 'event' <status> <description> " +
                                "'(from: ' <fromValue> 'to: ' <toValue>");
                    }
                    taskName = new StringBuilder();
                    StringBuilder from = new StringBuilder();
                    StringBuilder to = new StringBuilder();
                    int fromSeparator = args.indexOf("(from:");
                    for (int i = 3; i < fromSeparator; i++) {
                        taskName.append(args.get(i)).append(" ");
                    }
                    int toSeparator = args.indexOf("to:");
                    for (int i = fromSeparator + 1; i < toSeparator; i++) {
                        from.append(args.get(i)).append(" ");
                    }
                    for (int i = toSeparator + 1; i < args.size() - 1; i++) {
                        to.append(args.get(i)).append(" ");
                    }
                    query = "event " + taskName + "/from " + from + "/to " + to;
                    break;

                default:
                    System.out.println("unrecognizable command detected");
                    throw new BadAppleException("The sun shined brighter when your files weren't corrupted");
            }

            // upon reconstructing the command, execute it.
            Parser.ProcessQuery(query);
            if (status) {
                // if this task is already complete, mark it.
                Parser.ProcessQuery("mark " + taskIndex);
            }
            taskIndex++;
        }
    }
}
