import java.io.*;
import java.util.Calendar;
import java.util.Scanner;

public class Main {


    public static Task getInformation(Scanner scanner){
        System.out.println("введите наименование задачи:");
        String name = RecordTasks.getString(scanner.nextLine());
        System.out.println("введите описание задачи:");
        String task = RecordTasks.getString(scanner.nextLine());
        System.out.println("введите время в которое должна сработать задача(YYYY:MM:DD:hh:mm:ss):");
        String data = RecordTasks.getString(scanner.nextLine());
        Calendar calendar = TimeCounter.getCalendar(data);
        System.out.println("введите контактное ФИО:");
        String fio = RecordTasks.getString(scanner.nextLine());
        System.out.println("введите контактную почту:");
        String mail = RecordTasks.getString(scanner.nextLine());
        System.out.println("введите контактный номер:");
        String call = RecordTasks.getString(scanner.nextLine());
        boolean sch=true;
        int number=0;
        Task task1=new Task(name,task,calendar,call,fio,mail,number,sch);
        return task1;
    }

    public static void main(String[] args) throws IOException {
        TimeCounter timer;
        File file = new File("file.txt");
        TaskLog tasks = RecordTasks.inputTasks(file);
        //System.out.println(tasks.getTasks().size());
        if (tasks.getTasks().size() != 0) {
            VerificationTask.play(tasks);
            File file2 = new File("file.txt");
            RecordTasks.writeTasks(tasks, file2);
        }
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("введите команду для дальнейшей работы:");
            String command = scanner.nextLine();
            if (command.equals("add") == true) {
                int number = tasks.getNumber();
                Task task1 = getInformation(scanner);
                task1.setNumber(number);
                tasks.addTask(task1);
                TimeCounter.writeMassage(task1);
                RecordTasks.writeTasks(tasks, file);
            } else {
                if (command.equals("delete") == true) {
                    System.out.println("введите номер задачи");
                    int number = scanner.nextInt();
                    tasks.deleteTask(number);
                    //нужно добавить удаление таймера на задачу!!!!!!!!!!!!!
                    RecordTasks.writeTasks(tasks, file);
                } else {
                    if (command.equals("remov") == true) {
                        System.out.println("введите номер задачи которую хотите изменить:");
                        int number = scanner.nextInt();
                        System.out.println("если не хотите менять данный элемент оставьте поле пустым");
                        Task task = getInformation(scanner);
                        tasks.setTask(number, task);
                        TimeCounter.writeMassage(tasks.getTaskByNumber(number));
                        RecordTasks.writeTasks(tasks, file);
                    } else {
                        if (command.equals("help") == true) {
                            System.out.println("help-помощь");
                            System.out.println("delete-удаление");
                            System.out.println("add-добавление");
                            System.out.println("remov-изменение задачи");
                            System.out.println("task-задачи которые еще не были вызваны");
                        } else {
                            if (command.equals("task") == true) {
                                for (int i = 0; i < tasks.getTasks().size(); i++) {
                                    if (tasks.getTasks().get(i).getSch() == true) {
                                        tasks.getTasks().get(i).run();
                                        tasks.getTasks().get(i).setSch(true);
                                        //System.out.println(tasks.getTasks().get(i).getCalendar().getTime());
                                    }
                                }
                            }else {
                                if (command.equals("close") == true) {
                                    RecordTasks.writeTasks(tasks, file);
                                    break;
                                } else {
                                    System.out.println("не существует такой команды");
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
