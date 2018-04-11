    private void uniqueNameAndBirth() { //US23
        Iterator<Map.Entry<String,Individual>> indIt = individuals.entrySet().iterator();

        SimpleDateFormat birthDateFormat = new SimpleDateFormat("MM-dd-yyyy");

        try {
            while (indIt.hasNext()) {
                Map.Entry<String, Individual> indEnt = indIt.next();
                Iterator<Map.Entry<String,Individual>> indIt2 = individuals.entrySet().iterator();

                while(indIt2.hasNext()) {
                    Map.Entry<String, Individual> indEnt2 = indIt2.next();
                    if (indEnt.getValue().getName().equals(indEnt2.getValue().getName()) && indEnt.getValue().getID() != indEnt2.getValue().getID()) {
                        errors.add("US23 Error: There are two same names: "+ indEnt.getValue().getName());
                    }

                    if (indEnt.getValue().getBirthday().equals(indEnt2.getValue().getBirthday())  && indEnt.getValue().getID() != indEnt2.getValue().getID()) {
                        errors.add(String.format("US23 Error: There are two same birthdays: " + birthDateFormat.format(indEnt.getValue().getBirthday())));
                    }
                }

            }
        }catch (Exception e) {
            System.out.println(e.toString());
        }

    }
