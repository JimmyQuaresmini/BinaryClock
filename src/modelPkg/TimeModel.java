package modelPkg;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Implementation of TimeValueModel.
 * Model of time with implemented methods
 * for setting and getting time. Also
 * methods for getting the time in
 * binary or normal format.
 * */

public class TimeModel implements TimeValueModel {
    LocalTime theTime;
    boolean showOrdinaryTime = false;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");

    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    public void addPropertyChangeListener(PropertyChangeListener l) {
        pcs.addPropertyChangeListener(l);
    }

    public void addPropertyChangeListener(String propertyName, PropertyChangeListener l) {
        pcs.addPropertyChangeListener(propertyName, l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        pcs.removePropertyChangeListener(l);
    }

    public void removePropertyChangeListener(String propertyName, PropertyChangeListener l) {
        pcs.removePropertyChangeListener(propertyName, l);
    }

    public TimeModel() {
        theTime = LocalTime.now();
    }

    public LocalTime getTime() {
        return theTime;
    }

    public void setTime(LocalTime theTime) {
        LocalTime oldTime = getTime();
        this.theTime = theTime;
        LocalTime newTime = this.theTime;
        pcs.firePropertyChange("theTime", oldTime, newTime);
    }

    public void setShowOrdinaryTime(boolean showOrdinaryTime) {
        this.showOrdinaryTime = showOrdinaryTime;
    }

    public void readCurrentTime() {
        LocalTime newTime = LocalTime.now();
        setTime(newTime);
    }

    /**
     * Function to help with whether the user
     * wants the time in binary or ordinary time.
     * @return String-representation of the time
     */
    public String getTimeText() {
        String timeText = "";
        if (showOrdinaryTime == false)
            timeText = timeToBinaryText();
        else
            timeText = getTime().format(dtf);
        return timeText;
    }

    public String timeToBinaryText () {
        LocalTime timeToConvert = getTime();
        int[] binInts = new int[]{32, 16, 8, 4, 2, 1};
        String binTime = "";
        int tempTimeInt = 0;
        boolean doneConverting = false;
        boolean hourConverting = true;//only need 16-1 of "binInts"

        int hourInt = timeToConvert.getHour();//0-23
        int minuteInt = timeToConvert.getMinute();//0-59
        int secondInt = timeToConvert.getSecond();//0-59

        tempTimeInt = hourInt;
        String tempBinText = "";//for collecting the text after each round
        String tempBin = "";//used each round to help insert a space between the last 4 binary digits

        int start = 0;
        for (int convertRounds = 0; convertRounds < 3; convertRounds++) {
            if (hourConverting == true)
                start = 1;
            else
                start = 0;

            for (int i = start; i < 6; i++) {
                if (doneConverting == true) {
                    tempBin = tempBin + "0";
                    continue;
                }
                if (tempTimeInt > binInts[i]) {
                    tempBin = tempBin + "1";
                    tempTimeInt = tempTimeInt - binInts[i];
                }
                else if (tempTimeInt == binInts[i]) {
                    tempBin = tempBin + "1";
                    doneConverting = true;
                }
                else {
                    tempBin = tempBin + "0";
                }
            }
            tempBinText = tempBin.substring(0, tempBin.length() - 4);
            tempBinText = tempBinText + " " + tempBin.substring(tempBin.length() - 4, tempBin.length());
            binTime = binTime + tempBinText;
            tempBin = "";
            tempBinText = "";
            if (convertRounds < 2) {
                doneConverting = false;
                binTime = binTime + ":";
                if (convertRounds == 0) {
                    hourConverting = false;
                    tempTimeInt = minuteInt;
                }
                else if (convertRounds == 1) {
                    tempTimeInt = secondInt;
                }
            }
        }
        return binTime;
    }
}
