import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Fredricksen {
    public static void greeting(String line) {
        System.out.println(line);
        System.out.println("Hello! I'm Fredricksen");
        System.out.println("What can I do for you?");
        System.out.println(line);
    }

    public static void updateFile(ArrayList<Task> list) {
        String filename = "./data/Fredricksen.txt";
        File file = new File(filename);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            if (list.size() == 0) {
                file.delete();
                return;
            }
            for (int i = 0; i < list.size(); i++) {
                Task task = list.get(i);
                String type = task.getType();
                String content = task.getTask();
                boolean isDone = task.getDone();
                bw.write("type: " + type + " isDone: " + isDone + " content: " + content);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static ArrayList<Task> loadList() {
        ArrayList<Task> list = new ArrayList<>();
        String filename = "./data/Fredricksen.txt";
        File file = new File(filename);
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String next;
            while ((next = br.readLine()) != null) {
                int type = next.indexOf("type: ");
                int isDone = next.indexOf("isDone: ");
                int content = next.indexOf("content: ");
                String done = next.substring(isDone + 8, isDone + 9);
                Task newTask = new Task(next.substring(content + 9), next.substring(type + 6, type + 7), done.equals("t"));
                list.add(newTask);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        // read file, if have content, add to list, else leave list as empty.
        return list;
    }

    public static void listOfCommands() {
        System.out.println("You might have entered an invalid command!");
        System.out.println("Below are the available commands and formats to follow!");
        System.out.println("1. To view all your current task: list");
        System.out.println("2. To add new task:");
        System.out.println("    a. todos: todo <task>");
        System.out.println("    b. deadlines: deadline <task> /by <deadline>");
        System.out.println("    c. event: Event <event> /from <startdate, starttiming> /to <enddate, endtiming>");
        System.out.println("3. To delete a task: delete <task number>");
        System.out.println("4. To mark task as completed: mark <task number>");
        System.out.println("5. To unmark completed task: unmark <task number>");
        System.out.println("6. To exit program: bye");
    }
    public enum commands {
        list, todo, deadline, event, mark, unmark, delete, bye
    }
    public static void main(String[] args) {
        ArrayList<Task> list = loadList();
        Scanner in = new Scanner(System.in);
        // ArrayList<Task> list = new ArrayList<>();
        String line = "____________________________________________________________";
        greeting(line);
        boolean loop = true;
        while (loop) {
            System.out.println("");
            String s = in.nextLine();
            String[] split = s.split(" ");
            String first = split[0].toLowerCase();
            switch (first) {
                case "mark":
                    System.out.println(line);
                    if (split.length <= 1) {
                        listOfCommands();
                        break;
                    }
                    try {
                        Task t1 = list.get(Integer.parseInt(split[1]) - 1);
                        System.out.println("Nice! I've marked this task as done:");
                        t1.setDone();
                        System.out.println("    " + t1.printTask(t1.getType(), t1.getDone(), t1.getTask()));
                    } catch (IndexOutOfBoundsException e) {
                        String single = list.size() <= 1 ? "task" : "tasks";
                        System.out.println("You only have " + list.size() + " " + single + " currently. Type \"list\" to view all your current " + single);
                    }
                    System.out.println(line);
                    break;
                case "unmark":
                    System.out.println(line);
                    if (split.length <= 1) {
                        listOfCommands();
                        break;
                    }
                    try {
                        Task t2 = list.get(Integer.parseInt(split[1]) - 1);
                        System.out.println("OK, I've marked this task as not done yet:");
                        t2.setUndone();
                        System.out.println("    " + t2.printTask(t2.getType(), t2.getDone(), t2.getTask()));
                    } catch (IndexOutOfBoundsException e) {
                        String single = list.size() <= 1 ? "task" : "tasks";
                        System.out.println("You only have " + list.size() + " " + single + " currently. Type \"list\" to view all your current " + single);
                    }
                    System.out.println(line);
                    break;
                case "delete":
                    if (split.length <= 1) {
                        listOfCommands();
                        break;
                    }
                    String single = list.size() <= 1 ? "task" : "tasks";
                    System.out.println(line);
                    try {
                        Task t2 = list.get(Integer.parseInt(split[1]) - 1);
                        System.out.println("Noted. I've removed this task:");
                        list.remove(Integer.parseInt(split[1]) - 1);
                        System.out.println("    " + t2.printTask(t2.getType(), t2.getDone(), t2.getTask()));
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("You only have " + list.size() + " " + single + " currently. Type \"list\" to view all your current " + single);
                    }
                    System.out.println("Now you have " + list.size() + " " + single + " in the list.");
                    System.out.println(line);
                    break;
                case "bye":
                    System.out.println(line);
                    System.out.println("Bye. Hope to see you again soon!");
                    System.out.println(line);
                    loop = false;
                    break;
                case "":
                    System.out.println(line);
                    listOfCommands();
                    System.out.println(line);
                    break;
                case "list":
                    System.out.println(line);
                    System.out.println("Here are the tasks in your list:");
                    if (list.size() > 0) {
                        Task.printList(list);
                    }
                    System.out.println(line);
                    break;
                case "todo":
                case "event":
                case "deadline":
                    if (split.length <= 1) {
                        listOfCommands();
                        break;
                    }
                    System.out.println(line);
                    System.out.println("Got it. I've added this task: ");
                    String res = "";
                    switch (first) {
                        case "todo":
                            res = "T";
                            break;
                        case "event":
                            res = "E";
                            break;
                        case "deadline":
                            res = "D";
                            break;
                    }
                    int startInd1 = s.indexOf("/by");
                    int startInd2 = s.indexOf("/from");
                    Task newTask = null;
                    String ss = s.substring(first.length() + 1);
                    if (startInd2 == -1 && startInd1 == -1) {
                        newTask = new Task(ss, res, false);
                    } else {
                        DateTimeFormatter[] formats = new DateTimeFormatter[] {
                                DateTimeFormatter.ofPattern("d/M/yyyy HHmm"),
                                DateTimeFormatter.ofPattern("d/M/yyyy"),
                                DateTimeFormatter.ofPattern("d-M-yyyy"),
                        };
                        if (startInd1 != -1) {
                            // dl = deadline
                            String spl1 = s.substring(startInd1 + 4);
                            LocalDateTime deadline = null;
                            for (DateTimeFormatter format : formats) {
                                try {
                                    deadline = LocalDateTime.parse(spl1, format);
                                    break;
                                } catch (DateTimeParseException e) {
                                }
                            }

                            /** if (deadline != null) {
                                System.out.println(deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy, hh:mm a")));
                            } else {
                                System.out.println("Failed to parse date");
                            } **/

                            String dl = s.substring(first.length() + 1, startInd1) + "(" + s.substring(startInd1 + 1, startInd1 + 3) + ": " + deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy, hh:mm a")) + ")";
                            newTask = new Task(dl, res, false, deadline);
                        } else {
                            // e = event
                            int startTo = s.indexOf("/to");
                            String from = s.substring(startInd2 + 6, startTo - 1);
                            String to = s.substring(startTo + 4);
                            LocalDateTime duefrom = null;
                            LocalDateTime dueto = null;
                            for (DateTimeFormatter format : formats) {
                                try {
                                    duefrom = LocalDateTime.parse(from, format);
                                } catch (DateTimeParseException e) {
                                }

                                try {
                                    dueto = LocalDateTime.parse(to, format);
                                } catch (DateTimeParseException e) {
                                }

                            }
                            String e = "";
                            if (dueto != null && duefrom != null) {
                                e = s.substring(first.length() + 1, startInd2) +
                                    "(" + s.substring(startInd2 + 1, startInd2 + 5) + ": " + duefrom.format(DateTimeFormatter.ofPattern("MMM d yyyy, hh:mm a")) + " " + s.substring(startTo + 1, startTo + 3) +
                                    ": " + dueto.format(DateTimeFormatter.ofPattern("MMM d yyyy, hh:mm a")) + ")";
                            }

                            newTask = new Task(e, res, false, duefrom, dueto);
                        }
                    }
                    list.add(newTask);
                    String t = newTask.printTask(res, false, newTask.getTask());
                    // String task = "  [" + res + "]" + "[] " + s.substring(first.length() + 1, s.length());
                    System.out.println("    " + t);
                    String single1 = list.size() == 1 ? "task" : "tasks";
                    System.out.println("Now you have " + list.size() + " " + single1 + " in the list.");
                    System.out.println(line);
                    break;
                default:
                    listOfCommands();
                    break;
            }
            // TODO
            updateFile(list);
        }
        in.close();
    }
}
