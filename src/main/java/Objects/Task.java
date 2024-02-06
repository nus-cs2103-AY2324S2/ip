package Objects;

public class Task {
    private String name;
    private boolean mark = false;
    public Task(String name,boolean mark){
        this.name = name;
        this.mark = mark;
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

    public String toStringFile(){
        if  (getMark()){
            return "1|" + getName();
        }else{
            return "0|" + getName();
        }
    }
}
