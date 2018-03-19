private void BirthBeforeDeathOfParents () {//US09
        try {
            for (Map.Entry<String,Family> entry: families.entrySet()) {
                if (individuals.get(entry.getValue().getHusbandID()).getDeath() != null ||
                    individuals.get(entry.getValue().getWifeID()).getDeath() != null) {
                    for (Iterator<String> it1 = entry.getValue().getChildren().iterator(); it1.hasNext(); ) {
                        String child = it1.next();
                        if (individuals.get(entry.getValue().getHusbandID()).getDeath() != null) {
                            Calendar cal = Calendar.getInstance();
                            cal.setTime(individuals.get(entry.getValue().getHusbandID()).getDeath());
                            cal.add(Calendar.MONTH, 9);
                            Date tempDate = cal.getTime();
                            if (individuals.get(child).getBirthday().after(tempDate))
                                errors.add("Error US09: Birthday of " + individuals.get(child).getName() + "(" + child + ") is after 9 months of father's Death date in the family of " + entry.getValue().getID());
                        }
                        else if (individuals.get(entry.getValue().getWifeID()).getDeath() != null && individuals.get(child).getBirthday().after(individuals.get(entry.getValue().getWifeID()).getDeath())) {
                            errors.add("Error US09: Birthday of " + individuals.get(child).getName() + "(" + child + ") is after mother's Death date in the family of " + entry.getValue().getID());
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }
