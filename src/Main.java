
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Timer;

import static java.util.Calendar.*;

public class Main extends JDialog {
    private JPanel contentPane;
    private JFormattedTextField taskName;
    private JTextArea taskDescription;
    private JButton deleteButton;
    private JButton addButton;
    private DefaultListModel<String> listModel;
    private JList<String> taskList;
    private JFormattedTextField yearField;
    private JFormattedTextField monthField;
    private JFormattedTextField dayField;
    private JFormattedTextField hourField;
    private JFormattedTextField secondsField;
    private JFormattedTextField minutesField;
    private JFormattedTextField numberField;
    private JFormattedTextField mailField;
    private JFormattedTextField fioField;
    private JFormattedTextField selectedTaskNameFormattedTextField;
    private JButton editButton;
    private JScrollPane scrollPane;
    private JButton toTrayButton;
    private JScrollBar scrollBar1;
    private JTextField editName;
    private TrayIcon trayIcon;
    private SystemTray tray;
    private TaskLog tasks;
    private Timer timer;
    private File file;



    public static void output(String name,String task,String call,String fio,String mail){
        System.out.println("Execution.task");
        createDialog(name, task, fio, mail, call);
    }
    public static void createDialog(String title, String description, String fio, String mail, String number)
    {

        JDialog dialog = new JDialog();

        JTextArea textArea = new JTextArea();

        textArea.append(title + '\n');
        textArea.append(description + '\n');
        textArea.append(fio + '\n');
        textArea.append(mail + '\n');
        textArea.append(number);

        dialog.setModal(true);
        dialog.setLocation(200, 200);

        dialog.getContentPane().add(textArea);

        dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        dialog.setSize(400, 300);

        dialog.setVisible(true);
    }


    public Main() {
        super.setName("Task list");
        setContentPane(contentPane);
        contentPane.setName("Task manager");
        contentPane.updateUI();
        setResizable(false);
        setModal(true);

        listModel = new DefaultListModel<String>();
        taskList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        taskList.setModel(listModel);
        scrollPane.setViewportView(taskList);

        tray=SystemTray.getSystemTray();
        Image image=Toolkit.getDefaultToolkit().getImage("/Media/ddd.png");

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
        trayIcon=new TrayIcon(image, "SystemTray Demo", popup);
        trayIcon.setImageAutoSize(true);
        setIconImage(Toolkit.getDefaultToolkit().getImage("Media/ddd.png"));

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addClick();

                /* Передавать объект Task */
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteClick();
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /* taskList.getSelectedIndex() + 1 */
                editClick();
            }
        });

        toTrayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    toTray(true);
                } catch (AWTException ex) {
                    ex.printStackTrace();
                }
            }
        });
        taskList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                editPull();
            }
        });
        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);


        timer = new Timer();//создание таймер
        file = new File("file.xml");//задание файла с котого считывается информация
        try {
            tasks = RecordTasks.inputTasks(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (tasks.getTasks().size() != 0) {//если файл не пустой
            VerificationTask.play(tasks,timer);//постановка имеющихся задач на таймер
            File file2 = new File("file.xml");//задание файла в который записывается информация информация
            try {
                RecordTasks.writeTasks(tasks, file2);//перезапись в файл обновленной информации
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        for(int i =0; i<tasks.getTasks().size();i++){
            listModel.addElement(tasks.getTasks().get(i).getName()+" "+RecordTasks.data(tasks.getTasks().get(i).getCalendar()));
        }
        System.out.println(listModel.size());
        for(int i = 0;i<listModel.size();i++){
            System.out.println(listModel.get(i));
            System.out.println("---");
        }

    }

    private void addClick() {
        String data="";
        int mounth= Integer.parseInt(monthField.getText())-1;
        data+=yearField.getText()+":"+mounth+":"+dayField.getText()+":"+hourField.getText()+":"+minutesField.getText()+":"
        +secondsField.getText();
        System.out.println(data);
        Calendar calendar=TimeCounter.getCalendar(data);
        Task task=new Task(taskName.getText(),taskDescription.getText(),calendar,numberField.getText(),fioField.getText(),mailField.getText()
                ,true);
        tasks.addTask(task);
        TimeCounter.writeMassage(task,timer);
        try {
            RecordTasks.writeTasks(tasks,file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        listModel.addElement(task.getName()+" "+RecordTasks.data(task.getCalendar()));
        taskList.setModel(listModel);

        yearField.setText("");
        monthField.setText("");
        dayField.setText("");

        hourField.setText("");
        minutesField.setText("");
        secondsField.setText("");

        taskName.setText("");
        taskDescription.setText("");

        mailField.setText("");
        numberField.setText("");
        fioField.setText("");
    }

    private void deleteClick() {
        System.out.println("Delete task");
        System.out.println(taskList.getSelectedIndex() ); // Индекс начинается с -1
        if(taskList.getSelectedIndex()==-1 || taskList.getSelectedIndex()==listModel.getSize()) {
            listModel.removeElement(listModel.getSize());  // Удаление выбранного элемента
            taskList.setModel(listModel);
        }else{
            listModel.removeElement(taskList.getSelectedIndex());  // Удаление выбранного элемента
            taskList.setModel(listModel);
        }
        System.out.println("Delete task");

        tasks.getTasks().get(taskList.getSelectedIndex()).cancel();
        tasks.getTasks().get(taskList.getSelectedIndex());
        tasks.deleteTask(taskList.getSelectedIndex());
        try {
            RecordTasks.writeTasks(tasks,file);
        } catch (IOException e) {
            e.printStackTrace();
        }


        yearField.setText("");
        monthField.setText("");
        dayField.setText("");

        hourField.setText("");
        minutesField.setText("");
        secondsField.setText("");

        taskName.setText("");
        taskDescription.setText("");

        mailField.setText("");
        numberField.setText("");
        fioField.setText("");

    }

    private void editClick() {
        System.out.println("Edit task");
        String data="";
        int mounth= Integer.parseInt(monthField.getText())-1;
        data+=yearField.getText()+":"+mounth+":"+dayField.getText()+":"+hourField.getText()+":"+minutesField.getText()+":"
                +secondsField.getText();
        System.out.println(data);
        Calendar calendar=TimeCounter.getCalendar(data);
        Task task=new Task(taskName.getText(),taskDescription.getText(),calendar,numberField.getText(),fioField.getText(),mailField.getText()
                ,true);
        tasks.setTask(taskList.getSelectedIndex(),
                task);

        TimeCounter.writeMassage(task,timer);
        try {
            RecordTasks.writeTasks(tasks,file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        listModel.set(taskList.getSelectedIndex(), task.getName()+" "+RecordTasks.data(task.getCalendar()));

        yearField.setText("");
        monthField.setText("");
        dayField.setText("");

        hourField.setText("");
        minutesField.setText("");
        secondsField.setText("");

        taskName.setText("");
        taskDescription.setText("");

        mailField.setText("");
        numberField.setText("");
        fioField.setText("");

        //taskList.clearSelection();

        /* Добавить */
    }



    private void onCancel() {
        System.out.println("And finally exit...");
        // add your code here if necessary
        try {
            RecordTasks.writeTasks(tasks,file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        dispose();
    }

    private void toTray(boolean flag) throws AWTException {
        if(flag) {
            tray.add(trayIcon);
            setLocation(-400, -400);
            setSize(0,0);
            /*Хитровыебанный метод для сворачивания в трей JDialog. ORACLE НЕ ДОДУМАЛИСЬ!*/
        } else {
            tray.remove(trayIcon);
            setLocation(0, 0);
            setSize(1400, 400);
        }
    }

    private void editPull(){
        int a = 0;

        System.out.println("Index of selected item");
        System.out.println(taskList.getSelectedIndex());

        yearField.setText(String.valueOf(tasks.getTasks().get(taskList.getSelectedIndex()+a).getCalendar().get(YEAR)));
        monthField.setText(String.valueOf(tasks.getTasks().get(taskList.getSelectedIndex()+a).getCalendar().get(MONTH)+1));
        dayField.setText(String.valueOf(tasks.getTasks().get(taskList.getSelectedIndex()+a).getCalendar().get(DAY_OF_MONTH)));

        hourField.setText(String.valueOf(tasks.getTasks().get(taskList.getSelectedIndex()+a).getCalendar().get(HOUR_OF_DAY)));
        minutesField.setText(String.valueOf(tasks.getTasks().get(taskList.getSelectedIndex()+a).getCalendar().get(MINUTE)));
        secondsField.setText(String.valueOf(tasks.getTasks().get(taskList.getSelectedIndex()+a).getCalendar().get(SECOND)));

        taskName.setText(tasks.getTasks().get(taskList.getSelectedIndex()+a).getName());
        taskDescription.setText(tasks.getTasks().get(taskList.getSelectedIndex()+a).getTask());

        mailField.setText(tasks.getTasks().get(taskList.getSelectedIndex()+a).getMail());
        numberField.setText(tasks.getTasks().get(taskList.getSelectedIndex()+a).getCall());
        fioField.setText(tasks.getTasks().get(taskList.getSelectedIndex()+a).getFio());
    }

    public static void main(String[] args) {
        Main dialog = new Main();
        dialog.pack();
        dialog.setVisible(true);

        System.exit(0);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
