private void RecentDeaths () {//US36
    try {
        Date now = new Date ();
        for (Map.Entry<String, Individual> indEnt : individuals.entrySet()) {
            if (indEnt.getValue().getDeath() != null) {
                if ((int) ((now.getTime() - indEnt.getValue().getDeath().getTime()) / (1000*3600*24)) <= 30 &&
                    (int) ((now.getTime() - indEnt.getValue().getDeath().getTime()) / (1000*3600*24)) >= 0  ) {
                    errors.add("User Story 36[Recent Death]: (Die in 30 days) " + indEnt.getValue().getName() + "(" + indEnt.getValue().getID() + ") died in " + (int) ((now.getTime() - indEnt.getValue().getDeath().getTime()) / (1000*3600*24)) + " day(s)");
                }
            }
        }
    }
    catch (Exception e) {
        System.out.println(e.toString());
    }
}