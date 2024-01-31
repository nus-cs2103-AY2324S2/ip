package Duke.task;

public class Todos extends task {
    public Todos(String msg){
        super(msg);
    }

    @Override
    public String toString(){
        if (access_state()){
            return "[T][X] "+ access_message();
        }
        else{
            return "[T][ ] "+ access_message();
        }
    }
}
