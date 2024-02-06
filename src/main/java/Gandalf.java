import java.util.Scanner;

public class Gandalf {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Gandalf(String filepath){
        ui = new Ui();
        storage = new Storage(filepath);
        try {
            tasks = new TaskList(storage.load());
        } catch (GandalfException e) {
            //file does not exist, create new list
            tasks = new TaskList();
        }
    }

    public void run(){
        ui.welcome();
        Scanner scanner = new Scanner(System.in);
        while(true){
            String input = scanner.nextLine();
            if(input.length() == 0){ //ignore accidental new lines from user
                continue;
            }
            Parser parser = new Parser(input);
            StringBuilder[] parsedInput = parser.interpret(); //parsedInput = {taskType, taskName, date1, date2}
            if(parsedInput[0].toString().trim().equals("bye")){
                scanner.close();
                ui.bye();
                break;
            }
            else if(parsedInput[0].toString().trim().equals("list")){
                for(int i = 0; i < tasks.getList().size(); i++){
                    Task action = tasks.getList().get(i);
                    System.out.println((i + 1) + ". " + action);
                }
                System.out.println("Total number of tasks so far: " + (tasks.getList().size()));
            }
            else if(parsedInput[0].toString().trim().equals("delete")){
                tasks.delete(parsedInput[1].toString().trim());
                storage.store(tasks.getList());
            }
            else if(parsedInput[0].toString().trim().equals("mark")){
                int taskNumber = Integer.parseInt(parsedInput[1].toString());
                Task correspondingTask = tasks.getList().get(taskNumber - 1);
                correspondingTask.markStatus(true);
                ui.marked();
                System.out.println(correspondingTask);
                storage.store(tasks.getList());
            }
            else if(parsedInput[0].toString().trim().equals("unmark")){
                int taskNumber = Integer.parseInt(parsedInput[1].toString());
                Task correspondingTask = tasks.getList().get(taskNumber - 1);
                correspondingTask.markStatus(false);
                ui.unmarked();
                System.out.println(correspondingTask);
                storage.store(tasks.getList());
            }
            else{
                System.out.println("OVER HERREEE");
                tasks.add(parsedInput[0].toString().trim(), parsedInput[1].toString().trim(), parsedInput[2].toString().trim(), parsedInput[3].toString().trim());
                storage.store(tasks.getList());
                System.out.println("Total number of tasks so far: " + (tasks.getList().size()));
            }
        }
    }

    public static void main(String[] args) {
        new Gandalf("docs/gandalfData.txt").run();
    }
}
