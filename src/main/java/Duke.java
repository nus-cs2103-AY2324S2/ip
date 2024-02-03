import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Duke {
    private static int taskNum = 0;
    private static final String FILE_PATH = "./data/duke.txt";
    private static final Task[] tasks = new Task[100];
    public static void main(String[] args) throws DukeException {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm TALKTOMEORILLDIE");
        System.out.println("     What can I do for you?");
        System.out.println("    ____________________________________________________________");

        Scanner inputs = new Scanner(System.in); //this is the scanner obj

        try {
            loadTasks();
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }

        while (true) {
            String echo = inputs.nextLine(); //this is getting the input

            if (echo.equals("bye") || echo.equals("Bye")) {
                saveTasks();
                System.out.println("    ____________________________________________________________");
                System.out.println("     Bye. Hope to see you again soon!");
                System.out.println("    ____________________________________________________________");
                break;
            }

            if (echo.equals("list") || echo.equals("List")) {
                System.out.println("    ____________________________________________________________");
                System.out.println("     Here are the tasks in your list:");
                for (int i = 0; i < taskNum; i++) {
                    System.out.println("     " + (i + 1) + ". " + tasks[i]);
                }
                System.out.println("    ____________________________________________________________");
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
                int posBy = echo.indexOf("/by");
                if (pos != -1 && pos + 1 < echo.length() && posBy != -1 && posBy + 1 < echo.length()) {
                    String taskStr = echo.substring(pos + 1, posBy - 1);
                    String taskStrby = echo.substring(posBy + 4);

                    if (taskStr.isEmpty() || taskStrby.isEmpty()) {
                        throw new DukeException("Invalid command >:((");
                    }

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
                    LocalDateTime deadlineDate = LocalDateTime.parse(taskStrby, formatter);

                    tasks[taskNum] = new Deadline(taskStr, deadlineDate);

                    tasks[taskNum].markAsNotDone();
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     Got it. I've added this task:");
                    System.out.println("       " + tasks[taskNum].toString());
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
                        removeTask(taskNumber, taskNum);
                        taskNum--;
                        System.out.println("     Now you have " + (taskNum) + " tasks in the list.");
                        System.out.println("    ____________________________________________________________");
                    } else {
                        throw new DukeException("Invalid command >:(");
                    }
                }
                System.out.println(" ");
            } else if (echo.startsWith("On") || echo.startsWith("on")) {
                int pos = echo.indexOf(" ");
                if (pos != -1 && pos + 1 < echo.length()) {
                    String dateStr = echo.substring(pos + 1);
                    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate dateToCheck = LocalDate.parse(dateStr, dateFormatter);

                    System.out.println("    ____________________________________________________________");
                    System.out.println("     Deadlines/Events occurring on " + dateToCheck.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ":");
                    for (int i = 0; i < taskNum; i++) {
                        if (tasks[i] instanceof Deadline) {
                            Deadline deadline = (Deadline) tasks[i];
                            if (deadline.getBy().toLocalDate().isEqual(dateToCheck)) {
                                System.out.println("       " + tasks[i].toString());
                            }
                        }
                    }
                    System.out.println("    ____________________________________________________________");
                } else {
                    throw new DukeException("Invalid command >:(");
                }
            } else {
                throw new DukeException("Gurl I'm sorry, idk what that means :-(");
            }
        }
    }

    private static void removeTask(int index, int taskNum) {
        for (int i = index; i < taskNum - 1; i++) {
            tasks[i] = tasks[i + 1];
        }
        tasks[taskNum - 1] = null;
    }

    private static void saveTasks() {
        try {
            File fileReader = new File(FILE_PATH);
            if (fileReader.getParentFile().mkdirs()) {
                System.out.println("Directories created successfully.");
            } else {
                System.out.println("Saving it to your already created directories");
            }


            BufferedWriter writer = new BufferedWriter(new FileWriter(fileReader));

            for (int i = 0; i < taskNum; i++) {
                writer.write(tasks[i].toSaveString());
                writer.newLine();
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("can't save :((");
        }
    }

    private static void loadTasks() throws IOException {
        Path filePath = Paths.get(FILE_PATH);
        if (!Files.exists(filePath)) {
            return;
        }

        BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));

        String line;
        while ((line = reader.readLine()) != null) {
            char taskType = line.charAt(0);
            boolean isDone = line.charAt(4) == '1';

            int Sep1 = line.indexOf("|", 4);

            if (taskType == 'T') {
                String description = line.substring(Sep1 + 2);
                tasks[taskNum] = new Todo(description);
            } else if (taskType == 'D') {
                int Sep2 = line.indexOf(" | ", Sep1 + 3);
                String description = (Sep2 != -1) ? line.substring(Sep1 + 2, Sep2) : line.substring(Sep2 + 3);

                String dateString = line.substring(line.lastIndexOf("|") + 1).trim();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                LocalDateTime deadlineDateTime = LocalDateTime.parse(dateString, formatter);

                tasks[taskNum] = new Deadline(description, deadlineDateTime);
            } else if (taskType == 'E') {
                int Sep2 = line.indexOf(" | ", Sep1 + 3);
                int to = line.indexOf(" - ", Sep2 + 3);
                String description = (Sep2 != -1) ? line.substring(Sep1 + 2, Sep2) : line.substring(Sep2 + 3);
                String from = line.substring(Sep2 + 3, to);
                String too = line.substring(to + 3);
                tasks[taskNum] = new Event(description, from, too);
            }

            if (isDone) {
                tasks[taskNum].markAsDone();
            }

            taskNum++;
        }
    }
}
