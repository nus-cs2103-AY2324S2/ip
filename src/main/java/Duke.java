import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = "\t  ____   __  _  _  __    ___  ___  ____  ___  ____\n\t"
                + " (_  _) /__\\( \\/ )/__\\  / __)/ __)(_  _)/ __)(_  _)\n\t"
                + " .-_)( /(__)\\\\  //(__)\\ \\__ \\\\__ \\ _)(_ \\__ \\  )(\n\t"
                + "\\____)(__)(__)\\/(__)(__)(___/(___/(____)(___/ (__)\n";

        String line = "\t________________________________________________________________";

        Scanner read = new Scanner(System.in);
        System.out.println(line + "\n" + logo + "\n\t Hello! I'm JavAssist");
        System.out.println("\t What can I do for you?\n" + line);
        String input;
        while(true) {
            System.out.println();
            input = read.nextLine();
            System.out.println(line);
            if (!input.equals("bye")) {
                System.out.println("\t " + input + "\n" + line);
            } else {
                break;
            }
        }

        System.out.println("\t Bye. Hope to see you again soon!\n" + line);
    }
}
