public class UnmarkTask extends Command {
    public UnmarkTask(String userInput, CommandType commandType) throws AaronBotException{
        super(userInput, commandType);
    }
    
    @Override
    public void run(TaskList taskList, UI ui) {
        int index;
        try {
            index = IndexParser.getIndex(commandDetails);
        } catch (IndexFormatException e) {
            ui.errorMessage(e);
            return;
        }
        try {
            taskList.unmark(index);
        } catch (AaronBotException e) {
            ui.errorMessage(e);
            return;
        }
        ui.unmarkMessage(index, taskList);
    }

    @Override
    public boolean isBye() {
        return false;
    }

}

