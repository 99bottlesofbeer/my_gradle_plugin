import com.intellij.openapi.project.Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

public class MyJFrame extends JFrame{
    String string;
    JTextField textField;

    MyJFrame(String string)
    {
        this.string = string;

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(700,200);
        this.setLayout(new GridLayout(1,3));

        this.add(new JLabel(string));

        JPanel panel = new JPanel();
        panel.add(new JLabel("code"));
        textField = new JTextField(20);
        panel.add(textField);
        this.add(panel);

        JButton button = new JButton("confirm");
        button.addActionListener(new Listener(this));

        this.add(button);

        this.setVisible(true);
    }
    static class Listener implements ActionListener
    {
        MyJFrame myJFrame;
        Listener(MyJFrame myJFrame)
        {
            this.myJFrame = myJFrame;
        }
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            CodeMap.getInstance().put(myJFrame.string, myJFrame.textField.getText());
            myJFrame.setVisible(false);
            myJFrame.dispose();
        }
    }
}
