public class AddTask extends Command {
    public AddTask(String userInput, CommandType commandType) throws AaronBotException{
        super(userInput, commandType);
    }

    @Override
    public void run(TaskList taskList, UI ui) {
        TaskType taskType;
        String taskDetails;
        try {
            taskPresenceCheck(commandDetails);
        } catch(TaskNoNameException e) {
            ui.errorMessage(e);
            return;
        }
        try {
            taskType = TaskDetailParser.getTaskType(commandDetails);
            taskDetails = TaskDetailParser.getTaskDetails(commandDetails);
        } catch(InvalidTaskTypeException e) {
            ui.errorMessage(e);
            return;
        }
        try {
            taskList.addToList(taskType, taskDetails);
            ui.taskAddedMessage(taskList);
        } catch (TaskErrorException e) {
            ui.errorMessage(e);
            return;
        }
    }

    @Override
    public boolean isBye() {
        return false;
    }

    private static void taskPresenceCheck(String userInputString) throws TaskNoNameException {
        if (userInputString.split("\\s+").length <= 1) {
            throw new TaskNoNameException(userInputString);
        }
    }
}
