import java.io.FileWriter;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Duke {
    private static final String FILE_PATH = "./data/duke.txt";
    public static void main(String[] args) throws Exception {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Hello! I'm Yappy\n    What can I do for you?");
        System.out.println("    ____________________________________________________________");

        ArrayList<Task> tasks = new ArrayList<>();

        loadTasksFromFile(tasks, FILE_PATH);

        while(true) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            String input = bufferedReader.readLine();
            String[] token = input.split(" ");

            if (input.equals("bye")) {
                break;
            } else if (token.length == 1 && !input.equals("list")) {
                throw new DukeException("SOMETHING WENT WRONG!! Invalid input.");
            } else if (input.equals("list")) {
                System.out.println("    ____________________________________________________________");
                try {
                    if (tasks.isEmpty()) {
                        throw new DukeException("Your task list is empty.");
                    } else {
                        System.out.println("     Here are the tasks in your list");
                        for (int i = 1; i <= tasks.size(); i++) {
                            System.out.println("       " + i + "." + tasks.get(i - 1));
                        }
                    }
                } catch (DukeException e) {
                    System.out.println("       Error: " + e.getMessage());
                }
                System.out.println("    ____________________________________________________________");
            } else if (token[0].equals("mark")) {
                try {
                    int index = Integer.parseInt(token[1]) - 1;

                    if (index >= 0 && index < tasks.size()) {
                        tasks.get(index).markAsDone();
                        System.out.println("    ____________________________________________________________");
                        System.out.println("     Nice! I've marked this task as done:");
                        System.out.println("       " + tasks.get(index));
                        System.out.println("    ____________________________________________________________");
                    } else {
                        System.out.println("    ____________________________________________________________");
                        System.out.println("     Error: Invalid index. Please provide a valid task index.");
                        System.out.println("    ____________________________________________________________");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     Error: Please provide a valid numeric index for marking.");
                    System.out.println("    ____________________________________________________________");
                }
            } else if (token[0].equals("unmark")) {
                try {
                    int index = Integer.parseInt(token[1]) - 1;

                    if (index >= 0 && index < tasks.size()) {
                        tasks.get(index).markAsUndone();
                        System.out.println("    ____________________________________________________________");
                        System.out.println("     OK, I've marked this task as not done yet:");
                        System.out.println("       " + tasks.get(index));
                        System.out.println("    ____________________________________________________________");
                    } else {
                        System.out.println("    ____________________________________________________________");
                        System.out.println("     Error: Invalid index. Please provide a valid task index.");
                        System.out.println("    ____________________________________________________________");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     Error: Please provide a valid numeric index for unmarking.");
                    System.out.println("    ____________________________________________________________");
                }
            } else if (token[0].equals("deadline")) {
                String[] tokenD = input.split("/");

                String by = tokenD[1].substring(3).trim();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
                LocalDateTime deadlineDateTime = LocalDateTime.parse(by, formatter);

                Task d = new Deadline(tokenD[0].substring(9).trim(), deadlineDateTime);
                tasks.add(d);
                System.out.println("    ____________________________________________________________");
                System.out.println("     Got it. I've added this task:");
                System.out.println("       " + d);
                System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
                System.out.println("    ____________________________________________________________");
            } else if (token[0].equals("event")) {
                String[] tokenE = input.split("/");

                // Parse the date and time strings for 'from' and 'to'
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
                LocalDateTime fromDateTime = LocalDateTime.parse(tokenE[1].substring(5).trim(), formatter);
                LocalDateTime toDateTime = LocalDateTime.parse(tokenE[2].substring(3).trim(), formatter);

                // Create Events task with LocalDateTime for 'from' and 'to'
                Task e = new Events(tokenE[0].substring(6).trim(), fromDateTime, toDateTime);
                tasks.add(e);
                System.out.println("    ____________________________________________________________");
                System.out.println("     Got it. I've added this task:");
                System.out.println("       " + e);
                System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
                System.out.println("    ____________________________________________________________");
            } else if (token[0].equals("todo")) {
                if (input.substring(4).trim().isEmpty()) {
                    throw new DukeException("SOMETHING WENT WRONG!! No description found for your todo.");
                }
                Task t = new Todos(input.substring(4).trim());
                tasks.add(t);
                System.out.println("    ____________________________________________________________");
                System.out.println("     Got it. I've added this task:");
                System.out.println("       " + t);
                System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
                System.out.println("    ____________________________________________________________");
            } else if(token[0].equals("delete")) {
                try {
                    int index = Integer.parseInt(token[1]) - 1;

                    if (index >= 0 && index < tasks.size()) {
                        System.out.println("    ____________________________________________________________");
                        System.out.println("     Noted. I've removed this task:");
                        System.out.println("       " + tasks.get(index));
                        System.out.println("     Now you have " + (tasks.size() - 1) + " tasks in the list.");
                        System.out.println("    ____________________________________________________________");
                        tasks.remove(index);
                    } else {
                        System.out.println("    ____________________________________________________________");
                        System.out.println("     Error: Invalid index. Please provide a valid task index.");
                        System.out.println("    ____________________________________________________________");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     Error: Please provide a valid numeric index for deletion.");
                    System.out.println("    ____________________________________________________________");
                }
            } else {
                Task n = new Task(input);
                tasks.add(n);
                System.out.println("    ____________________________________________________________");
                System.out.println("    added: " + input);
                System.out.println("    ____________________________________________________________");
            }
            saveTasksToFile(tasks, FILE_PATH);
        }
        System.out.println("    ____________________________________________________________");
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }
    public static void saveTasksToFile(ArrayList<Task> taskList, String filePath) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : taskList) {
                bufferedWriter.write(task.toFileString());
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadTasksFromFile(ArrayList<Task> taskList, String filePath) {
        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Task task = createTaskFromLine(line);
                taskList.add(task);
            }

            bufferedReader.close();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Task createTaskFromLine(String line) {
        String[] parts = line.split(" \\| ");

        if(parts.length > 2) {
            String type = parts[0];
            String status = parts[1];
            String description = parts[2];

            Task task;

            switch (type) {
            case "T":
                task = new Todos(description);
                break;
            case "D":
                String byString = parts[3];

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
                LocalDateTime byDateTime = LocalDateTime.parse(byString, formatter);

                task = new Deadline(description, byDateTime);
                break;
            case "E":
                String fromString = parts[3];
                String toString = parts[4];

                DateTimeFormatter formatterE = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
                LocalDateTime fromDateTime = LocalDateTime.parse(fromString, formatterE);
                LocalDateTime toDateTime = LocalDateTime.parse(toString, formatterE);

                task = new Events(description, fromDateTime, toDateTime);
                break;
            default:
                task = new Task(description);
                break;
            }

            if (status.equals("1")) {
                task.markAsDone();
            }

            return task;
        } else {
            String status = parts[0];
            String description = parts[1];

            Task task = new Task(description);

            if (status.equals("1")) {
                task.markAsDone();
            }

            return task;
        }
    }
}