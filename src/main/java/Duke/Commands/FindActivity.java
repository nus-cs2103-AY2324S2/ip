package Duke.Commands;

import Duke.Activities.Activity;
import Duke.Activities.ActivityList;
import Duke.Converstion.Dialog;
import Duke.Exception.CommandException;

import java.util.ArrayList;
import java.util.List;

public class FindActivity extends Command{
    private final String SUBSTR;
    private ArrayList<Activity> foundList;
    public FindActivity(String input) {
        super(input);
        SUBSTR = input;
    }

    @Override
    public void execute(ActivityList list) throws CommandException {
        if (SUBSTR.isEmpty()) {
            throw new CommandException("empty search can't be done");
        } else {
             foundList = list.find(SUBSTR);
        }
    }

    @Override
    public String toString() {
        String output = Dialog.printLine();
        if (foundList.isEmpty()) {
            output += "No matching activities found\n";
        } else {
            output += "Here are the matching activities in your list:\n";
            for (int i = 0; i < foundList.size(); i++) {
                output += foundList.get(i).toString() + "\n";
            }
        }
        output += Dialog.printLine();
        return output;
    }

}
