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
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author chenshuaishuai
 */

public class GED {
    Map<String, Individual> individuals;
    Map<String, Family> families;
    Set<String> errors;

    public GED() {
        this.individuals =  new LinkedHashMap();
        this.families = new LinkedHashMap();
        this.errors = new LinkedHashSet();
    }
    
    private void checkErrors () {
        datesBeforeCurrentDate ();//US01
        birthBeforeMarriage ();//US02
    }
    
    public void traversal() throws FileNotFoundException, IOException, ParseException {
        String indKey = null, famKey = null;//用两个key控制扫描与记录的步骤
        File GEDfile = new File("resource/GEDCOM.ged");
        SimpleDateFormat formatter = new SimpleDateFormat ("d MMM yyyy", Locale.ENGLISH);//根据预设格式解析字符串，以转换成时间类
        String line;  
        
        try {
            Scanner sc = new Scanner(GEDfile);
            
            while(sc.hasNextLine()) {
                line = sc.nextLine().trim();
                
                if (line.startsWith("0") && line.endsWith("INDI")) {// 只有在0 level和INDI标签下，才会更换个人key和创建一个新人
                    indKey = line.substring(line.indexOf('@') + 1, line.lastIndexOf('@'));
                    Individual person = new Individual();
                    individuals.put(indKey, person);
                    individuals.get(indKey).setID(indKey);
                }
                else if (line.contains("NAME")) {
                    if (indKey != null) {
                    individuals.get(indKey).setName(line.substring(7));//因为去掉前后空格之后，固定从7号位开始是名字，不用split的一个原因是name存在空格，处理会多两句话
                    }
                }
                else if (line.contains("SEX")) {
                    individuals.get(indKey).setGender(line.charAt(line.length() - 1));
                }
                else if (line.contains("BIRT")) {
                    if (sc.hasNextLine()) {
                        line = sc.nextLine().trim();
                        
                        if (line.startsWith("2") && line.contains("DATE")) {//没写异常，所以会出现信息不合法，那么这个属性不过为空，一个方案是自己写异常，另一个方案是个人这个类增加一个属性记录个人信息是否合法
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
                    individuals.get(indKey).addFAMC(line.substring(line.indexOf('@') + 1, line.lastIndexOf('@')));
                }
                else if (line.contains("FAMS")) {
                    individuals.get(indKey).addFAMS(line.substring(line.indexOf('@') + 1, line.lastIndexOf('@')));
                }
                else if (line.startsWith("0") && line.endsWith("FAM")) {
                    famKey = line.substring(line.indexOf('@') + 1, line.lastIndexOf('@'));
                    Family family = new Family();
                    families.put(famKey, family);
                    families.get(famKey).setID(famKey);
                }
                else if (line.contains("HUSB")) {
                    families.get(famKey).setHusbandID(line.substring(line.indexOf('@') + 1, line.lastIndexOf('@')));
                }
                else if (line.contains("WIFE")) {
                    families.get(famKey).setWifeID(line.substring(line.indexOf('@') + 1, line.lastIndexOf('@')));
                }
                else if (line.contains("CHIL")) {
                    families.get(famKey).addChildren(line.substring(line.indexOf('@') + 1, line.lastIndexOf('@')));
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
            Iterator<Map.Entry<String, Family>> famIt = families.entrySet().iterator();//声明一个迭代器，等于这个集合入口的迭代器，用以遍历
            
            while (famIt.hasNext()) {//整个集合跑一遍，用来给丈夫和妻子的名字赋值，因为GEDCOM的家庭只记录id，没有名字，所以需要另外去个人信息调出来
                Map.Entry<String, Family> famEnt = famIt.next();
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
                if (now.get(Calendar.DAY_OF_YEAR) < birth.get(Calendar.DAY_OF_YEAR)) {
                    age -= 1;
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
        
        Iterator<Map.Entry<String, Individual>> indIt = individuals.entrySet().iterator();
        
        try {
            while (indIt.hasNext()) {
                Map.Entry<String, Individual> indEnt = indIt.next();

                indId = indEnt.getValue().getID();

                nam = indEnt.getValue().getName();

                gend = String.valueOf(indEnt.getValue().getGender());

                indBirthday = indFormatNoE.format(indEnt.getValue().getBirthday());

                age = String.valueOf(GED.getAgeByBirth(indEnt.getValue().getBirthday()));
                
                child = "{}";
                spouse = "{}";

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

                if (indEnt.getValue().getFAMC().isEmpty()) {
                    child = "NA";
                }
                else {///////////
                    Iterator<String> it = indEnt.getValue().getFAMC().iterator();
                    StringBuilder sb1 = new StringBuilder(child);
                    
                    while (it.hasNext()) {
                        String str = it.next();
                        sb1.insert(sb1.length() - 1, "'" + str + "',");
                    }

                    if (sb1.toString().endsWith(",}")) {
                        sb1.replace(sb1.toString().length() - 2, sb1.toString().length() - 1, "");
                    }

                    child = sb1.toString();
                }

                if (indEnt.getValue().getFAMS().isEmpty()) {
                    spouse = "NA";
                }
                else {
                    Iterator<String> it = indEnt.getValue().getFAMS().iterator();
                    StringBuilder sb2 = new StringBuilder(spouse);
                    
                    while (it.hasNext()) {
                        String str = it.next();
                        sb2.insert(sb2.length() - 1, "'" + str + "',");
                    }

                    if (sb2.toString().endsWith(",}")) {
                        sb2.replace(sb2.toString().length() - 2, sb2.toString().length() - 1, "");
                    }

                    spouse = sb2.toString();
                }

                tI.appendRow();
                tI.appendColum(indId).appendColum(nam).appendColum(gend).appendColum(indBirthday).appendColum(age).appendColum(alive).appendColum(death).appendColum(child).appendColum(spouse);
            }
            /*
            System.out.println("Individuals:");
            System.out.println(tI.toString());*/
            
            if (!fileOut.exists()) {
                fileOut.createNewFile();
            }
            
            FileWriter fw = new FileWriter(fileOut, true);
            BufferedWriter out = new BufferedWriter(fw);
            
            out.write("Individuals:\n");
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
        
        Iterator<Map.Entry<String, Family>> famIt = families.entrySet().iterator();
        
        try {
            while (famIt.hasNext()) {
                Map.Entry<String, Family> famEnt = famIt.next();

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
                    StringBuilder sb3 = new StringBuilder(children);
                    while (it.hasNext()) {
                        String str = it.next();
                        sb3.insert(sb3.length() - 1, "'" + str + "',");
                    }

                    if (sb3.toString().endsWith(",}")) {
                        sb3.replace(sb3.toString().length() - 2, sb3.toString().length() - 1, "");
                    }

                    children = sb3.toString();
                }
                tF.appendRow();
                tF.appendColum(idF).appendColum(married).appendColum(divorced).appendColum(husbId).appendColum(husbName).appendColum(wifeId).appendColum(wifName).appendColum(children);
            }
            /*
            System.out.println("Families:");
            System.out.println(tF.toString());*/
            
            if (!fileOut.exists()) {
                fileOut.createNewFile();
            }
            
            FileWriter fw = new FileWriter(fileOut, true);
            BufferedWriter out = new BufferedWriter(fw);
            
            out.write("Families:\n");
            out.write(tF.toString());
            out.close();
        } catch (IOException e) {
            System.err.println(e.toString());
        }
        
    }
    
    private void datesBeforeCurrentDate () {//US01
        Date now = new Date();
        Iterator<Map.Entry<String, Individual>> indIt = individuals.entrySet().iterator();
        Iterator<Map.Entry<String, Family>> famIt = families.entrySet().iterator();
        
        try {
            while (indIt.hasNext()) {
                Map.Entry<String, Individual> indEnt = indIt.next();
                
                if (indEnt.getValue().getBirthday() == null) {
                }
                else if (indEnt.getValue().getBirthday().after(now)) 
                     errors.add("Error US01: Birthday of " + indEnt.getValue().getName() + "(" + indEnt.getValue().getID() + ") occurrs after the current date");
                
                if (indEnt.getValue().getDeath() == null) {
                }
                else if (indEnt.getValue().getDeath().after(now))
                    errors.add("Error US01: Death day of " + indEnt.getValue().getName() + "(" + indEnt.getValue().getID() + ") occurrs after the current date");
            }
            
            while (famIt.hasNext()) {
                Map.Entry<String, Family> famEnt = famIt.next();
                
                if (famEnt.getValue().getMarried() == null) {
                }
                else if (famEnt.getValue().getMarried().after(now))
                    errors.add("Error US01: Married day of " + famEnt.getValue().getHusbandName() + " and " + famEnt.getValue().getWifeName() + "(family:" + famEnt.getValue().getID() +") occurrs after the current date");
                
                if (famEnt.getValue().getDivorced() == null) {
                }
                else if (famEnt.getValue().getDivorced().after(now))
                    errors.add("Error US01: Divorced day of " + famEnt.getValue().getHusbandName() + " and " + famEnt.getValue().getWifeName() + "(family:" + famEnt.getValue().getID() +") occurrs after the current date");
            }
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }
    
    private void birthBeforeMarriage () {//US02
        Iterator<Map.Entry<String, Individual>> indIt = individuals.entrySet().iterator();
        
        try {
            while (indIt.hasNext()) {
                Map.Entry<String, Individual> indEnt = indIt.next();
                Iterator<String> spIt = indEnt.getValue().getFAMS().iterator();
                
                while (spIt.hasNext()) {
                    String str = spIt.next();
                    
                    if (families.get(str).getMarried() == null) {
                    }
                    else if (families.get(str).getMarried().before(indEnt.getValue().getBirthday()))
                        errors.add("Error US02: Marriaged date of " + indEnt.getValue().getName() + "(" + indEnt.getValue().getID() + ") in the family of " + families.get(str).getID() + " is before the birthday");
                }
            }
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }
    
    /**
     *check errors by user stories, and then print all errors
     *checking must be before print
     */
    public void errorsPrint () {
        checkErrors();//check with users stories
        File fileOut = new File("resource/towTables.txt");
        Iterator<String> errIt = errors.iterator();
        
        try {
            if (!fileOut.exists()) {
                fileOut.createNewFile();
            }
            
            FileWriter fw = new FileWriter(fileOut, true);
            BufferedWriter out = new BufferedWriter(fw);
            
            out.write("Errors:\n");
            
            
            while (errIt.hasNext()) {
                String errStr = errIt.next();
                out.write(errStr + "\n");
            }
            
            out.close();
        } catch (IOException e) {
            System.err.println(e.toString());
        }
    }
}
