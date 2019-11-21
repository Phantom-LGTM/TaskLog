import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class TaskLog {
    private ArrayList<Task> tasks;
    private int number=0;

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

    public static void main(String[] args) {
        TaskLog tasks=new TaskLog() ;
        Calendar calendar = new GregorianCalendar(2020, 1, 15);
        calendar.set(Calendar.HOUR_OF_DAY, 15);
        calendar.set(Calendar.MINUTE, 30);
        String task = "задача";
        String call = "9764578";
        String mail = "";
        String fio = "fgj";
        String name = "test";
        boolean sch = true;
        for (int i = 0; i < 10; i++) {
            int number=tasks.getNumber();
            Task objective = new Task(name, task, calendar, call, fio, mail, number, sch);
            tasks.addTask(objective);
        }
        tasks.deleteTask(4);
        //System.out.println(tasks.getTasks().size());
        for (int i = 0; i < tasks.getTasks().size(); i++) {
           System.out.println(tasks.getTasks().get(i).getNumber());
        }
    }


}
