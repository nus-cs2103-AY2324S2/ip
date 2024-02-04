package chatbot;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Alfred {
    protected static final String LINE = "________________________________________________________________________________________";
    protected static ArrayList<Task> taskList = new ArrayList<Task>();

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
                    taskList.add(todo);
                } else if (input.startsWith("D")) {
                    String[] splitResult = input.split("\\|", 4);
                    int toggle = Integer.parseInt(splitResult[1].trim());
                    String description = splitResult[2].trim();
                    // Parsing LocalDateTime directly from splitResult
                    String[] dateParts = splitResult[3].split("\\|");
                    LocalDateTime by = parseTime(dateParts);
                    // Create Deadline object and add it to the list
                    Deadline deadline = new Deadline(description, by);
                    if (toggle == 1) {
                        deadline.toggleDone();
                    }
                    taskList.add(deadline);
                } else if (input.startsWith("E")) {
                    String[] splitResult = input.split("\\|", 4);
                    int toggle = Integer.parseInt(splitResult[1].trim());
                    String description = splitResult[2].trim();
                    String[] formattedBy = splitResult[3].split("\\|");
                    // Split the formattedBy array into two parts: start and end times
                    int halfLength = formattedBy.length / 2;
                    String[] startTimeParts = Arrays.copyOfRange(formattedBy, 0, halfLength);
                    String[] endTimeParts = Arrays.copyOfRange(formattedBy, halfLength, formattedBy.length);
                    LocalDateTime startTime = parseTime(startTimeParts);
                    LocalDateTime endTime = parseTime(endTimeParts);
                    Event event = new Event(description, startTime, endTime);
                    if (toggle == 1) {
                        event.toggleDone();
                    }
                    taskList.add(event);
                }
            }
    }

    private LocalDateTime parseTime(String[] endTimeParts) {
        int endDay = Integer.parseInt(endTimeParts[0].trim());
        int endMonth = Integer.parseInt(endTimeParts[1].trim());
        int endYear = Integer.parseInt(endTimeParts[2].trim());
        String endTimeStr = endTimeParts[3].trim();
        int endHour = Integer.parseInt(endTimeStr.substring(0, 2));
        int endMinute = Integer.parseInt(endTimeStr.substring(2));
        return LocalDateTime.of(endYear, endMonth, endDay, endHour, endMinute);
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
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            switch (taskType) {
                case "chatbot.ToDo":
                    fw.write("T | " + (task.isDone() ? 1 : 0) + " | " + task.getDescription() + "\n");
                    break;
                case "chatbot.Deadline":
                    Deadline deadline = (Deadline) task;
                    LocalDateTime by = deadline.getBy();
                    // Extract date components
                    int day = by.getDayOfMonth();
                    int month = by.getMonthValue(); // make this double-digit?
                    int year = by.getYear();
                    // Extract time components
                    int hour = by.getHour();
                    int minute = by.getMinute();
                    // Write the components to the file
                    fw.write("D | " + (task.isDone() ? 1 : 0) + " | " + task.getDescription() + " | " +
                            day + " | " + month + " | " + year + " | " +
                            String.format("%02d%02d", hour, minute) + "\n");
                    break;
                case "chatbot.Event":
                    Event event = (Event) task;
                    LocalDateTime startTime = event.getStartTime();
                    LocalDateTime endTime = event.getEndTime();

                    // Extract start time components
                    int startDay = startTime.getDayOfMonth();
                    int startMonth = startTime.getMonthValue();
                    int startYear = startTime.getYear();
                    int startHour = startTime.getHour();
                    int startMinute = startTime.getMinute();

                    // Extract end time components
                    int endDay = endTime.getDayOfMonth();
                    int endMonth = endTime.getMonthValue();
                    int endYear = endTime.getYear();
                    int endHour = endTime.getHour();
                    int endMinute = endTime.getMinute();

                    // Write the components to the file
                    fw.write("E | " + (task.isDone() ? 1 : 0) + " | " + task.getDescription() + " | " +
                            startDay + " | " + startMonth + " | " + startYear + " | " +
                            String.format("%02d%02d", startHour, startMinute) + " | " +
                            endDay + " | " + endMonth + " | " + endYear + " | " +
                            String.format("%02d%02d", endHour, endMinute) + "\n");
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
                taskList.add(todo);
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
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
                    LocalDateTime dateTime = LocalDateTime.parse(by, formatter);
                    Deadline deadline = new Deadline(description, dateTime);
                    taskList.add(deadline);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new AlfredException("Sorry Master Bruce. Please specify the due date or time by including /by due-date.");
                } catch (java.time.format.DateTimeParseException e) {
                    throw new AlfredException("Sorry Master Bruce. Please specify the due date or time in the format of dd/MM/yyyy HHmm.");
                }
                if (description.isEmpty()) {
                    throw new AlfredException("Sorry Master Bruce. The description of a deadline cannot be empty.");
                } else if (by.isEmpty()) {
                    throw new AlfredException("Sorry Master Bruce. Please specify the due date or time by including /by due-date.");
                }
                break;
            case EVENT:
                if (input.length() <= 6 || !input.contains("/from") || !input.contains("/to")) {
                    throw new AlfredException("Sorry Master Bruce. Please specify the description, start time, and end time of the event by including /from start-time /to end-time.");
                }
                input = input.substring(6).trim();
                // Regular expression patterns
                Pattern descriptionPattern = Pattern.compile("^(.*?)\\s*/from");
                Pattern fromPattern = Pattern.compile("/from\\s+(\\d{2}/\\d{2}/\\d{4}\\s+\\d{4})");
                Pattern toPattern = Pattern.compile("/to\\s+(\\d{2}/\\d{2}/\\d{4}\\s+\\d{4})");
                // Match patterns against input
                Matcher descriptionMatcher = descriptionPattern.matcher(input);
                Matcher fromMatcher = fromPattern.matcher(input);
                Matcher toMatcher = toPattern.matcher(input);
                String descriptionEvent = null;
                String startTime = null;
                String endTime = null;
                // Find description, start time, and end time
                if (descriptionMatcher.find()) {
                    descriptionEvent = descriptionMatcher.group(1);
                }
                if (fromMatcher.find()) {
                    startTime = fromMatcher.group(1);
                }
                if (toMatcher.find()) {
                    endTime = toMatcher.group(1);
                }
                // Check if description, start time, and end time are found
                if (descriptionEvent == null || startTime == null || endTime == null) {
                    throw new AlfredException("Sorry Master Bruce. Please specify both description, start time, and end time.");
                }
                // Parse start time and end time
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
                LocalDateTime startDateTime = LocalDateTime.parse(startTime, formatter);
                LocalDateTime endDateTime = LocalDateTime.parse(endTime, formatter);
                // Create Event object and add it to the list
                Event event = new Event(descriptionEvent, startDateTime, endDateTime);
                taskList.add(event);
                break;
            default:
                throw new AlfredException("Sorry Master Bruce. I don't understand what you mean.");
        }
        } catch (AlfredException e) {
            e.printError();
        }
        String singularTask = taskList.size() == 1 ? "task" : "tasks";
        this.echo(String.format("Got it. I've added this task:\n  %s\nNow you have %d %s in the list.",
                taskList.get(taskList.size() - 1).toString(), taskList.size(), singularTask));
    }

    private void updateData() {
        try {
            java.io.FileWriter fw = new java.io.FileWriter("data/alfred.txt");
            for (Task task : taskList) {
                addData(task);
            }
            fw.close();
        } catch (java.io.IOException e) {
            this.echo("Sorry Master Bruce. I cannot write to the data.");
        }
    }

    public static void printList(ArrayList<Task> taskList, Alfred alfred) {
        if (taskList == null || taskList.size() == 0) {
            alfred.echo("Sorry Master Bruce. There are no tasks in the list.");
            return;
        }
        System.out.println(LINE);
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(i + 1 + ". " + taskList.get(i).toString());
        }
        System.out.println(LINE);
    }

    public void markList(String index) {
        int extractedIdx = validateAndExtractIndex(index);
        if (extractedIdx == -1) {
            return;
        }
        if (taskList.get(extractedIdx).isDone()) {
            this.echo("Sorry Master Bruce. This task has already been marked as done.");
            return;
        }
        taskList.get(extractedIdx).toggleDone();
        this.echo("Nice! I've marked this task as done:\n  " + taskList.get(extractedIdx).toString());
    }

    public void unmarkList(String index) {
        int extractedIdx = validateAndExtractIndex(index);
        if (extractedIdx == -1) {
            return;
        }
        if (!taskList.get(extractedIdx).isDone()) {
            this.echo("Sorry Master Bruce. This task has already been marked as not done.");
            return;
        }
        taskList.get(extractedIdx).toggleDone();
        this.echo("OK, I've marked this task as not done yet:\n  " + taskList.get(extractedIdx).toString());
    }

    public void deleteList(String index) {
        int extractedIdx = validateAndExtractIndex(index);
        if (extractedIdx == -1) {
            return;
        }
        String removedTask = taskList.get(extractedIdx).toString();
        taskList.remove(extractedIdx);
        this.echo("Noted. I've removed this task:\n  " + removedTask + "\nNow you have " + taskList.size() + " tasks in the list.");
    }
    private int validateAndExtractIndex(String index) {
        try {
            if (index.isEmpty()) {
                throw new AlfredException("Please enter a number after the command.");
            }
            int extractedIdx = Integer.parseInt(index) - 1;
            if (taskList.isEmpty()) {
                throw new AlfredException("Sorry Master Bruce. There are no tasks in the list.");
            } else if (extractedIdx < 0 || extractedIdx >= taskList.size()) {
                if (taskList.size() == 1) {
                    throw new AlfredException("Sorry Master Bruce. The task number you have entered is not in the list. There is only one item in the list.");
                } else {
                    throw new AlfredException("Sorry Master Bruce. The task number you have entered is not in the list. " +
                            "Please enter a number in the range of 1 to " + taskList.size() + ".");
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
    private void findByDate(LocalDateTime dateTime) {
        ArrayList<Task> result = new ArrayList<Task>();
        for (Task task : taskList) {
            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                if (deadline.getBy().equals(dateTime)) {
                    result.add(deadline);
                }
            } else if (task instanceof Event) {
                Event event = (Event) task;
                if (event.getStartTime().equals(dateTime) || event.getEndTime().equals(dateTime)) {
                    result.add(event);
                }
            }
        }
        this.printList(result, this);
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
                alfred.printList(alfred.taskList, alfred);
                break;
            default:
                if (input.startsWith("unmark")) {
                    alfred.unmarkList(input.substring(6).trim());
                } else if (input.startsWith("mark")) {
                    alfred.markList(input.substring(4).trim());
                } else if (input.startsWith("delete")) {
                    alfred.deleteList(input.substring(6).trim());
                } else if (input.startsWith("find by date")) {
                    LocalDateTime dateTime = LocalDateTime.parse(input.substring(12).trim(), DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
                    alfred.findByDate(dateTime);
                } else {
                    alfred.addList(input);
                }
                break;
            }
        }
    }
}