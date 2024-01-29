package command;

import java.io.IOException;

import tasks.Task;

import run.Storage;
import run.TaskList;
import run.Ui;

import others.BelleException;

public class UnmarkCommand extends Command {
    private String index;

    public UnmarkCommand(String index) {
        this.index = index;
    }

    @Override
    public void execute(Storage s, TaskList t, Ui u) throws BelleException {
        try {
            Task doingtask = t.getTask(Integer.valueOf(index)-1);
            doingtask.undotask();
            System.out.println("--------------------------");
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(doingtask.toString());
            System.out.println("--------------------------");
            s.save(t.getList());
        } catch (IndexOutOfBoundsException e){
            throw new BelleException("This is not a valid number in my task list :(");
        } catch (IOException e) {
            throw new BelleException("run.Storage has error when running unmark command");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}