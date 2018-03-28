private void parentsNotTooOld() { //US12
        Iterator<Map.Entry<String, Family>> famIt = families.entrySet().iterator();
        Calendar husbandBirth = Calendar.getInstance();
        Calendar wifeBirth = Calendar.getInstance();
        Calendar childBirth = Calendar.getInstance();

        try {
            while (famIt.hasNext()) {
                Map.Entry<String, Family> famEnt = famIt.next();
                Iterator<String> chIt = famEnt.getValue().getChildren().iterator();

                while (chIt.hasNext()) {
                    String str = chIt.next();
                    husbandBirth.setTime(individuals.get(famEnt.getValue().getHusbandID()).getBirthday());
                    wifeBirth.setTime(individuals.get(famEnt.getValue().getWifeID()).getBirthday());
                    childBirth.setTime(individuals.get(str).getBirthday());

                    if (famEnt.getValue().getHusbandID() == null) {
                    }
                    else if(childBirth.get(Calendar.YEAR) - husbandBirth.get(Calendar.YEAR) >= 80 ){
                    	errors.add("Error US12: Age of father " + famEnt.getValue().getHusbandName() + "(" + famEnt.getValue().getHusbandID() + ") is more than 80 years older than his children " + individuals.get(str).getName() + "(" + individuals.get(str).getID() + ").");
                    }
                    
                    if (famEnt.getValue().getWifeID() == null) {
                    }else if(childBirth.get(Calendar.YEAR) - wifeBirth.get(Calendar.YEAR) >= 60){
                    	errors.add("Error US12: Age of mother " + famEnt.getValue().getWifeName() + "(" + famEnt.getValue().getWifeID() + ") is more than 60 years older than her children " + individuals.get(str).getName() + "(" + individuals.get(str).getID() + ").");
                    }
                }
            }
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }