# Hanxiao User Guide

<img src="Ui.png" alt="HanxiaoUi" style="zoom:50%;" />

## Setup 
1. Ensure you have **Java 11** installed in your Computer.
2. Move to the *folder* where you put the `jar` file.
3. Use `java -jar hanxiao.jar` to pen the jar file.

## Changing data source

**Usage:** cd `filePath`

**Example:** cd data/demo.txt

If the file stated does not exist, a new file will be created for you with the same name.
```
Sir a bad news, file not found. But the good news is we create one for you!
Please feel free to add task now
```
If the file exist, you will recieve a message:
```
Dear sir, your data source has been changed to 
data/demo.txt
.
```
## Adding todos

**Usage:** todo `description`

**Example:** todo write user guide

```
Got it. I've added this task.
    [T][ ][] write user guide
Now you have 1 tasks in the list.
```
## `time` format

#### Could be in 3 types
Should capitalize the **first letter**
1. yyyy-mm-dd  e.g. 2024-02-16
2. Today/Tomorrow
3. Monday/Mon ~ Sunday/Sun

## Adding deadlines

**Usage:** deadline `description` /by `time`

**Example:** deadline finalize ip /by Friday

```
Got it. I've added this task.
    [D][ ][] finalize ip (by Feb 23 2024)
Now you have 2 tasks in the list.
```

## Adding events

**Usage:** event `description` /from `time` /to `time`

**Example:** event weekend /from Sat /to Sun

```
Got it. I've added this task.
    [E][ ][] weekend (from: Feb 17 2024 to: Feb 18 2024)
Now you have 3 tasks in the list.
```

## Listing all tasks

**Usage:** `list`/`ls`

**Example:** `ls`

```
Here are the tasks in your list:
 1.[T][ ][] write user guide
 2.[D][ ][] finalize ip (by Feb 23 2024)
 3.[E][ ][] weekend (from: Feb 17 2024 to: Feb 18 2024)
```

## Delete a task

**Usage:** delete `index`

**Example:** delete 1

```
Noted. I've removed this task:
 [T][ ][] write user guide
Now you have 2 tasks in the list.
```

## Mark a task as finished

**Usage:** mark `index`

**Example:** mark 1

```
Nice! I've marked this task as done:
[D][X][] finalize ip (by Feb 23 2024)
```

## Unmark a finished task as not finished

**Usage:** unmark `index`

**Example:** unmark 1

```
OK, I've marked this task as not done yet:
[D][ ][] finalize ip (by Feb 23 2024)
```

## Find tasks with key word

**Usage:** find `keyWord`

**Example:** find week

```
  Here are the matching tasks in your list:
1.[E][ ][] weekend (from: Feb 17 2024 to: Feb 18 2024)
```

## Sort the task list by time

**Usage:** sort

**Example:** sort

```
Your tasks has been sorted:
 1.[E][ ][] weekend (from: Feb 17 2024 to: Feb 18 2024)
 2.[D][ ][] finalize ip (by Feb 23 2024)
```

## Tag a task

**Usage:** tag `index` `tagName`

**Example:** tag 1 upcoming

```
Nice! I've tag this task:
 [E][ ][#upcoming] weekend (from: Feb 17 2024 to: Feb 18 2024)
```

## Remove a tag from a task

**Usage:** untag `index` `tagName`

**Example:** untag 1 upcoming

```
Nice! I've remove the tag:
 [E][ ][] weekend (from: Feb 17 2024 to: Feb 18 2024)
```

## Update a task

**Usage:** update `index` `/field` `value`

**Example:** update 1 /from Today

```
Nice! I've update the tag:
  [E][ ][] weekend (from: Feb 16 2024 to: Feb 18 2024)
```


## Exit the program

**Usage:** `quit`/`exit`/`bye`



