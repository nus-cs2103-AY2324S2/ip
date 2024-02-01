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
        Task task = new Task(str);
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

    public void mark(int index){
        Task curr = list.get(index);
        curr.markMark();
        System.out.println(line+"\nCongrats on completing the task!\n" + curr.toString()+"\n"+line);
    }

    public void unmark(int index){
        Task curr = list.get(index);
        curr.unmarkMark();
        System.out.println(line + "\nUnmarked the task, :(\n" + curr.toString()+"\n"+line);
    }

    public String noOfTask(){
        return "You have "+list.size()+" tasks in the list";
    }

    public void remove(int index){
        Task curr = list.get(index);
        list.remove(index);
        System.out.println(line +"byebye task! \n"+curr.toString()+"\n"+noOfTask()+"\n" +line);
    }

    public void input(){
        Scanner sc = new Scanner(System.in);
        while (true) {
            String str = sc.nextLine();
            String[] splitStr = str.split("\\s+",2);

            switch (splitStr[0].toLowerCase()) {
                case "bye":
                    bye();
                    return;
                case "list":
                    printList();
                    break;
                case "mark":
                    mark(Integer.parseInt(splitStr[1])-1);
                    break;
                case "unmark":
                    unmark(Integer.parseInt(splitStr[1])-1);
                    break;
                case "delete":
                    remove(Integer.parseInt(splitStr[1])-1);
                    break;
                case "todo":
                    if (splitStr.length == 2){
                        Task todo1 = new Todo(splitStr[1]);
                        list.add(todo1);
                        System.out.println(line+"\nyippie added new task\n"+todo1.toString()+"\n"+line);
                    }else{
                        System.out.println("no input on what todo :(\n"+line);
                    }
                    break;

                case "deadline":
                    if (splitStr.length == 2) {
                        String[] splitStrDeadline = splitStr[1].split("/", 2);
                        if (splitStrDeadline.length == 2) {
                            Task deadline1 = new Deadline(splitStrDeadline[0], splitStrDeadline[1]);
                            list.add(deadline1);
                            System.out.println(line + "\nyessir added new deadline\n" + deadline1.toString() + "\n" + line);
                        }else {
                            System.out.println("no deadline input :(\n" + line);
                        }
                    }else{
                        System.out.println("no name input :(\n"+line);
                    }
                    break;
                case "event":
                    if (splitStr.length == 2) {
                        String[] splitStrEvent = splitStr[1].split("/");
                        if (splitStrEvent.length == 3) {
                            Task event1 = new Event(splitStrEvent[0], splitStrEvent[1], splitStrEvent[2]);
                            list.add(event1);
                            System.out.println(line + "\nalrighty added new event\n" + event1.toString() + "\n" + line);
                        }else{
                            System.out.println("no event start and end date :(\n"+line);
                        }
                    }else{
                        System.out.println("no event timeline input :(\n"+line);

                    }
                    break;
                default:
                    System.out.println("huh? what did you say?");
                    break;
            }
        }
    }


    public static void main(String[] args) {
        Poe poe1 = new Poe();
        poe1.greetings();
        poe1.input();


    }
}
