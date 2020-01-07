import org.xml.sax.SAXException;

import javax.sound.sampled.Port;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Calendar;
import java.util.TimerTask;

public class Task extends TimerTask {//класс задачи расширяющий TimerTask
    private Calendar calendar;//дата хранимая в задаче
    private String task;//описание задачи
    private String call;//контактный номер
    private String mail;//контактная почта
    private String fio;//ФИО
    private String name;//название задачи
    private boolean sch;//метка срабатывания задачи
    private int number;//номер задачи
    private String login;


    public Task(){}

    @Override
    public void run() {//метод запуска задачи
        setSch(false);//замена метки на сработано
       // Main.output(getName(),getTask(),getCall(),getFio(),getMail());
        try {
            Server.outTask(getName(),getTask(),getMail(),getCall(),getFio(),getLogin());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Task(String name,String task,Calendar calendar,String call,String fio,String mail,boolean sch, String login){// конструктор задачи
        this.task=task;
        this.calendar=calendar;
        this.name=name;
        this.call=call;
        this.fio=fio;
        this.mail=mail;
        this.sch=sch;
        this.login=login;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }//изменение даты

    public void setCall(String call){
        this.call=call;
    }//изменение контактного номера

    public String getCall(){
        return call;
    }//получение контактного номера

    public Calendar getCalendar() {
        return calendar;
    }//получение даты срабатывания

    public void setTask(String task){
        this.task=task;
    }//изменение описания

    public String getTask() {
        return task;
    }//получение описания

    public void setName(String name){
        this.name=name;
    }//изменение названия

    public String getName(){
        return name;
    }//получение названия

    public int getNumber() {
        return number;
    }//получение номера задачи

    public void setNumber(int number) {
        this.number = number;
    }//изменение номера

    public boolean getSch() {
        return sch;
    }//получение метки

    public void setSch(boolean sch) {
        this.sch = sch;
    }//изменение метки

    public String getFio() {
        return fio;
    }//получение ФИО

    public void setFio(String fio) {
        this.fio = fio;
    }//изменение ФИО

    public String getMail() {
        return mail;
    }//получение почты

    public void setMail(String mail) {
        this.mail = mail;
    }//изменение почты

    public void setLogin(InetAddress id) {
        this.login = login;
    }
    public String getLogin() {
        return login;
    }
}
