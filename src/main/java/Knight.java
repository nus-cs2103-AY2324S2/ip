import java.util.Objects;
import java.util.Scanner;

public class Knight {
    public static void main(String[] args) {
        String logo = "    ┓┏┓  •  ┓  \n"
                + "    ┃┫ ┏┓┓┏┓┣┓╋\n"
                + "    ┛┗┛┛┗┗┗┫┛┗┗\n"
                + "           ┛\n";


        System.out.println("    Greetings, Your Excellency! Thy humble\n"
                + logo
                + "    doth stand before thee. How may I serve thee on this day?");

        Scanner scan = new Scanner( System.in );
        String command;

        while (true) {
            command = scan.nextLine();

            if (command.equals("bye")) break;

            System.out.println("    " + command);
        }

        System.out.println("    Farewell, Your Excellency! May we cross paths once more in the near future.");
    }
}
