/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gedcomreader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
//import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author chenshuaishuai
 */

public class GED {
    Map<String, Individuals> individuals;
    Map<String, Families> families;

    public GED() {
        this.individuals =  new LinkedHashMap();
        this.families = new LinkedHashMap();
    }
    
    public void traversal() throws FileNotFoundException, IOException, ParseException {
        String indKey = null, famKey = null;
        File GEDfile = new File("resource/GEDCOM.ged");
        SimpleDateFormat formatter = new SimpleDateFormat ("d MMM yyyy", Locale.ENGLISH);
        String line;  
        
        try {
            Scanner sc = new Scanner(GEDfile);
            
            while(sc.hasNextLine()) {
                line = sc.nextLine().trim();
                
                if (line.startsWith("0") && line.endsWith("INDI")) {
                    indKey = line.substring(line.indexOf('@') + 1, line.lastIndexOf('@'));
                    Individuals person = new Individuals();
                    individuals.put(indKey, person);
                    individuals.get(indKey).setID(indKey);
                }
                else if (line.contains("NAME")) {
                    if (indKey != null) {
                    individuals.get(indKey).setName(line.substring(7));
                    }
                }
                else if (line.contains("SEX")) {
                    individuals.get(indKey).setGender(line.charAt(line.length() - 1));
                }
                else if (line.contains("BIRT")) {
                    if (sc.hasNextLine()) {
                        line = sc.nextLine().trim();
                        
                        if (line.startsWith("2") && line.contains("DATE")) {
                            individuals.get(indKey).setBirthday(formatter.parse(line.substring(7)));
                        }
                    }
                }
                else if (line.contains("DEAT")) {
                    individuals.get(indKey).setAlive(false);
                    
                    if (sc.hasNextLine()) {
                        line = sc.nextLine().trim();
                        
                        if (line.startsWith("2") && line.contains("DATE")) {
                            individuals.get(indKey).setDeath(formatter.parse(line.substring(7)));
                        }
                    }
                }
                else if (line.contains("FAMC")) {
                    individuals.get(indKey).setFAMC(line.substring(line.indexOf('@') + 1, line.lastIndexOf('@')));//line.substring(8).indexOf("@")));
                }
                else if (line.contains("FAMS")) {
                    individuals.get(indKey).setFAMS(line.substring(line.indexOf('@') + 1, line.lastIndexOf('@')));//line.substring(8).indexOf("@")));
                }
                else if (line.startsWith("0") && line.endsWith("FAM")) {
                    famKey = line.substring(line.indexOf('@') + 1, line.lastIndexOf('@'));//line.substring(3).indexOf("@"));
                    Families family = new Families();
                    families.put(famKey, family);
                    families.get(famKey).setID(famKey);
                }
                else if (line.contains("HUSB")) {
                    families.get(famKey).setHusbandID(line.substring(line.indexOf('@') + 1, line.lastIndexOf('@')));//line.substring(8).indexOf("@")));
                }
                else if (line.contains("WIFE")) {
                    families.get(famKey).setWifeID(line.substring(line.indexOf('@') + 1, line.lastIndexOf('@')));//line.substring(8).indexOf("@")));
                }
                else if (line.contains("CHIL")) {
                    families.get(famKey).addChildren(line.substring(line.indexOf('@') + 1, line.lastIndexOf('@')));//line.substring(8).indexOf("@")));
                }
                else if (line.contains("MARR")) {
                    if (sc.hasNextLine()) {
                        line = sc.nextLine().trim();
                        
                        if (line.startsWith("2") && line.contains("DATE")) {
                            families.get(famKey).setMarried(formatter.parse(line.substring(7)));
                        }
                    }
                }
                else if (line.contains("DIV")) {
                    if (sc.hasNextLine()) {
                        line = sc.nextLine().trim();
                        
                        if (line.startsWith("2") && line.contains("DATE")) {
                            families.get(famKey).setDivorced(formatter.parse(line.substring(7)));
                        }
                    }
                }
            }
            
            //get husband's name and wife's name by the connection between two classes
            Iterator<Map.Entry<String, Families>> famIt = families.entrySet().iterator();
            
            while (famIt.hasNext()) {
                Map.Entry<String, Families> famEnt = famIt.next();
                famEnt.getValue().setHusbandName(individuals.get(famEnt.getValue().getHusbandID()).getName());
                famEnt.getValue().setWifeName(individuals.get(famEnt.getValue().getWifeID()).getName());
            }
            
            sc.close();
        }
        catch (IOException | ParseException e) {
            System.err.println(e.toString());
        }
        
    }
    
    private static int getAgeByBirth(Date birthday) {
        int age = 0;
        
        try {
            Calendar now = Calendar.getInstance();
            now.setTime(new Date());

            Calendar birth = Calendar.getInstance();
            birth.setTime(birthday);

            if (birth.after(now)) {
                age = 0;
            } else {
                age = now.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
                if (now.get(Calendar.DAY_OF_YEAR) > birth.get(Calendar.DAY_OF_YEAR)) {
                    age += 1;
                }
            }
            return age;
        } catch (Exception e) {
            System.err.println(e.toString());
            return -1;
        }
    }
    
    public void individualsPrint() {
        String indId, nam, gend, indBirthday, age, alive, death, child, spouse;
        SimpleDateFormat indFormatNoE = new SimpleDateFormat("yyyy-MM-dd");
        File fileOut = new File("resource/towTables.txt");
        ConsoleTable tI = new ConsoleTable(9, true);
        tI.appendRow();
        tI.appendColum("ID").appendColum("Name").appendColum("Gender").appendColum("Birthday").appendColum("Age").appendColum("Alive").appendColum("Death").appendColum("Child").appendColum("Spouse");
        
        Iterator<Map.Entry<String, Individuals>> indIt = individuals.entrySet().iterator();
        
        try {
            while (indIt.hasNext()) {
                Map.Entry<String, Individuals> indEnt = indIt.next();

                indId = indEnt.getValue().getID();

                nam = indEnt.getValue().getName();

                gend = String.valueOf(indEnt.getValue().getGender());

                indBirthday = indFormatNoE.format(indEnt.getValue().getBirthday());

                age = String.valueOf(GED.getAgeByBirth(indEnt.getValue().getBirthday()));

                if (indEnt.getValue().getDeath() == null) {
                    alive = "True";
                }
                else alive = "False";

                if (indEnt.getValue().getDeath() == null) {
                    death = "NA";
                }
                else {
                    death = indFormatNoE.format(indEnt.getValue().getDeath());
                }

                if (indEnt.getValue().getFAMC() == null) {
                    child = "NA";
                }
                else {
                    child = "{'" + indEnt.getValue().getFAMC() + "'}";
                }

                if (indEnt.getValue().getFAMS() == null) {
                    spouse = "NA";
                }
                else {
                    spouse = "{'" + indEnt.getValue().getFAMS() + "'}";
                }

                tI.appendRow();
                tI.appendColum(indId).appendColum(nam).appendColum(gend).appendColum(indBirthday).appendColum(age).appendColum(alive).appendColum(death).appendColum(child).appendColum(spouse);
            }
            /*
            System.out.println("individuals:");
            System.out.println(tI.toString());*/
            
            if (!fileOut.exists()) {
                fileOut.createNewFile();
            }
            
            FileWriter fw = new FileWriter(fileOut, true);
            BufferedWriter out = new BufferedWriter(fw);
            
            out.write("individuals:\n");
            out.write(tI.toString());
            out.close();
        } catch (IOException e) {
            System.err.println(e.toString());
        }
        
    }
    
    public void familiesPrint() {
        String idF, married, divorced, husbId, husbName, wifeId, wifName, children;
        SimpleDateFormat formatNoE = new SimpleDateFormat("yyyy-MM-dd");
        File fileOut = new File("resource/towTables.txt");
        ConsoleTable tF = new ConsoleTable(8, true);
        
        tF.appendRow();
        tF.appendColum("ID").appendColum("Married").appendColum("Divorced").appendColum("Husband ID").appendColum("Husband Name").appendColum("Wife ID").appendColum("Wife Name").appendColum("Children");
        
        Iterator<Map.Entry<String, Families>> famIt = families.entrySet().iterator();
        try {
            while (famIt.hasNext()) {
                Map.Entry<String, Families> famEnt = famIt.next();

                idF = famEnt.getValue().getID();

                married = formatNoE.format(famEnt.getValue().getMarried());

                if (famEnt.getValue().getDivorced() == null) {
                    divorced = "NA";
                }
                else {
                    divorced = formatNoE.format(famEnt.getValue().getDivorced());
                }

                husbId = famEnt.getValue().getHusbandID();

                husbName = famEnt.getValue().getHusbandName();

                wifeId = famEnt.getValue().getWifeID();

                wifName = famEnt.getValue().getWifeName();

                children = "{}";

                if (famEnt.getValue().getChildren().isEmpty()) {
                    children = "NA";
                }
                else {
                    Iterator<String> it = famEnt.getValue().getChildren().iterator();
                    StringBuilder sb = new StringBuilder(children);
                    while (it.hasNext()) {
                        String str = it.next();
                        sb.insert(sb.length() - 1, "'" + str + "',");
                    }

                    if (sb.toString().endsWith(",}")) {
                        sb.replace(sb.toString().length() - 2, sb.toString().length() - 1, "");
                    }

                    children = sb.toString();
                }
                tF.appendRow();
                tF.appendColum(idF).appendColum(married).appendColum(divorced).appendColum(husbId).appendColum(husbName).appendColum(wifeId).appendColum(wifName).appendColum(children);
            }
            /*
            System.out.println("families:");
            System.out.println(tF.toString());*/
            
            if (!fileOut.exists()) {
                fileOut.createNewFile();
            }
            
            FileWriter fw = new FileWriter(fileOut, true);
            BufferedWriter out = new BufferedWriter(fw);
            
            out.write("families:\n");
            out.write(tF.toString());
            out.close();
        } catch (IOException e) {
            System.err.println(e.toString());
        }
        
    }
}
