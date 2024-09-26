# The TalkingBox User Guide

The TalkingBox is a chatbot app that helps users keep track of their tasks and other notes. 

## Features

### Adding, removing and listing tasks

You can add 3 types of tasks to the TalkingBox: Todos, Deadlines and Events

Example: 

`todo read book`

`deadline return book /by 24/3/2024 1600`

The app will then add your tasks to your task list
```
Added new task: 
[T] [] read book
current number of tasks: 1

```

Unwanted tasks can be deleted using the `delete` command


You can also view the entire task list with the `list` command

```
Current list of tasks: 
1. [T] [ ] read book
```

### Completing tasks

You can mark a task number as done using the `mark` command

```
mark 1


Task marked as done. Good job!
1. [T] [x] read book
```

Similarly, you can mark a task as not done using the `unmark` command

### Finding tasks

You can search for any tasks containg a certain keyword or key phrase in its name

``` 
find book

Here are the matching tasks in your list:
1. [T] [x] read book
```

### Adding and viewing notes

Besides tasks, the program can also be used to store notes using the `write` command

```dtd
write today was a good day

New note added!
'today was a good day

```

the list of notes currently stored can be viewed using the `notes` command

```
notes

Current list of notes:
1. today was a good day
```

Like tasks, notes can also be removed using the `remove` command