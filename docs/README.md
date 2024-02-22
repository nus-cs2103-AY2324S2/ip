# Duchess User Guide

![Screenshot of Duchess Ui](Ui.png)

## Introduction

Welcome to Duchess, your personal task manager! 
Duchess helps you stay organised by managing your tasks efficiently, 
like the efficient and respected leader that she is.

You can do things like 
- Adding a todo
- Adding deadlines
- Adding events
- Mark as done
- Mark as not done
- Delete task
- Find task by keyword
- Check if there are duplicate tasks

## Adding todo

To add a todo, use the following syntax:


> todo {description}


Example: `todo Buy Flowers`

The system will confirm the addition of the todo task:
```
Understood. I've added this TODO task:
[T][] Buy Flowers
Now you have X tasks in the list.
```

## Adding deadlines

To add a deadline, use the following syntax:


> deadline {description} /by {DD-MM-YYYY} {HHMM}


Example: `deadline Complete Report /by 22-03-2024 1800`

The system will confirm the addition of the deadline task:
```
Understood. I've added this DEADLINE task:
[D][] Complete Report (by: Mar 22 2024 06:00 PM)
Now you have X tasks in the list.
```

## Adding events

To add an event, use the following syntax:


> event {description} /from {start} /to {end}


Example: `event Birthday Party /from Saturday 6PM /to 10PM`

The system will confirm the addition of the event task:
```
Understood. I've added this EVENT task:
[E][] Birthday Party (from: Saturday 6PM to: 10PM)
Now you have X tasks in the list.
```

## Mark as done 

To mark a task as done, use the following syntax:


> mark {index}


Example: `mark 1`

The system will confirm the marking of the task as done:
```
Perfect! I've marked this task as done:
[T][X] buy new computer
```

## Mark as not done

To mark a task as not done, use the following syntax:


> unmark {index}


Example: `unmark 1`

The system will confirm the marking of the task as not done:
```
Understood, I've marked this task as not done yet:
[T][] buy new computer
```

## Delete task

To delete a task, use the following syntax:


> delete {index}


Example: `delete 1`

The system will confirm the deletion of the task:
```
Understood. I've deleted this task:
[T][] buy new computer
Now you have X tasks in the list.
```

## Find task by keyword

To find a task using a keyword, use the following syntax:


> find {keyword}


Example: `find Buy`

The system will confirm if a matching task or tasks have been found:
```
Here are the matching tasks in your list:
2.[T][] Buy Flowers
```

Or if no tasks have been found:
```
No matching tasks found.
```

## Check if there are duplicate tasks

To check if there are duplicate tasks, use the following syntax:


> duplicates


Example: `duplicates`

The system will confirm if there are duplicates:
```
Here are the duplicate tasks in your list:
4.[T][] Buy Flowers
5.[T][] Buy Flowers
```

Or if no there are no duplicates:
```
No duplicate tasks found.
```
