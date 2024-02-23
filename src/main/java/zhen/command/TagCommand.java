package zhen.command;
import zhen.*;

public class TagCommand extends Command {
    private final int tagIndex;
    private final String tagInfo;
    public TagCommand (int index, String tagMessage) {
        this.tagIndex = index;
        this.tagInfo = tagMessage;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws IndexOutOfBoundsException {
        if (tagIndex > taskList.getTaskCount() || tagIndex < 1) {
            throw new IndexOutOfBoundsException("Please only input index shown in the list");
        }
        taskList.tag(tagIndex, tagInfo);
        String replyMessage = "Nice! I've add the tag to the task.";
        Ui.print_message(replyMessage);
        storage.writeDisk(taskList.getTasks());
        return replyMessage;
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
