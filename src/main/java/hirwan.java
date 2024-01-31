import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class hirwan {
    static String FILE_PATH = "C:\\Users\\eugen\\Documents\\National University of Singapore\\Y2S2\\CS2103T\\IP\\src\\main\\java\\data\\hirwan.txt";
    static List<String> List = new ArrayList<>();

    public static void main(String[] args) {
        String logo = "I'm hirwan \n"
                + "_________________________________\n"
                + "what can I do for you? \n"
                + "_________________________________\n";

        Ui.output("Hello! " + logo);

        Tasklist List = new Tasklist(Storage.read());

        try (BufferedWriter fileWrite = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            while (true) {
                String text = Ui.input();
                int input = Parser.translate(text);

                if (input == 8) {
                    break;
                } else if (input == 1) {
                    List.printList();
                } else if (input == 2) {
                    try {
                        Ui.output("Got it. I've added this task: \n  " + "[T][ ] " + text.substring(5));
                        List.add(". " + "[T][ ] " + text.substring(5));
                        Ui.output("Now you have " + List.size() + " tasks in the list.");
                        Storage.writeTask(List.getList());
                    } catch (StringIndexOutOfBoundsException e) {
                        Ui.output("Error: Please enter a description for your todo command");
                    }
                } else if (input == 3) {
                    try {
                        String delimiter = " /by";
                        int Index = text.indexOf(delimiter);
                        String Day = text.substring(Index + 5);
                        String item = text.substring(9, Index);

                        LocalDateTime dayDate = translateDate(Day);
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d 'of' MMMM yyyy, ha");

                        List.add(". " + "[D][ ] " + item + " (by: " + dayDate.format(formatter) + ")");
                        Ui.output("Got it. I've added this task:\n  " + "[D][ ] " + item + " (by: " + dayDate.format(formatter) + ")");
                        Ui.output("Now you have " + List.size() + " tasks in the list.");
                    } catch (StringIndexOutOfBoundsException e) {
                        Ui.output("Error: Please enter a description or date for your deadline command");
                    }
                } else if (input == 4) {
                    try {
                        String delimiterstart = " /from";
                        String delimiterend = " /to";
                        int Indexstart = text.indexOf(delimiterstart);
                        int Indexend = text.indexOf(delimiterend);
                        String start = text.substring(Indexstart + 7, Indexend);
                        String end = text.substring(Indexend + 5);
                        String item = text.substring(6, Indexstart);

                        LocalDateTime startDate = translateDate(start);
                        LocalDateTime endDate = translateDate(end);
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d 'of' MMMM yyyy, ha");

                        List.add(". " + "[E][ ] " + item + " (from: " + startDate.format(formatter) + " to: " + endDate.format(formatter) + ")");
                        Ui.output("Got it. I've added this task:\n  " + "[E][ ] " + item + " (from: " + startDate.format(formatter) + " to: " + endDate.format(formatter) + ")");
                        Ui.output("Now you have " + List.size() + " tasks in the list.");
                        Storage.writeTask(List.getList());
                    } catch (StringIndexOutOfBoundsException e) {
                        Ui.output("Error: Please enter a description or date for your event to command");
                    }
                } else if (input == 5) {
                    try {
                        String number = text.substring(5);
                        int numberint = Integer.parseInt(number);
                        String temp = List.get(numberint - 1).substring(9);
                        String type = List.get(numberint - 1).substring(2, 5);

                        List.set(numberint - 1, ". " + type + "[X] " + temp);
                        Ui.output("Nice! I've marked this task as done: \n" + "[X] " + temp);
                        Storage.writeTask(List.getList());
                    } catch (IndexOutOfBoundsException e) {
                        Ui.output("Error: Please enter a valid index for marking!");
                    } catch (NumberFormatException e) {
                        Ui.output("Error: Please enter a numerical index to mark!");
                    }
                } else if (input == 6) {
                    try {
                        String number = text.substring(7);
                        int numberint = Integer.parseInt(number);
                        String temp = List.get(numberint - 1).substring(9);
                        String type = List.get(numberint - 1).substring(2, 5);

                        List.set(numberint - 1, ". " + type + "[ ] " + temp);
                        Ui.output("OK, I've marked this task as not done yet: \n" + "[ ] " + temp);
                        Storage.writeTask(List.getList());
                    } catch (IndexOutOfBoundsException e) {
                        Ui.output("Error: Please enter a valid index for unmarking!");
                    } catch (NumberFormatException e) {
                        Ui.output("Error: Please enter a numerical index to unmark!");
                    }
                } else if (input == 7) {
                    try {
                        int numberint = Integer.parseInt(text.substring(7)) - 1;
                        Ui.output("Noted. I've removed this task:\n"
                                + "  " + List.get(numberint).substring(2) + "\n"
                                + "Now you have " + (List.size() - 1) + " tasks in the list.");
                        List.delete(numberint);
                        Storage.writeTask(List.getList());
                    } catch (IndexOutOfBoundsException e) {
                        Ui.output("Error: Please enter a valid index for deletion!");
                    } catch (NumberFormatException e) {
                        Ui.output("Error: Please enter a numerical index to delete!");
                    }
                } else if (input ==9) {
                    Ui.output("Error: I am sorry but I do not recognise this command");
                }
            }
            Ui.output("Bye. Hope to see you again soon!");
        } catch (IOException e) {
            System.out.print(e.getMessage());
        }
    }

    public static LocalDateTime translateDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

        LocalDateTime dateStored = LocalDateTime.parse(date, formatter);
        return dateStored;
    }
}

class Ui {
    public static String input() {
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        return text;
    }

    public static void output(String printText) {
        System.out.println(printText);
    }
}

class Storage {
    static List<String> List = new ArrayList<>();
    static String FILE_PATH = "C:\\Users\\eugen\\Documents\\National University of Singapore\\Y2S2\\CS2103T\\IP\\src\\main\\java\\data\\hirwan.txt";

    public static List<String> read() {
        try {
            File filePath = new File(FILE_PATH);
            Scanner scan = new Scanner(filePath);
            while (scan.hasNext()) {
                List.add(scan.nextLine());
            }
        } catch (FileNotFoundException e) {
            Ui.output("Error: file to read data from cannot be found!");
        }
        return List;
    }

    public static void writeTask(List<String> List) {
        try {
            FileWriter file = new FileWriter(FILE_PATH);
            for (String tasks : List) {
                file.write(tasks + "\n");
            }
            file.close();
        } catch (IOException e) {
            Ui.output("Error: could not write to file");
            e.printStackTrace();
        }
    }

}

class Parser {
    public static int translate(String text) {

        if (text.equals("list")) {
            return 1;
        } else if (text.startsWith("todo")) {
            return 2;
        } else if (text.startsWith("deadline")) {
            return 3;
        } else if (text.startsWith("event")) {
            return 4;
        } else if (text.startsWith("mark")) {
            return 5;
        } else if (text.startsWith("unmark")) {
            return 6;
        } else if (text.startsWith("delete")) {
            return 7;
        } else if (text.equals("bye")) {
            return 8;
        } else {
            return 9;
        }
    }
}

class Tasklist {
    static List<String> List = new ArrayList<>();
    public Tasklist(List<String> List) {
        this.List = List;
    }
    public String get(int index) {
        return List.get(index);
    }

    public void add(String input) {
        List.add(input);
    }

    public void printList() {
        for (String element : List) {
            Ui.output(List.indexOf(element) + 1 + element);
        }
    }

    public void delete(int index) {
        List.remove(index);
    }

    public int size() {
        return List.size();
    }

    public List<String> getList() {
        return List;
    }

    public void set(int Index, String input) {
        List.set(Index, input);
    }
}