package hirwan;

import java.util.ArrayList;
import java.util.List;

public class Tasklist {
    static List<String> tasks = new ArrayList<>();

    /**
     * the tasklist constructor to create a tasklist object
     * @param List the list tobe converted into a task list
     */
    public Tasklist(List<String> List) {
        this.tasks = List;
    }

    /**
     * the get method that  returns the task at the provided indec
     * @param index the index of the task the user is retrieving
     * @return the task that the user retrieve.
     */
    public String get(int index) {
        return tasks.get(index);
    }

    /**
     * the add method that adds a task to the tasklist
     * @param input the task to be added
     */
    public void add(String input) {
        tasks.add(input);
    }

    /**
     * The delete method which removes the task at the given index
     * @param index the index of the task to be deleted
     */
    public void delete(int index) {
            tasks.remove(index);
    }

    /**
     * the size method which returns the size of the task list
     * @return the size of the tasklist
     */
    public int size() {
        return tasks.size();
    }

    /**
     * The getList method that returns the tasklist
     * @return the tasklist
     */
    public List<String> getList() {
        return tasks;
    }

    /**
     * the set method that sets a task at the given index
     * @param Index the index of the task to set
     * @param input the task to be set
     */
    public void set(int Index, String input) {
        tasks.set(Index, input);
    }

    /**
     * the deleteList method that deletes all contenst from the list
     */
    public void deleteList() {
        tasks.removeAll(tasks);
    }

    /**
     * the copylist method that makes a copu of the tasklist
     * @return the copied tasklist
     */
    public Tasklist copyList() {
        List<String> copiedlist = new ArrayList<>();
        copiedlist.addAll(this.tasks);
        Tasklist tasklist = new Tasklist(copiedlist);
        return tasklist;
    }
}
