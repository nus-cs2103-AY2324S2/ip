package lucky.commands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lucky.storage.Storage;
import lucky.tasks.Task;
import lucky.ui.Ui;

/**
 * The RemoveTagCommand class is responsible for executing the command to remove tag(s) from tasks.
 */
public class RemoveTagCommand extends Command {
    @Override
    public void execute(ArrayList<Task> tasks, String[] input) throws CommandException, IOException {
        // This regex pattern was generated using ChatGPT. Generating regex pattern through ChatGPT
        // can allow developers to focus more on less mundane stuff.
        Pattern pattern = Pattern.compile("(\\d+)\\s+(#[\\w\\d]+\\s*)+");

        if (input.length < 2) {
            throw new CommandException(
                    "Wrong format! (format: removetag <task no.> #tag1 #tag2 #tag3)");
        }

        Matcher matcher = pattern.matcher(input[1]);

        if (!matcher.matches()) {
            throw new CommandException(
                    "Wrong format! (format: removetag <task no.> #tag1 #tag2 #tag3)");
        }

        String[] tagDetails = input[1].split(" ");

        int index = Integer.parseInt(tagDetails[0]) - 1;

        if (index >= tasks.size()) {
            throw new CommandException("Task not found!");
        }

        tasks.get(index).removeTags(tagDetails);
        super.commandResponse = Ui.printOutput(
                "Okay the tags are now removed. " + "\nYour task looks like this now:",
                tasks.get(index).toString());

        Storage.writeToStorage(tasks);
    }
}
