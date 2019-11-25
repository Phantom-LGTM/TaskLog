

import java.io.*;
import java.util.Calendar;
import java.util.GregorianCalendar;



public class RecordTasks {
    public static void writeTasks(TaskLog tasks,  File file2) throws IOException {
        FileWriter fileWriter = new FileWriter(file2);
        BufferedWriter out = new BufferedWriter(fileWriter);
        out.write(String.valueOf(tasks.getSortOfTime().size()));
        out.write(' ');
        out.write('\n');
        out.write(' ');
        for (int i = 0; i < tasks.getSortOfTime().size(); i++) {
            out.write(String.valueOf(tasks.getSortOfTime().get(i).getNumber()));
            out.write(' ');
            out.write(tasks.getSortOfTime().get(i).getName());
            out.write(' ');
            out.write(tasks.getSortOfTime().get(i).getTask());
            out.write(' ');
            out.write(String.valueOf(tasks.getSortOfTime().get(i).getCalendar().get(Calendar.YEAR)));
            out.write(' ');
            out.write(String.valueOf(tasks.getSortOfTime().get(i).getCalendar().get(Calendar.MONTH)));
            out.write(' ');
            out.write(String.valueOf(tasks.getSortOfTime().get(i).getCalendar().get(Calendar.DAY_OF_MONTH)));
            out.write(' ');
            out.write(String.valueOf(tasks.getSortOfTime().get(i).getCalendar().get(Calendar.HOUR_OF_DAY)));
            out.write(' ');
            out.write(String.valueOf(tasks.getSortOfTime().get(i).getCalendar().get(Calendar.MINUTE)));
            out.write(' ');
            out.write(String.valueOf(tasks.getSortOfTime().get(i).getCalendar().get(Calendar.SECOND)));
            out.write(' ');
            out.write(tasks.getSortOfTime().get(i).getFio());
            out.write(' ');
            out.write(tasks.getSortOfTime().get(i).getCall());
            out.write(' ');
            out.write(tasks.getSortOfTime().get(i).getMail());
            out.write(' ');
            if(tasks.getSortOfTime().get(i).getSch()==true){
                out.write('t');
                out.write(' ');
            }else{
                out.write('f');
                out.write(' ');
            }
            out.write('\n');
            out.write(' ');
        }
        out.close();
    }

    public static TaskLog inputTasks(File file) throws IOException {
        FileReader fileReader = new FileReader(file);
        BufferedReader d = new BufferedReader(fileReader);
        TaskLog tasks=new TaskLog();
        int i;
        int counter=-1;
        String str="";
        while((i=d.read())!= -1){
            char ch=(char)i;
            str=str+ch;
            if(ch=='\n'){
                counter++;
            }
        }

        String delimeter=" ";
        String[] subStr;
        subStr=str.split(delimeter);
        /*for (int j = 0; j < subStr.length; j++){
            System.out.println(subStr[j]);
        }*/
        for (int j = 0; j < counter; j++){
            //System.out.print(subStr[4+j*14]);
            int number=(int) Float.parseFloat(subStr[2+j*14]);
            String name=subStr[3+j*14];
            String task=subStr[4+j*14];
            Calendar calendar = new GregorianCalendar(Integer.parseInt(subStr[5 + j * 14]),
                    (int) Float.parseFloat(subStr[6 + j * 14]), (int) Float.parseFloat(subStr[7 + j * 14]));
            calendar.set(Calendar.HOUR_OF_DAY, (int) Float.parseFloat(subStr[8 + j * 14]));
            calendar.set(Calendar.MINUTE, (int) Float.parseFloat(subStr[9 + j * 14]));
            calendar.set(Calendar.SECOND, (int) Float.parseFloat(subStr[10 + j * 14]));
            String fio = subStr[11 + j * 14];
            String call = subStr[12 + j * 14];
            String mail = subStr[13 + j * 14];
            boolean sch;
            //System.out.println(subStr[14+j*14]);
            if (subStr[14 + j * 14].equals("t")==true) {
                sch = true;
            }
            //if(subStr[14 + j * 14] == "f")
            else
            {
                sch = false;
            }
            Task objective = new Task(name, task, calendar, call, fio, mail, number, sch);
            tasks.addTask(objective);
        }
        d.close();
        /*for(int j=0;j<tasks.getTasks().size();j++){
            tasks.getTasks().get(j).setName(getStr(tasks.getTasks().get(j).getName(),"_"));
            tasks.getTasks().get(j).setMail(getStr(tasks.getTasks().get(j).getMail(),"_"));
            tasks.getTasks().get(j).setFio(getStr(tasks.getTasks().get(j).getFio(),"_"));
            tasks.getTasks().get(j).setCall(getStr(tasks.getTasks().get(j).getCall(),"_"));
            tasks.getTasks().get(j).setTask(getStr(tasks.getTasks().get(j).getTask(),"_"));
        }*/
        return tasks;
    }

    public static String getString(String string){
        String str=string.replace(" ","_");
        return str;
    }

    public static String getStr(String string,String znak){
        String str=string.replace(znak," ");
        return str;
    }

    /*public static void main(String[] args) throws IOException {
        File file = new File("file.txt");
        TaskLog tasks = new TaskLog();
        Calendar calendar = new GregorianCalendar(2019, 10, 22);
        calendar.set(Calendar.HOUR_OF_DAY, 12);
        calendar.set(Calendar.MINUTE, 50);
        String task = "задание";
        String call = "9764578";
        String mail = "";
        String fio = "fgj";
        String name = "test";
        boolean sch = true;
        for (int i = 0; i < 10; i++) {
            int number = tasks.getNumber();
            //tasks.setNumber(tasks.getNumber());
            //System.out.println(tasks.getNumber());
            Task objective = new Task(name, task, calendar, call, fio, mail, number, sch);
            tasks.addTask(objective);
        }
        writeTasks(tasks,file);

        TaskLog test=inputTasks(file);
        for(int i=0;i<test.getTasks().size();i++) {
            System.out.println(test.getTasks().get(i).getCalendar().getTime());
        }
    }*/
}
