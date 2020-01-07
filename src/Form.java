import org.xml.sax.SAXException;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Calendar;

public class Form extends JFrame {
    private JPanel panel;
    private JButton addButton;
    private JList tasks;
    private JButton trayButton;
    private JScrollPane scrollPane;
    private TrayIcon trayIcon;
    private SystemTray tray;
    private DefaultListModel<String> dlm = new DefaultListModel<String>();

    public Form(){
   }

   public Form(final TaskLog taskLog) throws IOException, SAXException, ParserConfigurationException {
       this.getContentPane().add(panel);
       dlm = defaultListModel();
       tasks.setModel(dlm);
       scrollPane.setViewportView(tasks);
       tasks.setSize(150,50);
       tasks.addListSelectionListener(new ListSelectionListener() {
           @Override
           public void valueChanged(ListSelectionEvent e) {
               int index=taskLog.getTasks().size()-tasks.getSelectedIndex()-1;
               Form2 form = new Form2(taskLog,taskLog.getTasks().get(index),index);
               form.pack();
               form.setSize(new Dimension(800,500));
               form.setVisible(true);
               //form.setDefaultCloseOperation(Form2.EXIT_ON_CLOSE);
               Toolkit toolkit = Toolkit.getDefaultToolkit();
               Dimension dimension = toolkit.getScreenSize();
               form.setLocation(dimension.width/2-400,dimension.height/2-250);
               form.setTitle(taskLog.getTasks().get(index).getName());
               form.addWindowListener(new WindowAdapter() {
                   public void windowClosed(WindowEvent e) {
                       try {
                           dlm=defaultListModel();
                       } catch (ParserConfigurationException ex) {
                           ex.printStackTrace();
                       } catch (SAXException ex) {
                           ex.printStackTrace();
                       } catch (IOException ex) {
                           ex.printStackTrace();
                       }
                       System.out.println("pizdec");
                       tasks.setModel(dlm);
                   }
               });
           }
       });
       addButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               Form3 form = new Form3(taskLog);
               form.pack();
               form.setSize(new Dimension(800,500));
               form.setVisible(true);
               //form.setDefaultCloseOperation(Form3.EXIT_ON_CLOSE);
               Toolkit toolkit = Toolkit.getDefaultToolkit();
               Dimension dimension = toolkit.getScreenSize();
               form.setLocation(dimension.width/2-400,dimension.height/2-250);
               form.setTitle("Task");
               form.addWindowListener(new WindowAdapter() {
                   public void windowClosed(WindowEvent e) {
                       try {
                           dlm=defaultListModel();
                       } catch (ParserConfigurationException ex) {
                           ex.printStackTrace();
                       } catch (SAXException ex) {
                           ex.printStackTrace();
                       } catch (IOException ex) {
                           ex.printStackTrace();
                       }
                       tasks.setModel(dlm);
                   }
               });
           }
       });
       tray=SystemTray.getSystemTray();
       Image image=Toolkit.getDefaultToolkit().getImage("Pictures/ddd.png");

       ActionListener exitListener=new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               System.out.println("Exiting....");
               System.exit(0);
           }
       };

       PopupMenu popup=new PopupMenu();
       MenuItem defaultItem=new MenuItem("Exit");
       defaultItem.addActionListener(exitListener);
       popup.add(defaultItem);
       defaultItem=new MenuItem("Open");
       defaultItem.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               try {
                   toTray(false);
               } catch (AWTException ex) {
                   ex.printStackTrace();
               }
           }
       });
       popup.add(defaultItem);
       trayIcon=new TrayIcon(image, "Task Manager", popup);
       trayIcon.setImageAutoSize(true);
       setIconImage(Toolkit.getDefaultToolkit().getImage("ddd.png"));
       trayButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               try {
                   toTray(true);
               } catch (AWTException ex) {
                   ex.printStackTrace();
               }
           }
       });
       setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
       addWindowListener(new WindowAdapter() {
           public void windowClosing(WindowEvent e) {
               onCancel();
           }
       });
   }

   public static String getTaskString(Task task){
        String taskString = "";
        taskString+=task.getName();
        taskString+="   ";
        taskString+=task.getCalendar().get(Calendar.YEAR);
        taskString+=":";
       taskString+=task.getCalendar().get(Calendar.MONTH+1);
       taskString+=":";
       taskString+=task.getCalendar().get(Calendar.DAY_OF_MONTH);
       taskString+=":";
       taskString+=task.getCalendar().get(Calendar.HOUR);
       taskString+=":";
       taskString+=task.getCalendar().get(Calendar.MINUTE);
       taskString+=":";
       taskString+=task.getCalendar().get(Calendar.SECOND);
       taskString+="   ";
       taskString+=task.getLogin();
       return taskString;
   }

   public static DefaultListModel defaultListModel() throws ParserConfigurationException, SAXException, IOException {
        TaskLog taskLog=RecordTasks.inputTasks(RecordTasks.getFile());
        DefaultListModel<String> dlm = new DefaultListModel<String>();
        for(int i = 0;i<taskLog.getTasks().size();i++){
           dlm.add(0,getTaskString(taskLog.getTasks().get(i)));
        }
        return dlm;
   }
    private void toTray(boolean flag) throws AWTException {
        if(flag) {
            tray.add(trayIcon);
            setVisible(false);
            //Хитровыебанный метод для сворачивания в трей JDialog. ORACLE НЕ ДОДУМАЛИСЬ!/
        } else {
            tray.remove(trayIcon);
            setVisible(true);
        }
    }
    private void onCancel() {
        System.out.println("And finally exit...");
        // add your code here if necessary
        dispose();
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
