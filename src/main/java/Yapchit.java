import java.util.ArrayList;
import java.util.Scanner;

public class Yapchit {

    private class Task{
        private String name;
        private boolean tag;

        public Task(String name){
            this.name = name;
            this.tag = false;
        }

        public void updateTag(boolean val){
            this.tag = val;
        }

        public String getName(){
            return this.name;
        }

        public boolean getTag(){
            return this.tag;
        }
    }
    ArrayList<Task> list = new ArrayList<>();
    public static void main(String[] args) {

        Yapchit bot = new Yapchit();

        bot.intro();

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();



        while(!(input.equals("bye"))){
            bot.parseInput(input);
            input = scanner.nextLine();
        }

        bot.outro();
    }

    private void parseInput(String input){

        String[] parts = input.split(" ", 2);

        if(parts.length == 1){
            if(input.equals("list")){
                this.printList();;
            } else {
                this.echo(input);
            }
        } else if(parts.length == 2){
            int idx = -1;
            try {
                idx = Integer.parseInt(parts[1]) - 1;
            } catch (NumberFormatException e){
                System.out.println("Please enter a number after 'mark'/'unmark'");
            }

            if(parts[0].equals("mark") && idx != -1){
                this.mark(idx, true);
            } else if (parts[0].equals("unmark") && idx != -1){
                this.mark(idx, false);
            }
        } else {
            System.out.println("Invalid input, please try again");
        }

    }

    private void intro(){
        String intro = "\t--------------------------------------------------\n"
                + "\tHello! I'm Yapchit\n"
                + "\tWhat can I do for you?\n"
                + "\t--------------------------------------------------\n";
        System.out.println(intro);
    }

    private void printList(){
        System.out.println(	"\t"+"--------------------------------------------------");
        System.out.println(	"\t"+"Here are the tasks in your list:");
        for(int i = 0; i < this.list.size(); i++){

            int idx = i + 1;
            Task item = this.list.get(i);
            String tag = item.tag == true ? "[X]" : "[ ]";
            System.out.println("\t" + idx +  ". " + tag + " " + item.getName());
        }
        System.out.println(	"\t"+"--------------------------------------------------");
    }

    private void mark(int idx, boolean val){
        list.get(idx).updateTag(val);
        System.out.println(	"\t"+"--------------------------------------------------");
        if(val){
            System.out.println(	"\t"+"Nice! I've marked this task as done:");
            System.out.println("\t[X]" + " " + list.get(idx).getName());
        } else {
            System.out.println(	"\t"+"OK, I've marked this task as not done yet:");
            System.out.println("\t[ ]" + " " + list.get(idx).getName());
        }
    }

    private void echo(String input){
        list.add(new Task(input));
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
