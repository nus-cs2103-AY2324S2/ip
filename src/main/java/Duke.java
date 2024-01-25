import java.util.Scanner;
import java.util.ArrayList;
public class Duke {

    public static void main(String[] args) {
        String line = "__________________________________________________";
        System.out.println(line);
        System.out.println("Hello! I'm Floofy");
        System.out.println("What can I do for you?");
        System.out.println(line);
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();
        loop:
        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.startsWith("mark")) {
                System.out.println(line);
                int idx = Integer.parseInt(userInput.substring(5));
                list.get(idx - 1).markTask();
                System.out.println(line);
            } else if (userInput.startsWith("unmark")) {
                System.out.println(line);
                int idx = Integer.parseInt(userInput.substring(7));
                list.get(idx - 1).unmarkTask();
                System.out.println(line);
            } else if (userInput.startsWith("todo")) {
                try {
                    if (userInput.length() < 6) {
                        throw new DukeException("Remember to add an actual task. Try again!");
                    }
                } catch (DukeException e) {
                    System.err.println(e.getMessage());
                    continue;
                }
                System.out.println(line);
                String todoTask = userInput.substring(5);
                ToDos newTodo = new ToDos(todoTask);
                list.add(newTodo);
                newTodo.addTask(list.size());
                System.out.println(line);
            } else if (userInput.startsWith("deadline")) {
                try {
                    // other improvements: no content, empty deadline
                    if (userInput.length() < 10) {
                        throw new DukeException("Remember to add an actual task. Try again!");
                    }
                    if (!(userInput.contains("/by"))) {
                        throw new DukeException("Remember to state the deadline after a '/by'!");
                    }
                } catch (DukeException e) {
                    System.err.println(e.getMessage());
                    continue;
                }
                int preIdx = userInput.indexOf("/by");
                int idx = preIdx + 4;
                String deadlineTask = userInput.substring(9, preIdx - 1);
                String deadlineBy = userInput.substring(idx);
                Deadline newDeadline = new Deadline(deadlineTask, deadlineBy);
                list.add(newDeadline);
                newDeadline.addTask(list.size());
                System.out.println(line);
            } else if (userInput.startsWith("event")) {
                try {
                    // other improvements: no content, empty start and end
                    if (userInput.length() < 7) {
                        throw new DukeException("Remember to add an actual task. Try again!");
                    }
                    if (!(userInput.contains("/from"))) {
                        throw new DukeException("Remember to state the start of your event after a '/from'!");
                    }
                    if (!(userInput.contains("/to"))) {
                        throw new DukeException("Remember to state the end of your event after a '/to'!");
                    }
                } catch (DukeException e) {
                    System.err.println(e.getMessage());
                    continue;
                }
                System.out.println(line);
                int preIdxFrom = userInput.indexOf("/from");
                int preIdxTo = userInput.indexOf("/to");
                int timeFromStart = preIdxFrom + 6;
                int timeFromEnd = preIdxTo - 1;
                int timeToStart = preIdxTo + 4;
                String eventTask = userInput.substring(6, preIdxFrom - 1);
                String eventFrom = userInput.substring(timeFromStart, timeFromEnd);
                String eventTo = userInput.substring(timeToStart);
                Event newEvent = new Event(eventTask, eventFrom, eventTo);
                list.add(newEvent);
                newEvent.addTask(list.size());
                System.out.println(line);
            } else {
                switch (userInput) {
                    case "bye":
                        System.out.println(line);
                        System.out.println("BYE BYE! Come back soon~~ YOUR WISH IS MY COMMAND <33");
                        System.out.println(line);
                        scanner.close();
                        break loop;
                    case "list":
                        System.out.println(line);
                        for (int i = 0; i < list.size(); i++) {
                            String numberedOutput = String.format("%d. %s", i + 1, list.get(i).toString());
                            System.out.println(numberedOutput);
                        }
                        System.out.println(line);
                        break;
                    default:
                        Task newTask = new Task(userInput);
                        list.add(newTask);
                        System.out.println(line);
                        System.out.println("added: " + userInput);
                        System.out.println(line);
                }
            }
        }
    }
}
