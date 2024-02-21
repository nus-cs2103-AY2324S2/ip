package utilities;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import enums.Priorities;
import exceptions.WilliamException;
import tasks.Task;

/**
 * Contains the task list e.g. it has operations to add/delete tasks in the list
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Constructor if there is no existing file
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructor if there is an existing txt file with tasks
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Getter method for task
     *
     * @return arraylist Arraylist of tasks
     */
    public List<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Returns a string representation of all the tasks in the list
     *
     * @return A string listing all tasks or stating that the list is empty
     */
    public String printList() {
        if (this.tasks.isEmpty()) {
            return "Your list is empty. Please add some tasks to the list first!" + "\n";
        } else {
            List<String> tasks = IntStream.range(0, this.tasks.size())
                    .mapToObj(i -> (i + 1) + ". " + this.tasks.get(i).toString())
                    .collect(Collectors.toList());
            return "Here are the tasks in your list:\n" + String.join("\n", tasks) + "\n";
        }
    }

    /**
     * Returns a string representation of all the tasks in the list, sorted by priority
     *
     * @return A string listing all tasks sorted by priority or stating that the list is empty
     */
    public String printListSortedByPriority() {
        if (this.tasks.isEmpty()) {
            return "Your list is empty. Please add some tasks to the list first!" + "\n";
        } else {
            List<String> sortedTasks = this.tasks.stream()
                    .sorted(Comparator.comparing(
                            task -> {
                                try {
                                    return Priorities.valueOf(task.getPriority().toUpperCase());
                                } catch (IllegalArgumentException e) {
                                    return Priorities.NONE;
                                }
                            },
                            Comparator.comparingInt(Priorities::ordinal).reversed()
                    ))
                    .map(Task::toString)
                    .collect(Collectors.toList());

            List<String> tasks = IntStream.range(0, sortedTasks.size())
                    .mapToObj(i -> (i + 1) + ". " + sortedTasks.get(i))
                    .collect(Collectors.toList());

            return "Here are the tasks in your list, sorted by priority:\n"
                    + String.join("\n", tasks) + "\n";
        }
    }

    /**
     * Adds task into the list of tasks
     *
     * @param task Input that is the task (could be todo, deadline or event)
     */
    public void addTask(Task task) {
        this.tasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + this.tasks.size() + " tasks in the list.\n");
    }

    /**
     * Deletes the specified task from the list and returns a message of the task being deleted
     *
     * @param input The ID of the task to delete
     * @return String that says whether the task has been deleted
     */
    public String deleteFromList(String input) {
        StringBuilder sb = new StringBuilder();
        if (this.tasks.isEmpty()) {
            return "There are no tasks to be deleted. Please add some tasks to the list first!\n";
        } else {
            try {
                int idOfItem = Integer.parseInt(input);
                int actualId = idOfItem - 1;
                Task removedTask = this.tasks.remove(actualId);
                sb.append("Noted. I've removed this task:\n").append(removedTask.toString())
                        .append("\nNow you have ").append(this.tasks.size())
                        .append(" tasks in the list.\n");
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                return "The Task ID is invalid. Please enter a valid number!" + "\n";
            }
        }
        return sb.toString();
    }

    /***
     * Marks/Un-marks the task as done
     *
     * @param input ID of the task
     * @param isMark True or False
     * @return Whether has it been successfully mark/un-mark
     */
    public boolean markAndUnmark(String input, boolean isMark) {
        int idOfItem = Integer.parseInt(input);
        int actualId = idOfItem - 1;
        boolean isMarkChanged = this.tasks.get(actualId).toggleMarkStatus(isMark);
        return isMarkChanged;
    }

    /**
     * Finds tasks based on whether their description contains the input
     *
     * @param input Input from the user to find the task
     * @return A message with the matching tasks or a message that says no task is found
     * @throws WilliamException If no tasks match the provided input
     */
    public String findTasks(String input) throws WilliamException {
        List<String> tasks = IntStream.range(0, this.tasks.size())
                .filter(i -> this.tasks.get(i).getName().contains(input))
                .mapToObj(i -> (i + 1) + ". " + this.tasks.get(i).toString())
                .collect(Collectors.toList());

        if (tasks.isEmpty()) {
            throw new WilliamException(
                    "No tasks match the provided input: " + input + ". Please try again!" + "\n");
        }

        return "Here are the matching tasks in your list:\n" + String.join("\n", tasks) + "\n";
    }

    /**
     * Prioritise task based on their user's priority level
     *
     * @param id            ID of the task
     * @param priorityLevel Priority level of the task
     * @return String indicating that the task has been prioritised
     * @throws WilliamException If the provided input does not match
     */
    public String prioritiseTask(String id, String priorityLevel) throws WilliamException {
        Priorities priority;
        int actualId = Integer.parseInt(id) - 1;

        if (actualId < 0 || actualId >= tasks.size()) {
            throw new WilliamException("Task ID does not exist. Please try again!");
        }

        try {
            priority = Priorities.valueOf(priorityLevel.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new WilliamException("Invalid priority level. Please try again!");
        }

        tasks.get(actualId).setPriority(priority.name());
        return "Task with ID " + id + " has been prioritised to " + priority + " priority.";
    }
}
