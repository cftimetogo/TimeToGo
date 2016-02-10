package de.arbeitsagentur.timetogo;

import java.io.Serializable;
import java.util.Date;

public class ConfigDaten implements Serializable {

    private String name;
    private Date dateOfBirth;
    private Boolean buttonStatus = false;

    public ConfigDaten() {
    }

    /*
    public ConfigDaten(String name, Date dateOfBirth) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }*/

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getButtonStatus() {
        return buttonStatus;
    }

    public void setButtonStatus(Boolean buttonStatus) {
        this.buttonStatus = buttonStatus;
    }

    /*
    public boolean equals(Object obj) {
        if (obj instanceof ConfigDaten) {
            ConfigDaten other = (ConfigDaten) obj;

            return this.name.equals(other.name)
                    && this.dateOfBirth.equals(other.dateOfBirth);

        } else {
            return false;
        }
    }

    public int hashCode() {
        return this.name.hashCode() * 37 + this.dateOfBirth.hashCode();
    }

    public String toString() {
        return "Name: " + this.name + ", dateOfBirth: " + this.dateOfBirth;
    }
    */
}