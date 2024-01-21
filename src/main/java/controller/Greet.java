package controller;

import view.GreetView;

public class Greet {
    private final GreetView greetView;
    
    public Greet() {
        this.greetView = new GreetView();
    }
    
    public void execute() {
        greetView.display();
    }
}
