package modelPkg;

import java.beans.PropertyChangeListener;
import java.io.Serializable;

/**
 * Model interface with property listener
 * */

public interface Model extends Serializable {
    void addPropertyChangeListener(PropertyChangeListener l);
    void addPropertyChangeListener(String propertyName, PropertyChangeListener l);
    void removePropertyChangeListener(PropertyChangeListener l);
    void removePropertyChangeListener(String propertyName, PropertyChangeListener l);
}
