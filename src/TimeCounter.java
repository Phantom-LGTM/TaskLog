import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;

public class TimeCounter {//класс таймера

    private static Timer timer = new Timer() ;

    public static void writeMassage(Task task,Timer timer) {//постановка задачи на выполнение
        timer.schedule(task, task.getCalendar().getTime());//постановка задачи на заданное время
    }



    public static Calendar getCalendar(String data) {//получение даты из строки
        String delimeter = ":";//делитель по которому будет делиться строка
        if (data.equals("") == false) {//если строка не пустая
            String[] subStr;//массив строк
            subStr = data.split(delimeter);//разделение строки по делителю
            Calendar calendar = new GregorianCalendar(Integer.parseInt(subStr[0]), Integer.parseInt(subStr[1]), Integer.parseInt(subStr[2]));//создание
            //Григорианского календаря
            calendar.set(Calendar.HOUR, Integer.parseInt(subStr[3]));//изменение часов
            calendar.set(Calendar.MINUTE, Integer.parseInt(subStr[4]));//изменение минут
            calendar.set(Calendar.SECOND, Integer.parseInt(subStr[5]));//изменение секунд
            return calendar;//вывод даты
        } else {//иначе
            Calendar calendar = new GregorianCalendar();//задается данное время
            return calendar;//вывод
        }
    }

    public static Timer getTimer() {
        return timer;
    }
}