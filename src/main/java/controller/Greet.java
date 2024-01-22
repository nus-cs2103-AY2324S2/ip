package controller;

import duke.Storage;
import view.GreetView;

public class Greet {
    private final GreetView greetView;
    
    public Greet() {
        this.greetView = new GreetView();
    }
    
    public void execute(Storage storage) {
        greetView.display();
    }
}
