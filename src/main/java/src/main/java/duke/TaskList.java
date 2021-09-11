package src.main.java.duke;

import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private ArrayList<Task> list = new ArrayList<>();

    /**
     * method to add the task to the list in src.main.java.duke.TaskList
     *
     * @param task
     */
    void add(Task task) {
        list.add(task);
    }

    /**
     * method to remove the specified task from the list
     *
     * @param n index of the task
     * @return string representation of the task deleted
     * @throws DukeException
     */
    String remove(int n) throws DukeException {
        if (n > list.size()) {
            throw new TaskNotFoundException("list has only " + list.size() + "tasks. Enter a valid task");
        }

        String deletedTask = list.get(n - 1).toString();
        list.remove(n - 1);
        return deletedTask;
    }

    /**
     * getter for accessing a particular from the list
     *
     * @param n index of the task
     * @return task object which is to be accessed
     */
    Task get(int n) {
        return list.get(n);
    }

    /**
     * method to mark a task in the list as done
     *
     * @param n index of the task
     * @throws DukeException
     */
    void markAsDone(int n) throws DukeException {
        if (n > list.size()) {
            throw new TaskNotFoundException("list has only " + list.size() + " tasks. Enter a valid task");
        }
        list.get(n - 1).markAsDone();
    }

    /**
     * method to convert the task list into a list of string to be stored in the database
     *
     * @return
     */
    List<String> convertListtoStringList() {
        ArrayList<String> content = new ArrayList<>();
        list.forEach((elem) -> {
            if (elem instanceof Deadline) {
                content.add(((Deadline) elem).makeStorageString());
            } else {
                content.add(elem.toString());
            }
        });
        return content;
    }

    /**
     * method to find all tasks in the list that match the given text
     *
     * @param text Keyword to be searched
     * @return list of all matching tasks
     */
    List<Task> find(String text) {
        ArrayList<Task> content = new ArrayList<>();

        list.forEach((elem) -> {
            String[] split = elem.getTask().split(" ");

            for (int i = 0; i < split.length; i++) {
                if (split[i].equals(text)) {
                    content.add(elem);
                    break;
                }
            }
        });
        return content;
    }

    /**
     * method to print task list on command
     */
    String printList() {
        StringBuilder text = new StringBuilder();
        if (list.size() == 0) {
            System.out.println("The list has no tasks");
            text = new StringBuilder("The list has no tasks");
        } else {
            for (int i = 0; i < this.list.size(); i++) {
                System.out.println((i + 1) + ". " + list.get(i).toString());
                text.append(i + 1).append(". ").append(list.get(i).toString()).append("\n");
            }
        }
        return text.toString();
    }

    String update(int index, String time, String task) {
        if (task.equals("event")) {
            Event event = (Event) list.get(index - 1);
            event.setTime(time);
            return event.toString();
        } else {
            Deadline deadline = (Deadline) list.get(index - 1);
            deadline.setTime(time);
            return deadline.toString();
        }
    }

    int getSize() {
        return list.size();
    }
}
