private void MarriageAfter14() {//US10
        try {
            for (Map.Entry<String, Family> entry: families.entrySet()) {
                if (getAgeByBirth(individuals.get(entry.getValue().getHusbandID()).getBirthday()) < 14) {
                    errors.add("Error US10: " + individuals.get(entry.getValue().getHusbandID()).getName() + "（" + entry.getValue().getHusbandID() + ")" + " is less than 14, he illegally married, in the family of " + entry.getValue().getID());
                }
                else if (getAgeByBirth(individuals.get(entry.getValue().getWifeID()).getBirthday()) < 14) {
                    errors.add("Error US10: " + individuals.get(entry.getValue().getWifeID()).getName() + "（" + entry.getValue().getWifeID() + ")" + " is less than 14; she illegally married, in the family of " + entry.getValue().getID());
                }
                else {
                    Calendar marriedCal = Calendar.getInstance();
                    marriedCal.setTime(entry.getValue().getMarried());
                    Calendar tempBirth = Calendar.getInstance();
                    tempBirth.setTime(individuals.get(entry.getValue().getHusbandID()).getBirthday());
                    if (marriedCal.get(Calendar.YEAR) - tempBirth.get(Calendar.YEAR) <  14) {System.err.println(tempBirth.get(Calendar.YEAR) +" < "+ marriedCal.get(Calendar.YEAR)+" - 14");
                        errors.add("Error US10: " + individuals.get(entry.getValue().getHusbandID()).getName() + "（" + entry.getValue().getHusbandID() + ")" + " was less than 14, when he got married, in the family of " + entry.getValue().getID());
                    }
                    tempBirth.setTime(individuals.get(entry.getValue().getWifeID()).getBirthday());
                    if (marriedCal.get(Calendar.YEAR) - tempBirth.get(Calendar.YEAR) <  14)
                        errors.add("Error US10: " + individuals.get(entry.getValue().getWifeID()).getName() + "（" + entry.getValue().getWifeID() + ")" + " was less than 14, when she got married, in the family of " + entry.getValue().getID());
                }
            }
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }
