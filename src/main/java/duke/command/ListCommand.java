//package duke.command;
//
//import runner.Storage;
//import runner.TaskList;
//
///**
// * List out all the tasks in the task list upon execution
// */
//
//public class ListCommand extends Command {
//
//    /**
//     * List all the tasks in the task list
//     * @param tasksList A TaskList class that represents task list
//     * @param ui A TextUi class that represents the ui
//     * @param storage A Storage class which represents the storage of file
//     */
//    @Override
//    public String execute(TaskList tasksList, TextUi ui, Storage storage) {
//        if (tasksList.getList().isEmpty()) {
//            return "Your task list empty, add something today!";
//        } else {
//            return ui.showTaskList(tasksList);
//        }
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