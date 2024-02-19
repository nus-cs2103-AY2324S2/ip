package chatbot.task;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class TaskList {

    private static final double FUZZY_SEARCH_THRESHOLD = 0.6;
    private ArrayList<Task> tasks;
    private int numTasks;
    private boolean isSaved;

    /**
     * Constructor for TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
        this.numTasks = 0;
        this.isSaved = false;
    }

    /**
     * adds a task to the list.
     * @param task The task to be added.
     */
    public boolean addTask(Task task) {
        this.tasks.add(task);
        this.numTasks++;
        this.isSaved = false;
        return true;
    }

    /**
     * Deletes a task from the list.
     * @param index The index of the task to be deleted.
     * @return The deleted task.
     */
    public Task deleteTask(int index) {
        Task task = this.tasks.remove(index);
        this.numTasks--;
        this.isSaved = false;
        return task;
    }

    /**
     * Gets the list of tasks.
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        ArrayList<Task> list = new ArrayList<>();
        for (Task task : this.tasks) {
            list.add(task);
        }
        return list;
    }

    /**
     * Marks a task as done.
     * @param index The index of the task to be marked.
     * @return The marked task.
     */
    public Task markTask(int index) {
        Task task = this.tasks.get(index);
        task.mark();
        this.isSaved = false;
        return task;
    }

    /**
     * Unmarks a task.
     * @param index The index of the task to be unmarked.
     * @return The unmarked task.
     */
    public Task unmarkTask(int index) {
        Task task = this.tasks.get(index);
        task.unmark();
        this.isSaved = false;
        return task;
    }

    /**
     * Gets the number of tasks in the list.
     * @return The number of tasks in the list.
     */
    public int getNumTasks() {
        return this.numTasks;
    }

    /**
     * Checks if the list of tasks is saved.
     * @return True if the list of tasks is saved, false otherwise.
     */
    public boolean isSaved() {
        return this.isSaved;
    }

    /**
     * Marks the list of tasks as saved.
     */
    public void save() {
        this.isSaved = true;
    }

    /**
     * Finds tasks with a description that contains the keyword or is similar to the keyword.
     * Similarity is determined using the Levenshtein distance.
     * @param input The keyword to search for.
     * @return The list of tasks that contain the keyword.
     */
    public ArrayList<Task> findTasks(String input) {
        ArrayList<Task> list = new ArrayList<>();
        for (Task task : this.tasks) {
            if (task.getDescription().contains(input)) {
                list.add(task);
            } else {
                String[] descriptionWords = task.getDescription().split(" ");
                String[] inputWords = input.split(" ");
                double overallSimilarity = 0;
                double currSimilarity;
                for (String inputWord : inputWords) {
                    currSimilarity = 0;
                    for (String descriptionWord : descriptionWords) {
                        currSimilarity = Math.max(currSimilarity,
                        fuzzySearch(descriptionWord.trim(), inputWord.trim()));
                    }
                    overallSimilarity += currSimilarity;
                }
                overallSimilarity /= inputWords.length;
                if (overallSimilarity > FUZZY_SEARCH_THRESHOLD) {
                    list.add(task);
                }
            }
        }
        return list;
    }

    /**
     * Finds tasks with a description that contains the keyword or is similar to the keyword.
     * Similarity is determined using the Levenshtein distance.
     * @param keyword The keyword to search for.
     * @return The list of tasks that contain the keyword.
     */
    private static double fuzzySearch(String string1, String string2) {
        int m = string1.length();
        int n = string2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else if (string1.charAt(i - 1) == string2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i][j - 1], Math.min(dp[i - 1][j], dp[i - 1][j - 1]));
                }
            }
        }
        return 1 - (double) dp[m][n] / Math.max(m, n);
    }

}
