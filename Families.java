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

public class Families {
    String ID;
    String Married;
    String Divorced;
    String HusbandID;
    String HusbandName;
    String WifeID;
    String WifeName;
    String[] Children;
    
    public void setAll( String addID,
                        String addMarried,
                        String addDivorced,
                        String addHusbandID,
                        String addHusbandName,
                        String addWifeID,
                        String addWifeName,
                        String[] addChildren) {
        ID = addID;
        Married = addMarried;
        Divorced = addDivorced;
        HusbandID = addHusbandID;
        HusbandName = addHusbandName;
        WifeID = addWifeID;
        WifeName = addWifeName;
        Children = addChildren;
    }
    
    public String getID() {
        return ID;
    }
    
    public String getMarried() {
        return Married;
    }
    
    public String getDivorced() {
        return Divorced;
    }
    
    public String getHusbandID() {
        return HusbandID;
    }
    
    public String getHusbandName() {
        return HusbandName;
    }
    
    public String getWifeID() {
        return WifeID;
    }
    
    public String getWifeName() {
        return WifeName;
    }
    
    public String[] getChildren() {
        return Children;
    }
}
