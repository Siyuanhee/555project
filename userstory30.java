import java.util.Iterator;
import java.util.Map;

private void listLivingMarried() { //US30
        Iterator<Map.Entry<String, Individual>> indIt = individuals.entrySet().iterator();

        try {
            while (indIt.hasNext()) {
                Map.Entry<String, Individual> indEnt = indIt.next();
                Iterator<String> famsIt = indEnt.getValue().getFAMS().iterator();
                
                if(indEnt.getValue().getDeath() == null) {
                	while (famsIt.hasNext()) {
                    	String str = famsIt.next();
                    	if(str != null){
	                	livingMarried.add(indEnt.getValue().getName() + "(" + indEnt.getValue().getID() + ")");
	                	break;
                    	}
                	}
                }  
            }
            
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }