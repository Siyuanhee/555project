
private void fewerThan15Siblings() { //US15
	Iterator<Map.Entry<String, Family>> famIt = families.entrySet().iterator();

    try {
        while (famIt.hasNext()) {
            Map.Entry<String, Family> famEnt = famIt.next();
            Iterator<String> chIt = famEnt.getValue().getChildren().iterator();
            int count = 0;
            
            while (chIt.hasNext()) {
            	String str = chIt.next();
            	if (famEnt.getValue().getChildren() == null) {
                }else{
                	count += 1; 
                }
            } 
            //System.out.print(famEnt.getValue().getID()+" "  + count +" ");
            if(count >= 15)
            	errors.add("Error US15: Family (" + famEnt.getValue().getID() + ") " + "have 15 or more siblings");
        }
    } catch (Exception e) {
        System.err.println(e.toString());
    }
}
