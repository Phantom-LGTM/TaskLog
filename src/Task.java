import java.util.Calendar;

public class Task {
    private Calendar calendar;
    private String task;
    private String call;
    private String mail;
    private String fio;
    private String name;
    private boolean sch;
    private int number;


    public Task(){}

    public Task(String name,String task,Calendar calendar,String call,String fio,String mail,int number,boolean sch){
        this.task=task;
        this.calendar=calendar;
        this.name=name;
        this.call=call;
        this.fio=fio;
        this.mail=mail;
        this.number=number;
        this.sch=sch;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public void setCall(String call){
        this.call=call;
    }

    public String getCall(){
        return call;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setTask(String task){
        this.task=task;
    }

    public String getTask() {
        return task;
    }

    public void setName(String name){
        this.name=name;
    }

    public String getName(){
        return name;
    }


    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isSch() {
        return sch;
    }

    public void setSch(boolean sch) {
        this.sch = sch;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
