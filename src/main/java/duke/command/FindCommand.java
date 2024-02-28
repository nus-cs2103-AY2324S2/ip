//package duke.command;
//
//import runner.Storage;
//import runner.TaskList;
//import duke.task.Task;
//
///**
// * Finds task(s) that matches the given input keyword upon execution
// */
//
//public class FindCommand extends Command {
//    private final String DETAILS;
//
//    public FindCommand(String details) {
//        this.DETAILS = details;
//    }
//
//    @Override
//    public String execute(TaskList tasksList, TextUi ui, Storage storage) {
//        ui.showFindMessage();
//        for (Task task: tasksList.getList()) {
//            if (task.toString().contains(this.DETAILS)) {
//                System.out.println(task);
//            }
//        }
//        return ui.showBorder();
//    }
//
//    @Override
//    public boolean isExit() {
//        return false;
//    }
//}