# Skibidi User Guide

![UI Image](https://github.com/LimZiJia/ip/blob/master/docs/UI.png?raw=true)

Skibidi is a Command-Line task manager. Use it to keep track of todo, deadlines and event tasks.

To use Skibidi chatbot, download `skibidi-v0.2.jar` from our lastest github
[release](https://github.com/LimZiJia/ip/releases),
and double click it or type the following line in terminal at the directory containing
`skibidi-v0.2.jar` to start the app:
```
java -jar skibidi-v0.2.jar
```


# Commands

## Adding todos

Format: `todo <task_name>`

e.g: `todo 2106_lab_2`

```
    Got it. I've added this task:

       [T][ ] 2106_lab_2
     Now you have 1 tasks in the list.
```

## Adding deadlines

Format: `deadline <task_name> /by <date>`
with date in the form of `YYYY-MM-DD`

e.g: `deadline CS2102_assignment_1 /by 2024-03-07`

```
    Got it. I've added this task:

       [D][ ] CS2102_assignment_1 (by: Mar 07 2024)
     Now you have 2 tasks in the list.
```

## Adding events

Format: `event <task_name> /from <date> /to <date>`
with date in the form of `YYYY-MM-DD`

e.g: `event mid_terms /from 2024-03-01 /to 2024-03-14`

```
    Got it. I've added this task:

       [E][ ] mid_terms (from: Mar 01 2024 to: Mar 14 2024)
     Now you have 3 tasks in the list.
```

## Listing tasks
Format: `list`

```
    Here are the tasks in your list:
     1.[T][ ] 2106_lab_2
     2.[D][ ] CS2102_assignment_1 (by: Mar 07 2024)
     3.[E][ ] mid_terms (from: Mar 01 2024 to: Mar 14 2024)
```

## Deleting tasks

Format: `delete <task_indexes>`

e.g: `delete 2`, `delete 2 1 3`

```
     Noted. I've removed this task:
       [E][ ] mid_terms (from: Mar 01 2024 to: Mar 14 2024)
     Now you have 2 tasks in the list.
     
     Noted. I've removed this task:
       [D][ ] CS2102_assignment_1 (by: Mar 07 2024)
     Now you have 1 tasks in the list.
     
     Noted. I've removed this task:
       [T][ ] 2106_lab_2
     Now you have 0 tasks in the list.
```

## Marking tasks as done

Format: `mark <task_indexes>`

e.g: `mark 2`,`mark 2 1 3`

```
     Nice! I've marked this task as done:
       [D][ ] CS2102_assignment_1 (by: Mar 07 2024)
       
     Nice! I've marked this task as done:
       [T][ ] 2106_lab_2
     
     Nice! I've marked this task as done:
       [E][ ] mid_terms (from: Mar 01 2024 to: Mar 14 2024)
```

## Unmarking tasks as not done

Format: `unmark <task_indexes>`

e.g: `unmark 2`, `unmark 2 1 3`

```
     OK, I've marked this task as not done yet:
       [D][ ] CS2102_assignment_1 (by: Mar 07 2024)
       
     OK, I've marked this task as not done yet:
       [T][ ] 2106_lab_2
     
     OK, I've marked this task as not done yet:
       [E][ ] mid_terms (from: Mar 01 2024 to: Mar 14 2024)
```

## Searching tasks

Format: `find <pattern>`

e.g: `find assignment`

```
     Here are the matching tasks in your list:
       1. [D][ ] CS2102_assignment_1 (by: Mar 07 2024)
```
