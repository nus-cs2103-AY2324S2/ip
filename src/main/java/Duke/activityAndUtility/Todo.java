package Duke.activityAndUtility;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Todo implements Activity {
    List<String> act;
    public Todo(String status, String name) {
        act = new ArrayList<>();
        act.add(status); // Status
        act.add(name); // Task name
    }

    @Override
    public void printActivity() {
        System.out.format("\t\t [T][%s]%s%n", act.get(0), act.get(1));
    }

    @Override
    public String getName() {
        return act.get(1);
    }

    @Override
    public void mark(String input) {
        if (Objects.equals(input, "mark")) {
            act.set(0, "√");
        } else if (Objects.equals(input, "unmark")) {
            act.set(0, "X");
        }
        System.out.format("\t%sed:%n", input);
        printActivity();
    }
}
