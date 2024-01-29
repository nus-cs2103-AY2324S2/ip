package command;

import tasks.Task;

import run.Storage;
import run.TaskList;
import run.Ui;


public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(Storage s, TaskList t, Ui u)  {
        int index = 1;
        System.out.println("--------------------------");
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < t.getSize(); i++) {
            Task currTask = t.getTask(i);
            if (currTask.getName().contains(this.keyword)) {
                System.out.println(String.valueOf(index) + "." + t.getTask(i).toString());
                index +=1;
            }
        }
        System.out.println("--------------------------");

    }

    @Override
    public boolean isExit() {
        return false;
    }
}