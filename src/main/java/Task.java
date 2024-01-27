public class Task {

    private String name;
    private Boolean isDone;
    public Task(String name){

        this.name = name;
        this.isDone = false;
    }

    @Override
    public String toString(){

        return name;
    }
}
