# Gops Userguide
![](Ui.png)

## Setup
1. Configure Java 11 SE
2. Download latest version of [JAR file](https://github.com/bgopi23/ip/releases)
3. Run JAR file

## Features
### Todo / Deadline / Event

Adds a task to tasklist

`Format`

```
todo [description]
deadline [description] /by [YYYY-MM-DD]
event [description]] /from [start-date] /to [end-date]
```

### List

Prints out list of tasks in the tasklist

`Format`

```
list
```
### Mark / Unmark

Marks or unmarks a specified task in the task list

`Format`

```
mark [index]
unmark [index]
```
### Delete

Deletes a specified task from the task list

`Format`

```
delete [index]
```
### Clear
Clears tasklist

`Format`

```
clear
```