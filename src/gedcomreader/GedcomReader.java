/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gedcomreader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

/**
 *
 * @author chenshuaishuai
 */
public class GedcomReader {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     * @throws java.io.FileNotFoundException
     * @throws java.text.ParseException
     */
    public static void main(String[] args) throws IOException, FileNotFoundException, ParseException {
        // TODO code application logic here
        GED ged = new GED();
        
        ged.traversal();
        
        ged.individualsPrint();
        
        ged.familiesPrint();
    }
    
}
