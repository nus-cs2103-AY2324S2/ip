import java.util.ArrayList;
import java.util.Scanner;

public class Jivox {


    ArrayList<String> list = new ArrayList<>();
    public void greet(){
        addDivider();
        System.out.println(" Hello! I'm Jivox");
        System.out.println(" What can I do for you?");
        addDivider();
    }

    public void exit(){
        addDivider();
        System.out.println(" Bye. Hope to see you again soon!");
        addDivider();
    }

    public void add(String input){
        this.list.add(input);
        addDivider();
        System.out.println("added: " + input);
        addDivider();
    }

    public void showList(){
        addDivider();
        for(int i = 0; i < this.list.size(); i++){
            System.out.println((i+1) + ". " + this.list.get(i));
        }
        addDivider();
    }

    public void addDivider(){
        System.out.println("============================================================");
    }

    public void run(){
        boolean isRunning = true;
        Scanner sc = new Scanner(System.in);
        do {
            String input = sc.nextLine();
            if(input.equals("bye")){
                isRunning = false;
            } else if(input.equals("list")){
                this.showList();
            }
            else{
               this.add(input);
            }
        } while (isRunning);

    }

}
