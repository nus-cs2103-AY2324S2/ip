
import java.util.ArrayList;
import java.util.stream.Stream;

public class TempStorage {
    Codec codec;
    ArrayList<Task> list;

    public TempStorage() {
        this.list = new ArrayList<>();
        this.codec = new Codec();
    }

    public void delete(int i) throws ProcessingException {
        try {
            Task task = list.get(i);
            list.remove(i);
            System.out.println(String.format("I have deleted this:\n%s", task));
        } catch (Exception e) {
            String message = "Something went wrong when executing your delete command: \n"
            + "Check your input again";
            throw new ProcessingException(message);
        }
    }

    public void mark(int i) throws ProcessingException {
        try {
            Task task = list.get(i);
            task.markDone();
            System.out.println(String.format("I have marked this:\n%s", task));
        } catch (Exception e) {
            String message = "Something went wrong when executing your mark command: \n"
            + "Check your input again";
            throw new ProcessingException(message);
        }
    }   

    public void unmark(int i) throws ProcessingException {
        try {
            Task task = list.get(i);
            task.markUndone();
            System.out.println(String.format("I have unmarked this:\n%s", task));
        } catch (Exception e) {
            String message = "Something went wrong when executing your unmark command: \n"
            + "Check your input again";
            throw new ProcessingException(message);
        }
    }


    public void add(Task task) throws ProcessingException {
        try {
            list.add(task);
            System.out.println(String.format("Got it. I've added this task:\n%s\nNow you have %d tasks in the list.", task, list.size()));
        } catch (Exception e) {
            String message = "Something went wrong when executing your add command: \n"
            + "Check your input again";
            throw new ProcessingException(message);
        }
    }

    public void load(ArrayList<String> encodedTasks) throws ProcessingException {
        for (String encodedTask : encodedTasks) {
            Task decodedTask = codec.decode(encodedTask);
            list.add(decodedTask);
        }
    }

    public Stream<String> save() {
        return list.stream().map(codec::encode);
    }

    public void  displayList() {
        if (list.isEmpty()) {
            System.out.println("Your list is empty! Try adding tasks (eg. todo homework)");
        } else {
            for (int i = 0; i < list.size(); i++) {
                Task task = list.get(i);
                System.out.println(String.format("%d. %s", i + 1, task));
            }
        }
    }
}
