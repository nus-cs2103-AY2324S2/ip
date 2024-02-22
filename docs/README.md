# Lunaris User Guide

![Ui](https://github.com/Derekljh/ip/assets/122243017/9f5596d8-e8a3-4be4-9130-cb9a8851f440)

## Product Description
Lunaris serves as your personal productivity companion, aiding you in maintaining organization with your tasks and deadlines. Crafted to be straightforward and user-friendly, it empowers you to better manage your time and responsibilities with ease.

## Adding Tasks
You can add three different types of tasks to Lunaris: ```todo```, ```deadline```, and ```event```. Each task type requires a distinct format for inputting the task details.

### Adding todo
To add todo tasks, use the following format:
```
todo "description"
```
Example: ```todo return book```

### Adding deadline
To add deadline tasks, use the following format:
```
deadline "description" /by "YYYY-MM-dd HHmm
```
Example: ```deadline finish homework /by 2023-02-23 2359```

### Adding event
To add event tasks, use the following format:
```
event "description" /from "YYYY-MM-dd HHmm" /to "YYYY-MM-dd HHmm"
```
Example: ```event midterms /from 2023-03-15 1000 /to 2023-03-15 1200```

## Listing tasks
To list all the tasks, use the following command:
```
list
```

## Mark tasks as done
To mark tasks as done, use the following format:
```
mark "task number"
```
Example: ```mark 2```

## Unmark tasks as done
To unmark tasks as done, use the following format:
```
unmark "task number"
```
Example: ```unmark 2```

## Delete tasks
To delete tasks, use the following format:
```
delete "task number"
```
Example: ```delete 2```

## Find tasks
To find tasks, use the following format:
```
find "keyword"
```
Example: ```find homework```

## Tag tasks
To tag tasks with a name, use the following format:
```
tag "task number" "tag name"
```
Example ```tag 2 priority```

### Exit the app
To exit the app, use the following command:
```
bye
```
