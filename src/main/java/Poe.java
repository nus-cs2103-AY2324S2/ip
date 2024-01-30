import java.util.ArrayList;
import java.util.Scanner;

public class Poe {
    String line = "=================================================";
    ArrayList<Task> list = new ArrayList<Task>(100);
    public void greetings(){
        String hello =  line + "\nYo! I'm Poe\nWhat can I do for you bro\n" + line;
        System.out.println(hello);
    }

    public void bye(){
        String byebye = line + "\nBye come again\n" + line;
        System.out.println(byebye);
    }

    public void addList(String str){
        Task task = new Task(str,false);
        list.add(task);
        System.out.println(line+"\nadded : "+str+"\n"+line);
    }
    public void printList(){
        System.out.println(line);
        for (int i = 0; i < list.size(); i++){
            System.out.printf("%d. %s\n",i+1,list.get(i).toString());
        }
        System.out.println(line);

    }

    public void update(String str){
        String[] splitStr =str.split("\\s+");
        int index = Integer.parseInt(splitStr[1])-1;
        boolean bool = !splitStr[0].equals("unmark");
        Task curr = list.get(index);
        if(bool){
            curr.markMark();
            System.out.println(line+"\nCongrats on completing the task!\n" + curr.toString()+"\n"+line);
        }else{
            curr.unmarkMark();
            System.out.println(line + "\nUnmarked the task, :(\n" + curr.toString()+"\n"+line);
        }
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
            }else if(str.toLowerCase().contains("mark")){
                update(str);
            }else{
                addList(str);
            }
        }
    }


    public static void main(String[] args) {
        Poe poe1 = new Poe();
        poe1.greetings();
        poe1.input();

    }
}
