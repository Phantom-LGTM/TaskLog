import java.io.*;
import java.util.Calendar;
import java.util.Scanner;
import java.util.Timer;

import static java.util.Calendar.*;

public class Main {


    public static Task getInformation(Scanner scanner){//метод ввода всех компанент задачи
        System.out.println("введите наименование задачи:");
        String name = RecordTasks.getString(scanner.nextLine());//ввод названия
        System.out.println("введите описание задачи:");
        String task = RecordTasks.getString(scanner.nextLine());//ввод описания
        System.out.println("введите время в которое должна сработать задача(YYYY:MM:DD:hh:mm:ss):");
        String data = scanner.nextLine();//ввод строки даты
        Calendar calendar = TimeCounter.getCalendar(data);//получение даты из строки
        calendar.add(MONTH,-1);//уменьшение месяца на 1, т.к. остчет месяцев начинается с 0
        System.out.println("введите контактное ФИО:");
        String fio = RecordTasks.getString(scanner.nextLine());//ввод ФИО
        System.out.println("введите контактную почту:");
        String mail = RecordTasks.getString(scanner.nextLine());//ввод почты
        System.out.println("введите контактный номер:");
        String call = RecordTasks.getString(scanner.nextLine());//ввод контактного номера
        boolean sch=true;//метка задется как true
        int number=0;//номер задается 0
        Task task1=new Task(name,task,calendar,call,fio,mail,number,sch);//создание задачи
        return task1;//вывод задачи
    }

    public static void output(int number,String name,String task,String call,String fio,String mail){
        System.out.println(number);//вывод номера задачи
        System.out.println("Название: "+name);//вывод названия
        System.out.println("Описание: "+task);//вывод описания задачи
        System.out.println("Номер телефона: "+call);//вывод контактного номера
        System.out.println("ФИО: "+fio);//вывод ФИО
        System.out.println("Почта: "+mail);//вывод контактной почты
    }

    public static void main(String[] args) throws IOException {//запуск программы
        Timer timer = new Timer();//создание таймера
        File file = new File("file.txt");//задание файла с котого считывается информация
        TaskLog tasks = RecordTasks.inputTasks(file);//считывание списка задач из файла
        if (tasks.getTasks().size() != 0) {//если файл не пустой
            VerificationTask.play(tasks,timer);//постановка имеющихся задач на таймер
            File file2 = new File("file.txt");//задание файла в который записывается информация информация
            RecordTasks.writeTasks(tasks, file2);//перезапись в файл обновленной информации
        }
        while (true) {//зацикливание программы
            Scanner scanner = new Scanner(System.in);//создание сканера
            System.out.println("введите команду для дальнейшей работы:");
            String command = scanner.nextLine();//ввод команды
            if (command.equals("add") == true) {//команда добавления задачи
                Task task1 = getInformation(scanner);//ввод всей информации задачи
                task1.setNumber(task1.getNumber());//запись номера задачи
                tasks.addTask(task1);//добавление задачи в список задач
                TimeCounter.writeMassage(task1,timer);//постановка задачи на выполнение
                RecordTasks.writeTasks(tasks, file);//перезапись в файл обновленной информации
            } else {
                if (command.equals("delete") == true) {//команда удаление задачи
                    System.out.println("введите номер задачи");
                    int number = scanner.nextInt();//ввод номера по которому удалится задача
                    tasks.getTaskByNumber(number).cancel();//удаление таймера
                    tasks.deleteTask(number);//удаление задачи из списка
                    RecordTasks.writeTasks(tasks, file);//перезапись в файл обновленной информации
                } else {
                    if (command.equals("remove") == true) {//команда изменения задачи
                        System.out.println("введите номер задачи которую хотите изменить:");
                        int number = scanner.nextInt();//ввод номера задачи
                        System.out.println("если не хотите менять данный элемент оставьте поле пустым");
                        Task task = getInformation(scanner);//заполнение всей обновленной информации о задаче
                        tasks.getTaskByNumber(number).cancel();//даление таймера задачи
                        tasks.setTask(number, task);//изменеие задачи
                        TimeCounter.writeMassage(tasks.getTaskByNumber(number),timer);//постановка задачи на таймер
                        RecordTasks.writeTasks(tasks, file);//перезапись информации
                    } else {
                        if (command.equals("help") == true) {//команда вывода всех команд
                            System.out.println("help-помощь");
                            System.out.println("delete-удаление");
                            System.out.println("add-добавление");
                            System.out.println("remov-изменение задачи");
                            System.out.println("task-задачи которые еще не были вызваны");
                        } else {
                            if (command.equals("task") == true) {//команда вывода всех задач которые еще не были вызваны
                                for (int i = 0; i < tasks.getTasks().size(); i++) {//перебор всех задач
                                    if (tasks.getTasks().get(i).getSch() == true) {//если метка равна true
                                        tasks.getTasks().get(i).run();//выполнеие задачи
                                        tasks.getTasks().get(i).setSch(true);//обнуление метки, постановка ее в положение true
                                    }
                                }
                            }else {
                                if (command.equals("close") == true) {//команда выхода из цикла
                                    RecordTasks.writeTasks(tasks, file);//перезапись в файл
                                    break;//остановка цикла
                                } else {//если ни одна из команд не совпала
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
