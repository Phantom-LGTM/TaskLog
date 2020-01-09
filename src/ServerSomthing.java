import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.*;
import java.net.Socket;
import java.util.Calendar;
import java.util.GregorianCalendar;

class ServerSomthing extends Thread {

    private Socket socket; // сокет, через который сервер общается с клиентом,
    // кроме него - клиент и сервер никак не связаны
    private BufferedReader in; // поток чтения из сокета
    private BufferedWriter out; // поток записи в сокет
    private String login;

    public ServerSomthing(Socket socket) throws IOException {
        this.socket = socket;
        // если потоку ввода/вывода приведут к генерированию исключения, оно проброситься дальше
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        login = in.readLine();
        start(); // вызываем run()
    }

    public Socket getSocket() {
        return socket;
    }

    public String getLogin() {
        return login;
    }

    @Override
    public void run() {
        String word;
        TaskLog tasks = new TaskLog();
        try {
            tasks = RecordTasks.inputTasks(RecordTasks.getFile());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        //System.out.println(tasks.getTasks().size());
        try {

            while (true) {
                word = in.readLine();
                if(word.equals("stop")) {
                    System.out.println(word);
                    RecordTasks.writeTasks(tasks,RecordTasks.getFile());
                    break;
                }
                if(word.equals("postpound")){
                    int num = Integer.parseInt(in.readLine());
                    int year = Integer.parseInt(in.readLine());
                    int month = Integer.parseInt(in.readLine());
                    int day = Integer.parseInt(in.readLine());
                    int hour = Integer.parseInt(in.readLine());
                    int minute = Integer.parseInt(in.readLine());
                    int second = Integer.parseInt(in.readLine());
                    Calendar calendar = new GregorianCalendar(year,month,day,hour,minute,second);
                    for(int i = 0;i<tasks.getTasks().size();i++){
                        if(tasks.getTasks().get(i).getNumber()==num){
                            tasks.getTasks().get(i).setCalendar(calendar);
                            TimeCounter.writeMassage(tasks.getTasks().get(i),TimeCounter.getTimer());
                            RecordTasks.writeTasks(tasks,RecordTasks.getFile());
                        }
                    }
                }
                if(word.equals("add")){
                    String name = in.readLine();
                    String task = in.readLine();
                    int year = Integer.parseInt(in.readLine());
                    int month = Integer.parseInt(in.readLine())-1;
                    int day = Integer.parseInt(in.readLine());
                    int hour = Integer.parseInt(in.readLine());
                    int minute = Integer.parseInt(in.readLine());
                    int second = Integer.parseInt(in.readLine());
                    String mail = in.readLine();
                    String call = in.readLine();
                    String fio = in.readLine();
                    Calendar calendar;
                    calendar = new GregorianCalendar(year,month,day,hour,minute,second);
                    Task task1=new Task(name,task,calendar,call,fio,mail,true,login,0);
                    tasks.addTask(task1);
                    //System.out.println(tasks.getTasks().size());
                    RecordTasks.writeTasks(tasks,RecordTasks.getFile());
                }
                if(word.equals("delete")){
                    int num = Integer.parseInt(in.readLine());
                    //System.out.println(name);
                    for(int i = 0;i<tasks.getTasks().size();i++){
                        if(num==tasks.getTasks().get(i).getNumber() & login.equals(tasks.getTasks().get(i).getLogin())){
                            tasks.deleteTask(i);
                            tasks.getTasks().get(i).cancel();
                            System.out.println(tasks.getTasks().get(i).getName());
                            break;
                        }
                    }
                    RecordTasks.writeTasks(tasks,RecordTasks.getFile());
                }
            }

        } catch (IOException e) {
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    private void send(String msg) {
        try {
            out.write(msg + "\n");
            out.flush();
        } catch (IOException ignored) {}
    }
}