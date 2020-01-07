import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Form2 extends JFrame{
    private JPanel panel;
    private JTextPane taskPane;
    private JTextField nameField;
    private JComboBox mounthBox;
    private JButton deleteButton;
    private JButton updateButton;
    private JTextField fioField;
    private JTextField numberField;
    private JTextField mailField;
    private JComboBox hourBox;
    private JComboBox minuteBox;
    private JComboBox dayBox;
    private JComboBox secondBox;
    private JComboBox yearBox;
    private JTextField loginField;

    public Form2(){
        this.getContentPane().add(panel);
    }

    public Form2(final TaskLog taskLog, Task task, final int index){
        this.getContentPane().add(panel);
        nameField.setText(task.getName());
        taskPane.setText(task.getTask());
        mailField.setText(task.getMail());
        numberField.setText(task.getCall());
        fioField.setText(task.getFio());
        yearBox.setSelectedIndex(task.getCalendar().get(Calendar.YEAR)-2020);
        mounthBox.setSelectedIndex(task.getCalendar().get(Calendar.MONTH));
        dayBox.setSelectedIndex(task.getCalendar().get(Calendar.DAY_OF_MONTH)-1);
        hourBox.setSelectedIndex(task.getCalendar().get(Calendar.HOUR_OF_DAY));
        minuteBox.setSelectedIndex(task.getCalendar().get(Calendar.MINUTE));
        secondBox.setSelectedIndex(task.getCalendar().get(Calendar.SECOND));
        loginField.setText(task.getLogin());
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                taskLog.deleteTask(index);
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
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                int year = Integer.valueOf(String.valueOf(yearBox.getModel().getSelectedItem()));
                int month = Integer.valueOf(String.valueOf(mounthBox.getModel().getSelectedItem()))-1;
                int day = Integer.valueOf(String.valueOf(dayBox.getModel().getSelectedItem()));
                int hour = Integer.valueOf(String.valueOf(hourBox.getModel().getSelectedItem()));
                int minute = Integer.valueOf(String.valueOf(minuteBox.getModel().getSelectedItem()));
                int second = Integer.valueOf(String.valueOf(secondBox.getModel().getSelectedItem()));
                Calendar calendar = new GregorianCalendar(year,month,day,hour,minute,second);
                String task = taskPane.getText();
                String mail = mailField.getText();
                String call = numberField.getText();
                String fio = fioField.getText();
                String login = loginField.getText();
                System.out.println(login);
                Task task1 = new Task(name,task,calendar,call, fio, mail,true,login);
                taskLog.setTask(index,task1);
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

    /*private deleteActions(){

    }*/

}
