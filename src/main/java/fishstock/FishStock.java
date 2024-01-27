package fishstock;

import java.nio.file.Files;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

class FishStock {
    protected static boolean startsWith(String keyword, String input) {
        return input.length() >= keyword.length() &&
                keyword.equals(input.substring(0, keyword.length()));
    }

    private static Task getTaskFromIndex(String keyword, String input, ArrayList<Task> list) {
        try {
            int num = Integer.parseInt(input.substring(keyword.length() + 1));
            return list.get(num - 1);

        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("OH NOSE! Task number cannot be empty..");
        } catch (NumberFormatException e) {
            System.out.println("OH NOSE! Task number has to be an integer..");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("OH NOSE! Task number must be in valid range..");
        }
        return null;
    }

    private static void addTaskHelper(ArrayList<Task> list, Task task, boolean isDone, boolean verbose) {
        if (task != null) {
            list.add(task);
            if (isDone) {
                task.markAsDone();
            }
            if (verbose) {
                System.out.println("This task has been added:\n  " + task +
                        "\n" + "Now you have " + list.size() +
                        " task(s) in total.");
            }
        }
    }

    private static boolean addTask(ArrayList<Task> list, String input, boolean isDone, boolean verbose) {
        if (startsWith(Todo.keyword, input)) {
            addTaskHelper(list, Todo.of(input), isDone, verbose);

        } else if (startsWith(Deadline.keyword, input)) {
            addTaskHelper(list, Deadline.of(input), isDone, verbose);

        } else if (startsWith(Event.keyword, input)) {
            addTaskHelper(list, Event.of(input), isDone, verbose);

        } else {
            return false;
        }
        return true;
    }

    private static boolean addTask(ArrayList<Task> list, String input) {
        return addTask(list, input, false, true);
    }

    public static void main(String[] args) throws IOException {
        ArrayList<Task> list = new ArrayList<>();
        String pathToDb = "./data/";
        String dbName = "tasks.txt";
        File db = new File(pathToDb + dbName);

        try {
            Scanner sc = new Scanner(db);
            while (sc.hasNext()) {
                String line = sc.nextLine();
                boolean isDone = line.endsWith("1");
                addTask(list, line.substring(0, line.length() - 2), isDone, false);
            }
        } catch (FileNotFoundException e) {
            new File(pathToDb).mkdir();
        }

        String line = "____________________________________________________________\n";
        Scanner sc = new Scanner(System.in);
        String input;

        System.out.println(line + "Hello, I'm FishStock.\nI might help if I feel like it.");

        while (true) {
            System.out.println(line);
            input = sc.nextLine();
            System.out.print(line);

            if ("bye".equals(input)) {
                break;
            }

            // list all Tasks
            if ("list".equals(input)) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println((i + 1) + "." + list.get(i));
                }

            // mark Task as done
            } else if (startsWith("mark", input)) {
                Task task = getTaskFromIndex("mark", input, list);
                if (task != null) {
                    task.markAsDone();
                    System.out.println("Did you actually finish this? \uD83E\uDD14:\n" +
                                       "  " + task);
                }

            // mark Task as undone
            } else if (startsWith("unmark", input)) {
                Task task = getTaskFromIndex("unmark", input, list);
                if (task != null) {
                    task.markAsUndone();
                    System.out.println("I knew you didn't finish it! \uD83D\uDE0F:\n" +
                                       "  " + task);
                }

            } else if (startsWith("delete", input)) {
                Task task = getTaskFromIndex("delete", input, list);
                if (task != null) {
                    list.remove(task);
                    System.out.println("This task has been removed:\n  " + task +
                                       "\n" + "Now you have " + list.size() +
                                       " task(s) in total.");
                }

            // try to add as a task
            } else if (!addTask(list, input)) {
                System.out.println("OH NOSE! Wakaranai... :(");
            }
        }

        try {
            FileWriter fw = new FileWriter(db);
            for (Task task : list) {
                fw.write(task.toSaveString());
            }
            fw.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Bye! You'll be back ;)\n" + line);
    }
}
