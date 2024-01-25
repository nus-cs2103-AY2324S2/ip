import java.util.*;

public class Duke {
    public static void main(String[] args) {
        List<String> activity = new ArrayList<>();
        List<String> completion = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        ActivityList list = new ActivityList();

        System.out.println("\t____________________________________________________________");
        System.out.println("\tHello! I'm Dad\n\tWhat can I do for you?");
        System.out.println("\t____________________________________________________________");

        while(true) {
            String input = scanner.next();

            if(Objects.equals(input, "list")) {
                list.printActivity();
            } else if (Objects.equals(input, "bye")) {
                break;
            } else if(Objects.equals(input, "mark") || Objects.equals(input, "unmark")) {
                System.out.println("\t____________________________________________________________");
                System.out.format("\tWhich activity you wish to %s: ", input);
                String key = scanner.next();
                list.markActivity(input, key);
            } else {
                list.addActivity(input);
            }
        }
        System.out.println("\t____________________________________________________________");
        System.out.println("\tLater");
        System.out.println("\t____________________________________________________________");
    }
}

class ActivityList {
    private List<String> activity;
    List<String> completion;

    public ActivityList() {
         this.activity = new ArrayList<>();
         this.completion = new ArrayList<>();
    }

    public boolean duplicate(String act) {
        return activity.contains(act);
    }
    public void addActivity(String act) {
        this.activity.add(act);
        this.completion.add("X");
        System.out.println("\t____________________________________________________________");
        System.out.println("\tadded: " + act);
        System.out.println("\t____________________________________________________________");
    }

    public void printActivity() {
        System.out.println("\t____________________________________________________________");
        System.out.println("\tList: ");
        for(int i = 0; i < activity.size(); i++) {
            System.out.format("\t\t%d. [%s]%s%n", i + 1,completion.get(i), activity.get(i));
        }
        System.out.println("\t____________________________________________________________");
    }

    public void markActivity(String input, String key) {
        if(Objects.equals(input, "mark")) {
            completion.set(activity.indexOf(key), "√");
        } else if (Objects.equals(input, "unmark")) {
            completion.set(activity.indexOf(key), "X");
        }
        System.out.println("\t____________________________________________________________");
        System.out.format("\t%sed: %s%n", input, key);
        System.out.println("\t____________________________________________________________");
    }
}