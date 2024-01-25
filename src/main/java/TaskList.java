import java.util.ArrayList;

public class TaskList {
    
    ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    public void addTask(String task, String fullDescription, PrintList printList) throws DukeCeption{
        try {
            if (task.toLowerCase().equals("todo")) {
                this.addTodo(fullDescription, printList);
            } else if (task.toLowerCase().equals("deadline")) {
                this.addDeadline(fullDescription, printList);
            } else {
                this.addEvent(fullDescription, printList);
            }
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeCeption("Make sure your /from/to/by is correct");
        }
    }

    public void printListAddNewTask(Task task, PrintList printList) {
        printList.add(String.format("Okay! added this task:"));
        printList.add(task.toString());
        printList.add(String.format("Now you have %d tasks in the list.", this.list.size()));
    }

    public void addTodo(String description, PrintList printList) throws DukeCeption {
        if (description.isEmpty()) {
            throw new DukeCeption("Todo cannot be empty!");
        } else {
            Task task = new ToDo(description);
            this.list.add(task);
            this.printListAddNewTask(task, printList);
        }
    }

    public void addDeadline(String description, PrintList printList) throws DukeCeption {
        if (description.isEmpty()) {
            throw new DukeCeption("Deadline cannot be empty!");
        } else {
            int indexOfBy = description.indexOf("/by");
            String taskDescription = description.substring(0, indexOfBy - 1);
            String by = description.substring(indexOfBy + 4);
            Task task = new Deadline(taskDescription, by);
            this.list.add(task);
            this.printListAddNewTask(task, printList);
        }
    }

    public void addEvent(String description, PrintList printList) throws DukeCeption {
        if (description.isEmpty()) {
            throw new DukeCeption("Event cannot be empty!");
        } else {
            int indexOfBy = description.indexOf("/from");
            int indexOfTo = description.indexOf("/to");
            String taskDescription = description.substring(0, indexOfBy - 1);
            String from = description.substring(indexOfBy + 6, indexOfTo - 1);
            String to = description.substring(indexOfTo + 4);
            Task task = new Event(taskDescription, from, to);
            this.list.add(task);
            this.printListAddNewTask(task, printList);
        }
    }

    public void markOrDelete(String command, String taskNumberString, PrintList printList) throws DukeCeption {
        try {
            int taskNumber = Integer.parseInt(taskNumberString);
            switch (command) {
                case "mark":
                    this.mark(taskNumber, printList);
                    break;
                case "unmark":
                    this.unmark(taskNumber, printList);
                    break;
                case "delete":
                    this.delete(taskNumber, printList);
                    break;
            }
        } catch (NumberFormatException e) {
            throw new DukeCeption("The number given is unrecognizable");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeCeption("The number is not in this list!");
        }
    }
           
    public void delete(int taskNumber, PrintList printList) throws DukeCeption {
        Task removedTask = this.list.get(taskNumber - 1);
        this.list.remove(taskNumber - 1);
        printList.add("This task is now removed:");
        printList.add(removedTask.toString());
        printList.add(String.format("Now you have %d tasks in the list.", this.list.size()));
    }

    public void mark(int taskNumber, PrintList printList) {
        Task task = this.list.get(taskNumber - 1);
        task.markAsDone();
        printList.add("Great! I will mark this as done:");
        printList.add(task.toString());
    }

    public void unmark(int taskNumber, PrintList printList) {
        Task task = this.list.get(taskNumber - 1);
        task.markAsNotDone();
        printList.add("Alright! this task is now unmarked:");
        printList.add(task.toString());
    }

    public void getList(PrintList printList) {
        printList.add("Here are the tasks in your list:");
        for (int i = 0; i < this.list.size(); i++) {
            printList.add(String.format("%d. %s",
                i + 1,
                this.list.get(i)));
        }
    }
}
