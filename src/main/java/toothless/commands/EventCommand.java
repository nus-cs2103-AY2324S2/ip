package toothless.commands;

import toothless.Storage;
import toothless.TaskList;
import toothless.ToothlessException;
import toothless.Ui;
import toothless.tasks.Task;
import toothless.tasks.Event;

public class EventCommand extends Command{
    private String detail;

    public EventCommand(String detail){
        this.detail = detail;
    }
    @Override
    public boolean handle(Ui ui, TaskList taskList, Storage storage) throws ToothlessException {
        if (detail.equals("")) {
            throw new ToothlessException("Human task no name :(");
        }

        int date1Index = detail.indexOf("/from");
        if (date1Index == -1){
            throw new ToothlessException("Human event no start date D:");
        }
        String description = detail.substring(0, date1Index - 1);
        detail = detail.substring(date1Index + 6);

        int date2Index = detail.indexOf("/to");
        if (date2Index == -1){
            throw new ToothlessException("Human event no end date D:");
        }

        String startDate = detail.substring(0, date2Index - 1);
        String endDate = detail.substring(date2Index + 4);

        Task t = new Event(description, startDate, endDate);
        taskList.addTask(t);

        System.out.println("Got it. I've added this task:");
        ui.showTask(t, taskList.size() - 1);
        System.out.format("Now you have %d tasks in the list.\n", taskList.size());

        return false;
    }
}
