private void lessThan150() { //US07
        Date now = new Date();
        Iterator<Map.Entry<String, Individual>> indIt = individuals.entrySet().iterator();

        try {
            while(indIt.hasNext()){
                Map.Entry<String, Individual> indEnt = indIt.next();
                Iterator<String> spIt = indEnt.getValue().getFAMS().iterator();
                int age;
                if (indEnt.getValue().getDeath() == null){
                    age = GED.getAgeByBirthAndDeath(indEnt.getValue().getBirthday(), now);
                }
                else{
                    age = GED.getAgeByBirthAndDeath(indEnt.getValue().getBirthday(),indEnt.getValue().getDeath());
                }
                if (age > 150){
                    errors.add("Error: US07: age of" + indEnt.getValue().getName() + "is greater than 150.");
                }
            }
        }catch (Exception e) {
            System.out.println(e.toString());
        }
    }
