package tasklist;

import java.util.List;

import exceptions.TaylorException;
import tasks.Task;

/**
 * Mark/Unmark task as done
 */
public class MarkTask {
    /**
     * No constructor needed
     */
    private MarkTask() {
        throw new AssertionError("Constructor is not allowed");
    }

    /**
     * Execute marking/unmarking
     * @param input : to mark or unmark and which one?
     * @param taskList
     */
    public static void execMarkTask(String input, List<Task> taskList) {
        String[] markWhat = input.split(" ");
        String what = markWhat[0];

        try {
            int num = Integer.parseInt(markWhat[1]) - 1;

            if (num < 0 || num >= taskList.size()) {
                throw new TaylorException("Invalid task number");
            }

            if (what.equals("mark")) {
                taskList.get(num).markIt();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(taskList.get(num));
            } else if (what.equals("unmark")) {
                taskList.get(num).unMark();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(taskList.get(num));
            } else {
                throw new TaylorException("Invalid command -  Only use mark/unmark");
            }
        } catch (ArrayIndexOutOfBoundsException err) {
            throw new TaylorException("Please insert task number!");
        }
    }
}
