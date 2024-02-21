# Cookie User Guide 

## Add todo, deadline and event tasks (optional tagging)
Easily keep track of your Todos, Deadlines and Events.

Here is how to create your tasks (tagging is optional): 
1. Todo: 
```
format: todo <todoName> #<tagName>
example: todo 2103 quiz #quiz
```
2. Deadline: 
```
format: deadline <deadlineName> /by <YYYY-MM-DD>
example: deadline complete PS2 /by 2023-03-03 
```
3. Event:
```
format: event <eventName> /from <YYYY-MM-DD> <time> /to <YYYY-MM-DD> <time>
example: event movie /from 2023-03-03 6pm /to 2023-03-03 8pm
```

## View complete task list

View your complete task list with the list command.
```
list 
```

## Delete tasks

Delete tasks from your task list once completed.
```
format: delete <taskIndex>
example: delete 1 
```

## Mark tasks as complete

Mark tasks once you have completed them.
```
format: mark <taskIndex>
example: mark 1 
```

## Search by keyword
See a complete list of tasks containing a specific keyword.
```
format: find <keyword>
example: find tutorial 
```

## Display by tag
Display tasks of a particular tag.
```
format: display tagged #<tagName>
example: display tagged #quiz 
```
