package Duke.main;

import Duke.activityAndUtility.LocalList;
import Duke.activityAndUtility.Activity;
import Duke.activityAndUtility.Deadline;
import Duke.activityAndUtility.Event;
import Duke.activityAndUtility.Todo;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;


public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ActivityList list = new ActivityList();

        System.out.println("\t____________________________________________________________");
        System.out.println("\tHello! I'm Dad\n\tWhat can I do for you?");
        System.out.println("\t____________________________________________________________");

        while(true) {
            String input = scanner.nextLine();
            String firstWord = input.split(" ")[0];
            if(Objects.equals(firstWord, "list")) {
                list.printActivity();
            } else if (Objects.equals(firstWord, "delete")) {
                list.deleteActivity(input.substring(input.indexOf(" ") + 1));
            } else if(Objects.equals(firstWord, "bye")) {
                break;
            } else if (Objects.equals(firstWord, "mark") || Objects.equals(firstWord, "unmark")) {
                list.markActivity(firstWord, input.substring(input.indexOf(" ") + 1));
            } else {
                list.addActivity(firstWord, input);
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
    private final String filePath = "./data/duke.txt";
    private LocalList loadList;

    public ActivityList() {
        this.loadList = new LocalList(filePath);
        this.activities = loadList.load();
        this.searchTable = new ArrayList<>();
        for (Activity activity : activities) {
            searchTable.add(activity.getName());
        }
    }


    public void addActivity(String type, String input) {
        Activity activity;
        int index = input.indexOf(" ") + 1;
        String subStr = input.substring(index);

        switch (type) {
            case "todo":
                activity = new Todo("X", subStr); // Pass only the relevant substring
                break;
            case "deadline":
                String[] deadlineParts = subStr.split(" /", 2);
                if (deadlineParts.length == 2) {
                    activity = new Deadline("X", deadlineParts[0], deadlineParts[1]); // Adjust Duke.main.Duke.activity.Deadline constructor to accept date and time
                } else {
                    throw new RuntimeException("Invalid deadline format");
                }
                break;
            case "event":
                String[] eventParts = subStr.split(" /", 3);
                if (eventParts.length == 3) {
                    activity = new Event("X", eventParts[0], eventParts[1], eventParts[2]); // Adjust Duke.main.Duke.activity.Event constructor
                } else {
                    throw new RuntimeException("Invalid event format");
                }
                break;
            default:
                throw new RuntimeException("Unknown activity type: " + type);
        }

        this.searchTable.add(activity.getName());
        this.activities.add(activity);
        System.out.println("\t____________________________________________________________");
        System.out.print("\tadded: ");
        activity.printActivity();
        System.out.println("\t____________________________________________________________");
        loadList.save(activities);
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
        loadList.save(activities);
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
        loadList.save(activities);
    }
}

