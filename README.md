# DukeYarr
```
Ahoy from
 _  _   __    ____  ____ 
( \/ ) /__\  (  _ \(  _ \
 \  / /(__)\  )   / )   /
 (__)(__)(__)(_)\_)(_)\_)
 ```
Have you ever felt:
- *Dangerously* forgetful?
- Bored of speaking plain English?
- Tired of minimalist app interfaces?

If you answered yes to any of the above, then DukeYarr is perfect for you! DukeYarr can be your swashbuckling assistant on the high seas of productivity.

## Getting Started with DukeYarr
To get acquainted with him:
1. download the most recent release from [here](https://github.com/H4mes/ip/releases/tag/v0.1);
2. run `java -jar duke.jar` from your terminal
3. add your tasks
4. get chummy with your new favourite scallywag :pirate_flag:

All this for the low, low price of **FREE**!

## Features
DukeYarr continues to grow more capable each day and is able to:
- [X] Handle To-dos
- [X] Handle Deadlines and Events 
- [ ] Implement a GUI interface (coming soon)

## Customisability
For enthusiastic programmers, DukeYarr is highly customisable with easily extended packages and classes. Here's a snippet of the `Task` class for example:
```java
public class Task {  
    protected String description;  
    protected boolean isDone;  
  
    public Task(String description) {  
        this.description = description;  
        this.isDone = false;  
    }  
  
    public String toString() {  
        return (isDone ? "[X] " + description : "[ ] " + description); //mark done task with X  
    }  
  
	public void markAsDone() {  
        isDone = true;  
    }
}
```
##
What are you waiting for? Download DukeYarr now and join our other happy users!
> The ~~One Piece~~ Productivity! The ~~One Piece~~ Productivity is real! – Edward Newgate
