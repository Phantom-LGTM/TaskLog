

import java.io.*;
import java.util.Calendar;
import java.util.GregorianCalendar;



public class RecordTasks {//класс отвечающий за хранение информации
    public static void writeTasks(TaskLog tasks,  File file2) throws IOException {//сетод записи задач в файл
        FileWriter fileWriter = new FileWriter(file2);//
        BufferedWriter out = new BufferedWriter(fileWriter);//
        out.write(String.valueOf(tasks.getSortOfTime().size()));//запись количества всех задач
        out.write("&tl; ");//запись делителя
        out.write('\n');
        out.write("&tl; ");
        for (int i = 0; i < tasks.getSortOfTime().size(); i++) {//перебор всех задач
            out.write(String.valueOf(tasks.getSortOfTime().get(i).getNumber()));//запись номера задачи
            out.write("&tl; ");
            out.write(tasks.getSortOfTime().get(i).getName());//запись названия
            out.write("&tl; ");
            out.write(tasks.getSortOfTime().get(i).getTask());//запись описания
            out.write("&tl; ");
            out.write(String.valueOf(tasks.getSortOfTime().get(i).getCalendar().get(Calendar.YEAR)));//запись года
            out.write("&tl; ");
            out.write(String.valueOf(tasks.getSortOfTime().get(i).getCalendar().get(Calendar.MONTH)));//запись месяца
            out.write("&tl; ");
            out.write(String.valueOf(tasks.getSortOfTime().get(i).getCalendar().get(Calendar.DAY_OF_MONTH)));//запись дня
            out.write("&tl; ");
            out.write(String.valueOf(tasks.getSortOfTime().get(i).getCalendar().get(Calendar.HOUR_OF_DAY)));//запись часа дня
            out.write("&tl; ");
            out.write(String.valueOf(tasks.getSortOfTime().get(i).getCalendar().get(Calendar.MINUTE)));//запись минут
            out.write("&tl; ");
            out.write(String.valueOf(tasks.getSortOfTime().get(i).getCalendar().get(Calendar.SECOND)));//запись секунд
            out.write("&tl; ");
            out.write(tasks.getSortOfTime().get(i).getFio());//запись ФИО
            out.write("&tl; ");
            out.write(tasks.getSortOfTime().get(i).getCall());//запись контактного номера
            out.write("&tl; ");
            out.write(tasks.getSortOfTime().get(i).getMail());//запись почты
            out.write("&tl; ");
            if(tasks.getSortOfTime().get(i).getSch()==true){//если метка равна true
                out.write('t');//запись t
                out.write("&tl; ");
            }else{//иначе
                out.write('f');//запись f
                out.write("&tl; ");
            }
            out.write('\n');
            out.write("&tl; ");
        }
        out.close();//закрытие потока
    }

    public static TaskLog inputTasks(File file) throws IOException {//считывание информации из файла
        FileReader fileReader = new FileReader(file);//
        BufferedReader d = new BufferedReader(fileReader);//
        TaskLog tasks=new TaskLog();//создание нового списка
        int i;
        int counter=-1;
        String str="";//пустая строка
        while((i=d.read())!= -1){//чтение всей информации из файла
            char ch=(char)i;//запись символа из файла
            str=str+ch;//запись символа в строку
            if(ch=='\n'){//счетчик количества задач
                counter++;
            }
        }

        String delimeter="&tl; ";//делитель строки
        String[] subStr;//массив строк
        subStr=str.split(delimeter);//разделение строки на массив строк
        for (int j = 0; j < counter; j++){//перебор всех задач
            //System.out.print(subStr[4+j*14]);
            int number=(int) Float.parseFloat(subStr[2+j*14]);//запись номера задачи
            String name=subStr[3+j*14];//запись названия задачи
            String task=subStr[4+j*14];//запись описания задачи
            Calendar calendar = new GregorianCalendar(Integer.parseInt(subStr[5 + j * 14]),
                    (int) Float.parseFloat(subStr[6 + j * 14]), (int) Float.parseFloat(subStr[7 + j * 14]));//запись даты
            calendar.set(Calendar.HOUR_OF_DAY, (int) Float.parseFloat(subStr[8 + j * 14]));//часы
            calendar.set(Calendar.MINUTE, (int) Float.parseFloat(subStr[9 + j * 14]));//минуты
            calendar.set(Calendar.SECOND, (int) Float.parseFloat(subStr[10 + j * 14]));//секунды
            String fio = subStr[11 + j * 14];//ФИО
            String call = subStr[12 + j * 14];//контактный номер
            String mail = subStr[13 + j * 14];//почта
            boolean sch;
            if (subStr[14 + j * 14].equals("t")==true) {//если t
                sch = true;//то метка равна true
            }
            else {//иначе
                sch = false;//метка равна false
            }
            Task objective = new Task(name, task, calendar, call, fio, mail, sch);//создание задачи
            tasks.addTask(objective);//добавление ее в список
        }
        d.close();//закрытие потока
        return tasks;//возвращение списка задач
    }

    public static String getString(String string){//на всякий случай что бы не совпало
        String str=string.replace("&tl; ","_");//замена делителя на пижнее подчеркивание
        return str;
    }

    public static String data(Calendar calendar){
        String data= calendar.getTime().toString();
        return data;
    }

}
