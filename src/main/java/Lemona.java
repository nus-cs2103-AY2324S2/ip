import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Lemona {
    private static final String path = "./data/lemona.txt";
    private static final String line = "\t______________________________________________________";
    public static void main(String[] args) {
        ArrayList<Task> list = new ArrayList<>();
        String addMessage = "\t Got it. I've added this task:";

        try {
            list = load(list);
        } catch (IOException e) {
            System.out.println(line);
            System.out.println("\t Sorry, I think I haven't had enough vitamin C."
                    + "\n\t I am unable to load tasks from the file."
                    + "\n\t I will need to go have some LEMONA");
        }

        //greeting
        System.out.println(line +
                "\n\t Hello! I'm Lemona" +
                "\n\t Would you like some vitamins?" +
                "\n" + line);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            String[] untrimmedParts = input.split(" ", 2);
            String[] parts = Arrays.stream(untrimmedParts)
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .toArray(String[]::new);
            int size = parts.length;

            System.out.println(line);
            try {

                if (parts[0].equals("mark") || parts[0].equals("unmark") || parts[0].equals("delete") && size == 1) {
                    if (size == 1) {
                        throw new MissingIndexException();
                    }
                }

                switch (parts[0]) {
                case ("bye"):
                    System.out.println("\t Bye. Don't forget to take a LEMONA!");
                    System.out.println(line);
                    scanner.close();
                    return;
                case ("list"):
                    if (list.size() == 0) {
                        System.out.println("\t I think you haven't had enough vitamin E."
                                + "\n\t You do not have any tasks on the list yet!"
                                + "\n\t I suggest you take some LEMONA.");
                        System.out.println(line);
                    } else {
                        System.out.println("\t Here are the tasks in your list:");
                        for (int i = 0; i < list.size(); i++) {
                            System.out.println("\t " + (i + 1) + "." + list.get(i).print());
                        }
                        System.out.println(line);
                    }
                    break;
                case ("mark"):
                    int index = Integer.parseInt(parts[1]);
                    if (list.size() < index) {
                        throw new OutOfIndexException();
                    } else if (list.get(index - 1).getStatusIcon().equals("X")) {
                        throw new DuplicateInstructionException();
                    }

                    list.get(index - 1).markAsDone();
                    save(list);
                    System.out.println("\t Nice! I've marked this task as done:" + "\n\t\t" +
                            list.get(index - 1).print());
                    System.out.println(line);
                    break;
                case ("unmark"):
                    index = Integer.parseInt(parts[1]);
                    if (list.size() < index) {
                        throw new OutOfIndexException();
                    } else if (list.get(index - 1).getStatusIcon().equals(" ")) {
                        throw new DuplicateInstructionException();
                    }

                    list.get(index - 1).unmarkAsDone();
                    save(list);
                    System.out.println("\t OK, I've marked this task as not done yet:" + "\n\t\t" +
                            list.get(index - 1).print());
                    System.out.println(line);
                    break;
                case ("delete"):
                    index = Integer.parseInt(parts[1]);
                    if (list.size() < index || index < 1) {
                        throw new OutOfIndexException();
                    }

                    System.out.println("\t OK, I've removed this task:" + "\n\t\t" +
                            list.get(index - 1).print());
                    list.remove(index - 1);
                    save(list);
                    System.out.println("\t Now you have " + list.size() + " tasks in the list.");
                    System.out.println(line);
                    break;
                case ("todo"):
                    if (size == 1) {
                        throw new MissingDescriptionException();
                    }

                    Task task = new Todo(parts[1]);
                    for (Task value : list) {
                        if (value.getDescription().equals(task.getDescription())) {
                            throw new DuplicateInstructionException();
                        }
                    }

                    list.add(task);
                    save(list);
                    System.out.println(addMessage);
                    System.out.print("\t   " + task.print());
                    System.out.println("\n\t Now you have " + list.size() + " tasks in the list.");
                    System.out.println(line);
                    break;
                case ("deadline"):
                    if (size == 1 || parts[1].split("/by ").length == 1) {
                        throw new MissingDescriptionException();
                    }

                    String[] content = parts[1].split("/by ");
                    task = new Deadline(content[0], content[1]);
                    for (Task value : list) {
                        if (value.getDescription().equals(task.getDescription())) {
                            throw new DuplicateInstructionException();
                        }
                    }

                    list.add(task);
                    save(list);
                    System.out.println(addMessage);
                    System.out.print("\t   " + task.print());
                    System.out.println("\n\t Now you have " + list.size() + " tasks in the list.");
                    System.out.println(line);
                    break;
                case ("event"):
                    if (size == 1 || parts[1].split("/from ").length == 1) {
                        throw new MissingDescriptionException();
                    }

                    content = parts[1].split("/from ");

                    if (size == 1 || content[1].split("/to ").length == 1) {
                        throw new MissingDescriptionException();
                    }

                    String[] dates = content[1].split(" /to ");
                    task = new Event(content[0], dates[0], dates[1]);
                    for (Task value : list) {
                        if (value.getDescription().equals(task.getDescription())) {
                            throw new DuplicateInstructionException();
                        }
                    }

                    list.add(task);
                    save(list);
                    System.out.println(addMessage);
                    System.out.print("\t  " + task.print());
                    System.out.println("\n\t Now you have " + list.size() + " tasks in the list.");
                    System.out.println(line);
                    break;
                default:
                    System.out.println("\t I think you haven't had enough vitamin C." +
                            "\n\t I can't understand what you want me to do!" +
                            "\n\t I suggest you take some LEMONA.");
                    System.out.println(line);
                }
            } catch (OutOfIndexException e) {
                System.out.println(e.toString(list.size()));
                System.out.println(line);
            } catch (MissingIndexException e) {
                System.out.println(e.toString(parts[0]));
                System.out.println(line);
            } catch (MissingDescriptionException e) {
                System.out.println(e.toString(parts[0]));
                System.out.println(line);
            } catch (DuplicateInstructionException e) {
                System.out.println(e.toString(parts[0]));
                System.out.println(line);
            } catch (IOException e) {
                System.out.println("\t Sorry, I think I haven't had enough vitamin C."
                        + "\n\t I am unable to save tasks into the file."
                        + "\n\t I will need to go have some LEMONA.");
                System.out.println(line);
            } catch (DateTimeParseException e) {
                System.out.println("\t I think you haven't had enough vitamin C."
                        + "\n\t Your time format should be :"
                        + "\n\t\t { dd/MM/yyyy HHmm }"
                        + "\n\t I suggest you take some LEMONA.");
                System.out.println(line);
            }
        }
    }

    public static String listToString(ArrayList<Task> list) {
        String content = "";
        for (int i = 0; i < list.size() - 1; i++) {
            Task task = list.get(i);
            content = content + task.getTaskInfo() + "\n";
        }
        Task task = list.get(list.size() - 1);
        content = content + task.getTaskInfo();
        return content;
    }

    public static ArrayList<Task> stringToList(ArrayList<Task> array, String text) {
        String[] list = text.split("(new) ");
        for (String s : list) {
            String[] info = s.split("/ ");
            switch (info[0]) {
            case "[T] ":
                Task task = new Todo(info[2]);
                if (info[1].equals("[X]")) {
                    task.markAsDone();
                }
                array.add(task);
                break;
            case "[D] ":
                String dueDate = LocalDateTime.parse(info[3], DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"))
                        .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
                task = new Deadline(info[2], dueDate);
                if (info[1].equals("[X]")) {
                    task.markAsDone();
                }
                array.add(task);
                break;
            case "[E] ":
                String startTime = LocalDateTime.parse(info[3], DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"))
                        .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
                String endTime = LocalDateTime.parse(info[4], DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"))
                        .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
                task = new Event(info[2], startTime, endTime);
                if (info[1].equals("[X]")) {
                    task.markAsDone();
                }
                array.add(task);
                break;
            default:
                System.out.println("File is corrupted!");
            }
        }
        return array;
    }

    public static ArrayList<Task> load(ArrayList<Task> array) throws IOException{
        File file = new File(path);
        ArrayList<Task> tasks = new ArrayList<>();
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            String task = scanner.nextLine();
            tasks = stringToList(array, task);
        }
        return tasks;
    }

    public static void save(ArrayList<Task> tasks) throws IOException{
        if (tasks.size() != 0) {
            File file = new File(path);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            fw.write(listToString(tasks));
            fw.close();
        }
    }
}
