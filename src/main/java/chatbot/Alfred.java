package chatbot;

import java.util.ArrayList;
import java.util.Scanner;

public class Alfred {
    protected static final String line = "________________________________________________________________________________________";
    protected static ArrayList<Task> list = new ArrayList<Task>();

    public void greet() {
        System.out.println(line);
        System.out.println("Hello! I'm Alfred");
        System.out.println("What can I do for you?");
        System.out.println(line);
    }

    public void bye() {
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }

    public void echo(String input) {
        System.out.println(line);
        System.out.println(input);
        System.out.println(line);
    }

    public void addList(String input) {
//        try {
//            if (input.equals("todo") || input.equals("deadline") || input.equals("event")) {
//                throw new AlfredException("Sorry Master Bruce. The description of a " + input + " cannot be empty. Please specify a description.");
//            }
//        } catch (AlfredException e) {
//            e.printError();
//            return;
//        }
        try {
            if (input.startsWith("todo")) {
                input = input.substring(5).trim();
                if (input.isEmpty()) {
                    throw new AlfredException("Sorry Master Bruce. The description of a todo cannot be empty. Please specify a description after todo.");
                }
                list.add(new ToDo(input));
            } else if (input.startsWith("deadline")) {
                String[] splitResult = input.split("/by", 2);
                String description = splitResult[0].substring(9).trim();
                if (description.isEmpty()) {
                    throw new AlfredException("Sorry Master Bruce. The description of an deadline cannot be empty. Please specify a description between deadline and /by.");
                } else if (splitResult.length == 1 || splitResult[1].trim().isEmpty()) {
                    // first condition checks for cases where /by is not present
                    throw new AlfredException("Sorry Master Bruce. Please specify the due date or time by including /by due-date.");
                }
                String by = splitResult[1].trim();
                list.add(new Deadline(description, by));
            } else if (input.startsWith("event")) {

                String[] splitResults = input.split("/", 3);
                String description = splitResults[0].substring(6).trim();
                if (description.isEmpty()) {
                    throw new AlfredException("Sorry Master Bruce. The description of an event cannot be empty. Please specify a description between event and /from.");
                } else if (!(input.contains("/from") && input.contains("/to"))) {
                    throw new AlfredException("Sorry Master Bruce. Please specify the start time and end time of the event by including /from start-time /to end-time.");
                } else if (!input.contains("/from")) {
                    throw new AlfredException("Sorry Master Bruce. Please specify the start time of the event by including /from start-time.");
                } else if (!input.contains("/to")) {
                    throw new AlfredException("Sorry Master Bruce. Please specify the end time of the event by including /to end-time.");
                }
                String startTime = splitResults[1].substring(4).trim();
                if (startTime.isEmpty()) {
                    throw new AlfredException("Sorry Master Bruce, you have not indicated the start time of your event. Please specify the start time of the event by including /from start-time.");
                }
                String endTime = splitResults[2].substring(2).trim();
                if (endTime.isEmpty()) {
                    throw new AlfredException("Sorry Master Bruce. you have not indicated the end time of your event.Please specify the end time of the event by including /to end-time.");
                }
                list.add(new Event(description, startTime, endTime));
            } else {
                throw new AlfredException("Sorry Master Bruce. I don't understand what you mean.");
            }
        } catch(AlfredException e){
            e.printError();
            return;
        }
        String singularTask = list.size() == 1 ? "task" : "tasks";
        this.echo(String.format("Got it. I've added this task:\n  %s\nNow you have %d %s in the list.", list.get(list.size()-1).toString(), list.size(), singularTask));
    }

    public void printList() {
        System.out.println(line);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + 1 + ". " + list.get(i).toString());
        }
        System.out.println(line);
    }

    public void markList(int index) {
        try {
            if (list.size() == 0) {
                throw new AlfredException("Sorry Master Bruce. There are no tasks in the list.");
            } else if (list.size() == 1 && index != 1) {
                throw new AlfredException("Sorry Master Bruce. The task number you have entered is not in the list. There is only 1 task in the list.");
            } else if (index >= list.size() || index < 0) {
                throw new AlfredException("Sorry Master Bruce. The task number you have entered is not in the list. Please enter a number in the range of 1 to " + list.size() + ".");
            } else if (list.get(index).isDone()) {
                throw new AlfredException("Sorry Master Bruce. This task has already been marked as done.");
            }
        } catch (AlfredException e) {
            e.printError();
            return;
        }
        list.get(index).toggleDone();
        System.out.println(line);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + list.get(index));
        System.out.println(line);
    }

    public void unmarkList(int index) {
        try {
            if (list.size() == 0) {
                throw new AlfredException("Sorry Master Bruce. There are no tasks in the list.");
            } else if (index >= list.size() || index < 0) {
                throw new AlfredException("Sorry Master Bruce. The task number you have entered is not in the list. Please enter a number in the range of 1 to " + list.size() + ".");
            } else if (!list.get(index).isDone()) {
                throw new AlfredException("Sorry Master Bruce. This task has already been marked as not done.");
            }
        } catch (AlfredException e) {
            e.printError();
            return;
        }
        list.get(index).toggleDone();
        System.out.println(line);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + list.get(index));
        System.out.println(line);
    }

    public static void main(String[] args) {
        Alfred alfred = new Alfred();
        alfred.greet();
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            switch (input) {
                case "bye":
                    alfred.bye();
                    return;
                case "list":
                    alfred.printList();
                    break;
                default:
                    if (input.startsWith("unmark")) {
                        int extractedIdx;
                        try {
                            extractedIdx = Integer.parseInt(input.substring(7));
                        } catch (NumberFormatException e) {
                            alfred.echo("Sorry Master Bruce. Please enter a number after 'unmark'.");
                            continue;
                        }
                        alfred.unmarkList(extractedIdx - 1);
                    } else if (input.startsWith("mark")) {
                        int extractedIdx;
                        try {
                            extractedIdx = Integer.parseInt(input.substring(5));
                        } catch (NumberFormatException e) {
                            alfred.echo("Sorry Master Bruce. Please enter a number after 'mark'.");
                            continue;
                        }
                        alfred.markList(extractedIdx - 1);
                    } else {
                        alfred.addList(input);
                    }
                    break;
            }
        }
    }
}