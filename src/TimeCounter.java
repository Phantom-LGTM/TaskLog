import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;

public class TimeCounter extends Timer{

    public static void writeMassage(Task task){
        Timer timer= new Timer();
        timer.schedule(task,task.getCalendar().getTime());
    }

    public static void deleteMassage(Task task){

    }

    public static Calendar getCalendar(String data){
        String delimeter=":";
        if(data.equals("")==false){
            String[] subStr;
            subStr=data.split(delimeter);
            Calendar calendar=new GregorianCalendar(Integer.parseInt(subStr[0]),Integer.parseInt(subStr[1]),Integer.parseInt(subStr[2]));
            calendar.set(Calendar.HOUR,Integer.parseInt(subStr[3]));
            calendar.set(Calendar.MINUTE,Integer.parseInt(subStr[4]));
            calendar.set(Calendar.SECOND,Integer.parseInt(subStr[5]));
            return calendar;
        }else {
            Calendar calendar = new GregorianCalendar();
            return calendar;
        }
    }

    /*public static Calendar getDate(){

    }*/

    /*public static void main(String[] args){

        Calendar calendar = new GregorianCalendar(2019, 10, 22);
        calendar.set(Calendar.HOUR_OF_DAY, 13);
        int number=0;
        calendar.set(Calendar.MINUTE, 2);
        String t = "задача";
        String call = "9764578";
        String mail = "";
        String fio = "fgj";
        String name = "test";
        boolean sch = true;
        Task task=new Task(name, t, calendar, call, fio, mail, number, sch);
        writeMassage(task);
    }*/

}
