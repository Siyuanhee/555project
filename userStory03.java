private void birthBeforeDeath() { //US03
        Iterator<Map.Entry<String, Individual>> indIt = individuals.entrySet().iterator();

        try {
            while (indIt.hasNext()) {
                Map.Entry<String, Individual> indEnt = indIt.next();

                if (indEnt.getValue().getBirthday() == null) {

                }
                else if (indEnt.getValue().getDeath() == null) {

                }
                else if (!indEnt.getValue().getBirthday().before(indEnt.getValue().getDeath()))
                    errors.add("Error US03: Birth date of " + indEnt.getValue().getName() + "(" + indEnt.getValue().getID() + ")" + " occurs after death date.");
                
        }
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }