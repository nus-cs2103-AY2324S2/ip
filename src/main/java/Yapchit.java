import java.util.Scanner;

public class Yapchit {
    public static void main(String[] args) {
        String intro = "\t--------------------------------------------------\n"
                + "\tHello! I'm Yapchit\n"
                + "\tWhat can I do for you?\n"
                + "\t--------------------------------------------------\n";
        System.out.println(intro);
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        Yapchit bot = new Yapchit();

        while(!(input.equals("bye"))){
            bot.echo(input);
            input = scanner.nextLine();
        }

        String outro = "\t--------------------------------------------------\n"
                + "\tBye. Hope to see you again soon!\n"
                + "\t--------------------------------------------------\n";

        System.out.println(outro);
    }

    private void echo(String input){
        String output = "\t--------------------------------------------------\n"
                + "\t" + input + "\n"
                + "\t--------------------------------------------------\n";
        System.out.println(output);
    }
}
