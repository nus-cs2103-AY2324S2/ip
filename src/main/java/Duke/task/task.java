package Duke.task;

import java.io.Serializable;

public class task implements Serializable {
    private String message;
    private boolean state = false;
    public task(String msg){
        this.message = msg;
        if (msg.length()==0){
            throw new IllegalArgumentException("input can't be empty");
        }
    }
    public void mark(){
        state = true;
    }

    public void unmark(){
        state = false;
    }
    protected boolean access_state(){
        return state;
    }
    public String access_message(){
        return message;
    }
    @Override
    public String toString(){
        if (state == true){
            return "[X] "+ message;
        }
        else{
            return "[ ] "+ message;
        }
    }
}
