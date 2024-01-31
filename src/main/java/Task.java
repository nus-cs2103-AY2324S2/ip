public class Task {
    private String item;
    private boolean mark;
    public Task(String item){
        super();
        this.mark = false;
        this.item = item;
    }
    public void mark(){
        this.mark = true;
    }
    public void unmark(){
        this.mark = false;
    }

    public boolean getMark(){
        return mark;
    }

    public String getItem(){
        return item;
    }
    @Override
    public String toString(){
        String X = mark ? "X" : " ";
        return ("[" + X + "] " + item);
    }
}
