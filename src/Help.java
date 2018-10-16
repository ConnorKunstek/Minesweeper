import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Help extends JFrame implements ActionListener{

    JButton done;

    public Help(){

        done = new JButton("Done");

        done.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                done();
            }
        });
    }

    public void done(){
        setVisible(false);
    }

    public void actionPerformed(ActionEvent e){}
}
