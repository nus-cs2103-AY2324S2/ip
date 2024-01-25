package chatbot;

import java.util.ArrayList;
import java.util.Scanner;

public class Alfred {
    protected static final String line = "________________________________________________________________________________________";
    protected static ArrayList<Task> list = new ArrayList<Task>();
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
        System.out.println(line);
        System.out.println(input);
        System.out.println(line);
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
    public void addList(String input) {
        try {
            TaskType taskType = null;
            if (input.startsWith("todo")) {
                taskType = TaskType.TODO;
                if (input.length() <= 5) {
                    throw new AlfredException("Sorry Master Bruce. The description of a todo cannot be empty.");
                }
                input = input.substring(5).trim();
            } else if (input.startsWith("deadline")) {
                taskType = TaskType.DEADLINE;
                if (input.length() <= 9) {
                    throw new AlfredException("Sorry Master Bruce. Please specify the description and due-date/time of the deadline by including /by.");
                }
                input = input.substring(9).trim();
            } else if (input.startsWith("event")) {
                taskType = TaskType.EVENT;
                if (input.length() <= 6) {
                    throw new AlfredException("Sorry Master Bruce. Please specify the description, start time and end time of the event by including /from start-time /to end-time.");
                }
                input = input.substring(6).trim();
            }
            if (taskType == null) {
                throw new AlfredException("Sorry Master Bruce. I don't understand what you mean.");
            }
            if (input.isEmpty()) {
                throw new AlfredException("Sorry Master Bruce. The description of a " + taskType + " cannot be empty.");
            }

            switch (taskType) {
                case TODO:
                    list.add(new ToDo(input));
                    break;
                case DEADLINE:
                    String[] splitResult = input.split("/by", 2);
                    String description = splitResult[0].trim();
                    String by = null;
                    try {
                        by = splitResult[1].trim();
                    }
                    catch (ArrayIndexOutOfBoundsException e) {
                        throw new AlfredException("Sorry Master Bruce. Please specify the due date or time by including /by due-date.");
                    }
                    if (description.isEmpty()) {
                        throw new AlfredException("Sorry Master Bruce. The description of a deadline cannot be empty.");
                    } else if (by.isEmpty()) {
                        throw new AlfredException("Sorry Master Bruce. Please specify the due date or time by including /by due-date.");
                    }
                    list.add(new Deadline(description, by));
                    break;
                case EVENT:
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
                        throw new AlfredException("Sorry Master Bruce. Please specify the start time and end time of the event by including /from start-time /to end-time.");
                    } else if (startTime.isEmpty()) {
                        throw new AlfredException("Sorry Master Bruce. Please specify the start time of the event by including /from start-time");
                    } else if (endTime.isEmpty()) {
                        throw new AlfredException("Sorry Master Bruce. Please specify the end time of the event by including /to end-time");
                    }
                    list.add(new Event(descriptionEvent, startTime, endTime));
                    break;
            }
        } catch(AlfredException e){
            e.printError();
            return;
        }
        String singularTask = list.size() == 1 ? "task" : "tasks";
        this.echo(String.format("Got it. I've added this task:\n  %s\nNow you have %d %s in the list.", list.get(list.size()-1).toString(), list.size(), singularTask));
    }


    public void printList() {
        if (list == null || list.size() == 0) {
            this.echo("Sorry Master Bruce. There are no tasks in the list.");
            return;
        }
        System.out.println(line);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + 1 + ". " + list.get(i).toString());
        }
        System.out.println(line);
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
        Alfred alfred = new Alfred();
        alfred.greet();
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            switch (input.trim()) {
                case "bye":
                    alfred.bye();
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