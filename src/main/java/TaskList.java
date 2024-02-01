import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskList {
    private List<Task> list;

    public TaskList() {
        list = new ArrayList<>();
    }

    public TaskList(List<Task> list) {
        this.list = list;
    }

    public void printList() {
        System.out.println("Here are the tasks in your list:");
        int number = 1;
        for (Task t : this.list) {
            System.out.printf("%d. %s", number, t);
            number++;
        }
    }

    public void add(Task t) {
        this.list.add(t);
    }

    public Task get(int idx) {
        return this.list.get(idx);
    }

    public int size() {
        return this.list.size();
    }

    public void mark(String in) {
        int i;
        try {
            i = Integer.parseInt(in.substring(5));
            Task t = this.list.get(i-1);
            t.markAsDone();
            System.out.print("Nice! I've marked this task as done:\n  ");
            System.out.print(t);
            this.list.set(i-1, t);

        } catch (NumberFormatException e) {
            System.out.println("Not a valid number!");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Sorry, index out of range!");
        }
    }

    public void unmark(String in) {
        try {
            int i = Integer.parseInt(in.substring(7));
            Task t = this.list.get(i-1);
            t.markAsNotDone();
            System.out.print("OK, I've marked this task as not done yet:\n  ");
            System.out.print(t);
            this.list.set(i-1, t);

        } catch (NumberFormatException e) {
            System.out.println("Not a valid number!");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Sorry, index out of range!");
        }
    }

    public void delete(String in) {
        try {
            int i = Integer.parseInt(in.substring(7));
            Task t = this.list.remove(i-1);
            System.out.print("Noted. I've removed this task::\n  ");
            System.out.println(t);
            System.out.printf("Now you have %d tasks in the list.\n", this.list.size());

        } catch (NumberFormatException e) {
            System.out.println("Not a valid number! Or perhaps add a ' '");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Sorry, index out of range!");
        }
    }

    public List<String> taskToSavedString() {
        return list.stream()
                .map(Task::toSavedString)
                .collect(Collectors.toList());
    }
}
