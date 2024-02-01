package taskList.commands;

import java.time.DateTimeException;

import taskList.*;
import taskList.tasks.Deadline;
import taskList.tasks.Event;
import taskList.tasks.Task;
import taskList.tasks.ToDo;

public class AddCommand implements Command{

    protected Task addedTask;

    public AddCommand(String string, int number) {
        // 0 - normal task, 1 - todo, 2 - deadline , 3 - event
        switch(number) {
            case 0:
                this.addedTask = new Task(string);
                break;
            case 1:
                ToDo newToDo = new ToDo(string);
                this.addedTask = newToDo;
                break;
            case 2:
                try {
                    String[] theParts = string.split("/",2);
                    if (theParts[1].trim().startsWith("by")) {
                        try {
                            Deadline newDeadline = new Deadline(theParts[0].trim(), theParts[1].trim());
                            this.addedTask = newDeadline;
                        } catch (DateTimeException e) {
                            System.out.println("Error creating Deadline: " + e.getMessage());
                        }
                    } else {
                        System.out.println("Error: /by cannot be found. Please try again");
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Error creating Deadline: Please enter a deadline.");
                } 
                break;

            case 3:
                try {
                    String[] theParts = string.split("/",3);
                    if (theParts[1].trim().startsWith("from")) {
                        if (theParts[2].trim().startsWith("to")) {
                            try {
                                Event newEvent = new Event(theParts[0].trim(), theParts[1].trim(), theParts[2].trim());
                                this.addedTask = newEvent;
                            } catch (ArrayIndexOutOfBoundsException|IllegalArgumentException e) {
                                System.out.println("Error creating Event: " + e.getMessage());
                            } 
                        } else {
                            System.out.println("Error: /to cannot be found. Please try again");
                        }
                    } else {
                        System.out.println("Error: /from cannot be found. Please try again");
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Error creating Event: Please format the input properly.");
                }                 
                break;       

            default: 
                System.out.println("error creating Task. Please contact the adminstrator.");
                break;
        }
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (addedTask != null) {
            taskList.addTask(addedTask);
            ui.showAddTaskMessage(addedTask, taskList.size());       
        }
    }
    
}
