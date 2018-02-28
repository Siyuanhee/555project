/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gedcomreader;

import java.util.Date;

/**
 *
 * @author chenshuaishuai
 */

public class Individuals {
    String ID;
    String Name;
    char Gender;
    Date Birthday;
    int Age;
    boolean Alive;
    Date Death;
    String FAMC;
    String FAMS;
    
    public void setID (String id) {
        ID = id;
    }
    
    public String getID() {
        return ID;
    }
    
    public void setName (String name) {
        Name = name;
    }
    
    public String getName() {
        return Name;
    }
    
    public void setGender (char gender) {
        Gender = gender;
    }
    
    public char getGender() {
        return Gender;
    }
    
    public void setBirthday (Date birthday) {
        Birthday = birthday;
    }
    
    public Date getBirthday() {
        return Birthday;
    }
    
    public void setAlive (boolean alive) {
        Alive = alive;
    }
    
    public boolean getAlive() {
        return Alive;
    }
    
    public void setDeath (Date death) {
        Death = death;
    }
    
    public Date getDeath () {
        return Death;
    }
    
    public void setFAMC (String famc) {
        FAMC = famc;
    }
    
    public String getFAMC() {
        return FAMC;
    }
    
    public void setFAMS (String fams) {
        FAMS = fams;
    }
    
    public String getFAMS() {
        return FAMS;
    }
}
