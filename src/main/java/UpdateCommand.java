public class UpdateCommand extends Command{
    private int taskSeqNo;
    private boolean isMarked;
    public UpdateCommand(){
    }

    public UpdateCommand(int taskSeqNo, boolean isMarked) {
        //taskSeqNo is the item no entered by user
        //decrease by 1 to get index no
        this.taskSeqNo = taskSeqNo;
        this.isMarked = isMarked;
    }
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException{
        int taskIndexInList = this.taskSeqNo-1;
        Task taskToUpdate = taskList.getItemFromListByIndex(taskIndexInList);
        taskToUpdate.setMarked(this.isMarked);
        taskList.updateTaskInList(taskIndexInList, taskToUpdate);
        storage.Store(taskList.toString());
        StringBuilder sb = new StringBuilder();
        if (isMarked) {
            sb.append("Nice! I've marked this task as done:\n");
        } else {
            sb.append("OK, I've marked this task as note done yet:\n");
        }
        sb.append(taskToUpdate.printOutput());
        ui.setCommandOutput(sb.toString());
    }
}
