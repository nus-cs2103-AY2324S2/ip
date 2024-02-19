package fredricksen.commands;

import fredricksen.tasks.Task;
import fredricksen.tasks.TaskList;

/**
 * Represents a "Find" Command, which extends the Command class.
 * A "Find" Command is a command that creates a FindCommand object with
 * an Array of each word from the user input command, the user input command
 * and the TaskList tasks to store the Task type object.
 */
public class FindCommand extends Command {

    private String fullCommand;
    private String[] commandWords;
    private TaskList tasks;

    /**
     * Constructs a FindCommand instance with the specified user input command
     * and the TaskList to store the Task type task in.
     *
     * @param fullCommand The user input command to be executed.
     * @param commandWords The Array of each word from the user input command.
     * @param tasks The TaskList to store the various tasks.
     */
    public FindCommand(String fullCommand, String[] commandWords, TaskList tasks) {
        this.commandWords = commandWords;
        this.fullCommand = fullCommand;
        this.tasks = tasks;
    }
    /**
     * Executes the Find command.
     * It gets the matchList that is a TaskList with all the Tasks that matches the keyWord.
     * It then returns a formatted String about all the Task that are in the matchList.
     *
     * @return A String that is the message to display all the Tasks in the matchList TaskList.
     */
    @Override
    public String execute() {
        int keywordIndex = this.fullCommand.indexOf(this.commandWords[1]);
        String keyWord = this.fullCommand.substring(keywordIndex);
        TaskList matchList = getKeywordMatchList(this.tasks, keyWord);
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < matchList.size(); i++) {
            sb.append(i + 1).append(". ").append(matchList.getTask(i)).append("\n");
        }
        return sb.toString();
    }

    /**
     * A function that find all the Tasks from the TaskList that matches the keyWord
     * and adds them into a separate TaskList.
     *
     * @param tasks The TaskList containing all the current Tasks.
     * @param keyWord The key word to find the match for in each Tasks in TaskList.
     * @return A TaskList with all the Tasks that have a match with the keyWord.
     */
    public TaskList getKeywordMatchList(TaskList tasks, String keyWord) {
        TaskList matchList = new TaskList();
        for (int i = 0; i < tasks.size(); i++) {
            Task currTask = tasks.getTask(i);
            if (findWordPresent(currTask, keyWord)) {
                matchList.addTask(currTask);
            }
        }
        return matchList;
    }
    /**
     * Returns a boolean based on whether the word passed by the user
     * matches a word in the Task from the list of current tasks.
     *
     * @param currTask The current Task to check.
     * @param word The word to find a match in currTask.
     * @return A boolean if the current Task contains that word.
     */
    public boolean findWordPresent(Task currTask, String word) {
        return currTask.getTask().contains(word);
    }
}

