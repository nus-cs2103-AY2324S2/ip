public class DeadlineCommand extends Command{
    private String detail;

    public DeadlineCommand(String detail){
        this.detail = detail;
    }
    @Override
    public boolean handle(Ui ui, TaskList taskList, Storage storage) throws ToothlessException {
        if (detail.equals("")) {
            throw new ToothlessException("Human task no name :(");
        }

        int dateIndex = detail.indexOf("/by");
        if (dateIndex == -1){
            throw new ToothlessException("Human deadline no deadline @_@");
        }

        String description = detail.substring(0, dateIndex - 1);
        String date = detail.substring(dateIndex + 4);

        Task t = new Deadline(description, date);
        taskList.addTask(t);

        System.out.println("Got it. I've added this task:");
        ui.showTask(t, taskList.size() - 1);
        System.out.format("Now you have %d tasks in the list.\n", taskList.size());

        return false;
    }
}
