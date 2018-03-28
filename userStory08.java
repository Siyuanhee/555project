private void birthBeforeMarriageOfParents() { //US08
        Iterator<Map.Entry<String, Individual>> indIt = individuals.entrySet().iterator();
        Calendar birth = Calendar.getInstance();
        Calendar divorced = Calendar.getInstance();

        try {
            while (indIt.hasNext()) {
                Map.Entry<String, Individual> indEnt = indIt.next();
                Iterator<String> famcIt = indEnt.getValue().getFAMC().iterator();

                while (famcIt.hasNext()) {
                    String str = famcIt.next();
                    birth.setTime(indEnt.getValue().getBirthday());
                    divorced.setTime(families.get(str).getMarried());

                    if (families.get(str).getMarried() == null) {

                    }
                    else if (indEnt.getValue().getBirthday() == null) {

                    }else if (families.get(str).getDivorced() == null) {

                    }
                    else if (families.get(str).getMarried().after(indEnt.getValue().getBirthday()))
                        errors.add("Error US08: Birthday of " + indEnt.getValue().getName() + "(" + indEnt.getValue().getID() + ") in the family of " + families.get(str).getID() + " is before the marriage of parents.");
                    else if (((birth.get(Calendar.YEAR) - divorced.get(Calendar.YEAR))*12 + (birth.get(Calendar.MONTH) - divorced.get(Calendar.MONTH)) > 9))
                        errors.add("Error US08: Birthday of " + indEnt.getValue().getName() + "(" + indEnt.getValue().getID() + ") in the family of " + families.get(str).getID() + " is more than 9 months after parents divorce.");
                }
            }
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }