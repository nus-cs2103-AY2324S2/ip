import java.util.ArrayList;

public class History {

    ArrayList<Task> history;

    public History() {
        history = new ArrayList<>();
    }

    public void addTask(Task task) {
        history.add(task);
    }

    public void markTask(int idx) {
        try {
            history.get(idx).complete();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Jeez, you really ought to give me a number I can work with... got that?");
            System.out.println("[Item index exceeds history count]\n");
            return;
        }
    }

    public void listHistory() {
        int num = 1;
        if (history.size() == 0) {
            System.out.println("Looks like you have way too much free time on your hands, huh.");
            System.out.println("[No items in list]");
        }
        for (Task s : history) {
            if (s.isDone()) {
                System.out.println(num + "." + s.fullStatus());
            } else {
                System.out.println(num + "." + s.fullStatus());
            }
            num += 1;
        }
        System.out.println();
    }

    public void deleteTask(int index) {
        String fullStatus;
        try {
            fullStatus = history.get(index).fullStatus();
            history.remove(index);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("There's nothing there, dummy...");
            System.out.println("[Tried to remove non-existent event]\n");
            return;
        }
        System.out.println("Fine! If that's what you really want...");
        System.out.println("[Removed " + fullStatus + "]\n");
    }

}
