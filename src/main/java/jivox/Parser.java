package jivox;


import jivox.exception.JivoxException;

public class Parser {
    public COMMANDS parseCommand(String rawInput) throws JivoxException {
        String[] input = rawInput.split(" ",2);
        try{
            return COMMANDS.valueOf(input[0].toUpperCase());
        } catch (IllegalArgumentException e){
            throw new JivoxException("Opps! I can't understand your Input, Please try again");
        }
    }

    public String[] parseInput(String rawInput){
        return rawInput.split(" ",2);
    }

    public String[] split(String input, String reg){
        return input.split(reg);
    }
    public String[] split(String input, String reg,int limit){
        return input.split(reg,limit);
    }

}
