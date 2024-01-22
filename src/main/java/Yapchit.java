import java.util.ArrayList;
import java.util.Scanner;

public class Yapchit {

    ArrayList<String> list = new ArrayList<>();
    public static void main(String[] args) {

        Yapchit bot = new Yapchit();

        bot.intro();

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        while(!(input.equals("bye"))){
            if(input.equals("list")){
                bot.printList();;
            } else {
                bot.echo(input);
            }
            input = scanner.nextLine();
        }

        bot.outro();
    }

    private void intro(){
        String intro = "\t--------------------------------------------------\n"
                + "\tHello! I'm Yapchit\n"
                + "\tWhat can I do for you?\n"
                + "\t--------------------------------------------------\n";
        System.out.println(intro);
    }

    private void printList(){
        System.out.println(	"\t"+"--------------------------------------------------\n");
        for(int i = 0; i < this.list.size(); i++){
            System.out.println("\t" + (i + 1) + ". " + this.list.get(i));
        }
        System.out.println(	"\t"+"--------------------------------------------------\n");
    }

    private void echo(String input){
        list.add(input);
        String output = "\t--------------------------------------------------\n"
                + "\t" + input + "\n"
                + "\t--------------------------------------------------\n";
        System.out.println(output);
    }

    private void outro(){
        String outro = "\t--------------------------------------------------\n"
                + "\tBye. Hope to see you again soon!\n"
                + "\t--------------------------------------------------\n";

        System.out.println(outro);
    }
}
