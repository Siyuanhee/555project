private void RecentDivorced () {//US37
    try {
        Date now = new Date ();
        for (Map.Entry<String, Family> famEnt : families.entrySet()) {
            if (famEnt.getValue().getDivorced() != null) {
                if ((int) ((now.getTime() - famEnt.getValue().getDivorced().getTime()) / (1000*3600*24)) <= 30 &&
                    (int) ((now.getTime() - famEnt.getValue().getDivorced().getTime()) / (1000*3600*24)) >= 0) {
                    errors.add("User Story 37[Recent Divorce]: (Divorce in 30 days) " + famEnt.getValue().getHusbandName() + " and " + famEnt.getValue().getWifeName() + "( family ID:" + famEnt.getValue().getID() + ") divorced in " + (int) ((now.getTime() - famEnt.getValue().getDivorced().getTime()) / (1000*3600*24)) + " day(s)");
                }
            }
        }
    }
    catch (Exception e) {
        System.out.println(e.toString());
    }
}