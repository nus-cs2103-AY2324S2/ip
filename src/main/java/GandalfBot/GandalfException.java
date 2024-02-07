package GandalfBot;

import java.util.ArrayList;

public class GandalfException extends Exception{
    public GandalfException(String message){
        super(message);
    }
}

/**
public static void checkCommand(String[] taskInfo, ArrayList<GandalfBot.Task> data) throws GandalfBot.GandalfException{
    if(!taskInfo[0].equals("todo") && !taskInfo[0].equals("deadline") && !taskInfo[0].equals("event") && !taskInfo[0].equals("delete") && !taskInfo[0].equals("mark")&& !taskInfo[0].equals("unmark")){
        throw new GandalfBot.GandalfException("Please forgive me for I do not understand. They are spoken in a tongue lost in time.");
    }
    else if(taskInfo[0].equals("mark") || taskInfo[0].equals("unmark") || taskInfo[0].equals("delete")){
        try{
            data.get(Integer.parseInt(taskInfo[1]) - 1);
        }
        catch (IndexOutOfBoundsException e){
            throw new GandalfBot.GandalfException("There are no such tasks my old friend");
        }
    }
    else if(taskInfo.length == 1){
        throw new GandalfBot.GandalfException("I cannot add " + taskInfo[0] + " without a description");
    }
}*/