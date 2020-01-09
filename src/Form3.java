import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Form3 extends JFrame{
    private JPanel panel;
    private JTextPane textPane1;
    private JTextField nameField;
    private JTextField mailField;
    private JTextField numberField;
    private JTextField fioField;
    private JComboBox dayBox;
    private JComboBox secondBox;
    private JComboBox yearBox;
    private JComboBox hourBox;
    private JComboBox monthBox;
    private JComboBox minuteBox;
    private JButton okButton;
    private JTextField loginField1;

    public Form3(){
    }

    public Form3(final TaskLog taskLog){
        this.getContentPane().add(panel);
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                int year = Integer.valueOf(String.valueOf(yearBox.getModel().getSelectedItem()));
                int month = Integer.valueOf(String.valueOf(monthBox.getModel().getSelectedItem()))-1;
                int day = Integer.valueOf(String.valueOf(dayBox.getModel().getSelectedItem()));
                int hour = Integer.valueOf(String.valueOf(hourBox.getModel().getSelectedItem()));
                int minute = Integer.valueOf(String.valueOf(minuteBox.getModel().getSelectedItem()));
                int second = Integer.valueOf(String.valueOf(secondBox.getModel().getSelectedItem()));
                Calendar calendar = new GregorianCalendar(year,month,day,hour,minute,second);
                String task = textPane1.getText();
                String mail = mailField.getText();
                String call = numberField.getText();
                String fio = fioField.getText();
                String login = loginField1.getText();
                Task task1 = new Task(name,task,calendar,call, fio, mail,true,login,0);
                taskLog.addTask(task1);
                try {
                    RecordTasks.writeTasks(taskLog,RecordTasks.getFile());
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (ParserConfigurationException ex) {
                    ex.printStackTrace();
                } catch (SAXException ex) {
                    ex.printStackTrace();
                } catch (TransformerException ex) {
                    ex.printStackTrace();
                }
                dispose();
            }
        });


    }
}
