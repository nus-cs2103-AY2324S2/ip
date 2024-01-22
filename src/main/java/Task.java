public class Task {
    private String name;
    private boolean tag;

    public Task(String name){
        this.name = name;
        this.tag = false;
    }

    public void updateTag(boolean val){
        this.tag = val;
    }

    public String getName(){
        return this.name;
    }

    public boolean getTag(){
        return this.tag;
    }
}
