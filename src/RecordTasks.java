

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.net.InetAddress;
import java.util.Calendar;
import java.util.GregorianCalendar;



public class RecordTasks {//класс отвечающий за хранение информации
    private static File file= new File("file.xml");//задание файла с котого считывается информация;

    public static void writeTasks(TaskLog tasks,  File file) throws IOException, ParserConfigurationException, SAXException, TransformerException {//сетод записи задач в файл
        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.newDocument();
        Element root = document.createElement("tasks");
        document.appendChild(root);
        for(int i=0;i<tasks.getTasks().size();i++) {
            Element task = document.createElement("task");
            root.appendChild(task);
            task.setAttribute("name",tasks.getTasks().get(i).getName());
            task.setAttribute("task",tasks.getTasks().get(i).getTask());
            task.setAttribute("year", String.valueOf(tasks.getTasks().get(i).getCalendar().get(Calendar.YEAR)));
            task.setAttribute("month", String.valueOf(tasks.getTasks().get(i).getCalendar().get(Calendar.MONTH)));
            task.setAttribute("day", String.valueOf(tasks.getTasks().get(i).getCalendar().get(Calendar.DAY_OF_MONTH)));
            task.setAttribute("hour", String.valueOf(tasks.getTasks().get(i).getCalendar().get(Calendar.HOUR_OF_DAY)));
            task.setAttribute("minute", String.valueOf(tasks.getTasks().get(i).getCalendar().get(Calendar.MINUTE)));
            task.setAttribute("second", String.valueOf(tasks.getTasks().get(i).getCalendar().get(Calendar.SECOND)));
            task.setAttribute("mail",tasks.getTasks().get(i).getMail());
            task.setAttribute("number",tasks.getTasks().get(i).getCall());
            task.setAttribute("FIO",tasks.getTasks().get(i).getFio());
            task.setAttribute("mark", String.valueOf(tasks.getTasks().get(i).getSch()));
            task.setAttribute("login", String.valueOf(tasks.getTasks().get(i).getLogin()));
            task.setAttribute("number", String.valueOf(tasks.getTasks().get(i).getNumber()));
            task.setAttribute("numberTaskLog", String.valueOf(tasks.getNumber()));
        }
        Transformer t = TransformerFactory.newInstance().newTransformer();
        t.setOutputProperty(OutputKeys.INDENT, "yes");
        t.transform(new DOMSource(document),new StreamResult(file));
    }


    public static TaskLog inputTasks(File file) throws IOException, SAXException, ParserConfigurationException {//считывание информации из файла
        TaskLog tasks = new TaskLog();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(file);
        NodeList employeeElements = document.getDocumentElement().getElementsByTagName("task");

        for (int i = 0; i < employeeElements.getLength(); i++) {
            Node task = employeeElements.item(i);
            NamedNodeMap attributes = task.getAttributes();
            int year = Integer.parseInt(attributes.getNamedItem("year").getNodeValue());
            int month = Integer.parseInt(attributes.getNamedItem("month").getNodeValue());
            int day = Integer.parseInt(attributes.getNamedItem("day").getNodeValue());
            int hour = Integer.parseInt(attributes.getNamedItem("hour").getNodeValue());
            int minute = Integer.parseInt(attributes.getNamedItem("minute").getNodeValue());
            int second = Integer.parseInt(attributes.getNamedItem("second").getNodeValue());
            boolean mark = Boolean.parseBoolean(attributes.getNamedItem("mark").getNodeValue());
            String login = attributes.getNamedItem("login").getNodeValue();
            int number = Integer.parseInt(attributes.getNamedItem("number").getNodeValue());

            Calendar calendar = new GregorianCalendar(year,month,day,hour,minute,second);
            tasks.setNumber(Integer.parseInt(attributes.getNamedItem("numberTaskLog").getNodeValue()));
            tasks.addTask(new Task(attributes.getNamedItem("name").getNodeName(),attributes.getNamedItem("task").getNodeValue(),calendar,
                    attributes.getNamedItem("number").getNodeValue(),attributes.getNamedItem("FIO").getNodeValue(),attributes.getNamedItem("mail").getNodeValue(),mark,login,number));
        }
        return tasks;
    }

    public static File getFile() {
        return file;
    }

    public static String data(Calendar calendar){
        String data= calendar.getTime().toString();
        return data;
    }

}
