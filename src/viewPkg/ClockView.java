package viewPkg;

import modelPkg.TimeValueModel;

import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;

public class ClockView extends BaseClockView {
    JLabel timeDisplayer = new JLabel();
    //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("mm:ss.SS");//hh:mm:ss

    public ClockView() {
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(600,70));
        setOpaque(true);
        timeDisplayer.setFont(new Font("Century", Font.BOLD, 40));
        timeDisplayer.setForeground(Color.MAGENTA);
        add(timeDisplayer);
        setVisible(true);
    }

    public ClockView(TimeValueModel m) {
        this();
        setModel(m);
        timeDisplayer.setText(model.getTimeText());
        //model.timeToBinaryText()
        //model.getTime().format(dtf)
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(getForeground());
        if (model == null)
            return;
        timeDisplayer.setText(model.getTimeText());
        //model.timeToBinaryText()
        //model.getTime().format(dtf)
    }
}
