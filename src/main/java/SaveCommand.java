public class SaveCommand extends Command {
    public SaveCommand(String[] commandArr) {
        super(commandArr);
    }

    @Override
    public void runCommand(TaskList taskList, SaveFile saveFile, Ui ui) {
        try {
            System.out.println("\tSaving tasks to file: " + saveFile.getFileName());
            saveFile.saveTasksToFile(taskList);
            System.out.println("\tSave done!");
        } catch (TalkingBotException e) {
            System.out.println(e);
        }
    }
}
