package command;

import java.io.IOException;

import tasks.Task;
import tasks.EventTask;
import tasks.DeadlineTask;
import tasks.TodoTask;

import run.Storage;
import run.TaskList;
import run.Ui;

import others.BelleException;

public class AddTaskCommand extends Command {
    private String type;
    private String msg;

    public AddTaskCommand(String type, String msg) {
        this.type = type;
        this.msg = msg;
    }

    @Override
    public void execute(Storage s, TaskList t, Ui u) throws BelleException {
        Task curr;
        if (type.equals("todo")) {
            try {
                curr = new TodoTask(msg.substring(5), false);
            } catch (StringIndexOutOfBoundsException e) {
                throw new BelleException("You did not specify a title for this todo task");
            }
        } else if (type.equals("deadline")) {
            try {
                String[] deadlinelist = msg.substring(9).split(" /by ");
                curr = new DeadlineTask(deadlinelist[0], false, deadlinelist[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new BelleException("You did not specify " +
                        "all the required information for deadline task.");
            } catch (StringIndexOutOfBoundsException e) {
                throw new BelleException("You did not specify " +
                        "all the required information for deadline task.");
            }
        } else {
            try {
                String[] eventlist = msg.substring(6).split(" /from ");
                String[] startend = eventlist[1].split(" /to ");
                curr = new EventTask(eventlist[0], false, startend[0], startend[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new BelleException("You did not " +
                        "specify all the required information for deadline task.");
            } catch (StringIndexOutOfBoundsException e) {
                throw new BelleException("You did not " +
                        "specify all the required information for deadline task.");
            }
        }
        t.addTask(curr);
        System.out.println("--------------------------");
        System.out.println("Got it. I've added this task:");
        System.out.println(curr.toString());
        System.out.println("Now you have " + t.getSize()+ " tasks in the list.");
        System.out.println("--------------------------");

        s.save(t.getList());

    }

    @Override
    public boolean isExit() {
        return false;
    }
}