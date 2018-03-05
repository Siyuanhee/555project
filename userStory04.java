private void marriageBeforeDivorce() { //US04
    	Iterator<Map.Entry<String, Family>> famIt = families.entrySet().iterator();

        try {
            while (famIt.hasNext()) {
                Map.Entry<String, Family> famEnt = famIt.next();

                if (famEnt.getValue().getMarried() == null) {

                }
                else if (famEnt.getValue().getDivorced() == null) {

                }
                else if (!famEnt.getValue().getMarried().before(famEnt.getValue().getDivorced()))
                    errors.add("Error US04: Family" + "(" +famEnt.getValue().getID() + ")" + " Husband: " 
                               + famEnt.getValue().getHusbandName() + "(" + famEnt.getValue().getHusbandID() + ")" + " Wife: " 
                               + famEnt.getValue().getWifeName() + "(" + famEnt.getValue().getWifeID() + ")" + " married date occurs after divorced.");
                
        }
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }