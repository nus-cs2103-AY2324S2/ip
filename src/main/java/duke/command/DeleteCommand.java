//package duke.command;
//
//import runner.Storage;
//import runner.TaskList;
//import duke.task.Task;
//
///**
// * Deletes a task from task list upon execution
// */
//
//public class DeleteCommand extends Command {
//    private final int TASK_NUM;
//
//    /**
//     * Constructs a DeleteCommand class with given parameter
//     * @param num A string representation of integer
//     */
//    public DeleteCommand(String num) {
//        this.TASK_NUM = Integer.parseInt(num);
//    }
//
//    /**
//     * Deletes tasks from task list, generates ui message to user and save changes to file
//     * @param tasksList A TaskList class that represents task list
//     * @param ui A TextUi class that represents the ui
//     * @param storage A Storage class which represents the storage of file
//     */
//    @Override
//    public String execute(TaskList tasksList, TextUi ui, Storage storage) {
//        Task deletedTask = tasksList.get(TASK_NUM);
//        tasksList.deleteFromTaskList(TASK_NUM);
//        storage.saveToFile(tasksList.getList());
//        return ui.showDeleteTaskMessage(deletedTask);
//    }
//
//    /**
//     * Returns a boolean value to indicate whether to exit the program
//     * @return a boolean value
//     */
//    @Override
//    public boolean isExit() {
//        return false;
//    }
//}