public class SaveCommand extends Command {
    public SaveCommand(String[] commandArr) {
        super(commandArr);
    }

    @Override
    public void runCommand(TaskList taskList, SaveFile saveFile, Ui ui) {
        try {
            ui.printSaveOngoingMsg(saveFile.getFileName());
            saveFile.saveTasksToFile(taskList);
            ui.printSaveDoneMsg();
        } catch (TalkingBotException e) {
            ui.printGenericError(e);
        }
    }
}
