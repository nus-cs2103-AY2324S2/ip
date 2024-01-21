import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = "\t  ____   __  _  _  __    ___  ___  ____  ___  ____\n\t"
                + " (_  _) /__\\( \\/ )/__\\  / __)/ __)(_  _)/ __)(_  _)\n\t"
                + " .-_)( /(__)\\\\  //(__)\\ \\__ \\\\__ \\ _)(_ \\__ \\  )(\n\t"
                + "\\____)(__)(__)\\/(__)(__)(___/(___/(____)(___/ (__)\n";

        String line = "\t________________________________________________________________";

        Scanner read = new Scanner(System.in);

        ArrayList<String> list = new ArrayList<>();

        System.out.println(line + "\n" + logo + "\n\t Hello! I'm JavAssist");
        System.out.println("\t What can I do for you?\n" + line);
        String input;
        while(true) {
            System.out.println();
            input = read.nextLine();
            System.out.println(line);
            if (input.equals("list")) {
                print(list);
                System.out.println(line);
            } else if (!input.equals("bye")) {
                list.add(input);
                System.out.println("\t added: " + input + "\n" + line);
            } else {
                break;
            }
        }

        System.out.println("\t Bye. Hope to see you again soon!\n" + line);
    }

    private static void print(ArrayList<String> list) {
        int count = 1;
        for (String s : list) {
            System.out.println("\t " + count + ". " + s);
        }
    }
}
