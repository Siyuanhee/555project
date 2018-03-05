package gedcomreader;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.junit.Test;


public class HSprint1Test {
// test for US04
	@Test 
	
	public void testBirthBeforeDeath() throws ParseException {
		
		SimpleDateFormat formatter = new SimpleDateFormat ("d MMM yyyy", Locale.ENGLISH);
		
		Individual I1 = new Individual();
		Individual I2 = new Individual();
		Individual I3 = new Individual();
		Individual I4 = new Individual();
		Individual I5 = new Individual();
		
		I1.setBirthday(formatter.parse("01 JAN 1980"));
		I1.setDeath(formatter.parse("02 FEB 2010"));
		
		I2.setBirthday(formatter.parse("03 FEB 1980"));
		I2.setDeath(formatter.parse("04 JAN 2010"));
		
		I3.setBirthday(formatter.parse("05 MAR 2017"));
		I3.setDeath(formatter.parse("06 APR 2010"));
		
		I4.setBirthday(formatter.parse("07 JUN 2010"));
		I4.setDeath(formatter.parse("07 MAY 2010"));
		
		I5.setBirthday(formatter.parse("09 DEC 1980"));
		I5.setDeath(formatter.parse("08 DEC 1980"));
		
		assertEquals(true, US.birthBeforeDeath(I1));
		assertEquals(true, US.birthBeforeDeath(I2));
		assertEquals(false, US.birthBeforeDeath(I3));
		assertEquals(false, US.birthBeforeDeath(I4));
		assertEquals(false, US.birthBeforeDeath(I5));
	}
	
	public void testmarriageBeforeDivorce() throws ParseException{
		
		SimpleDateFormat formatter = new SimpleDateFormat ("d MMM yyyy", Locale.ENGLISH);
		
		Family F1 = new Family();
		Family F2 = new Family();
		Family F3 = new Family();
		Family F4 = new Family();
		Family F5 = new Family();
		
		F1.setMarried(formatter.parse("01 JAN 1980"));
		F1.setDivorced(formatter.parse("02 FEB 2010"));
		
		F2.setMarried(formatter.parse("03 FEB 1980"));
		F2.setDivorced(formatter.parse("04 JAN 2010"));
		
		F3.setMarried(formatter.parse("05 MAR 2017"));
		F3.setDivorced(formatter.parse("06 APR 2010"));
		
		F4.setMarried(formatter.parse("07 JUN 2010"));
		F4.setDivorced(formatter.parse("07 MAY 2010"));
		
		F5.setMarried(formatter.parse("09 DEC 1980"));
		F5.setDivorced(formatter.parse("08 DEC 1980"));
		
		assertEquals(true, US.marriageBeforeDivorce(F1));
		assertEquals(true, US.marriageBeforeDivorce(F2));
		assertEquals(false, US.marriageBeforeDivorce(F3));
		assertEquals(false, US.marriageBeforeDivorce(F4));
		assertEquals(false, US.marriageBeforeDivorce(F5));
	}
	
	
}