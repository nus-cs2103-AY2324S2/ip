import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {

    public ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }



    public void addTask(ArrayList<String> input, Task task) {
        try {
            if (input.get(0).equals("todo")) {
                    tasks.add(task);
            } else if (input.get(0).equals("deadline")) {
                try{
                    tasks.add(task);
                } catch (DateTimeParseException e) {
                    throw new YpxmmException("Brother, follow format can or not? Enter dates in dd-mm-yyyy HHmm (24-08-2024 1800)");
                }
            } else {
                try {
                    tasks.add(task);
                } catch (DateTimeParseException e) {
                    throw new YpxmmException("Brother, follow format can or not? Enter dates in dd-mm-yyyy HHmm (24-08-2024 1800)");
                }
            }
        } catch (YpxmmException y) {
            System.out.println(y.getMessage());
        }
    }

    public void deleteTask(int index) {
        try {
            try {
                String t = tasks.get(index - 1).toString();
                tasks.remove(index - 1);

            } catch (IndexOutOfBoundsException e) {
                throw new YpxmmException("Eh u seh isit? Now your list got " +
                        (tasks.isEmpty() ? "no tasks to delete." : tasks.size() +
                                " tasks, enter any number from 1 to " + tasks.size()));
            }
        } catch (YpxmmException y) {
            System.out.println(y.getMessage());
        }
    }
}
