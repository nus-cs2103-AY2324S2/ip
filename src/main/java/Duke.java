import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;

import common.DukeException;
import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("\nHello from\n" + logo);
        System.out.println("________________________________________\n");

        System.out.println("Hello! I'm NextGenerationJarvis.");
        System.out.println("What can I do for you?");
        System.out.println("________________________________________\n");

        /** Used to store Tasks */
        LinkedList<Task> taskList = new LinkedList<>();

        // Read file
        File file = new File("./data/duke.txt");
        int currentAttempt = 0;
        int maxAttempt = 2;

        while (++currentAttempt <= maxAttempt) {
            try {
                System.out.println("Startup Attempt #" + currentAttempt + "/" + maxAttempt + ":");
                if (file.createNewFile()) {
                    System.out.println("duke.txt does not exist.");
                    System.out.println("duke.txt successfully created.");
                } else {
                    System.out.println("duke.txt exist.");
                    System.out.println("duke.txt successfully loaded.");
                }
                break;
            } catch (IOException e) {
                System.out.println("IOException occured: " + e.getMessage());

                // creating directory
                File dir = new File("./data");
                boolean isDirectoryCreated = dir.mkdir();
                if (isDirectoryCreated) {
                    System.out.println("Directory ./data created.");
                }
            } finally {
                System.out.println("________________________________________\n");
            }
        }

        // Reading inputs from the file
        // e.g. E | 1 | resiDANCE | *time*
        try {
            Scanner fileScanner = new Scanner(file);
            boolean hasError = false;

            while (fileScanner.hasNext()) {
                String[] tasks = fileScanner.nextLine().split(" \\| ");
                String type = tasks[0];
                boolean isDone = Integer.parseInt(tasks[1]) == 1 ? true : false;

                if (type.equals("T")) {
                    ToDo td = new ToDo(isDone, tasks[2]);
                    taskList.add(td);

                } else if (type.equals("D")) {
                    try {
                        Deadline d = new Deadline(isDone, tasks[2], tasks[3]);
                        taskList.add(d);

                    } catch (DukeException e) {
                        System.out.println(e.getMessage() + "\n");
                        hasError = true;
                    }

                } else if (type.equals("E")) {
                    try {
                        Event e = new Event(isDone, tasks[2], tasks[3]);
                        taskList.add(e);
                    } catch (DukeException e) {
                        System.out.println(e.getMessage() + "\n");
                        hasError = true;
                    }
                }
            }
            fileScanner.close();
            System.out.println(hasError
                    ? "________________________________________\n"
                    : "data.txt loaded without error.\n________________________________________\n");

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine().strip();

        // loop only exits if input is "bye"
        while (!userInput.toLowerCase().equals("bye")) {
            System.out.println("________________________________________\n");

            // Level-2: if the input is "list"
            if (userInput.toLowerCase().equals("list")) {
                System.out.println("Here are the tasks in your list:");

                for (int i = 1; i <= taskList.size(); i++) {
                    Task t = taskList.get(i - 1);
                    System.out.println(i + ". " + t.toString());
                }

            } else {
                // using String splitting as main parsing tool
                // format <cmd> <task name> /by | /from <deadline> /to <endtime>
                StringTokenizer st = new StringTokenizer(userInput);
                String cmd = st.nextToken();

                // Level-3: mark & unmark
                if (cmd.equals("mark") || (cmd.equals("unmark"))) {
                    try {
                        int indexOfTask = Integer.parseInt(st.nextToken());
                        changeStatus(taskList, cmd, indexOfTask);

                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Invalid task number. :(");

                    } catch (NumberFormatException e) {
                        System.out.println("Input is not an integer. :(");

                    } catch (NoSuchElementException e) {
                        System.out.println("Missing task number. :(");
                    }

                    // Level-4: ToDo, Deadline, Event
                } else if (cmd.equals("todo") || cmd.equals("event") || cmd.equals("deadline")) {
                    try {
                        addTask(taskList, cmd, st);

                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }

                    // Level-6: Delete
                } else if (cmd.equals("delete")) {
                    try {
                        int indexOfTask = Integer.parseInt(st.nextToken());
                        delete(taskList, cmd, indexOfTask);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Invalid task number. :(");

                    } catch (NumberFormatException e) {
                        System.out.println("Input is not an integer. :(");

                    } catch (NoSuchElementException e) {
                        System.out.println("Missing task number. :(");
                    }

                    // Level-5: Throw exception for other inputs
                } else {
                    try {
                        throw new DukeException("OOPS!! Pls try again. :)");
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
            System.out.println("________________________________________\n");
            userInput = scanner.nextLine();
        }

        // writing to file
        try {
            FileWriter fw = new FileWriter(file, false);
            for (Task t : taskList) {
                fw.write(t.toData());
                fw.write(System.lineSeparator());
            }
            fw.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("________________________________________\n");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("________________________________________\n");

        scanner.close();
    }

    // Level-3: mark & unmark
    public static void changeStatus(LinkedList<Task> taskList, String cmd,
            int indexOfTask) throws IndexOutOfBoundsException,
            NumberFormatException, NoSuchElementException {

        Task t = taskList.get(indexOfTask - 1);
        if (cmd.equals("mark")) {
            t.markAsDone();
            System.out.println("Nice! I've marked this task as done:");

        } else if (cmd.equals("unmark")) {
            t.unmark();
            System.out.println("OK, I've marked this task as not done yet:");
        }

        System.out.println(" " + t.toString());
    }

    // Level-4: ToDo, Deadline, Event
    public static void addTask(LinkedList<Task> taskList, String cmd,
            StringTokenizer st) throws DukeException {

        if (cmd.equals("todo")) {
            String taskName = "";
            while (st.hasMoreTokens()) {
                taskName += st.nextToken() + " ";
            }
            if (taskName.equals("")) {
                throw new DukeException("Missing task name. :(");
            } else {
                ToDo td = new ToDo(taskName.strip());
                taskList.add(td);
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + td.toString());
                System.out.println("Now you have " + taskList.size() + " tasks in the list.");
            }

        } else if (cmd.equals("deadline")) {
            try {
                String taskName = "";
                String deadline = "";

                while (st.hasMoreTokens()) {
                    String temp = st.nextToken();
                    if (temp.equals("/by")) {
                        deadline = st.nextToken();
                        break;
                    } else {
                        taskName += temp + " ";
                    }
                }

                if (taskName.equals("") || deadline.equals("")) {
                    throw new DukeException("Missing field(s) / incorrect input(s). :(\nCheck if you have used the keyword \"/by\"");
                } else {
                    Deadline d = new Deadline(taskName.strip(), deadline);
                    taskList.add(d);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + d.toString());
                    System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                }

            } catch (NoSuchElementException e) {
                System.out.println("Missing deadline. :(");
            }

        } else if (cmd.equals("event")) {
            try {
                String taskName = "";
                String startTime = "";
                String endTime = "";

                while (st.hasMoreTokens()) {
                    String temp = st.nextToken();
                    if (temp.equals("/from")) {
                        startTime = st.nextToken();
                        continue;
                    } else if (temp.equals("/to")) {
                        endTime = st.nextToken();
                        break;
                    } else {
                        taskName += temp + " ";
                    }
                }

                if (taskName.equals("") || startTime.equals("") || endTime.equals("")) {
                    throw new DukeException("Missing field(s) / incorrect input(s). :(\nCheck if you have used the keyword \"/from\" and \"/to\"");
                } else {
                    Event e = new Event(taskName.strip(), startTime, endTime);
                    taskList.add(e);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + e.toString());
                    System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                }
                
            } catch (NoSuchElementException e) {
                System.out.println("Missing field(s) / incorrect input(s). :(\nCheck if you have used the keyword \"/from\" and \"/to\"");
            }
        }

    }

    // Level-6: Delete
    public static void delete(LinkedList<Task> taskList, String cmd,
            int indexOfTask) throws IndexOutOfBoundsException,
            NumberFormatException, NoSuchElementException {

        Task t = taskList.remove(indexOfTask - 1);

        System.out.println("Noted, I've removed this task:");
        System.out.println(" " + t.toString());
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }
}
