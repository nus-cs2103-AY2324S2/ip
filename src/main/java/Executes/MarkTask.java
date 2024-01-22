package Executes;

import Actions.Action;
import Exceptions.DukeException;

import java.util.List;

public class MarkTask {
    private MarkTask() {
        throw new AssertionError("Constructor is not allowed");
    }

    public static void exec(String input, List<Action> actionList) {
        String[] markWhat = input.split(" ");
        String what = markWhat[0];

        try {
            int num = Integer.parseInt(markWhat[1]) - 1;

            if (num < 0 || num >= actionList.size()) {
                throw new DukeException("Invalid task number");
            }

            if (what.equals("mark")) {
                actionList.get(num).markIt();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(actionList.get(num));
            } else if (what.equals("unmark")) {
                actionList.get(num).unMark();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(actionList.get(num));
            } else {
                throw new DukeException("Invalid command -  Only use mark/unmark");
            }
        } catch (ArrayIndexOutOfBoundsException err) {
            throw new DukeException("Please insert task number!");
        }
    }
}
