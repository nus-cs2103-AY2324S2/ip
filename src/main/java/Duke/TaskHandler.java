package Duke;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.String.join;

public class TaskHandler{

    /**
     * Handles user input and performs operation based of it
     *
     * @param input
     * @param storage
     * @param filePath
     * @throws IOException
     */
    public static void doTasks(String input, TaskList storage, Path filePath) throws IOException {
        int taskNum = storage.size() + 1;
        if (input.equals("list")) {
            String contents = storage.showALlTask();

            UI.listMsg(contents);

        } else if (input.equals("help")) {
            UI.showAvailCommands();

        } else if (input.startsWith("find")) {
            String[] temp = input.split(" ");
            ArrayList<String> keywords = new ArrayList<>(Arrays.asList(temp));
            try {
                checkInput(temp, storage, UI.errorMsg("find"));
                keywords.remove(0);
                String keyword = String.join(" ", keywords);
                TaskList matchedTasks = findMatchedTasks(keyword, storage);
                String content = matchedTasks.showALlTask();
                UI.printMatchedTasks(content);


            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }

        } else if (input.equals("check dates")) {
            Scanner scan = new Scanner(System.in);
            UI.checkDatesMsg();
            String fromDate = scan.nextLine();
            System.out.println("End: ");
            String toDate = scan.nextLine();
            ArrayList<Task> taskList = TaskHandler.checkSchedule(fromDate, toDate, storage);
            UI.scheduledTaskMsg();
            for (Task task : taskList) {
                System.out.println(task.toString());
            }
        } else if (input.startsWith("mark")) {
            String[] temp = input.split(" ");
            try {
                checkInput(temp, storage, UI.errorMsg("mark"));
                int index = Integer.parseInt(temp[1]);
                boolean toMark = true;
                Task task = storage.mark(index, toMark);
                UI.markedMsg(task);

            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }

        } else if (input.startsWith("unmark")) {
            String[] temp = input.split(" ");
            try {
                checkInput(temp, storage, UI.errorMsg("unmark"));
                int index = Integer.parseInt(temp[1]);
                boolean toMark = false;
                Task task = storage.mark(index, toMark);
                UI.unMarkedMsg(task);

            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }

        } else if (input.startsWith("delete")) {
            String[] temp = input.split(" ");
            try {
                checkInput(temp, storage, UI.errorMsg("delete"));
                int index = Integer.parseInt(temp[1]);
                Task task  = storage.delete(index);
                UI.deleteMsg(task, storage);

            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }

        } else if (input.startsWith("todo")) {
            try {
                String[] test = input.split(" ");
                checkTaskInput(test, storage, UI.errorMsg("todo"));
                String msg = input.substring(5);
                ToDo task = new ToDo(msg);
                UI.printAddMsg(task, taskNum);
                storage.add(task);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }

        } else if (input.startsWith("deadline")) {
            try {
                String[] test = input.split(" ");
                checkTaskInput(test, storage, UI.errorMsg("deadline"));
                String msg = input.substring(9);
                Pattern p = Pattern.compile("(.+) /by (\\d+/\\d+/\\d+)(.*)");
                Matcher m = p.matcher(msg);
                Deadline task;
                if (m.matches()) {
                    LocalDate date = getDate(m.group(2));
                    task = new Deadline(m.group(1), date);
                } else {
                    throw new DukeException("__________________________________\n" +
                            "Need a deadline!!\n" +
                            "__________________________________\n");
                }
                UI.printAddMsg(task, taskNum);
                storage.add(task);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }

        } else if (input.startsWith("event")) {
            try {
                String[] test = input.split(" ");
                checkTaskInput(test, storage, UI.errorMsg("event"));
                String msg = input.substring(6);
                Pattern p = Pattern.compile("(.+) /from (\\d+/\\d+/\\d+)(.*) /to (\\d+/\\d+/\\d+)(.*)");
                Matcher m = p.matcher(msg);
                Event task;
                if (m.matches()) {
                    LocalDate fromDate = getDate(m.group(2));
                    LocalDate toDate = getDate(m.group(4));
                    task = new Event(m.group(1), fromDate, toDate);
                } else {
                    throw new DukeException("__________________________________\n" +
                            "Need a period for the event!!\n" +
                            "__________________________________\n");
                }
                UI.printAddMsg(task, taskNum);
                storage.add(task);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }

        } else {
            try {
                throw new DukeException("__________________________________\n" +
                        "What is this task???\n" +
                        "__________________________________\n");
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
        Files.writeString(filePath, "");
        for (Task task : storage) {
            try {
                Files.writeString(filePath, "\n" + task.toString(), StandardOpenOption.APPEND);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }


    }

    /**
     * Check for tasks that occur during the date range provided
     *
     * @param start
     * @param end
     * @param storage
     * @return ArrayList<Task> containing task in the date range
     */
    public static ArrayList<Task> checkSchedule(String start, String end, ArrayList<Task> storage) {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            LocalDate fromDate = getDate(start);
            LocalDate toDate = getDate(end);
            for (Task task : storage) {
                if (task instanceof Deadline || task instanceof Event) {
                    SpecialTask specialTask = (SpecialTask) task;
                    if (specialTask.inRange(fromDate, toDate)) {
                        tasks.add(specialTask);
                    }
                }
            }
            return tasks;
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        return tasks;
    }

    /**
     * Translate the date of String type into a LocalDate type
     *
     * @param msg
     * @return LocalDate
     * @throws DukeException
     */
    private static LocalDate getDate(String msg) throws DukeException {
        Pattern p = Pattern.compile("(\\d+)/(\\d+)/(\\d+)");
        Matcher m = p.matcher(msg);
        if (m.matches()) {
            String month = String.format("%02d", Integer.parseInt(m.group(2)));
            String day = String.format("%02d", Integer.parseInt(m.group(1)));
            String dateString = m.group(3) + "-" + month + "-" + day;
            return LocalDate.parse(dateString);
        } else {
            throw new DukeException("Date format is wrong!!");
        }
    }

    /**
     * Check if user input is correct for the mark, unmark and delete commands
     *
     * @param temp
     * @param storage
     * @param errorMsg
     * @throws DukeException
     */
    private static void checkInput(String[] temp, TaskList storage, String errorMsg) throws DukeException {
        if (temp.length <= 1) {
            throw new DukeException(errorMsg);
        }
        if (temp[0].equals("mark") || temp[0].equals("unmark") || temp[0].equals("delete")) {
            int number = Integer.parseInt(temp[1]);
            if (number > storage.size() || number <= 0) {
                throw new DukeException("__________________________________\n" +
                        "The task is not in the list!!");
            }
        }
    }

    /**
     * Check if user input is correct for the tasks commands
     *
     * @param temp
     * @param storage
     * @param errorMsg
     * @throws DukeException
     */
    private static void checkTaskInput(String [] temp, TaskList storage, String errorMsg) throws DukeException {
        if (temp.length <= 1) {
            throw new DukeException(errorMsg);
        }

    }

    private static TaskList findMatchedTasks(String keyword, TaskList storage) throws DukeException {
        Pattern p = Pattern.compile(keyword);
        TaskList matchedTasks = new TaskList();
        for (Task task : storage) {
            String taskMsg = task.toString();
            Matcher m = p.matcher(taskMsg);
            if (m.find()) {
                matchedTasks.add(task);
            }
        }
        if (matchedTasks.isEmpty()) {
            throw new DukeException("__________________________________\n" +
                    "There is no such matches!!\n" +
                    "__________________________________");
        }
        return matchedTasks;

    }

}
