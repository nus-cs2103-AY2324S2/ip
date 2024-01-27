package fishstock;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

class FishStock {
    private Ui ui;
    private String filePath;

    private FishStock(String filePath) {
        this.filePath = filePath;
    }

    protected static boolean startsWith(String keyword, String input) {
        return input.length() >= keyword.length() &&
                keyword.equals(input.substring(0, keyword.length()));
    }

    protected static Task getTaskFromIndex(String keyword, String input, ArrayList<Task> list) {
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

    protected static boolean addTask(ArrayList<Task> list, String input, boolean isDone, boolean verbose) {
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

    protected static boolean addTask(ArrayList<Task> list, String input) {
        return addTask(list, input, false, true);
    }

    private void run() {
        ArrayList<Task> list = new ArrayList<>();
        String pathToDb = filePath.substring(0, filePath.lastIndexOf("/"));
        File db = new File(filePath);
        Ui ui = new Ui();

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

        ui.run(list);

        try {
            FileWriter fw = new FileWriter(db);
            for (Task task : list) {
                fw.write(task.toSaveString());
            }
            fw.close();

        } catch (
                IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        new FishStock("./data/tasks.txt").run();
    }
}
