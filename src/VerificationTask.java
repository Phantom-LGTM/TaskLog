import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class VerificationTask {//запуск задач

        public static void play(TaskLog tasks){// запуск задач
        for(int i=0;i<tasks.getTasks().size();i++) {//перебор всех задач
            Calendar calendar = new GregorianCalendar();//создание календаря с настоящим временем
            if (tasks.getTasks().get(i).getSch() == true) {//если задача еще не была вызвана
                if (tasks.getTasks().get(i).getCalendar().getTime().before(calendar.getTime()) ||
                        tasks.getTasks().get(i).getCalendar().getTime().equals(calendar.getTime()))//если время задачи меньше текущего или такое
                {
                    tasks.getTasks().get(i).run();//запуск задачи
                    System.out.println("-----------------------------------------");//для более удобного чтения
                }
                if (tasks.getTasks().get(i).getCalendar().getTime().after(calendar.getTime()))//если время задачи больше текущего времени
                {
                    TimeCounter.writeMassage(tasks.getTasks().get(i));//постановка задачи на таймер
                }
            }
        }
    }
}
