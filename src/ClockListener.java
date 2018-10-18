
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import javax.swing.JTextField;

public class ClockListener implements ActionListener
{
    private int minutes;
    private int seconds;
    private int milliseconds;
    private JTextField timerLabel;

    public void actionPerformed(ActionEvent e)
    {
        SimpleDateFormat date = new SimpleDateFormat("mm.ss.SSS");

        if (milliseconds == 1000)
        {
            milliseconds = 000;
            seconds++;
        }
        if (seconds == 60) {
            seconds = 00;
            minutes++;
        }
        timerLabel = new JTextField(9);
        timerLabel.setText(String.valueOf(minutes + ":" + seconds + ":" + milliseconds));
        milliseconds++;
    }

    public JTextField getTimerLabel(){
        return timerLabel;
    }
}