    private void noBigamy() { //US11
        Date now = new Date();
        Iterator<Map.Entry<String, Individual>> indIt = individuals.entrySet().iterator();

        try {
            while (indIt.hasNext()){
                Map.Entry<String, Individual> indEnt = indIt.next();
                Iterator<String> spIt = indEnt.getValue().getFAMS().iterator();
                Map<Date, Date> marriage = new HashMap<>();

                while (spIt.hasNext()){
                    String str = spIt.next();
                    if (families.get(str).getDivorced() == null){
                        marriage.put(families.get(str).getMarried(), now);
                    }
                    else{
                        marriage.put(families.get(str).getMarried(),families.get(str).getDivorced());
                    }
                }

                for (Map.Entry<Date, Date> entry: marriage.entrySet()){
                    for (Date key: marriage.keySet()){
                        if (key.after(entry.getKey()) && key.before(entry.getValue())){ //判断某结婚日期是否在结婚日期和离婚日期之间
                            errors.add("Error: US11: " + indEnt.getValue().getName() + "has bigamy!");
                        }
                    }
                }

            }

        }catch (Exception e) {
            System.out.println(e.toString());
        }
    }
