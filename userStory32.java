private void listMultipleBirth() { //US32
        Iterator<Map.Entry<String,Individual>> indIt = individuals.entrySet().iterator();
        SimpleDateFormat birthDateFormat = new SimpleDateFormat("MM-dd-yyyy");
        Set<String> birthList = new LinkedHashSet<>();

        try {
            while (indIt.hasNext()) {
                Map.Entry<String, Individual> indEnt = indIt.next();
                Iterator<Map.Entry<String, Individual>> indIt2 = individuals.entrySet().iterator();

                while (indIt2.hasNext()) {
                    Map.Entry<String, Individual> indEnt2 = indIt2.next();
                    if (indEnt.getValue().getBirthday().equals(indEnt2.getValue().getBirthday()) && indEnt.getValue().getID() != indEnt2.getValue().getID()) {
                        errors.add(String.format("There are two same birthdays: " + birthDateFormat.format(indEnt.getValue().getBirthday())));
                    }
                }
            }
        }catch (Exception e) {
            System.out.println(e.toString());
        }
    }
