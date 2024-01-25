public class Task {
    String nameOfTask;
    boolean status;

    public Task(String nameOfTask){
        this.nameOfTask = nameOfTask;
        this.status = false;
    }
    public void markStatus(boolean status){
        this.status = status;
    }

    public boolean getStatus(){
        return this.status;
    }

    @Override
    public String toString(){
        if(status){
            return "[X] " + nameOfTask;
        }
        else{
            return "[ ] " + nameOfTask;
        }
    }
}
