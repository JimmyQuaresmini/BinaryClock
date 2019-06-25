package modelPkg;

import java.time.LocalTime;

/**
 * Model for a LocalTime value with
 * methods for increasing and
 * resetting time.
 * */

public interface TimeValueModel extends Model {
    LocalTime getTime();
    void setTime(LocalTime lt);
    void setShowOrdinaryTime(boolean show);
    void readCurrentTime();
    String getTimeText();
    String timeToBinaryText();
}
