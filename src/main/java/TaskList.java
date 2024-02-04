import java.io.IOException;
import java.util.*;
import java.io.File;
import java.io.FileWriter;

public class TaskList {

    private static String taskAdded = "(˃.˂) Got it. I've added this task:";
    private ArrayList<Task> tasks;

    public TaskList(){

        tasks = new ArrayList<Task>();
    }

    public TaskList(String path){ //read from file

        tasks = new ArrayList<Task>();

        File file;
        Scanner scanner;

        try{

            file = new File(path);

            file.createNewFile(); //if file exists, does nothing
            scanner = new Scanner(file);

        } catch(IOException e) {

            System.out.println("An exception was thrown!");
            return;
        }

        while(scanner.hasNextLine()){

            String line = scanner.nextLine();

            if(line.length() < 3){

                System.out.println("File format error! May have been corrupted");
                return;
            }

            String taskName;
            boolean isDone;

            if(line.charAt(1) == '1'){

                isDone = true;
            }
            else{

                isDone = false;
            }

            switch(line.charAt(0)){

                case 'T':

                    taskName = line.substring(2);
                    addTodo(taskName, isDone);

                    break;

                case 'D':

                    int index = line.indexOf("/");
                    taskName = line.substring(2, index);
                    String deadline = line.substring(index+1);

                    addDeadline(taskName, deadline, isDone);

                    break;

                case 'E':

                    int firstIndex = line.indexOf("/");
                    taskName = line.substring(2, firstIndex);
                    String interval = line.substring(firstIndex+1);
                    int secondIndex = interval.indexOf("/");
                    String startTime = interval.substring(0, secondIndex);
                    String endTime = interval.substring(secondIndex+1);

                    addEvent(taskName, startTime, endTime, isDone);

                    break;

                default:

                    System.out.println("File corrupted");

            }
        }

    }

    public void save(String path) {

        try{

            FileWriter writer = new FileWriter(path);

            for(int i=0;i<tasks.size();++i){

                writer.write(tasks.get(i).header()+tasks.get(i).getName());

                writer.write(tasks.get(i).additionalInfo());

                writer.write("\n");
            }

            writer.close();

        } catch (IOException e){

            System.out.println("File format error! May have been corrupted");
            return;
        }
    }

    private void parse(){


    }

    public void printListSize(){

        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    public void addDeadline(String name, String deadline, boolean isDone){

        Task task = new Deadline(name, deadline, isDone);
        tasks.add(task);
        System.out.println(taskAdded);
        System.out.println(task);
        printListSize();
    }

    public void addTodo(String name, boolean isDone){

        Task task = new Todo(name, isDone);
        tasks.add(task);
        System.out.println(taskAdded);
        System.out.println(task);
        printListSize();
    }

    public void addEvent(String name, String start, String end, boolean isDone){

        Task task = new Event(name, start, end, isDone);
        tasks.add(task);
        System.out.println(taskAdded);
        System.out.println(task);
        printListSize();
    }

    public void deleteTask(Integer index){

        Task task = tasks.get(index-1);
        tasks.remove(index-1);
        System.out.println("(•̀-•́) Noted, I've removed this task:");
        System.out.println(task);
        printListSize();
    }

    public void markTask(Integer index){

        Task task = tasks.get(index-1);
        task.mark();
        System.out.println("(•̀ᗜ•́) Nice, I've marked this task as done:");
        System.out.println(task);
    }

    public void unmarkTask(Integer index){

        Task task = tasks.get(index-1);
        task.unmark();
        System.out.println("(ᗒᗣᗕ) OK, I've marked this task as not done yet:");
        System.out.println(task);
    }

    @Override
    public String toString(){

        String string = "";

        for(Integer i=1;i<=tasks.size();++i){

            string += i + ". " + tasks.get(i-1);

            if(i != tasks.size()){
                string += "\n";
            }
        }

        return string;
    }
}
