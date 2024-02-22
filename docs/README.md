# Dobby User Guide

![/Users/leedoye/ip/docs/Ui.png]

Dobby will help your task managing skill

## Adding deadlines

1. list the tasks: 'List'
- returns a list of tasks in the storage

- input example: 

```
list
```

2. mark a task as done: 'mark'
- mark nth task as done (make sure inputting the correct index number)

- input example: 
```
mark 2
```

3. unmark a task as done: 'unmark'
- unmark nth task as done (make sure inputting the correct index number)

- input example: 
```
unmark 2
```

4. adding a todo task: 'todo'
- adding a todo task without any due date

- input example: 
```
todo reading
```
5. adding a task with a deadline: 'deadline'
- adding a task with a deadline in format of '/by dd/mm/yyyy 24:00'

- input example: 
```
deadline return book /by 2/12/2019 1800
```

5. adding a task with a starting date and ending date: 'event'
- adding a event in format of starting date as '/from dd/mm/yyyy 24:00' and ending date as '/to dd/mm/yyyy 24:00'

- input example: 
```
event festival /from 2/12/2019 1800 /to 2/12/2019 2000
```

6. finding a task: 'find'
- finding a task in the list with a keywork

- input example: 
```
find reading
```

7. deleting a task: 'delete'
- deleting a task in the list using an index

- input example: 
```
delete 3
```

- adding same task with same task name will not be allowed. 
- write in the correct format for adding tasks