import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class Server {

    public static final int PORT = 8080;
    public static LinkedList<ServerSomthing> serverList = new LinkedList<ServerSomthing>(); // список всех нитей

    public static void outTask(String name,String task,String mail,String call,String fio,String login) throws IOException {//все что нужно отправить пользователю
        for( int i =0; i<serverList.size();i++) {
            if (login.equals(serverList.get(i).getLogin())) {
                Socket socket = serverList.get(i).getSocket();
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                out.write(name+"\n");
                out.flush();
                out.write(task+"\n");
                out.flush();
                out.write(mail+"\n");
                out.flush();
                out.write(call+"\n");
                out.flush();
                out.write(fio+"\n");
                out.flush();
                //System.out.println("otpravil");
            }
        }
    }

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(PORT);
        try {
            while (true) {
                // Блокируется до возникновения нового соединения:
                Socket socket = server.accept();
                try {
                    serverList.add(new ServerSomthing(socket)); // добавить новое соединенние в список
                } catch (IOException e) {
                    // Если завершится неудачей, закрывается сокет,
                    // в противном случае, нить закроет его при завершении работы:
                    socket.close();
                }
            }
        } finally {
            server.close();
        }
    }
}