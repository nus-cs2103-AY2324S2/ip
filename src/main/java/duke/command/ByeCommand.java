//package duke.command;
//
//import runner.Storage;
//import runner.TaskList;
//
///**
// * Executes exit from the app action
// */
//public class ByeCommand extends Command {
//
//    /**
//     * Displays exit message and exit the program
//     * @param tasksList A TaskList class that represents task list
//     * @param ui A TextUi class that represents the ui
//     * @param storage A Storage class which represents the storage of file
//     */
//    @Override
//    public String execute(TaskList tasksList, TextUi ui, Storage storage) {
//        return ui.showExitMessage();
//    }
//
//    /**
//     * Returns a boolean value to indicate whether to exit the program
//     * @return a boolean value
//     */
//    @Override
//    public boolean isExit() {
//        return true;
//    }
//}