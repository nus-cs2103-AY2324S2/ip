public class ShowList extends Command {
    public ShowList(String userInput, CommandType commandType) throws AaronBotException{
        super(userInput, commandType);
    }

    @Override
    public void run(TaskList taskList, UI ui) {
        taskList.showList();
    }

    @Override
    public boolean isBye() {
        return false;
    }
    
}
