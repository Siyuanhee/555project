/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gedcomreader;

/**
 *
 * @author chenshuaishuai
 */

public class Individuals {
    String ID;
    String Name;
    char Gender;
    String Birthday;
    int Age;
    boolean Alive;
    String Death;
    String[] Child;
    String[] Spouse;
    
    public void setAll( String addID,
                        String addName,
                        char addGender,
                        String addBirthday,
                        int addAge,
                        boolean addAlive,
                        String addDeath,
                        String[] addChild,
                        String[] addSpouse) {
        ID = addID;
        Name = addName;
        Gender = addGender;
        Birthday = addBirthday;
        Age = addAge;
        Alive = addAlive;
        Death = addDeath;
        Child = addChild;
        Spouse = addSpouse;
    }
    
    public String getID() {
        return ID;
    }
    
    public String getName() {
        return Name;
    }
    
    public char getGender() {
        return Gender;
    }
    
    public String getBirthday() {
        return Birthday;
    }
    
    public boolean getAlive() {
        return Alive;
    }
    
    public String getDeath () {
        return Death;
    }
    
    public String[] getChild() {
        return Child;
    }
    
    public String[] getSpouse() {
        return Spouse;
    }
}
