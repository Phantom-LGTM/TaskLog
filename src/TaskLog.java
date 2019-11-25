import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class TaskLog {
    private ArrayList<Task> tasks;
    private int number=1;

    public TaskLog(){
        ArrayList<Task> objectives = new ArrayList<Task>();
        tasks=objectives;
    }

    public TaskLog(ArrayList<Task> objective){
        tasks=objective;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task objective){// добавление новой задачи
        tasks.add(objective);
        this.number++;
    }

    public Task getTaskByNumber(int number){
        Task task= new Task();
        for(int i = 0; i<tasks.size();i++) {
            if (number ==tasks.get(i).getNumber()){
                task=tasks.get(i);
            }
        }
        return task;
    }

    public void addTask(String name,String task,Calendar calendar,String call,String fio,String mail,boolean sch){// добавление новой задачи
        int number=this.number;
        Task objective=new Task(name,task,calendar,call,fio,mail,number,sch);
        tasks.add(objective);
        this.number++;
    }

    public void deleteTask(int numberTask){//удаление по номеру задачи
        for(int i=0;i<tasks.size();i++){
            if(tasks.get(i).getNumber()==numberTask){
                tasks.remove(i);
            }
        }
    }

    /*public void deleteTask(Task objective){// удаление по ссылке на задачу
        tasks.remove(objective);
    }*/

    public void setTask(int numberTask, String task, Calendar calendar){//изменение задачи и даты у задачи
        for(int i=0;i<tasks.size();i++){
            if(tasks.get(i).getNumber()==numberTask){
                tasks.get(i).setTask(task);
                tasks.get(i).setCalendar(calendar);
            }
        }
    }

    public void setTask(int numberTask,Task task1){//изменение задачи и даты у задачи
        for(int i=0;i<tasks.size();i++){
            if(tasks.get(i).getNumber()==numberTask){
                if(task1.getTask().equals("")==false) {
                    tasks.get(i).setTask(task1.getTask());
                }
                tasks.get(i).setCalendar(task1.getCalendar());
                if(task1.getCall().equals("")==false) {
                    tasks.get(i).setCall(task1.getCall());
                }
                if(task1.getFio().equals("")==false) {
                    tasks.get(i).setFio(task1.getCall());
                }
                if(task1.getMail().equals("")==false) {
                    tasks.get(i).setMail(task1.getMail());
                }
                if(task1.getTask().equals("")==false) {
                    tasks.get(i).setName(task1.getName());
                }
            }
        }
    }

    public ArrayList<Task> getSortOfTime(){
        ArrayList<Task> sortTask=new ArrayList<Task>();
        for(int i =0;i<tasks.size();i++){
            sortTask.add(tasks.get(i));
        }
        for (int i = 1; i < tasks.size(); i++) {
            if ( sortTask.get(i).getCalendar().getTime().before(sortTask.get(i-1).getCalendar().getTime())==true) {//<
                Task temp = sortTask.get(i);
                sortTask.set(i,sortTask.get(i-1)) ;
                sortTask.set(i-1,temp);
                for (int z = i - 1; (z - 1) >= 0; z--) {//>=
                    if (sortTask.get(z).getCalendar().getTime().before(sortTask.get(z-1).getCalendar().getTime())==true) {//<
                        Task temp2 = sortTask.get(z);
                        sortTask.set(z,sortTask.get(z-1)) ;
                        sortTask.set(z-1,temp2);
                    } else {
                        break;
                    }
                }
            }
        }
        return sortTask;
    }



    /*public static void main(String[] args) {
        TaskLog tasks = new TaskLog();
        Calendar calendar = new GregorianCalendar(2020, 1, 15);
        calendar.set(Calendar.HOUR_OF_DAY, 15);
        calendar.set(Calendar.MINUTE, 30);
        Calendar calendar2 = new GregorianCalendar(2020, 1, 15);
        calendar2.set(Calendar.HOUR_OF_DAY, 1);
        calendar2.set(Calendar.MINUTE, 30);
        String task = "задача";
        String call = "9764578";
        String mail = "";
        String fio = "fgj";
        String name = "test";
        boolean sch = true;
        for (int i = 0; i < 10; i++) {
            int number = tasks.getNumber();
            Task objective = new Task(name, task, calendar, call, fio, mail, number, sch);
            tasks.addTask(objective);
        }
        for (int i = 0; i < tasks.getTasks().size(); i++) {
            System.out.println(tasks.getTasks().get(i).getCalendar().getTime());
        }
        /*int number = tasks.getNumber();
        Task objective2 = new Task(name, task, calendar2, call, fio, mail, number, sch);
        tasks.addTask(objective2);
        tasks.deleteTask(4);
        //System.out.println(tasks.getTasks().size());

        for (int i = 0; i < tasks.getTasks().size(); i++) {
            System.out.println(tasks.getSortOfTime().get(i).getCalendar().getTime());
        }
    }*/


}
