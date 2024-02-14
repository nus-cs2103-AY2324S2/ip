import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    
    public static void main(String[] args) {
        System.out.println(TextUi.introMessage());
        Scanner sc = new Scanner(System.in);
        String userInput;
        FileManager fileManager = new FileManager("./data/duke.txt");
        ArrayList<Task> tasks = fileManager.loadTasks();
        int count = tasks.size();

        try {
            while(true) {
                userInput = sc.nextLine();
                String[] userInputArray = userInput.split("\\s+", 2);

                boolean isTodo = userInputArray[0].equals("todo");
                boolean isDeadline = userInputArray[0].equals("deadline");
                boolean isEvent = userInputArray[0].equals("event");
                boolean isMark = userInputArray[0].equals("mark");
                boolean isUnmark = userInputArray[0].equals("unmark");
                char lastChar = userInput.charAt(userInput.length() - 1);


                if (userInput.equals("bye")) {
                    System.out.println(TextUi.outroMessage());
                    break;
                } else if (userInputArray[0].equals("list")) {
                    TextUi.listTasks(tasks, count);
                } else if (isTodo || isEvent || isDeadline) {
                    if (isTodo) {
                        tasks.add(new Todo(userInputArray[1], false));
                        count++;
                        System.out.println(TextUi.addComment(tasks.get(count - 1), count));
                        fileManager.saveTasks(tasks);
                    }
                    if (isEvent) {
                        String task = TextUi.extractTaskName(userInput);
                        int index = userInput.indexOf("/");
                        String when = TextUi.replacer(userInput).substring(index);
                        tasks.add(new Event(task, false, when));
                        count++;
                        System.out.println(TextUi.addComment(tasks.get(count - 1), count));
                        fileManager.saveTasks(tasks);
                    }
                    if (isDeadline) {
                        int index = userInput.indexOf("/by") + 4;
                        String dateString = userInput.substring(index);
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
                        LocalDateTime dateTime = LocalDateTime.parse(dateString, formatter);
                        String task = TextUi.extractTaskName(userInput);
                        tasks.add(new Deadline(task, false, dateTime));
                        count++;
                        System.out.println(TextUi.addComment(tasks.get(count - 1), count));
                        fileManager.saveTasks(tasks);
                    }

                } else if (isMark || isUnmark) {
                    if (isMark) {
                        int lastNumber = Character.getNumericValue(lastChar);
                        tasks.get(lastNumber - 1).finishTask();
                        System.out.println(TextUi.markMessage(tasks.get(lastNumber - 1)));
                        fileManager.saveTasks(tasks);
                    } else {
                        int lastNumber = Character.getNumericValue(lastChar);
                        tasks.get(lastNumber - 1).redoTask();
                        System.out.println(TextUi.markMessage(tasks.get(lastNumber - 1)));
                        fileManager.saveTasks(tasks);
                    }
                } else if (userInputArray[0].equals("delete")) {
                    int index = Character.getNumericValue(lastChar);
                    Task temp = tasks.get(index - 1);
                    tasks.remove(index - 1);
                    System.out.println(TextUi.deleteMessage(temp, tasks.size()));
                    fileManager.saveTasks(tasks);
                } else {
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
}
