/*import javax.swing.*;
import java.awt.event.*;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class Notification extends JFrame{
        private static final long serialVersionUID = 1L;
        public Notification(String title, String description, String fio, String mail, String number) {
            createDialog(title, description, fio, mail, number);
            System.out.println("LOOOOOOOHHHHHHH");
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

            dialog.getContentPane().add(textArea);

            dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            dialog.setSize(400, 300);

            dialog.setVisible(true);
        }
}
*/