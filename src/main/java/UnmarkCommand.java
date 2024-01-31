import java.io.IOException;

public class UnmarkCommand extends Command{
    private String com;
    public UnmarkCommand(String i){
        super(0);
        com = i;

    }

    @Override
    public void execute(TaskList tl, Ui ui, Storage st) {// method to mark task (mark command)
        int noArr;
        String[] inputs = com.split(" ");
        try {
            if(!(inputs.length == 2)){
                throw new CommandFormatException();
            }
            noArr = Integer.parseInt(inputs[1])-1;
            String out = tl.unMark(noArr);
            ui.showMessage(out);
            st.write(tl.getList());
        } catch (IndexOutOfBoundsException e){ //when the given number is out of bounds (exception handling)
            ui.showMessage("No task number " + inputs[1]);
        } catch (NumberFormatException e){ //when the given number is not a number (exception handling)
            ui.showMessage("The task number given is not a number");
        } catch (CommandFormatException e){
            ui.showMessage("The command format for unmark is unmark number (e.g.: unmark 1)");
        } catch (IOException e){
            ui.showMessage("Save failed");
        }
    }
}

