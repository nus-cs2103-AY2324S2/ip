package duke.command;

import duke.tasks.Task;

import duke.run.Storage;
import duke.run.TaskList;
import duke.run.Ui;

import duke.others.BelleException;

public class MarkCommand extends Command {
    private String index;

    public MarkCommand(String index) {
        this.index = index;
    }

    @Override
    public void execute(Storage s, TaskList t, Ui u) throws BelleException {
        try {
            Task doingtask = t.getTask(Integer.valueOf(index)-1);
            doingtask.setTaskDone();
            System.out.println("--------------------------");
            System.out.println("Nice! I have marked this task as done:");
            System.out.println(doingtask.toString());
            System.out.println("--------------------------");
            s.save(t.getList());
        } catch (IndexOutOfBoundsException e){
            throw new BelleException("This is not a valid number in my task list :(");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}