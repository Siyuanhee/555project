import java.util.Iterator;
import java.util.Map;

private void listLivingSingle() { //US31 over 30 never married
        Iterator<Map.Entry<String, Individual>> indIt = individuals.entrySet().iterator();

        try {
            while (indIt.hasNext()) {
                Map.Entry<String, Individual> indEnt = indIt.next();
                Iterator<String> famsIt = indEnt.getValue().getFAMS().iterator();
                
                if(indEnt.getValue().getDeath() == null && getAgeByBirthAndDeath(indEnt.getValue().getBirthday(), indEnt.getValue().getDeath()) > 30) {
                	while (!famsIt.hasNext()) {
                		
	                	livingSingle.add(indEnt.getValue().getName() + "(" + indEnt.getValue().getID() + ")");
	                	break;
                	}
                }  
            }
            
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }
