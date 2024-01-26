import java.util.ArrayList;
import java.util.Scanner;

public class Poe {
    String line = "=================================================";
    ArrayList<String> list = new ArrayList<String>(100);
    public void greetings(){
        String hello =  line + "\nYo! I'm Poe\nWhat can I do for you bro\n" + line;
        System.out.println(hello);
    }

    public void bye(){
        String byebye = line + "\nBye come again\n" + line;
        System.out.println(byebye);
    }

    public void addList(String str){
        list.add(str);
    }
    public void printList(){
        System.out.println(line);
        for (int i = 0; i < list.size(); i++){
            System.out.printf("%d. %s\n",i+1,list.get(i));
        }
        System.out.println(line);

    }

    public void input(){
        Scanner sc = new Scanner(System.in);
        while(true) {
            String str = sc.nextLine();
            if (str.toLowerCase().equals("bye")) {
                bye();
                break;
            }else if(str.toLowerCase().equals("list")){
                printList();
            }else{
                addList(str);
                System.out.println(line+"\nadded : "+str+"\n"+line);
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
