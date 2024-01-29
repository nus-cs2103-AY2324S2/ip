package command;

import java.io.IOException;

import tasks.Task;

import run.Storage;
import run.TaskList;
import run.Ui;

import others.BelleException;

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
        } catch (IOException e) {
            throw new BelleException("run.Storage has error when running mark command");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}