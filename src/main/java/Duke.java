import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = " _______  __                       __ \n"
                + "|     __||__|.-----..-----..---.-.|  |\n"
                + "|__     ||  ||  _  ||     ||  _  ||  |\n"
                + "|_______||__||___  ||__|__||___._||__|\n"
                + "             |_____|                  \n";
        String div = "\n" + "~~**~~";
        System.out.println("Hello! My name is\n" + logo);
        System.out.println("How can I help?");
        System.out.println(div);

        Scanner scanner = new Scanner(System.in);
        while(true) {
            String userInput = scanner.nextLine();
            if (userInput.equals("bye") || userInput.equals("Bye")) {
                System.out.println(div);
                break;
            } else {
                System.out.println(div);
                System.out.println(userInput);
                System.out.println(div);
            }
        }

        System.out.println("Bye! Hope you come back soon :D");
        System.out.println(div);

    }
}
