import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class VerificationTask {

        public static void play(TaskLog tasks){
        for(int i=0;i<tasks.getTasks().size();i++) {
            Calendar calendar = new GregorianCalendar();
            if (tasks.getTasks().get(i).getSch() == true) {
                if (tasks.getTasks().get(i).getCalendar().getTime().before(calendar.getTime()) ||
                        tasks.getTasks().get(i).getCalendar().getTime().equals(calendar.getTime()))
                {
                    tasks.getTasks().get(i).run();
                    System.out.println("-----------------------------------------");
                }
                if (tasks.getTasks().get(i).getCalendar().getTime().after(calendar.getTime()))
                {
                    TimeCounter.writeMassage(tasks.getTasks().get(i));
                }
            }
        }
    }

}
