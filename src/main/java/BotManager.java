import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class BotManager {
    private final String name;
    private final ArrayList<Task> tasks;
    private final File file;

    BotManager(String fileFolder, String fileName, String name) {
        this.name = name;
        this.tasks = new ArrayList<>();
        File directory = new File(fileFolder);
        if (!directory.exists()) {
            try {
                boolean isDirMade = directory.mkdirs();
                if (!isDirMade) {
                    throw new DukeException("    Oops! Something is wrong with directory creation!\n");
                }
            } catch (DukeException e) {
                System.out.println(e);
            }
        }
        file = new File(fileFolder + "/" + fileName);
        if (!file.exists()) {
            try {
                boolean isCreated = file.createNewFile();
                if (!isCreated) {
                    throw new DukeException("    Oops! Something is wrong with file creation!\n");
                }
            } catch (IOException e) {
                System.out.println("    Oops! Unable to create a file for storage!\n");
            } catch (DukeException e) {
                System.out.println(e);
            }
        } else {
            try {
                Scanner sc = new Scanner(file);
                while (sc.hasNextLine()) {
                    String line = sc.nextLine();
                    tasks.add(Task.lineToTask(line));
                }
            } catch (FileNotFoundException e) {
                System.out.println("    Oops! Unable to find the file!\n");
            } catch (DukeException e) {
                System.out.println(e);
            }
        }
    }

    void greeting() {
        System.out.println("    Hello! I'm " + name);
        System.out.println("    What can I do for you?\n");
    }

    void exit() {
        System.out.println("    Bye. Hope to see you again soon!\n");
    }

    private Task createTask(String prompt) throws DukeException {
        String[] order = prompt.split(" ", 2);
        String taskType = order[0];
        switch (taskType) {
        case "todo":
            if (order.length == 1) {
                throw new DukeException("    Oops! Not sure about the description of the todo!\n");
            } else if (order[1].trim().isEmpty()) {
                throw new DukeException("    Oops! Not sure about the description of the todo!\n");
            }
            return new Todo(order[1]);
        case "deadline":
            String[] deadline = order[1].split(" /by ");
            if (deadline.length == 1) {
                throw new DukeException("    Oops! Incorrect format!\n");
            } else if (deadline[0].trim().isEmpty()) {
                throw new DukeException("    Oops! Not sure about the description of the deadline!\n");
            } else if (deadline[1].trim().isEmpty()) {
                throw new DukeException("    Oops! Not sure about the deadline!\n");
            }
            return new Deadline(deadline[0], deadline[1]);
        case "event":
            String[] startTime = order[1].split(" /from ");
            if (startTime.length == 1) {
                throw new DukeException("    Oops! Incorrect format!\n");
            } else if (startTime[0].trim().isEmpty()) {
                throw new DukeException("    Oops! Not sure about the description of the event!\n");
            } else if (startTime[1].split(" /to ")[0].trim().isEmpty()) {
                throw new DukeException("    Oops! Not sure about the starting time!\n");
            }
            String[] endTime = order[1].split(" /to ");
            if (endTime.length == 1) {
                throw new DukeException("    Oops! Incorrect format!\n");
            } else if (endTime[1].trim().isEmpty()) {
                throw new DukeException("    Oops! Not sure about the ending time!\n");
            }
            return new Event(startTime[0], startTime[1].split(" /to ")[0], endTime[1]);
        default:
            throw new DukeException("    Sorry! I don't see what you mean...\n");
        }
    }

    private void addTask(String prompt) throws DukeException {
        Task task = createTask(prompt);
        try {
            FileWriter fw = new FileWriter(file, true);
            fw.write(task.taskToLine() + "\n");
            fw.close();
        } catch (IOException e) {
            System.out.println("    Oops! unable to write to the file!\n");
        }
        tasks.add(task);
        System.out.println("    Got it. I've added this task:");
        System.out.println("      " + task);
        if (tasks.size() == 1) {
            System.out.println("    Now you have 1 task in the list\n");
        } else {
            System.out.printf("    Now you have %d tasks in the list\n%n", tasks.size());
        }
    }

    private void deleteTask(int num) throws DukeException {
        if (num < 0 || num > tasks.size()) {
            throw new DukeException("    OOPS! Invalid Index!\n");
        }
        Task task = tasks.remove(num - 1);
        System.out.println("    Noted. I've removed this task:");
        System.out.println("      " + task);
        if (tasks.size() == 1) {
            System.out.println("    Now you have 1 task in the list\n");
        } else {
            System.out.printf("    Now you have %d tasks in the list\n", tasks.size());
        }
        deleteLineInFile(num);
    }

    private void list() {
        if (tasks.isEmpty()) {
            System.out.println("    No tasks yet...\n");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println("    " + (i + 1) + "." + tasks.get(i));
            }
        }
        System.out.println();
    }

    private void mark(int num) throws DukeException {
        if (num <= 0 || num > tasks.size()) {
            throw new DukeException("    OOPS! Invalid Index!\n");
        }
        Task task = tasks.get(num - 1);
        if (task.isDone()) {
            System.out.println("    Already done. No need to mark again.\n");
        } else {
            task.mark();
            System.out.println("    Nice! I've marked this task as done:");
            System.out.println("      " + task + '\n');
            editLineInFile(num, task);
        }
    }

    private void unmark(int num) throws DukeException {
        if (num <= 0 || num > tasks.size()) {
            throw new DukeException("OOPS! Invalid Index!\n");
        }
        Task task = tasks.get(num - 1);
        if (!task.isDone()) {
            System.out.println("    Not done in the first place. No need to unmark.\n");
        } else {
            task.unmark();
            System.out.println("    OK, I've marked this task as not done yet:");
            System.out.println("      " + task + '\n');
            editLineInFile(num, task);
        }
    }

    private void deleteLineInFile(int num) {
        try {
            int i = 1;
            ArrayList<String> lines = new ArrayList<>();
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String newLine = sc.nextLine();
                if (i != num) {
                    lines.add(newLine);
                }
                i++;
            }
            FileWriter fw = new FileWriter(file);
            for (String line : lines) {
                fw.write(line + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("    Oops! Something is wrong with the file management!\n");
        }
    }

    private void editLineInFile(int num, Task task) {
        try {
            int i = 1;
            ArrayList<String> lines = new ArrayList<>();
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String newLine = sc.nextLine();
                if (i != num) {
                    lines.add(newLine);
                } else {
                    lines.add(task.taskToLine());
                }
                i++;
            }
            FileWriter fw = new FileWriter(file);
            for (String line : lines) {
                fw.write(line + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("    Oops! Something is wrong with the file management!\n");
        }
    }

    void searchDate(String date) throws DukeException {
        LocalDate localDate = Task.parseDate(date);
        ArrayList<Task> result = new ArrayList<>();
        for (Task task : tasks) {
            if (task.matchDate(localDate)) {
                result.add(task);
            }
        }
        if (result.isEmpty()) {
            System.out.println("    Sorry! No tasks can satisfy your query conditions...");
        } else {
            System.out.println("    OK! The search results are as follows:");
            for (int i = 1; i <= result.size(); i++) {
                System.out.printf("    %d. %s\n", i, result.get(i - 1));
            }
        }
    }

    void answer(String prompt) {
        String[] order = prompt.split(" ");
        try {
            switch (order[0]) {
            case "list":
                list();
                break;
            case "mark":
                mark(Integer.parseInt(order[1]));
                break;
            case "unmark":
                unmark(Integer.parseInt(order[1]));
                break;
            case "delete":
                deleteTask(Integer.parseInt(order[1]));
                break;
            case "search":
                searchDate(order[1]);
                break;
            default:
                addTask(prompt);
            }
        } catch (DukeException e) {
            System.out.println(e);
        }
    }
}
