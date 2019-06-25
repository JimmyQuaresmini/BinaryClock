package viewPkg;

import modelPkg.TimeValueModel;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Base class of a view that allows
 * inherited classes to listen to updates
 * of values.
 * */

public abstract class BaseClockView extends JPanel implements PropertyChangeListener {
    protected TimeValueModel model;

    public void setModel(TimeValueModel m) {
        if (model != null)
            model.removePropertyChangeListener(this);
        model = m;
        if (model != null)
            model.addPropertyChangeListener(this);

    }

    public void propertyChange(PropertyChangeEvent e) {
        repaint();
    }
}
