private void UniqueFamilyBySpouse () {//US24
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Map<String, Family> tempFamilies = new LinkedHashMap(families);
        try {
            for (Map.Entry<String, Family> en1 : families.entrySet()) {
                tempFamilies.remove(en1.getKey());
                for (Map.Entry<String, Family> en2 : tempFamilies.entrySet()) {
                    if (en1.getValue().getHusbandName().equals(en2.getValue().getHusbandName()) &&
                        en1.getValue().getWifeName().equals(en2.getValue().getWifeName())       &&
                        en1.getValue().getMarried().equals(en2.getValue().getMarried())) {
                        errors.add("Error US24: there are 2 families(" + en1.getValue().getID() + " and " + en2.getValue().getID() + ") with the same spouses(" + en1.getValue().getHusbandName() + " and " + en1.getValue().getWifeName() + ") getting married in the same date " + formatter.format(en1.getValue().getMarried()));
                    }

                }
            }
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }
