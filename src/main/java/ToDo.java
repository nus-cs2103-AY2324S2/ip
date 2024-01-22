public class ToDo extends Task{
    public ToDo(String name){
        super(name);
    }

    @Override
    public String toString(){
        String tag = super.getTag() ? "[X]" : "[ ]";
        return "[T]" + tag + " " + super.getName();
    }
}
