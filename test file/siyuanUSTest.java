package gedcomreader;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class siyuanUSTest {
	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("Test begin");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("Test END");
	}

	@Before
	public void setUp() throws Exception {
		System.out.println("begin to setup");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("Test done");
	}

	@Test
	public void testBirthBeforeDeath() throws ParseException, FileNotFoundException, IOException {
		GED GEDTest = new GED();
		GEDTest.traversal();
		GEDTest.checkErrors();
		
		assertTrue(GEDTest.errors.contains("Error US03: Birth date of Sarah /Lewis/(I12) occurs after death date."));

	}

	@Test
	public void testMarriageBeforeDivorce() throws ParseException, FileNotFoundException, IOException {
		
		GED GEDTest = new GED();
		GEDTest.traversal();
		GEDTest.checkErrors();
		
		assertTrue(GEDTest.errors.contains("Error US04: Family(F2) Husband: David /Brown/(I2) Wife: Jennifer /Brown/(I3) married date occurs after divorced."));
		
	}

	@Test
	public void testBirthAfterMarriageOfParents() throws ParseException, FileNotFoundException, IOException {
		
		GED GEDTest = new GED();
		GEDTest.traversal();
		GEDTest.checkErrors();
		
		assertTrue(GEDTest.errors.contains("Error US08: Birthday of John /Brown/(I1) in the family of F2 is more than 9 months after parents divorce."));
		
	}
	
	@Test
	public void testParentsNotTooOld() throws ParseException, FileNotFoundException, IOException {
		
		GED GEDTest = new GED();
		GEDTest.traversal();
		GEDTest.checkErrors();
		
		assertTrue(GEDTest.errors.contains("Error US12: Age of mother Jennifer /Brown/(I3) is more than 60 years older than her children John /Brown/(I1)."));
		assertTrue(GEDTest.errors.contains("Error US12: Age of father Joseph /James/(I9) is more than 80 years older than his children Karen /James/(I4)."));
		
	}
	
	@Test
	public void testFewerThan15Siblings() throws ParseException, FileNotFoundException, IOException {
		
		GED GEDTest = new GED();
		GEDTest.traversal();
		GEDTest.checkErrors();

		
		assertTrue(GEDTest.errors.contains("Error US15: Family (F5) have 15 or more siblings"));
		
	}
	
	@Test
	public void testListDeceased() throws ParseException, FileNotFoundException, IOException {
		
		GED GEDTest = new GED();
		GEDTest.traversal();
		GEDTest.checkErrors();

		
		assertTrue(GEDTest.deceased.contains("Jennifer /Brown/(I3)"));
		assertTrue(GEDTest.deceased.contains("Joseph /James/(I9)"));
		
	}
	
}
