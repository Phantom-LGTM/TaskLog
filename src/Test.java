/*
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Test {
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

            Calendar calendar = new GregorianCalendar(year,month,day,hour,minute,second);

            tasks.addTask(new Task(attributes.getNamedItem("name").getNodeName(),attributes.getNamedItem("task").getNodeValue(),calendar,
                    attributes.getNamedItem("number").getNodeValue(),attributes.getNamedItem("FIO").getNodeValue(),attributes.getNamedItem("mail").getNodeValue(),mark));
        }
        return tasks;
    }

   public static void main(String[] args) throws ParserConfigurationException, TransformerException, SAXException, IOException {
        TaskLog tasks = new TaskLog();
        File file = new File("file.xml");
        String name="name";
        String task ="task";
        String call = "123456789";
        String fio = "EVA";
        String mail = "mail";
        boolean sch = false;
        Calendar calendar = new GregorianCalendar(2019,11,12,15,30,40);
        for( int i = 0; i<10;i++){
            Task objective = new Task(name, task, calendar, call, fio, mail, sch);
            tasks.addTask(objective);
        }
        writeTasks(tasks,file);
        System.out.println(tasks.getTasks().size());

        TaskLog tasks2=inputTasks(file);
        for(int i = 0;i<tasks2.getTasks().size();i++) {
            System.out.println(tasks2.getTasks().get(i).getSch());
        }
    }




}
*/