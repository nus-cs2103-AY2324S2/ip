public class Bye extends Command {
    public Bye(String userInput, CommandType commandType) throws AaronBotException{
        super(userInput, commandType);
    }

    @Override
    public void run(TaskList taskList, UI ui) {
        ui.goodbyeMessage();
    }

    @Override
    public boolean isBye() {
        return true;
    }

    
}
