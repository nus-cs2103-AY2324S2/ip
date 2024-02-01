import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.ArrayList;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void addTask(int i, String text, Storage storage) {
        Task x = null;
        if (i == 0) {
            x = new ToDo(text.trim());
        } else if (i == 1) {
            try {
                String[] parts = text.split("/by");
                if (parts.length < 2) {
                    throw new DukeException("Deadlines need a deadline /by ... ");
                } else if (parts.length > 2) {
                    throw new DukeException("There can only be 1 instance of /by. String cannot be parsed...");
                }
                try {
                    x = new Deadline(parts[0].trim(), parts[1].trim());
                } catch (DateTimeParseException d) {
                    if (d.getMessage().equals("Start date cannot be after end date")) {
                        System.out.println(d.getMessage());
                    } else {
                        System.out.println("Enter date in the format yyyy-mm-ddTHH:MM");
                    }
                    return;
                }
            } catch (DukeException d) {
                System.out.println(d);
                return;
            }

        } else if (i == 2) {
            String[] parts = text.split("/from");

            try {
                if (parts.length < 2) {
                    throw new DukeException("Events need a /from and a /to in this order");
                }
                String[] dates = parts[1].split("/to");
                if (parts.length > 2 || dates.length > 2) {
                    throw new DukeException("There can only be 1 instance of /from and /to\n" +
                            "String cannot be parsed...");
                } else if (dates.length < 2) {
                    throw new DukeException("Events need a /from and a /to in this order");
                }
                try {
                    x = new Event(parts[0].trim(), dates[0].trim(), dates[1].trim());
                    storage.saveTasks(this);
                } catch (DateTimeParseException d) {
                    if (d.getMessage().equals("Start date cannot be after end date")) {
                        System.out.println(d.getMessage());
                    } else {
                        System.out.println("Enter date in the format yyyy-mm-ddTHH:MM");
                    }
                    return;
                }
            } catch (DukeException d) {
                System.out.println(d);
                return;
            }

        }
        this.tasks.add(x);
        storage.saveTasks(this);
        System.out.println("Got it. I've added this task:");
        System.out.println(x);
        System.out.println("Now you have " + this.tasks.size() + " tasks in your list.");
    }

    private void deleteTask(int index) {
        this.tasks.remove(index);
    }

    public void deleteTask(String indexStr, Storage storage) {
        try {
            if (indexStr.trim().equals("")) {
                throw new DukeException("Give an index to remove");
            }
            int index = Integer.parseInt(indexStr.trim());
            Task t = this.getTask(index - 1);
            this.deleteTask(index - 1);
            System.out.println("Noted. I've removed this task:");
            System.out.println(t);
            System.out.println("Now you have " + this.getSize() + " tasks in the list.");
            storage.saveTasks(this);

        } catch (NumberFormatException n) {
            System.out.println("Index to be removed needs to be an integer");
        } catch (IndexOutOfBoundsException i) {
            System.out.println("Give a valid index to remove");
        } catch (DukeException d) {
            System.out.println(d);
        }
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    public int getSize() {
        return this.tasks.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");
        for (int i = 0; i < this.tasks.size(); i++) {
            sb.append(i + 1).append(". ").append(this.tasks.get(i)).append("\n");
        }
        return sb.toString();
    }

    public void print() {
        System.out.println(this);
    }
}
