package duke.storage;

import java.util.ArrayList;
import java.util.stream.Stream;

import duke.codec.Codec;
import duke.exceptions.ProcessingException;
import duke.search.Search;
import duke.tasks.Task;

/**
 * The `TempStorage` class is responsible for temporary storage and management of tasks in memory.
 * It allows adding, deleting, marking, unmarking tasks, loading tasks from encoded strings, and
 * displaying the task list.
 */
public class TempStorage {
    private final Codec codec;
    private final ArrayList<Task> list;

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
    public void delete(int i) throws ProcessingException {
        try {
            Task task = list.get(i);
            list.remove(i);
            System.out.printf("I have deleted this:\n%s%n", task);
        } catch (Exception e) {
            String message = "Something went wrong when executing your delete command: \n"
                + "Check your input again";
            throw new ProcessingException(message);
        }
    }

    /**
     * Marks a task at the specified index as done.
     *
     * @param i The index of the task to be marked as done.
     * @throws ProcessingException If there is an issue executing the mark command or if the index is out of bounds.
     */
    public void mark(int i) throws ProcessingException {
        try {
            Task task = list.get(i);
            task.markDone();
            System.out.printf("I have marked this:\n%s%n", task);
        } catch (Exception e) {
            String message = "Something went wrong when executing your mark command: \n"
                + "Check your input again";
            throw new ProcessingException(message);
        }
    }

    /**
     * Marks a task at the specified index as undone.
     *
     * @param i The index of the task to be marked as undone.
     * @throws ProcessingException If there is an issue executing the unmark command or if the index is out of bounds.
     */
    public void unmark(int i) throws ProcessingException {
        try {
            Task task = list.get(i);
            task.markUndone();
            System.out.printf("I have unmarked this:\n%s%n", task);
        } catch (Exception e) {
            String message = "Something went wrong when executing your unmark command: \n"
                + "Check your input again";
            throw new ProcessingException(message);
        }
    }


    /**
     * Adds a new task to the task list.
     *
     * @param task The task to be added.
     * @throws ProcessingException If there is an issue executing the add command.
     */
    public void add(Task task) throws ProcessingException {
        try {
            list.add(task);
            System.out.printf("Got it. I've added this task:\n%s\nNow you have %d tasks in the list.%n",
                    task,
                    list.size());

        } catch (Exception e) {
            String message = "Something went wrong when executing your add command: \n"
                + "Check your input again";
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
    public void displayList() {
        if (list.isEmpty()) {
            System.out.println("Your list is empty! Try adding tasks (eg. todo homework)");
        } else {
            for (int i = 0; i < list.size(); i++) {
                Task task = list.get(i);
                System.out.printf("%d. %s%n", i + 1, task);
            }
        }
    }

    /**
     * Displays a filtered list of tasks matching the search query.
     *
     * @param query The search query to match against task names.
     */
    public void displaySearchList(String query) {
        ArrayList<Task> resultList = Search.search(list, query);
        if (resultList.isEmpty()) {
            System.out.println("Your search was fruitless. Trying looking again");
        } else {
            System.out.println("Here are your matching search results");
            for (int i = 0; i < resultList.size(); i++) {
                Task task = resultList.get(i);
                System.out.printf("%d. %s%n", i + 1, task);
            }
        }
    }

}
