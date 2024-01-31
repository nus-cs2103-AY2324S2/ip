import java.io.*;
import java.util.Scanner;

public class Duke {
    private static int taskNum = 0;
    private static final String FILE_PATH = "./data/duke.txt";
    private static final Task[] tasks = new Task[100];
    public static void main(String[] args) {
        System.out.println("    _______________________________________________________");
        System.out.println("     Hello! I'm TALKTOMEORILLDIE");
        System.out.println("     What can I do for you?");
        System.out.println("    _______________________________________________________");

        Scanner inputs = new Scanner(System.in); //this is the scanner obj

        try {
            loadTasks();
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }

        while (true) {
            try {
                String echo = inputs.nextLine(); //this is getting the input

                if (echo.equals("bye") || echo.equals("Bye")) {
                    System.out.println("    _______________________________________________________");
                    System.out.println("     Bye. Hope to see you again soon!");
                    System.out.println("    _______________________________________________________");
                    break;
                }

                if (echo.equals("list") || echo.equals("List")) {
                    System.out.println("    _______________________________________________________");
                    System.out.println("     Here are the tasks in your list:");
                    for (int i = 0; i < taskNum; i++) {
                        System.out.println("     " + (i + 1) + ". " + tasks[i]);
                    }
                    System.out.println("    _______________________________________________________");
                } else if (echo.startsWith("mark") || echo.startsWith("Mark")) {
                    int pos = echo.indexOf(" ");
                    if (pos != -1 && pos + 1 < echo.length()) {
                        String taskStr = echo.substring(pos + 1);

                        int taskNumber = Integer.parseInt(taskStr) - 1; //cause array
                        if (taskNumber >= 0 && taskNumber < taskNum) {
                            tasks[taskNumber].markAsDone();
                            System.out.println("    ____________________________________________________________");
                            System.out.println("     Nice! I've marked this task as done:");
                            System.out.println("       " + tasks[taskNumber]);
                            System.out.println("    ____________________________________________________________");
                        } else {
                            throw new DukeException("Invalid task number >:((");
                        }
                    }
                } else if (echo.startsWith("unmark") || echo.startsWith("Unmark")) {
                    int pos = echo.indexOf(" ");
                    if (pos != -1 && pos + 1 < echo.length()) {
                        String taskStr = echo.substring(pos + 1);

                        int taskNumber = Integer.parseInt(taskStr) - 1;
                        if (taskNumber >= 0 && taskNumber < taskNum) {
                            tasks[taskNumber].markAsNotDone();
                            System.out.println("    ____________________________________________________________");
                            System.out.println("     Nice! I've marked this task as not done:");
                            System.out.println("       " + tasks[taskNumber]);
                            System.out.println("    ____________________________________________________________");
                        } else {
                            throw new DukeException("Invalid task number >:((");
                        }
                    }
                } else if (echo.startsWith("todo") || echo.startsWith("Todo")) {
                    int pos = echo.indexOf(" ");
                    if (pos != -1 && pos + 1 < echo.length()) {
                        String taskStr = echo.substring(pos + 1);

                        if (taskStr.isEmpty()) {
                            throw new DukeException("Are you gonna be doing nothing?");
                        }

                        tasks[taskNum] = new Todo(taskStr);

                        tasks[taskNum].markAsNotDone();
                        System.out.println("    ____________________________________________________________");
                        System.out.println("     Got it. I've added this task:");
                        System.out.println("       " + tasks[taskNum]);
                        taskNum++;
                        System.out.println("     Now you have " + (taskNum) + " tasks in the list.");
                        System.out.println("    ____________________________________________________________");
                    } else {
                        throw new DukeException("Invalid command >:((");
                    }
                } else if (echo.startsWith("deadline") || echo.startsWith("Deadline")) {
                    int pos = echo.indexOf(" ");
                    int posBy = echo.indexOf("/");
                    if (pos != -1 && pos + 1 < echo.length() && posBy != -1 && posBy + 1 < echo.length()) {
                        String taskStr = echo.substring(pos + 1, posBy - 1);
                        String taskStrby = echo.substring(posBy + 4);

                        if (taskStr.isEmpty() || taskStrby.isEmpty()) {
                            throw new DukeException("Invalid command >:((");
                        }

                        tasks[taskNum] = new Deadline(taskStr, taskStrby);

                        tasks[taskNum].markAsNotDone();
                        System.out.println("    ____________________________________________________________");
                        System.out.println("     Got it. I've added this task:");
                        System.out.println("       " + tasks[taskNum]);
                        taskNum++;
                        System.out.println("     Now you have " + (taskNum) + " tasks in the list.");
                        System.out.println("    ____________________________________________________________");
                    } else {
                        throw new DukeException("Invalid command >:((");
                    }
                } else if (echo.startsWith("event") || echo.startsWith("Event")) {
                    int pos = echo.indexOf(" ");
                    int posFrom = echo.indexOf("/from");
                    int posTo = echo.indexOf("/to");
                    if (pos != -1 && pos + 1 < echo.length() && posFrom != -1 && posFrom + 1 < echo.length() && posTo != -1 && posTo + 1 < echo.length()) {
                        String taskStr = echo.substring(pos + 1, posFrom - 1);
                        String taskStrFrom = echo.substring(posFrom + 6, posTo - 1);
                        String taskStrTo = echo.substring(posTo + 4);

                        if (taskStr.isEmpty() || taskStrFrom.isEmpty() || taskStrTo.isEmpty()) {
                            throw new DukeException("Invalid command >:((");
                        }

                        tasks[taskNum] = new Event(taskStr, taskStrFrom, taskStrTo);

                        tasks[taskNum].markAsNotDone();
                        System.out.println("    ____________________________________________________________");
                        System.out.println("     Got it. I've added this task:");
                        System.out.println("       " + tasks[taskNum]);
                        taskNum++;
                        System.out.println("     Now you have " + (taskNum) + " tasks in the list.");
                        System.out.println("    ____________________________________________________________");
                    } else {
                        throw new DukeException("Invalid command >:(");
                    }
                } else if (echo.startsWith("delete") || echo.startsWith("Delete")) {
                    int pos = echo.indexOf(" ");
                    if (pos != -1 && pos + 1 < echo.length()) {
                        String taskStr = echo.substring(pos + 1);
                        int taskNumber = Integer.parseInt(taskStr) - 1;

                        if (taskStr.isEmpty()) {
                            throw new DukeException("You're deleting air");
                        }

                        if (taskNumber >= 0 && taskNumber < taskNum) {
                            System.out.println("    ____________________________________________________________");
                            System.out.println("     Noted. I've removed this task:");
                            System.out.println("       " + tasks[taskNumber]);
                            removeTask(tasks, taskNumber, taskNum);
                            taskNum--;
                            System.out.println("     Now you have " + (taskNum) + " tasks in the list.");
                            System.out.println("    ____________________________________________________________");
                        } else {
                            throw new DukeException("Invalid command >:(");
                        }
                    }
                } else {
                    throw new DukeException("Gurl I'm sorry, idk what that means :-(");
                }

                //saveTasks();
            } catch (DukeException e) {
                System.out.println("    ____________________________________________________________");
                System.out.println("     OOPS!!! " + e.getMessage());
                System.out.println("    ____________________________________________________________");
            }
        }
    }

    private static void removeTask(Task[] tasks, int index, int tasknum) {
        for (int i = index; i < tasknum - 1; i++) {
            tasks[i] = tasks[i + 1];
        }
        tasks[tasknum - 1] = null;
    }

    private static void loadTasks(Task[] tasks) {
    }

    private static void loadTasks() throws IOException {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));

            String line;
            while ((line = reader.readLine()) != null) {
                char taskType = line.charAt(0);
                boolean isDone = line.charAt(4) == '1';

                int Sep1 = line.indexOf(" | ", 6);
                int Sep2 = line.indexOf(" | ", Sep1 + 3);
                int to = line.indexOf(" - ", Sep1 + 3);

                String description = line.substring(Sep1 + 3, Sep2);

                if (taskType == 'T') {
                    tasks[taskNum] = new Todo(description);
                } else if (taskType == 'D') {
                    String by = line.substring(Sep2 + 3);
                    tasks[taskNum] = new Deadline(description, by);
                } else if (taskType == 'E') {
                    String from = line.substring(Sep2 + 3);
                    String too = line.substring(to + 3);
                    tasks[taskNum] = new Event(description, from, too);
                }

                if (isDone) {
                    tasks[taskNum].markAsDone();
                }

                taskNum++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("File doesn't exist??");
        }
    }
}
