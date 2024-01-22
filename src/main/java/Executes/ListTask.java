package Executes;

import Actions.Action;

import java.util.List;

public class ListTask {
    /**
     * No constructor needed
     */
    private ListTask() {
        throw new AssertionError("Constructor is not allowed");
    }

    /**
     * List out all the tasks
     * @param actionList
     */
    public static void exec(List<Action> actionList) {
        System.out.println("Here are the tasks in your list:");
        int pos = 1;
        if (actionList.isEmpty()) {
            System.out.println("List is empty.");
        } else {
            for (Action acting : actionList) {
                System.out.println(pos++ + ". " + acting);
            }
        }
    }

}
