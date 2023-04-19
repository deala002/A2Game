# Scope
This is a basic timer application designed for meetings.

It allows users to add subtasks, and then counts down, notifying users when time is up for one task and they should be moving onto the next item on the agenda.

The goal of this application is to keep your meeting (in person or online) on track.

# Overview
![view of meeting timer application](doc/manualPhotos/wholeTimer.png)
## Setup
1. Download the gitLab folder and unzpip all files.

![Download the folder](doc/manualPhotos/Setup1.png)

2. Right click the **timer.html** file and click *'open with'*.
3. Choose your preferred browser to run the timer in.

![Run in browser](doc/manualPhotos/Setup2.png)

## Usage

### Adding a task

Tasks are the building blocks of everything in this application. There are 2 ways to add a task:

**1. Press the *Add Task* button.**

This will add two input boxes to the screen next to each other, one titled **Task name**, which is where the name of the task should go, and the other with the number 0 inside, where the time allocated for the task (in minutes) should go.

Please note that you can not add a task while the timer is running. In order to add a task you must reset the timer, or wait for it to finish.

![Add task example](doc/manualPhotos/addTaskExample.png)

**2. Press the *Choose File* button.**

This will open your file explorer and enable you to open a **.csv** file to input your tasks. The .csv **must be in the format pictured below**, with one column for task names, and another for task times. The header row will not be included in the task import.

![CSV Example](doc/manualPhotos/CSVExample.png)

There is no limit (within reason) to the number of tasks that can be imported, as long as the file is a .csv and follows this format.

Once you open the .csv file, the program will automatically place the information from the .csv in the same format as above, and you are free to edit any names or times however you want.

*Please note that importing a new set of tasks will automatically reset the timer.*

### Running the timer
Once you have added tasks to the timer, press the *Start* button to run the timer. The timer will automatically determine when a subtask is finished and move on to the next one.

When the timer starts, the *Start* button will turn into a *Pause* button, and the *Add Task* and *Remove task* buttons will disappear. These buttons will reappear when the timer finishes, or is reset.

![start the timer](doc/manualPhotos/startTimer.png)

### Pausing the timer

Pressing the *pause* button will stop the timer at it's current point in time.

Once the timer is paused, the *Pause* button will turn back into a *Start* button, which can be pressed to continue counting down from the point the timer stopped at.

![Pause the timer](doc/manualPhotos/pauseTimer.png)

### Resetting the timer.

Pressing the *reset* button will reset the timer back to the time it originally started at.

*Note: If you add time to subtasks during the countdown, the reset **will** include these added times when it resets*.

![Reset the timer](doc/manualPhotos/resetTimer.png) 

### Skipping a task

Pressing the *Skip* button  will make the timer jump to the time the next task in the queue starts at, and begin counting down time for that task.

![Skip a task](doc/manualPhotos/skipTimer.png)

### Adding time to a subtask

Pressing the *+1 min* button will add an additional minute to the current subtask. This can be helpful if you need an extra few moments to discuss a meeting topic.

![Add time to subtask](doc/manualPhotos/addTime.png)

### Removing a task

Pressing the *Remove Task* button will remove the last task from the list. This can not be undone.

Note that this can not be done while the timer is running. In order to remove a task you must reset the timer, or wait for it to finish.

![Remove a task](doc/manualPhotos/removeTask.png)

### Exporting an agenda

Pressing the *Export Task* button will save the current configuration as a **.csv** file. It will be saved in the same format that the system requires agendas to be imported in.

**Steps to export an agenda**
1. Press the *Export Tasks* button.
2. Choose the folder in your file explorer to save the **.csv** file to..
3. Name the agenda (The default name is 'Task data').
4. Press the save button.

![Export agenda](doc/manualPhotos/exportTasks.png)

# That's all!
We hope you enjoy using this timer application. Please report any issues or feature requests on our gitlab page!
