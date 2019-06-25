package mainPkg;

import controllerPkg.ClockController;
import modelPkg.TimeModel;
import modelPkg.TimeValueModel;
import viewPkg.ClockView;
import viewPkg.BaseClockView;

import javax.swing.*;
import java.awt.*;

public class BinClockMain extends JFrame {
    public BinClockMain() {
        setTitle("Binary Clock");
        TimeValueModel m = new TimeModel();
        BaseClockView v = new ClockView(m);
        ClockController c = new ClockController(m);
        setLayout(new FlowLayout());
        add(c);
        add(v);
        pack();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] arg) {
        BinClockMain binClock = new BinClockMain();
    }
}
