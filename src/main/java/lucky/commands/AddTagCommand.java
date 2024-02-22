package lucky.commands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lucky.storage.Storage;
import lucky.tasks.Task;
import lucky.ui.Ui;

public class AddTagCommand extends Command {

    @Override
    public void execute(ArrayList<Task> tasks, String[] input) throws CommandException, IOException {
        // TODO change checker later
        Pattern pattern = Pattern.compile("(\\d+)\\s+(#[\\w\\d]+\\s*)+");

        if (input.length < 2) {
            throw new CommandException(
                    "Wrong format! (format: tag <task no.> #tag1 #tag2 #tag3)");
        }

        Matcher matcher = pattern.matcher(input[1]);

        if (!matcher.matches()) {
            throw new CommandException(
                    "Wrong format! (format: tag <task no.> #tag1 #tag2 #tag3)");
        }

        String[] tagDetails = input[1].split(" ");

        int index = Integer.parseInt(tagDetails[0]) - 1;

        if (index >= tasks.size()) {
            throw new CommandException("Task not found!");
        }

        tasks.get(index).addTags(tagDetails);
        super.commandResponse = Ui.printOutput("I have added the tags:", tasks.get(index).toString());

        Storage.writeToStorage(tasks);
    }
}
