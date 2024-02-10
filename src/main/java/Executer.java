public class Executer {
    
    public static boolean execute(CommandType commandType, String userCommand, TaskList taskList, UI ui) {
        try {
            switch (commandType) {
            case ADDTASK:
                AddTask addCommand = new AddTask(userCommand, commandType);
                addCommand.run(taskList, ui);
                return addCommand.isBye();
            case BYE:
                Bye byeCommand = new Bye(userCommand, commandType);
                byeCommand.run(taskList, ui);
                return byeCommand.isBye();
            case MARK:
                MarkTask markCommand = new MarkTask(userCommand, commandType);
                markCommand.run(taskList, ui);
                return markCommand.isBye();
            case UNMARK:
                UnmarkTask unmarkCommand = new UnmarkTask(userCommand, commandType);
                unmarkCommand.run(taskList, ui);
                return unmarkCommand.isBye();
            case DELETE:
                DeleteTask deleteCommand = new DeleteTask(userCommand, commandType);
                deleteCommand.run(taskList, ui);
                return deleteCommand.isBye();
            case SHOW_LIST:
                ShowList showCommand = new ShowList(userCommand, commandType);
                showCommand.run(taskList, ui);
                return showCommand.isBye();
            default:
                throw new NonsenseCommandException(
                    "Student I don't know this command: " + userCommand);
            }
        } catch (AaronBotException e) {
            System.out.println(e);
            return false;
        }
    }

}
