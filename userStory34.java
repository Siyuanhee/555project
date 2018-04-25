private void listLargeAgeDifferences() { //US34
        Iterator<Map.Entry<String,Family>> famIt = families.entrySet().iterator();
        Set<String> ageList = new LinkedHashSet<>();
        int hAge;
        int wAge;

        try {
            while (famIt.hasNext()) {
                Map.Entry<String, Family> famEnt = famIt.next();
                hAge = GED.getAgeByBirthAndDeath(individuals.get(famEnt.getValue().getHusbandID()).getBirthday(),famEnt.getValue().getMarried());
                wAge = GED.getAgeByBirthAndDeath(individuals.get(famEnt.getValue().getWifeID()).getBirthday(),famEnt.getValue().getMarried());
                if (hAge >= wAge * 2 || wAge >= hAge * 2){
                    errors.add("Family "+famEnt.getValue().getID()+" has a large age difference.");
                }
            }
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }
