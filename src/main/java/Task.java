public class Task {
    private String name;
    private boolean mark;
    public Task(String name){
        this.name = name;
        this.mark = false;
    }

    public String getName() {
        return name;
    }
    public boolean getMark(){
        return mark;
    }
    public void toggleMark(){
        this.mark = !this.mark;
    }

    public void markMark(){
        this.mark = true;
    }

    public void unmarkMark(){
        this.mark = false;
    }


    @Override
    public String toString(){
        if  (getMark()){
            return "[X] " + getName();
        }else{
            return "[ ] " + getName();
        }
    }
}
