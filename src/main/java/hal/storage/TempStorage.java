package hal.storage;

import java.util.ArrayList;
import java.util.StringJoiner;
import java.util.stream.Stream;

import org.apache.commons.lang3.SerializationUtils;

import hal.codec.Codec;
import hal.command.Command;
import hal.exceptions.ProcessingException;
import hal.search.Search;
import hal.tasks.Task;



/**
 * The `TempStorage` class is responsible for temporary storage and management of tasks in memory.
 * It allows adding, deleting, marking, unmarking tasks, loading tasks from encoded strings, and
 * displaying the task list.
 */
public class TempStorage {
    private final Codec codec;
    private ArrayList<Task> list;

    /**
     * Initializes a new `TempStorage` object with an empty task list and a `Codec` for encoding and decoding tasks.
     */
    public TempStorage() {
        this.list = new ArrayList<>();
        this.codec = new Codec();
    }

    /**
     * Deletes a task at the specified index from the task list.
     *
     * @param i The index of the task to be deleted.
     * @throws ProcessingException If there is an issue executing the delete command or if the index is out of bounds.
     */
    public String delete(int i) throws ProcessingException {
        try {
            Task task = list.get(i);
            list.remove(i);
            return String.format("I have deleted this:\n%s\n", task);
        } catch (IndexOutOfBoundsException e) {
            throw ProcessingException.exceptionCommandExecution(Command.DELETE, e);
        }
    }

    /**
     * Marks a task at the specified index as done.
     *
     * @param i The index of the task to be marked as done.
     * @throws ProcessingException If there is an issue executing the mark command or if the index is out of bounds.
     */
    public String mark(int i) throws ProcessingException {
        try {
            Task task = list.get(i);
            if (!task.getDone()) {
                task.markDone();
                return String.format("I have marked this:\n%s", task);
            } else {
                throw new ProcessingException(String.format("%s is already marked!", task));
            }
        } catch (IndexOutOfBoundsException e) {
            throw ProcessingException.exceptionCommandExecution(Command.MARK, e);
        }
    }

    /**
     * Marks a task at the specified index as undone.
     *
     * @param i The index of the task to be marked as undone.
     * @throws ProcessingException If there is an issue executing the unmark command or if the index is out of bounds.
     */
    public String unmark(int i) throws ProcessingException {
        try {
            Task task = list.get(i);
            if (task.getDone()) {
                task.markUndone();
                return String.format("I have unmarked this:\n%s", task);
            } else {
                throw new ProcessingException(String.format("%s is already unmarked!", task));
            }

        } catch (IndexOutOfBoundsException e) {
            throw ProcessingException.exceptionCommandExecution(Command.UNMARK, e);
        }
    }


    /**
     * Adds a new task to the task list.
     *
     * @param task The task to be added.
     * @throws ProcessingException If there is an issue executing the add command.
     */
    public String add(Task task) throws ProcessingException {
        try {
            list.add(task);
            return String.format("Got it. I've added this task:\n%s\nNow you have %d tasks in the list.\n",
                    task,
                    list.size());

        } catch (IllegalArgumentException e) {
            String message = "Something went wrong when adding to the list! \n"
                + "Check your input again\n";
            throw new ProcessingException(message);
        }
    }

    /**
     * Loads tasks from encoded strings and adds them to the task list.
     *
     * @param encodedTasks The list of encoded task strings.
     * @throws ProcessingException If there is an issue decoding and loading tasks.
     */
    public void load(ArrayList<String> encodedTasks) throws ProcessingException {
        assert list.isEmpty();
        for (String encodedTask : encodedTasks) {
            Task decodedTask = codec.decode(encodedTask);
            list.add(decodedTask);
        }
    }

    public Stream<String> save() {
        return list.stream().map(codec::encode);
    }

    /**
     * Displays the task list to the console.
     * If the list is empty, it informs the user to add tasks.
     */
    public String displayList() {
        if (list.isEmpty()) {
            return "Your list is empty! Try adding tasks (eg. todo homework)";
        }
        StringJoiner joiner = new StringJoiner("\n");
        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            joiner.add(String.format("%d. %s", i + 1, task));
        }
        return joiner.toString();
    }

    /**
     * Displays a filtered list of tasks matching the search query.
     *
     * @param query The search query to match against task names.
     */
    public String displaySearchList(String query) {
        ArrayList<Task> resultList = Search.search(list, query);
        if (resultList.isEmpty()) {
            return "Your search was fruitless. Trying looking again";
        }
        StringJoiner joiner = new StringJoiner("\n");
        joiner.add("Here are your matching search results");
        for (int i = 0; i < resultList.size(); i++) {
            Task task = resultList.get(i);
            joiner.add(String.format("%d. %s", i + 1, task));
        }
        return joiner.toString();
    }
    public void restore(ArrayList<Task> newList) {
        list = SerializationUtils.clone(newList);
    }
    public ArrayList<Task> getCurrentList() {
        return SerializationUtils.clone(list);
    }
}
