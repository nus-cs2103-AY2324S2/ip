public class TaskCommand extends Command {
    public TaskCommand(String[] commandArr) {
        super(commandArr);
    }

    @Override
    public void runCommand(TaskList taskList, SaveFile saveFile, Ui ui) {
        String[] curCommand = super.getCommandArr();
        StringBuilder sbDescription = new StringBuilder();
        for (int idx = 1; idx < curCommand.length; idx++) {
            sbDescription.append(curCommand[idx]);
            if (idx < curCommand.length - 1) {
                sbDescription.append(" ");
            }
        }
        String fullDescription = sbDescription.toString();
        try {
            Task curTask = Task.generateTask(fullDescription, curCommand[0]);
            taskList.addTask(curTask);
            System.out.println("\tAlright, I've added this task to your list:");
            System.out.println("\t\t" + curTask);
            System.out.println(String.format("\tYou now have %d tasks in the list.", taskList.getSize()));
        } catch (TalkingBotException e) {
            System.out.println("\t" + e);
        }
    }
}
