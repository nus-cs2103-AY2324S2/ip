package Duke;

import java.util.ArrayList;
import Duke.command.*;
import Duke.task.*;
public class TaskList{
    private ArrayList<task> strlist = new ArrayList<>();
    private int number_task = 0;
    TaskList(ArrayList<task> tasklist){
        this.strlist = tasklist;
        number_task = this.strlist.size();
    }
    public int accessNumberTask(){
        return number_task;
    }
    public ArrayList<task> accessList(){
        return strlist;
    }
    public void insert(task tsk){
        strlist.add(tsk);
        number_task++;
    }
    public String delete(int index){
        String temp = strlist.get(index-1).toString();
        strlist.remove(index-1);
        number_task--;
        return temp;
    }
    public String mark(int index){
        strlist.get(index-1).mark();
        return strlist.get(index-1).toString();
    }
    public String unmark(int index){
        strlist.get(index-1).unmark();
        return strlist.get(index-1).toString();
    }
    @Override
    public String toString(){
        String str = "";
        for (int i=0; i<strlist.size();i++){
            if (i == strlist.size()-1){
                str = str + (i+1)+". "+ strlist.get(i);
                break;
            }
            str = str + (i+1)+". "+ strlist.get(i)+ "\n ";
        }
        return str;
    }
}
