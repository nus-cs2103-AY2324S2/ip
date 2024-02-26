package dylanbot;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Represents a list of tasks, with operations to add/delete tasks in the list
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private Ui ui;
    private HashMap<String, ArrayList<Integer>> tagList;

    /**
     * Creates a TaskList based on the given ArrayList of tasks and the given Ui
     *
     * @param tasks The specified ArrayList
     * @param ui The Ui to be used
     */
    public TaskList(ArrayList<Task> tasks, HashMap<String, ArrayList<Integer>> tagList, Ui ui) {
        this.tasks = tasks;
        this.tagList = tagList;
        this.ui = ui;
    }

    /**
     * Creates a new empty TaskList
     *
     * @param ui The Ui to be used
     */
    public TaskList(Ui ui) {
        this.tasks = new ArrayList<>();
        this.tagList = new HashMap<>();
        this.ui = ui;
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public Task getTask(int idx) {
        return this.tasks.get(idx - 1);
    }

    public int getSize() {
        return this.tasks.size();
    }

    public HashMap<String, ArrayList<Integer>> getTagList() {
        return this.tagList;
    }

    /**
     * Determines if the list of tasks is empty
     *
     * @return whether the list of tasks is empty
     */
    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    /**
     * Marks the task at the specified 1-based index as completed
     *
     * @param idx 1-based index
     */
    public String mark(int idx) {
        tasks.get(idx - 1).mark();
        return "Aight marked this task as done:\n\t"
                + tasks.get(idx - 1);
    }

    /**
     * Marks the tasks at the specified 1-based index as not completed
     *
     * @param idx 1-based index
     */
    public String unmark(int idx) {
        tasks.get(idx - 1).unmark();
        return "Sian marked this task as undone:\n\t"
                + tasks.get(idx - 1);
    }

    /**
     * Creates a TodoTask and adds it to the list of tasks
     *
     * @param desc Description of the TodoTask
     */
    public String createTodo(String desc) {
        Task curr = new TodoTask(desc);
        tasks.add(curr);
        return "Roger doger, added this task: \n\t" + curr.toString();
    }

    /**
     * Creates a DeadlineTask and adds it to the list of tasks
     *
     * @param desc Description of the DeadlineTask
     * @param deadlineStr Deadline of the DeadlineTask
     * @throws DateTimeParseException If deadlineStr is of an invalid format
     */
    public String createDeadline(String desc, String deadlineStr) throws DateTimeParseException {
        try {
            DeadlineTask curr = new DeadlineTask(desc, Storage.convertStringToDateTime(deadlineStr));
            tasks.add(curr);
            return "Roger doger, added this task: \n\t" + curr.toString();
        } catch (DateTimeParseException e) {
            return "Improper date format, TRY AGAIN!!!";
        }
    }

    /**
     * Creates an EventTask and adds it to the list of tasks
     *
     * @param desc Description of the EventTask
     * @param fromStr Starting date of the EventTask
     * @param toStr Ending date of the EventTask
     */
    public String createEvent(String desc, String fromStr, String toStr) {
        try {
            EventTask curr = new EventTask(desc, Storage.convertStringToDateTime(fromStr),
                    Storage.convertStringToDateTime(toStr));
            tasks.add(curr);
            return "Roger doger, added this task: \n\t" + curr.toString();
        } catch (DateTimeParseException e) {
            return "Improper date format, TRY AGAIN!!!";
        }
    }

    /**
     * Deletes the task at the specified 1-based index from the list of tasks
     *
     * @param idx 1-based index
     */
    public String deleteTask(int idx) {
        Task toRemove = tasks.get(idx - 1);
        tasks.remove(idx - 1);
        ArrayList<String> tagsToRemove = new ArrayList<>();
        for (String tag : tagList.keySet()) {
            ArrayList<Integer> indices = tagList.get(tag);
            indices.remove(Integer.valueOf(idx));
            if (indices.isEmpty()) {
                tagsToRemove.add(tag);
                continue;
            }
            ArrayList<Integer> newIndices = new ArrayList<>();
            for (int i : indices) {
                if (i > idx) {
                    newIndices.add(i - 1);
                } else {
                    newIndices.add(i);
                }
            }
            tagList.put(tag, newIndices);
        }
        for (String tag : tagsToRemove) {
            tagList.remove(tag);
        }
        return "Aight removed this task:\n\t" + toRemove.toString();
    }

    /**
     * Finds all tasks that contain the specified term
     *
     * @param term The specified search term
     */
    public String findTerm(String term) {
        ArrayList<Task> res = new ArrayList<>();
        for (Task t : tasks) {
            if (t.getDesc().contains(term)) {
                res.add(t);
            }
        }
        if (res.isEmpty()) {
            return "Shag no results found for your search for: '" + term + "'";
        } else {
            StringBuilder response = new StringBuilder();
            response.append("Here you go, results from your search for: '" + term + "'");
            response.append("\n").append(ui.displayTasks(res));
            assert response.length() > 0 : "Results should be non-zero if search term can be found";
            return response.toString();
        }
    }

    public String getTasksByTag(String tag) {
        if (!this.tagList.containsKey(tag)) {
            return "Shag no tasks tagged with " + tag;
        } else {
            ArrayList<Integer> indices = this.tagList.get(tag);
            ArrayList<Task> taggedTasks = new ArrayList<>();
            for (int idx : indices) {
                taggedTasks.add(this.tasks.get(idx - 1));
            }
            StringBuilder response = new StringBuilder();
            response.append("Here you go, results from your filter for: #" + tag);
            response.append("\n").append(ui.displayTasks(taggedTasks));
            return response.toString();
        }
    }

    /**
     * Tags the task at the specified 1-based index with the specified tag
     *
     * @param idx 1-based index
     * @param tag The specified tag
     */
    public String tagTask(int idx, String tag) {
        tasks.get(idx - 1).addTag(tag);
        ArrayList<Integer> tasksWithTag = tagList.getOrDefault(tag, new ArrayList<>());
        tasksWithTag.add(idx - 1);
        tagList.put(tag, tasksWithTag);
        return "Tagged " + tasks.get(idx - 1).getDesc() + " with tag #" + tag;
    }
}
