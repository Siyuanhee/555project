 private void correctGender() { //US21
        Iterator<Map.Entry<String, Family>> famIt = families.entrySet().iterator();

        try {
            while (famIt.hasNext()) {
                Map.Entry<String, Family> famEnt = famIt.next();
                if (individuals.get(famEnt.getValue().getHusbandID()).getGender() == 'F') {
                    errors.add("US21 Error: Husband in family "+famEnt.getValue().getID()+ " has a wrong gender.");
                }
                if (individuals.get(famEnt.getValue().getWifeID()).getGender() == 'M') {
                    errors.add("US21 Error: Wife in family "+famEnt.getValue().getID()+" has a wrong gender.");
                }
            }
        }catch (Exception e) {
            System.out.println(e.toString());
        }
    }
