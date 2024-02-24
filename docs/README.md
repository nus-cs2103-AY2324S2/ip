# BotBot User Guide

![Screenshot](/docs/Ui.png)

A Bot to manage events

## Features

### Adding an event

Add various types of events to the list

ToDo: todo <name> | e.g. todo eat
Deadline: deadline <name> /by yyyy-MM-dd HH:mm | e.g. deadline eat /by 1999-01-01 22:00
Event: event <name> /from yyyy-MM-dd HH:mm /to yyyy-MM-dd HH:mm | e.g. event helloo /from 1999-01-01 10:00 /to 1999-01-01 22:00

### Deleting an event

Delete the nth event from the list

Delet: delete <int> | delete 1

### Listing all events 

Lists all current events in the list

List: list | e.g. list

### Customising events

Various features to note down further details of events

Marking as done: mark <int> |  e.g. mark 1
Unmarking: unmark <int> | e.g. unmark 2
Setting priority of event: togglePrio <int> | e.g. togglePrio 3

### Deleting events

remove an event from the list

Delete: delete <int> | e.g. delete 2

### Finding events

Finding an event with certain names

Find: find <name> | e.g. find Concert

### Closing the chatbot

Exit the chatbot once done

Exit: bye | e.g. bye



