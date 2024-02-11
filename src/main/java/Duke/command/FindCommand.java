package Duke.command;
import java.util.ArrayList;
import Duke.Storage;
import Duke.TaskList;
import Duke.Ui;
import Duke.task.task;

public class FindCommand extends Command{
    private String stringToFind;
    public FindCommand(String str){
        stringToFind = str;
    }
    @Override
    public void execute(TaskList tsk, Ui ui, Storage storage) {
       String stringToPrint = "Here are the matching tasks in your list:\n";
       int counter = 0;
       for(int i = 0; i<tsk.accessList().size(); i++){
           if(tsk.accessList().get(i).access_message().contains(stringToFind)){
               counter ++;
               stringToPrint = stringToPrint + counter + ". "+ tsk.accessList().get(i)+"\n";
           }
       }
       stringToPrint = stringToPrint.substring(0, stringToPrint.length()-1);
       Ui.print_message(stringToPrint);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
