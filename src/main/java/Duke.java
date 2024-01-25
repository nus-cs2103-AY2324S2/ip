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
            } else if(Objects.equals(str, "bye")) {
                break;
            } else if(Objects.equals(str, "mark") || Objects.equals(str, "unmark")) {
                System.out.println(input.substring(input.indexOf(" ") + 1));
                list.markActivity(str, input.substring(input.indexOf(" ") + 1));
            } else if(Objects.equals(str, "todo") || Objects.equals(str, "deadline") || Objects.equals(str, "event")){
                list.addActivity(input);
            } else {
                System.out.println("unbearable");
            }
        }
        System.out.println("\t____________________________________________________________");
        System.out.println("\tLater");
        System.out.println("\t____________________________________________________________");
    }
}

class ActivityList {
    private List<Activity> activities;
    private List<String> searchTable;

    public ActivityList() {
         this.activities = new ArrayList<>();
         this.searchTable = new ArrayList<>();
    }

    public void addActivity(String input) {
        try {
            Activity activity = new Activity(input);
            this.activities.add(activity);
            this.searchTable.add(activity.getName());
            System.out.println("\t____________________________________________________________");
            System.out.print("\tadded: ");
            activity.printActivity();
            System.out.println("\t____________________________________________________________");
        } catch(RuntimeException e) {
            System.out.println("to long or too short won't do the job");
        }
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
        int index = this.searchTable.indexOf(key);
        this.activities.get(index).setCompletion(input);
    }
}

class Activity {
    private List<String> act;

    public Activity(String input) {
        act = new ArrayList<>();
        act.add(input.substring(0,1).toUpperCase());
        act.add("X");
        String subStr = input.substring(input.indexOf(" ") + 1);
        if(subStr.contains(" /")) {
            String[] slips = subStr.split(" /");
            act.addAll(List.of(slips));
        } else {
            act.add(subStr);
        }
        if(act.size() > 5) {
            throw new RuntimeException();
        } else if (input.split(" ").length < 2) {
            throw new RuntimeException();
        }
    }

    public String getName() {
        System.out.println(act.get(2));
        return act.get(2);
    }
    public void printActivity() {
        System.out.format("\t\t [%s][%s]%s", act.get(0), act.get(1), act.get(2));
        if(act.size() == 5) {
            System.out.format("(%s %s)", act.get(3),act.get(4));
        } else if (act.size() == 4){
            System.out.format("(%s)", act.get(3));
        }
        System.out.format("%n");
    }

    public void setType(String type) {
        this.act.set(0, type);
    }
    public void setCompletion(String input) {
        if(Objects.equals(input, "mark")) {
            this.act.set(1, "√");
        } else if (Objects.equals(input, "unmark")) {
            this.act.set(1, "X");
        }
        System.out.println("\t____________________________________________________________");
        System.out.format("\t%sed: [%s][%s]%s%n",
                input, this.act.get(0), this.act.get(1), this.act.get(2));
        System.out.println("\t____________________________________________________________");
    }
}