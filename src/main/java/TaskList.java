import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class TaskList {
    
    private ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    public void uiAddNewTask(Task task, Ui ui) {
        ui.add(String.format("Okay! added this task:"));
        ui.add(task.toString());
        ui.add(String.format("Now you have %d tasks in the list.", this.list.size()));
    }

    public void addTask(String task, String fullDescription, Ui ui) throws DukeCeption {
        try {
            switch (task) {
                case "todo":
                    this.addTodo(fullDescription, ui);
                    break;
                case "deadline":
                    this.addDeadline(fullDescription, ui);
                    break;
                case "event":
                    this.addEvent(fullDescription, ui);
                    break;
            }
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeCeption("Make sure your /from/to/by is correct");
        }
    }

    public void addTodo(String description, Ui ui) throws DukeCeption {
        if (description.isEmpty()) {
            throw new DukeCeption("Todo cannot be empty!");
        } else {
            Task task = new ToDo(description);
            this.list.add(task);
            this.uiAddNewTask(task, ui);
        }
    }

    public void addDeadline(String description, Ui ui) throws DukeCeption {
        String[] descriptionList = description.split("/by", 2);
        try {
            if (description.isEmpty()) {
                throw new DukeCeption("Deadline cannot be empty!");
            } else {
                String taskDescription = descriptionList[0].trim();
                String by = descriptionList[1].trim();
                Task task = new Deadline(taskDescription, by);
                this.list.add(task);
                this.uiAddNewTask(task, ui);
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeCeption("Make sure /by is written properly");
        }
        
    }

    public void addEvent(String description, Ui ui) throws DukeCeption {
        try {
            if (description.isEmpty()) {
                throw new DukeCeption("Event cannot be empty!");
            } else {
                String[] descriptionList = description.split("/from", 2);
                String[] fromAndToList = descriptionList[1].split("/to", 2);
                String taskDescription = descriptionList[0].trim();
                String from = fromAndToList[0].trim();
                String to = fromAndToList[1].trim();
                Task task = new Event(taskDescription, from, to);
                this.list.add(task);
                this.uiAddNewTask(task, ui);
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeCeption("Make sure /from and /to is written properly");
        }
        
    }

    public void markOrDelete(String command, String taskNumberString, Ui ui) throws DukeCeption {
        try {
            int taskNumber = Integer.parseInt(taskNumberString);
            switch (command) {
                case "mark":
                    this.mark(taskNumber, ui);
                    break;
                case "unmark":
                    this.unmark(taskNumber, ui);
                    break;
                case "delete":
                    this.delete(taskNumber, ui);
                    break;
            }
        } catch (NumberFormatException e) {
            throw new DukeCeption("The number given is unrecognizable");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeCeption("The number is not in this list!");
        }
    }
           
    public void delete(int taskNumber, Ui ui) throws DukeCeption {
        Task removedTask = this.list.get(taskNumber - 1);
        this.list.remove(taskNumber - 1);
        ui.add("This task is now removed:");
        ui.add(removedTask.toString());
        ui.add(String.format("Now you have %d tasks in the list.", this.list.size()));
    }

    public void mark(int taskNumber, Ui ui) {
        Task task = this.list.get(taskNumber - 1);
        task.markAsDone();
        ui.add("Great! I will mark this as done:");
        ui.add(task.toString());
    }

    public void unmark(int taskNumber, Ui ui) {
        Task task = this.list.get(taskNumber - 1);
        task.markAsNotDone();
        ui.add("Alright! this task is now unmarked:");
        ui.add(task.toString());
    }

    public void getList(Ui ui) {
        ui.add("Here are the tasks in your list:");
        for (int i = 0; i < this.list.size(); i++) {
            ui.add(String.format("%d. %s",
                    i + 1,
                    this.list.get(i)));
        }
    }

    public void textToTask(String line) {
        String[] separate = line.split(";;");
        String taskType = separate[0];
        boolean isDone = separate[1].equals("1") ? true : false;
        String description = separate[2];
        Task task;

        switch (taskType) {
            case "T":
                task = new ToDo(description, isDone);
                break;
            case "D":
                String by = separate[3];
                task = new Deadline(description, by, isDone);
                break;
            default:
                String from = separate[3];
                String to = separate[4];
                task = new Event(description, from, to, isDone);
                break;
        }
        list.add(task);
    }

    public void loadList(File file, Ui ui) {
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                this.textToTask(scanner.nextLine());
            }
            scanner.close();
            ui.add("File retrieved!");
        } catch (FileNotFoundException e) {
            ui.add(e.getMessage());
        } catch (Exception e) {
            ui.add("File is corrupted :/");
            ui.add("Making new file instead");
            list = new ArrayList<Task>();
        } finally {
            ui.print();
        }
    }

    public void saveList(File file) {
        try {
            FileWriter writer = new FileWriter(file, false);
            for (Task line : list) {
                writer.write(line.saveFormat() + "\n");
            }
            writer.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    } 

}
