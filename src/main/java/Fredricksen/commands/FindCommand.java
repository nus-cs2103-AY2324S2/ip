package Fredricksen.commands;

import Fredricksen.commands.Command;
import Fredricksen.tasks.TaskList;
import Fredricksen.tasks.taskType.Task;

public class FindCommand extends Command {

    public String fullCommand;
    public String[] command;
    public TaskList tasks;
    public FindCommand(String fullCommand, String[] command, TaskList tasks) {
        this.command = command;
        this.fullCommand = fullCommand;
        this.tasks = tasks;
    }

    @Override
    public String execute() {
        int keywordIndex = this.fullCommand.indexOf(this.command[1]);
        String keyWord = this.fullCommand.substring(keywordIndex);
        TaskList matchList = getKeywordMatchList(this.tasks, keyWord);
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < matchList.size(); i++) {
            sb.append(i + 1).append(". ").append(matchList.getTask(i)).append("\n");
        }
        return sb.toString();
    }

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
     * Returns a boolean of whether the date passed
     * by the user is in the list of current tasks.
     * @param word
     * @return
     */
    public boolean findWordPresent(Task currTask, String word) {
        return currTask.getTask().contains(word);
    }
}

