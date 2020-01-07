import java.io.*;
import java.net.Socket;

public class Test {

        private static Socket clientSocket; //сокет для общения
        private static BufferedReader reader; // нам нужен ридер читающий с консоли, иначе как
        // мы узнаем что хочет сказать клиент?
        private static BufferedReader in; // поток чтения из сокета
        private static BufferedWriter out; // поток записи в сокет
        private static String login="LOGIN";

        public static void main(String[] args) {
                try {
                        try {
                                        // адрес - локальный хост, порт - 4004, такой же как у сервера
                                        clientSocket = new Socket("localhost", 8080); // этой строкой мы запрашиваем
                                        //  у сервера доступ на соединение
                                        reader = new BufferedReader(new InputStreamReader(System.in));
                                        // читать соообщения с сервера
                                        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                                        // писать туда же
                                        out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                                        out.write(login + "\n"); // отправляем сообщение на сервер
                                        out.flush();
                                        while (true) {
                                                System.out.println("Вы что-то хотели сказать? Введите это здесь:");
                                                String word = reader.readLine();
                                                if(word.equals("stop")){
                                                        out.write(word + "\n"); // отправляем сообщение на сервер
                                                        out.flush();
                                                        break;
                                                }
                                                if(word.equals("add")){
                                                        out.write(word + "\n"); // отправляем сообщение на сервер
                                                        out.flush();
                                                        System.out.println("введите имя");
                                                        String name=reader.readLine();
                                                        System.out.println("введите описание");
                                                        String task=reader.readLine();
                                                        System.out.println("введите год");
                                                        String year=reader.readLine();
                                                        System.out.println("введите месяц");
                                                        String mounth=reader.readLine();
                                                        System.out.println("введите день");
                                                        String day=reader.readLine();
                                                        System.out.println("введите час");
                                                        String hour=reader.readLine();
                                                        System.out.println("введите минуты");
                                                        String minute=reader.readLine();
                                                        System.out.println("введите секунды");
                                                        String second=reader.readLine();
                                                        System.out.println("введите почту");
                                                        String mail=reader.readLine();
                                                        System.out.println("введите номер");
                                                        String call=reader.readLine();
                                                        System.out.println("введите фио");
                                                        String fio=reader.readLine();
                                                        out.write(name + "\n"); // отправляем сообщение на сервер
                                                        out.flush();
                                                        out.write(task + "\n"); // отправляем сообщение на сервер
                                                        out.flush();
                                                        out.write(year + "\n"); // отправляем сообщение на сервер
                                                        out.flush();
                                                        out.write(mounth + "\n"); // отправляем сообщение на сервер
                                                        out.flush();
                                                        out.write(day + "\n"); // отправляем сообщение на сервер
                                                        out.flush();
                                                        out.write(hour + "\n"); // отправляем сообщение на сервер
                                                        out.flush();
                                                        out.write(minute + "\n"); // отправляем сообщение на сервер
                                                        out.flush();
                                                        out.write(second + "\n"); // отправляем сообщение на сервер
                                                        out.flush();
                                                        out.write(mail + "\n"); // отправляем сообщение на сервер
                                                        out.flush();
                                                        out.write(call + "\n"); // отправляем сообщение на сервер
                                                        out.flush();
                                                        out.write(fio + "\n"); // отправляем сообщение на сервер
                                                        out.flush();

                                                }
                                                if(word.equals("delete")){
                                                        out.write(word+"\n");
                                                        out.flush();
                                                        System.out.println("введите наименование задачи");
                                                        String name = reader.readLine();
                                                        out.write(name+"\n");
                                                        out.flush();
                                                }
                                                String serverWord = in.readLine(); // ждём, что скажет сервер
                                                System.out.println(serverWord); // получив - выводим на экран
                                        }
                        } finally { // в любом случае необходимо закрыть сокет и потоки
                                System.out.println("Клиент был закрыт...");
                                clientSocket.close();
                                in.close();
                                out.close();
                        }
                } catch (IOException e) {
                        System.err.println(e);
                }

        }
}