import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;

public class Lemona {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();
        String line = "\t______________________________________________________";
        String addMessage = "\t Got it. I've added this task:";

        File f = new File("./data/lemona.txt");
        try {
            if (f.createNewFile()) {
                System.out.println("New file created");
            } else {
                System.out.println("File already exists");
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        //greeting
        System.out.println(line +
                "\n\t Hello! I'm Lemona" +
                "\n\t Would you like some vitamins?" +
                "\n" + line);

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
                        System.out.println("\t Here are the tasks in your list:");
                        for (int i = 0; i < list.size(); i++) {
                            System.out.println("\t " + (i + 1) + "." + list.get(i).print());
                        }
                        System.out.println(line);
                        break;
                    case ("mark"):
                        int index = Integer.parseInt(parts[1]);
                        if (list.size() < index) {
                            throw new OutOfIndexException();
                        } else if (list.get(index - 1).getStatusIcon().equals("X")) {
                            throw new DuplicateInstructionException();
                        }

                        list.get(index - 1).markAsDone();
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
                        System.out.println("\t Now you have " + list.size() + " tasks in the list.");
                        System.out.println(line);
                        break;
                    case ("todo"):
                        if (size == 1) {
                            throw new MissingDescriptionException();
                        }

                        Task task = new Todo(parts[1]);
                        for (int i = 0; i < list.size(); i++) {
                            if (list.get(i).getDescription().equals(task.getDescription())) {
                                throw new DuplicateInstructionException();
                            }
                        }

                        list.add(task);
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
                        for (int i = 0; i < list.size(); i++) {
                            if (list.get(i).getDescription().equals(task.getDescription())) {
                                throw new DuplicateInstructionException();
                            }
                        }

                        list.add(task);
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

                        String[] dates = content[1].split("/to ");
                        task = new Event(content[0], dates[0], dates[1]);
                        for (int i = 0; i < list.size(); i++) {
                            if (list.get(i).getDescription().equals(task.getDescription())) {
                                throw new DuplicateInstructionException();
                            }
                        }

                        list.add(task);
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
            }
        }
    }
}
