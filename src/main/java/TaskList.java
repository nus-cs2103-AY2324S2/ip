import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

class TaskList {
    private final ArrayList<Task> tasks;

    TaskList(ArrayList<String> load) throws DukeException {
        tasks = new ArrayList<>();
        for (String task : load) {
            tasks.add(lineToTask(task));
        }
    }

    void print() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks yet...");
        } else {
            System.out.println("The tasks as as follows:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + "." + tasks.get(i));
            }
        }
    }

    Task retrieve(int num) throws DukeException {
        if (num <= 0 || num > tasks.size()) {
            throw new DukeException("OOPS! Invalid Index!");
        }
        return tasks.get(num - 1);
    }

    Task remove(int num) throws DukeException {
        if (num <= 0 || num > tasks.size()) {
            throw new DukeException("OOPS! Invalid Index!");
        }
        return tasks.remove(num - 1);
    }

    void countSize() {
        if (tasks.size() == 1) {
            System.out.println("Now you have 1 task in the list");
        } else {
            System.out.printf("Now you have %d tasks in the list\n", tasks.size());
        }
    }

    private Task lineToTask(String line) throws DukeException {
        String[] lineSplit = line.split(" \\| ");
        switch (lineSplit[0]) {
        case "T":
            Task newTask = new Todo(lineSplit[2]);
            if (lineSplit[1].equals("O")) {
                newTask.mark();
            }
            return newTask;
        case "D":
            if (lineSplit.length < 4) {
                throw new DukeException("Oops! The file format is wrong!");
            }
            newTask = new Deadline(lineSplit[2], lineSplit[3]);
            if (lineSplit[1].equals("O")) {
                newTask.mark();
            }
            return newTask;
        case "E":
            if (lineSplit.length < 5) {
                throw new DukeException("Oops! The file format is wrong!");
            }
            newTask = new Event(lineSplit[2], lineSplit[3], lineSplit[4]);
            if (lineSplit[1].equals("O")) {
                newTask.mark();
            }
            return newTask;
        default:
            throw new DukeException("Oops! The file format is wrong!");
        }
    }

    void searchDate(LocalDate localDate) {
        ArrayList<Task> result = new ArrayList<>();
        for (Task task : tasks) {
            if (task.matchDate(localDate)) {
                result.add(task);
            }
        }
        if (result.isEmpty()) {
            System.out.println("Sorry! No tasks can satisfy your query conditions...");
        } else {
            System.out.println("OK! The search results are as follows:");
            for (int i = 1; i <= result.size(); i++) {
                System.out.printf("  %d. %s\n", i, result.get(i - 1));
            }
        }
    }

    void add(Task task) {
        tasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        countSize();
    }
}
