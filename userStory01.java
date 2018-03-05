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
