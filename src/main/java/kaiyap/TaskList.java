package kaiyap;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import exceptions.InvalidInputException;
import exceptions.KaiYapException;
import exceptions.MissingInputException;

/**
 * The TaskList class manages a list of tasks in the KaiYap application.
 * It provides functionalities to add, remove, retrieve, and create tasks.
 * This class works closely with the Ui class for user interactions and handles
 * different types of tasks such as Todo, Deadline, and Event.
 */
public class TaskList {
    private final ArrayList<Task> tasks;
    private final Ui ui;

    /**
     * Constructor for creating a TaskList object.
     *
     * @param ui The UI object that TaskList will interact with.
     */
    public TaskList(Ui ui) {
        tasks = new ArrayList<>();
        this.ui = ui;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added to the list.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the task list based on its index.
     *
     * @param index The index of the task to be removed.
     */
    public void remove(int index) {
        tasks.remove(index);
    }

    /**
     * Retrieves a task from the task list based on its index.
     *
     * @param index The index of the task to be retrieved.
     * @return The task at the specified index.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Creates a task based on the input string given.
     *
     * @param input The input string from the user.
     * @return The task that is newly created.
     */
    public Task taskCreator(String input) throws KaiYapException {
        KaiYap.TaskType type;
        try {
            type = KaiYap.TaskType.valueOf(input.split(" ")[0].toUpperCase());
        } catch (Exception e) {
            throw new InvalidInputException("\tI don't quite understand what you mean. Please try again! UwU :3");
        }
        Task task;
        switch (type) {
        case TODO:
            task = createTodo(input);
            break;
        case DEADLINE:
            task = createDeadline(input);
            break;
        case EVENT:
            task = createEvent(input);
            break;
        default:
            throw new InvalidInputException("\tI don't quite understand what you mean. Please try again! UwU :3");
        }
        return task;
    }

    /**
     * Creates a todo based on the input string given.
     *
     * @param input The input string from the user.
     * @return The Todo that is newly created.
     */
    public Todo createTodo(String input) throws KaiYapException {
        if (input.equals("todo")) {
            throw new MissingInputException("\tYour todo needs a description. Please try again! UwU :3");
        } else {
            String todoTask = input.substring(input.indexOf(' ') + 1);
            for (Task t : tasks) {
                if (t instanceof Todo && todoTask.equals(t.getListItem())) {
                    throw new InvalidInputException("\tAre you sure? Methinks this todo exists already."
                            + " Please try again! UwU :3");
                }
            }
            return new Todo(todoTask, input);
        }
    }

    /**
     * Creates a deadline based on the input string given.
     *
     * @param input The input string from the user.
     * @return The Deadline that is newly created.
     */
    public Deadline createDeadline(String input) throws KaiYapException {
        if (input.equals("deadline")) {
            throw new MissingInputException("Your deadline needs a description. Please try again! UwU :3");
        } else {
            try {
                String deadlineTask = input.substring(input.indexOf(" ") + 1, input.indexOf("/by")).strip();
                for (Task t : tasks) {
                    if (t instanceof Deadline && deadlineTask.equals(t.getListItem())) {
                        throw new InvalidInputException("\tAre you sure? Methinks this deadline exists already."
                                + " Please try again! UwU :3");
                    }
                }
                return new Deadline(
                        deadlineTask,
                        input,
                        LocalDateTime.parse(input.substring(
                                input.indexOf("/by") + 3).strip(), DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"))
                );
            } catch (DateTimeParseException d) {
                throw new InvalidInputException(
                        "\tYour deadline format is wrong - please use the dd/MM/yyyy MMhh format! UwU :3");
            } catch (Exception e) {
                throw new MissingInputException(
                        "\tYour deadline is missing some important information. Please try again! UwU :3");
            }
        }
    }

    /**
     * Creates an event based on the input string given.
     *
     * @param input The input string from the user.
     * @return The Event that is newly created.
     */
    public Event createEvent(String input) throws KaiYapException {
        if (input.equals("event")) {
            throw new MissingInputException("Your event needs a description. Please try again! UwU :3");
        } else {
            try {
                String eventTask = input.substring(input.indexOf(" ") + 1, input.indexOf("/from")).strip();
                for (Task t : tasks) {
                    if (t instanceof Event && eventTask.equals(t.getListItem())) {
                        throw new InvalidInputException("\tAre you sure? Methinks this event exists already."
                                + " Please try again! UwU :3");
                    }
                }
                return new Event(
                        eventTask,
                        input,
                        LocalDateTime.parse(
                                input.substring(input.indexOf("/from") + 5, input.indexOf("/to")).strip(),
                                DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm")),
                        LocalDateTime.parse(
                                input.substring(input.indexOf("/to") + 3).strip(),
                                DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"))
                );
            } catch (DateTimeParseException d) {
                ui.printError("\tYour event timeline format is wrong - please use the dd/MM/yyyy MMhh format! UwU :3");
                return null;
            } catch (Exception e) {
                throw new MissingInputException(
                        "\tYour event is missing some important information. Please try again! UwU :3");
            }
        }
    }
    /**
     * Searches for tasks containing the specified keyword.
     *
     * @param keyword The keyword to search for in task descriptions.
     * @return A list of tasks that contain the keyword.
     */
    public List<Task> findTasks(String keyword) {
        return tasks
                .stream()
                .filter(t -> t.getListItem().toUpperCase().contains(keyword.toUpperCase()))
                .collect(Collectors.toList());
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Determines if any task's description contains the given word.
     *
     * @param word The word to search for within task descriptions.
     * @return true if the word is found in any description, false otherwise.
     */
    public boolean contains(String word) {
        for (Task t : tasks) {
            if (t.getListItem().contains(word)) {
                return true;
            }
        }
        return false;
    }
}
