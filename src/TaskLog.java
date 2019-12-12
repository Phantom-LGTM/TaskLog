import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Timer;


public class TaskLog {//класс списка задач
    private ArrayList<Task> tasks;//список задач
    private int number=1;//номер задачи(просто хранит информацию о номере следующей задачи)




    public TaskLog(){//конструктор создает пустой список
        ArrayList<Task> objectives = new ArrayList<Task>();
        tasks=objectives;
    }

    public TaskLog(ArrayList<Task> objective){
        tasks=objective;
    }//создает список из уже готового листа задач

    public int getNumber() {
        return number;
    }//получение номера следующей задачи

    public void setNumber(int number) {
        this.number = number;
    }//изменение номера

    public ArrayList<Task> getTasks() {
        return tasks;
    }//получение списка

    /*public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }//изменение списка*/

    public void addTask(Task objective){// добавление новой задачи по уже созданной задаче
        tasks.add(objective);//добавление в список задачи
        objective.setNumber(getNumber());
        this.number++;//увеличение номера следующей задачи
    }

    public Task getTaskByNumber(int number){//получение задачи но ее номеру
        Task task= new Task();//новая пустая задача
        for(int i = 0; i<tasks.size();i++) {//перебор всех элементов списка
            if (number ==tasks.get(i).getNumber()){//если принимаемый номер равен номеру задачи
                task=tasks.get(i);//присвоение необходимой задачи пустой
            }
        }
        return task;
    }

    /*public void addTask(String name,String task,Calendar calendar,String call,String fio,String mail,boolean sch){// добавление новой задачи
        Task objective=new Task(name,task,calendar,call,fio,mail,this.number,sch);//создание задачи
        tasks.add(objective);//добавление ее в список
        this.number++;//увеличение на 1 номер следующей задачи
    }*/

    public void deleteTask(int numberTask){//удаление по номеру задачи
        tasks.remove(numberTask);//удаление выбраного элемента
    }

    public void setTask(int numberTask,Task task1){//изменение задачи и даты у задачи
        for(int i=0;i<tasks.size();i++){//перебор списка
            if(tasks.get(i).getNumber()==numberTask){//если номер задачи рвен принятому номеру
                if(task1.getTask().equals("")==false) {//если описание принятой задачи не равено пустой строке
                    tasks.get(i).setTask(task1.getTask());//изменение описания на принятое описание
                }
                tasks.get(i).setCalendar(task1.getCalendar());//изменение даты на принятую
                if(task1.getCall().equals("")==false) {//если контактный номер принятой задачи не равено пустой строке
                    tasks.get(i).setCall(task1.getCall());//изменение контактного номера на принятый
                }
                if(task1.getFio().equals("")==false) {//если ФИО принятой задачи не равено пустой строке
                    tasks.get(i).setFio(task1.getFio());//изменение ФИО на принятое
                }
                if(task1.getMail().equals("")==false) {//если почта принятой задачи не равено пустой строке
                    tasks.get(i).setMail(task1.getMail());//изменение почты на принятую
                }
                if(task1.getName().equals("")==false) {//если название принятой задачи не равено пустой строке
                    tasks.get(i).setName(task1.getName());//изменение названия на принятое
                }
            }
        }
    }

    public ArrayList<Task> getSortOfTime(){//сортировка списка по времени
        ArrayList<Task> sortTask=new ArrayList<Task>();//пустой список который будет сортированным
        for(int i =0;i<tasks.size();i++){//перебор списка
            sortTask.add(tasks.get(i));//заполнение сортированного списка
        }
        for (int i = 1; i < tasks.size(); i++) {//перебор сортированного списка
            if ( sortTask.get(i).getCalendar().getTime().before(sortTask.get(i-1).getCalendar().getTime())==true) {//"<" если задача i-тая
                // раньше задачи i-1
                Task temp = sortTask.get(i);//запись i-той задачи
                sortTask.set(i,sortTask.get(i-1)) ;//запись в i-тую i-1-той задачи
                sortTask.set(i-1,temp);//запись i-той задачи в i-1
                for (int z = i - 1; (z - 1) >= 0; z--) {//перебор списка от i-1 элемента до 0
                    if (sortTask.get(z).getCalendar().getTime().before(sortTask.get(z-1).getCalendar().getTime())==true) {//< если задача
                        // z-тая раньше задачи z-1
                        Task temp2 = sortTask.get(z);//запись z-той задачи
                        sortTask.set(z,sortTask.get(z-1)) ;//запись в z-тую z-1-той задачи
                        sortTask.set(z-1,temp2);//запись z-той задачи в z-1
                    } else {//иначе
                        break;//заершить цикл
                    }
                }
            }
        }
        return sortTask;//возвращает отсортированный список
    }

}
