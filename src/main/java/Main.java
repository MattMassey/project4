import java.util.*;

/**
 * Created by 016308 on 5/1/2019.
 * CSCI 1660, program is almost identical to project 3, except it
 * serializes the task list into a JSON object upon exiting the program
 */
public class Main {
    static Scanner input = new Scanner(System.in);
    static TaskCollection taskList = new TaskCollection();

    public static void main(String[] args) {
        System.out.println("This programs allows you to keep a running to-do list.");
        System.out.println("(1) Add a task.\n(2) Change a task.\n(3) Remove a task.\n(4) List tasks.\n(0) Exit");
        int choice = Integer.parseInt(input.nextLine());
        while (choice != 0) {
            String tempName;
            String tempDesc; //These three variables store temporary data
            int tempInt = 0;
            boolean goodInput = false;

            switch (choice) {
                case 1:
                    System.out.println("Name of task?");
                    tempName = input.nextLine();
                    System.out.println("Description?");
                    tempDesc = input.nextLine();
                    while (!goodInput) {
                        try {
                            System.out.println("Priority? (0-5, 5 highest)");
                            tempInt = Integer.parseInt(input.nextLine());
                            if (tempInt < 0 || tempInt > 5) {
                                throw new Exception ();
                            } else {
                                goodInput = true;
                            }
                        } catch (Exception e) {
                            System.out.println("Oops!  That was not an integer, or was out of range.");
                        }
                    }
                    taskList.addTask(tempName, tempDesc, tempInt);
                    Collections.sort(taskList.getTasks());
                    System.out.println("Done.");
                    break;
                case 2:
                    System.out.println("Name of task?");
                    tempName = input.nextLine();
                    System.out.println("New description?");
                    tempDesc = input.nextLine();
                    taskList.changeTask(tempName, tempDesc);
                    System.out.println("Done.");
                    break;
                case 3:
                    System.out.println("Name of task?");
                    tempName = input.nextLine();
                    taskList.removeTask(tempName);
                    System.out.println("Done.");
                    break;
                case 4:
                    System.out.println("Would you like to: (1) list all tasks, or (2) only those of a specific priority?");
                    while (!goodInput) {
                        try {
                            tempInt = Integer.parseInt(input.nextLine());
                            if (tempInt == 1 || tempInt == 2) {
                                goodInput = true;

                            } else {
                                throw new Exception();
                            }
                        } catch (Exception e) {
                            System.out.println("Oops!  That was not an integer, or was out of range.");
                        }
                    }
                    goodInput = false;
                    if (tempInt == 1) {
                        for (Task o : taskList.getTasks()) {
                            System.out.println(o);
                        }
                    } else {
                        while (!goodInput) {
                            try {
                                System.out.println("What priority?");
                                taskList.listTasks(Integer.parseInt(input.nextLine()));
                                goodInput = true;
                            } catch (Exception e) {
                                System.out.println("That was not an integer.");
                            }
                        }
                    }
                    break;
                default:
                    System.out.println("Invalid choice.  Please try again.");
                    break;
            }
            System.out.println("(1) Add a task.\n(2) Change a task.\n(3) Remove a task.\n(4) List tasks.\n(0) Exit");
            choice = Integer.parseInt(input.nextLine());
        }
        taskList.serializeCollection();
        System.out.println("Goodbye!");
    }
}
