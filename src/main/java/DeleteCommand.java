public class DeleteCommand extends Command {
    public DeleteCommand(String[] commandArr) {
        super(commandArr);
    }

    @Override
    public void runCommand(TaskList taskList, SaveFile saveFile, Ui ui) {
        String[] curCommand = super.getCommandArr();
        int deleteIdxInt = Integer.valueOf(curCommand[1]);
        Task removedTask = taskList.removeTask(deleteIdxInt - 1);
        System.out.println("\tRemoving task:");
        System.out.println(String.format("\t\t%s", removedTask));
        System.out.println(String.format("\tYou now have %d tasks in the list.",
                taskList.getSize()));
    }
}
