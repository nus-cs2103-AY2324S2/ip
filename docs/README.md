# Brian User Guide

![Brian Ui](./Ui.png)

Brian is your friend who will keep track of your tasks for you.

# Features

## Adding Todos
Add a todo.

```
todo {todo}
```

Brian will send a response stating how many tasks there are left in the list

## Adding Deadlines
```
deadline {deadline} /by {due_date} 
```
`due_date` should be in the format `dd/MM/yyyy`
## Adding Events
```
deadline {event} /from {start_date} /to {end_date}
```
`start_date` and `end_date` should be in the format `dd/MM/yyyy`

## Listing Tasks

```
list 
```

## Deleting Tasks
```
delete {index}
```
The index can be obtained from the list command

## Mark/Unmark Tasks as Done/Undone
```
mark {index}
unmark {index}
```

## Find Tasks
Find a task whose body matches the search query.
```
find {search_query}
```
