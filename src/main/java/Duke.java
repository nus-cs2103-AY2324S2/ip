import java.util.*;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ActivityList list = new ActivityList();

        System.out.println("\t____________________________________________________________");
        System.out.println("\tHello! I'm Dad\n\tWhat can I do for you?");
        System.out.println("\t____________________________________________________________");
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

        while(true) {
            String input = scanner.nextLine();
            String str = input.split(" ")[0];
            if(Objects.equals(str, "list")) {
                list.printActivity();
            } else if (Objects.equals(str, "delete")) {
                list.deleteActivity(input.substring(input.indexOf(" ") + 1));
            } else if(Objects.equals(str, "bye")) {
                break;
            } else if (Objects.equals(str, "mark") || Objects.equals(str, "unmark")) {
                list.markActivity(str, input.substring(input.indexOf(" ") + 1));
            } else {
                list.addActivity(str, input);
            }
        }
        System.out.println("\t____________________________________________________________");
        System.out.println("\tLater");
        System.out.println("\t____________________________________________________________");
    }
}

class ActivityList {
    private ArrayList<Activity> activities;
    private ArrayList<String> searchTable;

    public ActivityList() {

        this.activities = new ArrayList<>();
        this.searchTable = new ArrayList<>();
    }

    public void addActivity(String type, String input) {
        Activity activity;
        if (type.equals("todo")) {
            activity = new Todo(input);
            this.searchTable.add(activity.getName());
            this.activities.add(activity);
        } else if (type.equals("deadline")) {
            activity = new Deadline(input);
            this.searchTable.add(activity.getName());
            this.activities.add(activity);
        } else if (type.equals("event")) {
            activity = new Event(input);
            this.searchTable.add(activity.getName());
            this.activities.add(activity);
        } else {
            throw new RuntimeException("unbearable");
        }
        System.out.println("\t____________________________________________________________");
        System.out.print("\tadded: ");
        activity.printActivity();
        System.out.println("\t____________________________________________________________");
    }

    public void printActivity() {
        System.out.println("\t____________________________________________________________");
        System.out.println("\tList: ");
        for(int i = 0; i < activities.size(); i++) {
            System.out.format("%s. ", i + 1);
            activities.get(i).printActivity();
        }
        System.out.println("\t____________________________________________________________");
    }

    public void markActivity(String input, String key) {
        if (searchTable.contains(key)) {
            int index = this.searchTable.indexOf(key);
            this.activities.get(index).mark(input);
        } else {
            throw new RuntimeException("can't find activity");
        }
    }

    public void deleteActivity(String input) {
        try {
            int index = Integer.parseInt(input);
            Activity removed = activities.remove(index - 1);
            searchTable.remove(index - 1);
            System.out.println("\t____________________________________________________________");
            System.out.format("\tI have removed: ");
            removed.printActivity();
            System.out.println("\t____________________________________________________________");
        } catch (NumberFormatException e) {
            System.out.println("The string does not contain a valid integer.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("to long or too short won't do the job");
        }
    }
}

interface Activity {
    public void printActivity();
    public String getName();
    public void mark(String input);
}
class Todo implements Activity {
    private List<String> act;
    public Todo(String input) {
        act = new ArrayList<>();
        act.add("X");
        int index = input.indexOf(" ") + 1;
        if (index > 0) {
            String subStr = input.substring(index);
            act.add(subStr);
        } else {
            throw new RuntimeException("too short");
        }
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

class Deadline implements Activity {
    private List<String> act;
    public Deadline(String input) {
        act = new ArrayList<>();
        act.add("X");
        int index = input.indexOf(" ") + 1;
        if (index > 0) {
            String subStr = input.substring(index);
            if (subStr.indexOf(" /") + 1 > 0) {
                act.add(subStr.substring(index, subStr.indexOf(" /") - 1));
                act.add(subStr.substring(subStr.indexOf(" /") + 1));
            }
        } else {
            throw new RuntimeException("unbearable");
        }
    }

    @Override
    public void printActivity() {
        System.out.format("\t\t [D][%s]%s(%s)%n", act.get(0), act.get(1),act.get(2));
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

class Event implements Activity {
    private List<String> act;
    public Event(String input) {
        act = new ArrayList<>();
        act.add("X");
        int index = input.indexOf(" ") + 1;
        String subStr = input.substring(index);
        if (index > 0 && subStr.contains(" /")) {
            String[] slips = subStr.split(" /");
            if (slips.length == 3) {
                act.addAll(List.of(slips));
            } else {
                throw new RuntimeException("unbearable");
            }
        } else {
            throw new RuntimeException("unbearable");
        }
    }

    @Override
    public void printActivity() {
        System.out.format("\t\t [E][%s]%s(%s %s)%n",
                act.get(0), act.get(1),act.get(2), act.get(3));
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