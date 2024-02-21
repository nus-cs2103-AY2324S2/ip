# Cookie User Guide 

## Add todo, deadline and event tasks (optional tagging)
Easily keep track of your Todos, Deadlines and Events!

Here is how to create your tasks (tagging is optional): 
1. Todo: `todo <todoName> #<tagName>`
```
todo 2103 quiz #quiz
```
2. Deadline: `deadline <deadlineName> /by <YYYY-MM-DD>`
```
deadline complete PS2 /by 2023-03-03 
```
3. Event: `event <eventName> /from <YYYY-MM-DD> <time> /to <YYYY-MM-DD> <time>`
```
event movie /from 2023-03-03 6pm /to 2023-03-03 8pm
```
## View complete task list

Easily view your complete task list with the list command.
```
list 
```

## Delete tasks

Easily delete tasks from your task list once completed.

`delete <taskIndex>`
```
delete 1 
```

## Mark tasks as complete

Easily mark tasks once you have completed them.

`mark <taskIndex>` 
```
mark 1 
```

## Search by keyword
See a complete list of tasks containing a specific keyword.

`find <keyword>`
```
find tutorial 
```

## Display by tag
Display tasks of a particular tag.

`display tagged #<tagName>`
```
display tagged #quiz 
```
