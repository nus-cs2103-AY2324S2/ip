package chatbot;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Alfred {
    protected static final String LINE = "________________________________________________________________________________________";
    protected static ArrayList<Task> list = new ArrayList<Task>();

    public Alfred(String data) {
        java.io.File file;
        try {
            file = new java.io.File(data);
            this.processData(file);
        } catch (FileNotFoundException e) {
            this.echo("Sorry Master Bruce. I cannot find the data. I will create a new file for you.");
            // create a new file in the same location
            file = new java.io.File("data/alfred.txt");
        }
    }
    public void processData(File file) throws FileNotFoundException {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String input = sc.nextLine();
                if (input.startsWith("T")) {
                    String[] splitResult = input.split("\\|", 3);
                    int toggle = Integer.parseInt(splitResult[1].trim());
                    String description = splitResult[2].trim();
                    ToDo todo = new ToDo(description);
                    if (toggle == 1) {
                        todo.toggleDone();
                    }
                    list.add(todo);
                } else if (input.startsWith("D")) {
                    String[] splitResult = input.split("\\|", 4);
                    int toggle = Integer.parseInt(splitResult[1].trim());
                    String description = splitResult[2].trim();
                    String[] formattedBy = splitResult[3].split("\\|");
                    for (int i = 0; i < formattedBy.length; i++) {
                        formattedBy[i] = formattedBy[i].trim();
                    }
                    String by = String.join(" ", formattedBy).trim();
                    Deadline deadline = new Deadline(description, by);
                    if (toggle == 1) {
                        deadline.toggleDone();
                    }
                    list.add(deadline);
                } else if (input.startsWith("E")) {
                    String[] splitResult = input.split("\\|", 4);
                    int toggle = Integer.parseInt(splitResult[1].trim());
                    String description = splitResult[2].trim();
                    String[] formattedBy = splitResult[3].split("\\|");
                    String startTime = "";
                    String endTime = "";
                    int halfLength = formattedBy.length / 2;
                    for (int i = 0; i < formattedBy.length; i++) {
                        if (i < halfLength) {
                            startTime += formattedBy[i].trim() + " ";
                        } else {
                            endTime += formattedBy[i].trim() + " ";
                        }
                    }
                    Event event = new Event(description, startTime.trim(), endTime.trim());
                    if (toggle == 1) {
                        event.toggleDone();
                    }
                    list.add(event);
                }
            }
    }

    public enum TaskType {
        TODO, DEADLINE, EVENT
    }
    public void greet() {
        this.echo("Hello! I'm Alfred\nWhat can I do for you?");
    }

    public void bye() {
        this.echo("Bye. Hope to see you again soon!");
    }

    public void echo(String input) {
        System.out.println(LINE);
        System.out.println(input);
        System.out.println(LINE);
    }

    private TaskType determineTaskType(String input) throws AlfredException {
        if (input.startsWith("todo")) {
            return TaskType.TODO;
        } else if (input.startsWith("deadline")) {
            return TaskType.DEADLINE;
        } else if (input.startsWith("event")) {
            return TaskType.EVENT;
        } else {
            throw new AlfredException("Sorry Master Bruce. I don't understand what you mean.");
        }
    }

    private void addData(Task task) {
        try {
            java.io.FileWriter fw = new java.io.FileWriter("data/alfred.txt", true);
            String taskType = task.getClass().getName();
            switch (taskType) {
                case "chatbot.ToDo":
                    fw.write("T | " + (task.isDone() ? 1 : 0) + " | " + task.getDescription() + "\n");
                    break;
                case "chatbot.Deadline":
                    Deadline deadline = (Deadline) task;
                    String by = deadline.getBy();
                    String[] splitResult = by.split(" ");
                    // Join the parts with |
                    String formattedBy = String.join(" | ", splitResult);
                    fw.write("D | " + (task.isDone() ? 1 : 0) + " | " + task.getDescription() + " | " + formattedBy + "\n");
                    break;

                case "chatbot.Event":
                    Event event = (Event) task;
                    String[] splitResults = event.getStartTime().split(" ", 3);
                    String[] splitResults2 = event.getEndTime().split(" ", 3);
                    String startFormattedBy = String.join(" | ", splitResults);
                    String endFormattedBy = String.join(" | ", splitResults2);
                    fw.write("E | " + (task.isDone() ? 1 : 0) + " | " + task.getDescription() + " | " + startFormattedBy + " | "+ endFormattedBy + "\n");
                    break;
            }
            fw.close();
        } catch (java.io.IOException e) {
            this.echo("Sorry Master Bruce. I cannot write to the data.");
        }
    }
    public void addList(String input) {
        try {
            TaskType taskType = determineTaskType(input);
            switch (taskType) {
                case TODO:
                    if (input.length() <= 5) {
                        throw new AlfredException("Sorry Master Bruce. The description of a todo cannot be empty.");
                    }
                    input = input.substring(5).trim();
                    ToDo todo = new ToDo(input);
                    //addData(todo);
                    list.add(todo);
                    break;
                case DEADLINE:
                    if (input.length() <= 9) {
                        throw new AlfredException("Sorry Master Bruce. Please specify the description and due-date/time of the deadline by including /by.");
                    }
                    input = input.substring(9).trim();
                    String[] splitResult = input.split("/by", 2);
                    String description = splitResult[0].trim();
                    String by = null;
                    try {
                        by = splitResult[1].trim();
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new AlfredException("Sorry Master Bruce. Please specify the due date or time by including /by due-date.");
                    }
                    if (description.isEmpty()) {
                        throw new AlfredException("Sorry Master Bruce. The description of a deadline cannot be empty.");
                    } else if (by.isEmpty()) {
                        throw new AlfredException("Sorry Master Bruce. Please specify the due date or time by including /by due-date.");
                    }
                    Deadline deadline = new Deadline(description, by);
                    //addData(deadline);
                    list.add(deadline);
                    break;
                case EVENT:
                    if (input.length() <= 6 || !input.contains("/from") || !input.contains("/to")) {
                        throw new AlfredException("Sorry Master Bruce. Please specify the description, start time, and end time of the event by including /from start-time /to end-time.");
                    }
                    input = input.substring(6).trim();
                    String[] splitResults = input.split("/", 3);
                    String descriptionEvent = splitResults[0].trim();
                    String startTime = null;
                    String endTime = null;
                    for (String s : splitResults) {
                        if (s.startsWith("from")) {
                            startTime = s.substring(4).trim();
                        } else if (s.startsWith("to")) {
                            endTime = s.substring(2).trim();
                        }
                    }
                    if (descriptionEvent.isEmpty()) {
                        throw new AlfredException("Sorry Master Bruce. The description of an event cannot be empty.");
                    } else if (startTime == null || endTime == null || startTime.isEmpty() && endTime.isEmpty()) {
                        throw new AlfredException("Sorry Master Bruce. Please specify the start time and end time by including /from start-time /to end-time.");
                    }
                    Event event = new Event(descriptionEvent, startTime, endTime);
                    //addData(event);
                    list.add(event);
                    break;
            }
        }
        catch (AlfredException e) {
            e.printError();
        }
        String singularTask = list.size() == 1 ? "task" : "tasks";
        this.echo(String.format("Got it. I've added this task:\n  %s\nNow you have %d %s in the list.", list.get(list.size()-1).toString(), list.size(), singularTask));
    }

    private void updateData() {
        try {
            java.io.FileWriter fw = new java.io.FileWriter("data/alfred.txt");
            for (Task task : list) {
                addData(task);
            }
            fw.close();
        } catch (java.io.IOException e) {
            this.echo("Sorry Master Bruce. I cannot write to the data.");
        }
    }

    public void printList() {
        if (list == null || list.size() == 0) {
            this.echo("Sorry Master Bruce. There are no tasks in the list.");
            return;
        }
        System.out.println(LINE);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + 1 + ". " + list.get(i).toString());
        }
        System.out.println(LINE);
    }

    public void markList(String index) {
        int extractedIdx = validateAndExtractIndex(index);
        if (extractedIdx == -1) {
            return;
        }
        if (list.get(extractedIdx).isDone()) {
            this.echo("Sorry Master Bruce. This task has already been marked as done.");
            return;
        }
        list.get(extractedIdx).toggleDone();
        this.echo("Nice! I've marked this task as done:\n  " + list.get(extractedIdx).toString());
    }

    public void unmarkList(String index) {
        int extractedIdx = validateAndExtractIndex(index);
        if (extractedIdx == -1) {
            return;
        }
        if (!list.get(extractedIdx).isDone()) {
            this.echo("Sorry Master Bruce. This task has already been marked as not done.");
            return;
        }
        list.get(extractedIdx).toggleDone();
        this.echo("OK, I've marked this task as not done yet:\n  " + list.get(extractedIdx).toString());
    }

    public void deleteList(String index) {
        int extractedIdx = validateAndExtractIndex(index);
        if (extractedIdx == -1) {
            return;
        }
        String removedTask = list.get(extractedIdx).toString();
        list.remove(extractedIdx);
        this.echo("Noted. I've removed this task:\n  " + removedTask + "\nNow you have " + list.size() + " tasks in the list.");
    }
    private int validateAndExtractIndex(String index) {
        try {
            if (index.isEmpty()) {
                throw new AlfredException("Please enter a number after the command.");
            }
            int extractedIdx = Integer.parseInt(index) - 1;
            if (list.isEmpty()) {
                throw new AlfredException("Sorry Master Bruce. There are no tasks in the list.");
            } else if (extractedIdx < 0 || extractedIdx >= list.size()) {
                if (list.size() == 1) {
                    throw new AlfredException("Sorry Master Bruce. The task number you have entered is not in the list. There is only one item in the list.");
                } else {
                    throw new AlfredException("Sorry Master Bruce. The task number you have entered is not in the list. Please enter a number in the range of 1 to " + list.size() + ".");
                }
            }
            return extractedIdx;
        } catch (NumberFormatException e) {
            this.echo("Sorry Master Bruce. Please enter a valid number.");
        } catch (AlfredException e) {
            e.printError();
        }
        return -1;
    }

    public static void main(String[] args) {
        Alfred alfred = new Alfred("data/alfred.txt");
        alfred.greet();
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            switch (input.trim()) {
                case "bye":
                    alfred.bye();
                    alfred.updateData();
                    return;
                case "list":
                    alfred.printList();
                    break;
                default:
                    if (input.startsWith("unmark")) {
                        alfred.unmarkList(input.substring(6).trim());
                    } else if (input.startsWith("mark")) {
                        alfred.markList(input.substring(4).trim());
                    } else if (input.startsWith("delete")) {
                        alfred.deleteList(input.substring(6).trim());
                    }
                    else {
                        alfred.addList(input);
                    }
                    break;
            }
        }
    }
}