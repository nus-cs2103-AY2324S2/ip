import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

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

interface Activity {
    public void printActivity();
    public String getName();
    public void mark(String input);
}
class Todo implements Activity {
    List<String> act;
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
    List<String> act;
    LocalDate date;
    LocalTime time;

    public Deadline(String input) {
        act = new ArrayList<>();
        act.add("X");
        int index = input.indexOf(" ") + 1;
        if (index > 0) {
            String[] subStr = input.substring(index).split(" /by ", 2);;
            act.addAll(List.of(subStr));
        } else {
            throw new RuntimeException("unbearable");
        }
        date = DateTimeFormat.getDate(act.get(2));
        time = DateTimeFormat.getTime(act.get(2));
    }

    @Override
    public void printActivity() {
        if (date != null && time != null) {
            String dateOutput = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            String timeOutput = time.format(DateTimeFormatter.ofPattern("HH:mm"));
            System.out.format("\t\t [D][%s]%s(by: %s, %s)%n", act.get(0), act.get(1), dateOutput, timeOutput);
        } else {
            System.out.format("\t\t [D][%s]%s(by: %s)%n", act.get(0), act.get(1),act.get(2));
        }
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
    List<String> act;
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

class LocalList {
    private final String filePath;

    public LocalList(String filePath) {
        this.filePath = filePath;
    }

    // Load tasks from the file
    public ArrayList<Activity> load() {
        ArrayList<Activity> loadedActivities = new ArrayList<>();
        File file = new File(filePath);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" \\| ");
                Activity activity = null;
                switch (parts[0]) {
                    case "T":
                        activity = new Todo("todo " + parts[2]);
                        break;
                    case "D":
                        activity = new Deadline("deadline " + parts[2] + " /by " + parts[3]);
                        break;
                    case "E":
                        activity = new Event("event " + parts[2] + " /" + parts[3] + " /" + parts[4]);
                        break;
                }
                if (activity != null) {
                    if (parts[1].trim().equals("√")) {
                        activity.mark("mark");
                    }
                    loadedActivities.add(activity);
                }
            }
            scanner.close();
        } catch (IOException e) {
            System.out.println("An error occurred while loading the activities.");
        }
        return loadedActivities;
    }

    // Save tasks to the file
    public void save(ArrayList<Activity> activities) {
        try {
            FileWriter writer = new FileWriter(filePath);
            for (Activity activity : activities) {
                if (activity instanceof Todo) {
                    writer.write("T | " + ((Todo) activity).act.get(0) + " | " + ((Todo) activity).act.get(1) + "\n");
                } else if (activity instanceof Deadline) {
                    writer.write("D | " + ((Deadline) activity).act.get(0) + " | " + ((Deadline) activity).act.get(1)
                            + " | " + ((Deadline) activity).act.get(2) + "\n");
                } else if (activity instanceof Event) {
                    writer.write("E | " + ((Event) activity).act.get(0) + " | " + ((Event) activity).act.get(1)
                            + " | " + ((Event) activity).act.get(2) + " | " + ((Event) activity).act.get(3) + "\n");
                }
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while saving the activities.");
        }
    }
}

class DateTimeFormat {
    static public LocalDate getDate(String input) {
        String[] dateString = input.split(" ", 2);
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try {
            return LocalDate.parse(dateString[0], formatter1);
        } catch (DateTimeParseException ignored) {}
        try {
            return LocalDate.parse(dateString[0], formatter2);
        } catch (DateTimeParseException format) {
            return null;
        }
    }

    static public LocalTime getTime(String input) {
        String[] timeString = input.split(" ", 2);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm");
        try{
            return LocalTime.parse(timeString[1], formatter);
        }  catch (DateTimeParseException format) {
            return null;
        }
    }
}