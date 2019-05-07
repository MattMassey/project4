import java.util.ArrayList;
import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by 016308 on 5/1/2019.
 */
public class TaskCollection {
    private ArrayList<Task> tasks = new ArrayList<>();

    public void addTask(String name, String desc, int pri) {
        Task newTask = new Task();
        newTask.setTitle(name);
        newTask.setDescription(desc);
        newTask.setPriority(pri);
        tasks.add(newTask);
    }

    public void removeTask(String taskName) {
        for (Task o : tasks) {
            if (o.getTitle().equalsIgnoreCase(taskName)) {
                tasks.remove(o);
                break;
            }
        }
    }

    public void changeTask(String taskName, String taskDescription) {
        for (Task o : tasks) {
            if (o.getTitle().equalsIgnoreCase(taskName)) {
                o.setDescription(taskDescription);
            }
        }
    }

    public void listTasks(int priority) {
        try {
            for (Task o : tasks) {
                if (o.getPriority() == priority) {
                    System.out.println(o);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    public void serializeCollection() {
        Gson gson = new Gson();

        try {
            FileWriter writer = new FileWriter("data.json");
            gson.toJson(this,writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
