import java.io.*;
import java.util.*;
public class GedcomReader {

	public static void main(String[] args) throws Exception {
		GED ged = new GED();
		ged.readGedcom("Family-2-29-Jan-2018-163.ged","output.txt");
		ged.display("output.txt", "FinalOutput.txt");
	}
}
	
class Individuals{
	
	String ID;
	String Name;
	char Gender;
	String Birthday;
	int Age;
	boolean Alive;
	String Death;
	String[] Child;
	String[] Spouse;
	
	Individuals(){
		ID = "";
		Name = "";
		Gender = ' ';
		Birthday = "";
		Age = 1;
		Alive = true;
		Death = "N/A";
		Child = new String[5];
		Child[0] = "N/A";
		Spouse = new String[5];
		Spouse[0] = "N/A";
	}
	
	Individuals(String newID,String newName,char newGender,
			String newBirthday,int newAge,boolean newAlive,String newDeath,String newChild[],String newSpouse[]){
		ID = newID;
		Name = newName;
		Gender = newGender;
		Birthday = newBirthday;
		Age = newAge;
		Alive = newAlive;
		Death = newDeath;
		Child = newChild;
		Spouse = newSpouse;
	}
	
	String getID(){
		return ID;
	}
	
	void setID(String newID){
		ID = newID;
	}
	
	String getName(){
		return Name;
	}
	
	void setName(String newName){
		Name = newName;
	}
	
	char getGender(){
		return Gender;
	}
	
	void setGender(char newGender){
		Gender = newGender;
	}
	
	String getBirthday(){
		return Birthday;
	}
	
	void setBirthday(String newBirthday){
		Birthday = newBirthday;
	}
	
	int getAge(){
		return Age;
	}
	
	void setAge(int newAge){
		Age = newAge;
	}
	
	boolean getAlive(){
		return Alive;
	}
	
	void setAlive(boolean newAlive){
		Alive = newAlive;
	}
	
	String getDeath(){
		return Death;
	}
	void setDeath(String newDeath){
		Death = newDeath;
	}
	
	String[] getChild(){
		return Child;
	}
	
	void setChild(String[] newChild){
		Child = newChild;
	}
	
	String[] getSpouse(){
		return Spouse;
	}
	
	void setSpouse(String[] newSpouse){
		Spouse = newSpouse;
	}
	
	void reset(){
		ID = "";
		Name = "";
		Gender = ' ';
		Birthday = "";
		Age = 1;
		Alive = true;
		Death = "N/A";
		Child = new String[5];
		Child[0] = "N/A";
		Spouse = new String[5];
		Spouse[0] = "N/A";
	}
}

class Families{
	String ID;
	String Married;
	String Divorced;
	String HusbandID;
	String HusbandName;
	String WifeID;
	String WifeName;
	String[] Children;
	
	Families(){
		ID = "";
		Married = "";
		Divorced = "N/A";
		HusbandID = "";
		HusbandName = "";
		WifeID = "";
		WifeName = "";
		Children = new String[20];
	}
	
	Families(String NewID,String NewMarried,String NewDivorced,String NewHusbandID
			,String NewHusbanName,String NewWifeID,String NewWifeName,String NewChildren[]){
		
		ID = NewID;
		Married = NewMarried;
		Divorced = NewDivorced;
		HusbandID = NewHusbandID;
		HusbandName = NewHusbanName;
		WifeID = NewWifeID;
		WifeName = NewWifeName;
		Children = NewChildren;
	}
	
	String getID(){
		return ID;
	}
	
	void setID(String newID){
		ID = newID;
	}
	
	String getMarried(){
		return Married;
	}
	
	void setMarried(String newMarried){
		Married = newMarried;
	}
	
	String getDivorced(){
		return Divorced;
	}
	
	void setDivorced(String newDivorced){
		Divorced = newDivorced;
	}
	
	String getHusbandID(){
		return HusbandID;
	}
	
	void setHusbandID(String newHusbandID){
		HusbandID = newHusbandID;
	}
	
	String getHusbandName(){
		return HusbandName;
	}
	
	void setHusbandName(String newHusbandName){
		HusbandName = newHusbandName;
	}
	
	String getWifeID(){
		return WifeID;
	}
	
	void setWifeID(String newWifeID){
		WifeID = newWifeID;
	}
	
	String getWifeName(){
		return WifeName;
	}
	
	void setWifeName(String newWifeName){
		WifeName = newWifeName;
	}
	
	String[] getChildren(){
		return Children;
	}
	
	void setChildren(String newChildren[]){
		Children = newChildren;
	}
	
	void reset(){
		ID = "";
		Married = "";
		Divorced = "N/A";
		HusbandID = "";
		HusbandName = "";
		WifeID = "";
		WifeName = "";
		Children = new String[20];
	}
	
}

class GED{
	
	void readGedcom(String inputFile, String outputFile)throws Exception{
		java.io.File sourceFile = new java.io.File(inputFile);
		if (!sourceFile.exists()){
			System.out.println("Sources file does not exist");
			System.exit(1);
		}
		
		java.io.File targetFile = new java.io.File(outputFile);
	
		try(
				Scanner input = new Scanner(sourceFile);
				java.io.PrintWriter output = new java.io.PrintWriter(targetFile);
		){
			while(input.hasNext()){
				
				int level = input.nextInt();
				String tag = input.next();
				String arguments = input.nextLine().trim();
				String valid = "";
				
				if(arguments.equals("FAM")||arguments.equals("INDI")){
					String S1="";
					S1 = tag;
					tag = arguments;
					arguments = S1;
				}
				
				if(tag.equals("INDI")||(tag.equals("NAME") && level == 1)||tag.equals("SEX")||tag.equals("BIRT")||
					tag.equals("DEAT")||tag.equals("FAMC")||tag.equals("FAMS")||tag.equals("FAM")||
					tag.equals("MARR")||tag.equals("HUSB")||tag.equals("WIFE")||tag.equals("CHIL")||
					tag.equals("DIV")||(tag.equals("DATE") && level == 2)||tag.equals("HEAD")||tag.equals("TRLR")||
					tag.equals("NOTE")){
					valid = "Y";
				}else{
					valid = "N";
				}
				
				output.println(level + " " + tag + " " + valid + " " + arguments);
			}		
		}
	}
	
	void display(String inputFile,String outputFile)throws Exception{

		java.io.File sourceFile = new java.io.File(inputFile);
		int [] allLevels = new int[6000];
		String[] allTags = new String[6000];
		String[] allValid = new String[6000];
		String[] allArguments = new String[6000];
		Individuals I = new Individuals();
		Families F = new Families();
		
		boolean a = true;
		boolean b = true;
		
		int i = 0;
		int j = 0;
		int k = 0;

		String tempSpouse ="";
		String tempChild ="";
		String tempChildren="";
		
		if (!sourceFile.exists()){
			System.out.println("Sources file does not exist");
			System.exit(1);
		}
		
		java.io.File targetFile = new java.io.File(outputFile);
		if (targetFile.exists()){
			System.out.println("Target file already exists");
			System.exit(2);
		}
		
		try(
				Scanner input = new Scanner(sourceFile);
		){
			while(input.hasNext()){
				
				int level = input.nextInt();
				String tag = input.next();
				String valid = input.next();
				String arguments = input.nextLine().trim();
				if(!valid.equals("N")){
					allLevels[i] = level;
					allTags[i] = tag;
					allValid[i] = valid;
					allArguments[i] = arguments;
					i++;
				}
			}		
		}
		try(
				java.io.PrintWriter output = new java.io.PrintWriter(targetFile);
				Scanner input = new Scanner(sourceFile);
				
		){
			while(k<allLevels.length){
				
				if(a){
					output.println("Individuals");
					output.println("+-----+------------------+------+---------------+-----+-------+---------------+---------------+---------------+");
					output.printf("|%-5s|%-18s|%-6s|%-15s|%-5s|%-7s|%-15s|%-15s|%-15s|","ID","Name","Gender","Birthday","Age","Alive","Death","Child","Spouse");
					output.println("");
					output.println("+-----+------------------+------+---------------+-----+-------+---------------+---------------+---------------+");
					a = false;
				}
				
				if(b && allLevels[k] == 0 && allTags[k].equals("FAM")){
					output.println("+-----+------------------+------+---------------+-----+-------+---------------+---------------+---------------+");
					output.println("Families");
					output.println("+-----+---------------+---------------+------------+------------------+---------+------------------+---------------+");
					output.printf("|%-5s|%-15s|%-15s|%-12s|%-18s|%-9s|%-18s|%-15s|","ID","Married","Divorced","Husband ID","Husband Name","Wife ID","Wife Name","Children");
					output.println("");
					output.println("+-----+---------------+---------------+------------+------------------+---------+------------------+---------------+");
					b = false;
				}
				
				if(allLevels[k] == 0 && allTags[k].equals("INDI")){
					
					I.setID(allArguments[k]);
					tempChild = "";
					tempSpouse = "";
				}
	
				if(allLevels[k] == 1 && allTags[k].equals("NAME")){
					I.setName(allArguments[k]);
				}
				
				if(allLevels[k] == 1 && allTags[k].equals("SEX")){
					I.setGender(allArguments[k].charAt(0));
				}
				
				if(allLevels[k] == 2 && allTags[k].equals("DATE")){
					if(allLevels[k-1] == 1 && allTags[k-1].equals("BIRT")){
						I.setBirthday(allArguments[k]);
					}
					if(allLevels[k-1] == 1 && allTags[k-1].equals("DEAT")){
						I.setDeath(allArguments[k]);
						I.setAlive(false);
				}
			}
				
				if(allLevels[k] == 1 && allTags[k].equals("FAMC")){
	
					tempChild += allArguments[k]+" ";
					I.setChild(tempChild.split(" "));
					if(allLevels[k+1] == 0 ){
						String id = replaceSymbol(I.getID());
						String name = I.getName();
						char gender = I.getGender();
						String birthday = adjustDate(I.getBirthday());
						String death = adjustDate(I.getDeath());
						Boolean alive = I.getAlive();
						int age = 0;
						if(alive){
							String[] year = I.getBirthday().split(" ");
							age = 2018 - Integer.valueOf(year[2]);
						}else if(!alive){
							String[] yearB = I.getBirthday().split(" ");
							String[] yearD = I.getDeath().split(" ");
							age = Integer.valueOf(yearD[2]) - Integer.valueOf(yearB[2]);
						}
						
						String[] child = I.getChild();
						String[] spouse = I.getSpouse();
						String outputChild ="";
						String outputSpouse ="";
						
						for(int n = 0; n<spouse.length; n++){
							if(spouse[n]!= null)
								outputSpouse += spouse[n]+ " ";
						}

						for(int n = 0; n<child.length; n++){
							if(child[n]!= null)
								outputChild += child[n]+ " ";
						}
						
						outputChild = replaceSymbol(outputChild);
						outputSpouse = replaceSymbol(outputSpouse);
						
						output.printf("|%-5s|%-18s|%-6c|%-15s|%-5d|%-7b|%-15s|%-15s|%-15s|",id,name,gender,birthday,age,alive,death,outputChild,outputSpouse);
						output.println("");
						I.reset();
					}
				}
				
				if(allLevels[k] == 1 && allTags[k].equals("FAMS")){
					
					tempSpouse += allArguments[k]+" ";
					I.setSpouse(tempSpouse.split(" "));
					if(allLevels[k+1] == 0 ){
						String id = replaceSymbol(I.getID());
						String name = I.getName();
						char gender = I.getGender();
						String birthday = adjustDate(I.getBirthday());
						String death = adjustDate(I.getDeath());
						Boolean alive = I.getAlive();
						int age = 0;
						String systemYear = getSystemYear();
						if(alive){
							String[] year = birthday.split(" ");
							age = Integer.valueOf(systemYear) - Integer.valueOf(year[0]);
						}else if(!alive){
							String[] yearB = birthday.split(" ");
							String[] yearD = death.split(" ");
							age = Integer.valueOf(yearD[0]) - Integer.valueOf(yearB[0]);
						}
						String[] child = I.getChild();
						String[] spouse = I.getSpouse();
						String outputChild ="";
						String outputSpouse ="";
						
						for(int n = 0; n<spouse.length; n++){
							if(spouse[n]!= null)
								outputSpouse += spouse[n]+ " ";
						}

						for(int n = 0; n<child.length; n++){
							if(child[n]!= null)
								outputChild += child[n]+ " ";
						}
						
						outputChild = replaceSymbol(outputChild);
						outputSpouse = replaceSymbol(outputSpouse);
						
						output.printf("|%-5s|%-18s|%-6c|%-15s|%-5d|%-7b|%-15s|%-15s|%-15s|",id,name,gender,birthday,age,alive,death,outputChild,outputSpouse);
						output.println("");
						
						I.reset();
					}
				}
				
				if(allLevels[k] == 0 && allTags[k].equals("FAM")){
					
					F.setID(allArguments[k]);
					tempChildren = "";

				}
				
				if(allLevels[k] == 1 && allTags[k].equals("WIFE")){
					F.setWifeID(allArguments[k]);
					for(j=0;j<allArguments.length;j++){
						if(F.WifeID.equals(allArguments[j]) && allTags[j].equals("INDI") )
							F.setWifeName(allArguments[j+1]);
					}
				}
				
	
				if(allLevels[k] == 1 && allTags[k].equals("HUSB")){
					F.setHusbandID(allArguments[k]);
					for(j=0;j<allArguments.length;j++){
						if(F.HusbandID.equals(allArguments[j]) && allTags[j].equals("INDI") ){
							F.setHusbandName(allArguments[j+1]);}
					}
				}
				
				
				if(allLevels[k] == 1 && allTags[k].equals("CHIL")){
					
					tempChildren += allArguments[k]+" ";
					F.setChildren(tempChildren.split(" "));
				}
				
				if(allLevels[k] == 2 && allTags[k].equals("DATE")){
					if(allLevels[k-1] == 1 && allTags[k-1].equals("MARR")){
						F.setMarried(allArguments[k]);
					}
					if(allLevels[k-1] == 1 && allTags[k-1].equals("DIV")){
						F.setDivorced(allArguments[k]);
					}
				}
				if(allLevels[k+1] == 0 && allLevels[k] == 2){
					String ID = replaceSymbol(F.getID());
					String Married = adjustDate(F.getMarried());
					String Divorced = adjustDate(F.getDivorced());
					String HusbandID = replaceSymbol(F.getHusbandID());
					String HusbandName = F.getHusbandName();
					String WifeID = replaceSymbol(F.getWifeID());
					String WifeName = F.getWifeName();
					String[] Children = F.getChildren();
					String outputChildren = "";
					for(int n = 0; n<Children.length; n++){
						if(Children[n]!= null){
							outputChildren += Children[n] + " ";
						}
					}
					
					outputChildren = replaceSymbol(outputChildren);

					output.printf("|%-5s|%-15s|%-15s|%-12s|%-18s|%-9s|%-18s|%-15s|",ID,Married,Divorced,HusbandID,HusbandName,WifeID,WifeName,outputChildren);
					output.println("");
					F.reset();
				}

				if(allLevels[k] == 0 && allTags[k].equals("TRLR") ){
					output.println("+-----+---------------+---------------+------------+------------------+---------+------------------+---------------+");
				}
				k++;
			}
		}
	}
	public static String adjustDate(String rowDate){
		if(rowDate != "N/A"){
			String output = " ";
			rowDate = rowDate.replaceAll("JAN","01");
			rowDate = rowDate.replaceAll("FEB","02");
			rowDate = rowDate.replaceAll("MAR","03");
			rowDate = rowDate.replaceAll("APR","04");
			rowDate = rowDate.replaceAll("MAY","05");
			rowDate = rowDate.replaceAll("JUN","06");
			rowDate = rowDate.replaceAll("JUL","07");
			rowDate = rowDate.replaceAll("AUG","08");
			rowDate = rowDate.replaceAll("SEP","09");
			rowDate = rowDate.replaceAll("OCT","10");
			rowDate = rowDate.replaceAll("NOV","11");
			rowDate = rowDate.replaceAll("DEC","12");
			String[] temp = rowDate.split(" ");
			output = temp[2] + " " + temp[1] + " " +temp[0];
			return output;
		}
		return rowDate;
	}
	
	public static String replaceSymbol(String input){
		input = input.replaceAll("@", "");
		return input;
	}
	
	public static String getSystemYear() {
        Calendar date = Calendar.getInstance();
        String year = String.valueOf(date.get(Calendar.YEAR));
        return year;
	}
}
