import java.util.Scanner;

public class Poe {
    String line = "=================================================";
    public void greetings(){
        String hello =  line + "\nYo! I'm Poe\nWhat can I do for you bro\n" + line;
        System.out.println(hello);
    }

    public void bye(){
        String byebye = line + "\nBye come again\n" + line;
        System.out.println(byebye);
    }

    public void input(){
        Scanner sc = new Scanner(System.in);
        while(true){
            String str = sc.nextLine();
            if (str.toLowerCase().equals("bye")){
                bye();
                break;
            }else{
                System.out.println(line+"\n"+str+"\n"+line);
            }
        }
    }


    public static void main(String[] args) {
        Poe poe1 = new Poe();
        Scanner sc = new Scanner(System.in);
        poe1.greetings();
        poe1.input();

    }
}
