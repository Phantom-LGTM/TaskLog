import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.IOException;



public class Main{
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        TaskLog taskLog = RecordTasks.inputTasks(RecordTasks.getFile());
        Form form = new Form(taskLog);
        form.pack();
        form.setSize(new Dimension(800,500));
        form.setVisible(true);
        form.setDefaultCloseOperation(Form.EXIT_ON_CLOSE);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        form.setLocation(dimension.width/2-400,dimension.height/2-250);
        form.setTitle("Task manager");
    }
}