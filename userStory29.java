
private void listDeceased() { //US29
        Iterator<Map.Entry<String, Individual>> indIt = individuals.entrySet().iterator();

        try {
            while (indIt.hasNext()) {
                Map.Entry<String, Individual> indEnt = indIt.next();
                
                if(indEnt.getValue().getDeath() !=null) {
                	deceased += indEnt.getValue().getName() + "(" + indEnt.getValue().getID() + ")\r\n";
                }  
            }
            
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }
