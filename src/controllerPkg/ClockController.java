package controllerPkg;

import modelPkg.TimeValueModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ClockController extends JPanel implements ActionListener {
    private TimeValueModel model;
    private JButton btnStart = new JButton("Start");
    private JButton btnStop = new JButton("Stop");
    private Timer timer;
    private JPanel startStop = new JPanel();
    private JButton btnShowNormalTime = new JButton("Normal time");
    private JPanel showNormalPanel = new JPanel();

    public ClockController() {
        setPreferredSize(new Dimension(170, 70));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        startStop.setPreferredSize(new Dimension(165, 33));
        startStop.add(btnStart);
        startStop.add(btnStop);
        add(startStop);
        btnStart.addActionListener(this);
        btnStart.setToolTipText("Press to start/continue");
        btnStop.addActionListener(this);
        btnStop.setToolTipText("Stop the timer");
        timer = new Timer(1000, this);

        showNormalPanel.setPreferredSize(new Dimension(165, 33));
        showNormalPanel.add(btnShowNormalTime);
        add(showNormalPanel);
        btnShowNormalTime.addActionListener(this);
        btnShowNormalTime.setToolTipText("Toggle binary and normal time");
    }

    public ClockController(TimeValueModel m) {
        this();
        setModel(m);
    }

    public void setModel(TimeValueModel m) { model = m; }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnStart) {
            timer.start();
        }
        else if (e.getSource() == btnStop) {
            timer.stop();
        }
        else if (e.getSource() == timer) {
            if (model != null)
                model.readCurrentTime();
        }
        else if (e.getSource() == btnShowNormalTime) {
            if (btnShowNormalTime.getText().equals("Normal time")) {
                if (model != null)
                    model.setShowOrdinaryTime(true);
                btnShowNormalTime.setText("Binary");
            }
            else if (btnShowNormalTime.getText().equals("Binary")) {
                if (model != null)
                    model.setShowOrdinaryTime(false);
                btnShowNormalTime.setText("Normal time");
            }
        }
    }
}
