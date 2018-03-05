private void marriageBeforeDeath() { //US05
        Iterator<Map.Entry<String, Individual>> indIt = individuals.entrySet().iterator();

        try {
            while (indIt.hasNext()) {
                Map.Entry<String, Individual> indEnt = indIt.next();
                Iterator<String> spIt = indEnt.getValue().getFAMS().iterator();

                while (spIt.hasNext()) {
                    String str = spIt.next();

                    if (families.get(str).getMarried() == null) {

                    }
                    else if (indEnt.getValue().getDeath() == null) {

                    }
                    else if (families.get(str).getMarried().after(indEnt.getValue().getDeath()))
                        errors.add("Error US05: Marriaged date of " + indEnt.getValue().getName() + "(" + indEnt.getValue().getID() + ") in the family of " + families.get(str).getID() + " is after the death date.");
                }
            }
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }
