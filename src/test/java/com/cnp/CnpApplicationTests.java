package com.cnp;


import com.cnp.cnpgen.Cnpgen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CnpApplicationTests {

	Cnpgen cnp;

	@BeforeEach
	void setUp() {
		cnp = new Cnpgen();
	}

	@Test
	@DisplayName("randomGender returns string")
	void testGenderType() {

		assert (cnp.randomGender() instanceof String);
	}

	@RepeatedTest(10)
	@DisplayName("randomGender returns 1 or 2")
	void testGenderValue() {
		String val = cnp.randomGender();
		if (!(val.compareTo("1") == 0 || val.compareTo("2") == 0)) {
			//System.out.println(val);
			fail("Value returned not 1 or 2");
		}
	}

	@Test
	@DisplayName("Test if random date is string")
	void testRandomDateType()
	{
		assert(cnp.randomDate() instanceof String);

	}

	@RepeatedTest(10)
	@DisplayName("Test if value can be date")
	void testRandomDateVal()
	{
		assert (testDateExc());
	}

	boolean testDateExc()
	{
		try{
			String val = cnp.randomDate();
			//System.out.println(val);
			LocalDate dt = LocalDate.parse(val);
		}
		catch (Exception e)
		{
			return false;
		}
		return true;

	}

	@Test
	@DisplayName("formatGender returns string")
	void testFormatGenderType() {
//		cnp.randomGender();
//		assert (cnp.formatGender() instanceof String);
	}

	@Test
	@DisplayName("Test build")
	void testBuildCnp()
	{
		cnp.buildCnp();
	}

	@RepeatedTest(50)
	@DisplayName("Test build cnp")
	void testBuildCnpMade()
	{
		cnp.buildCnp(Integer.parseInt(cnp.randomGender()),1999,2001, 52);
		String test = cnp.getCnp();
		char sex = test.charAt(0);
		assert ((sex-'0' == 1) || (sex-'0' == 2) || (sex-'0' == 3) || (sex-'0' == 4) || (sex-'0' == 5) || (sex-'0' == 6));
		assert ((test.charAt(1)+""+test.charAt(2)).equals("99") || (test.charAt(1)+""+test.charAt(2)).equals("00") || (test.charAt(1)+""+test.charAt(2)).equals("01"));
		assert ((test.charAt(7)+""+test.charAt(8)).equals("52"));
		System.out.println(test);
	}
	@Test
	@DisplayName("Test build cnp null sex")
	void testBuildCnpMade1()
	{
		cnp.buildCnp(null,1999,2001, 52);
		String test = cnp.getCnp();
		char sex = test.charAt(0);
		assert ((sex-'0' == 1) || (sex-'0' == 2) || (sex-'0' == 3) || (sex-'0' == 4) || (sex-'0' == 5) || (sex-'0' == 6));
		System.out.println(test);
	}
	@Test
	@DisplayName("Test build cnp begin null")
	void testBuildCnpMade2()
	{
		cnp.buildCnp(1,null,2001, 52);
		String test = cnp.getCnp();
		System.out.println(test);
	}
	@Test
	@DisplayName("Test build cnp end null")
	void testBuildCnpMade3()
	{
		cnp.buildCnp(1,1999,null, 52);
		String test = cnp.getCnp();
		System.out.println(test);
	}
	@Test
	@DisplayName("Test build cnp county null")
	void testBuildCnpMade4()
	{
		cnp.buildCnp(1,1999,2001, null);
		String test = cnp.getCnp();
		System.out.println(test);
	}

	@RepeatedTest(100)
	@DisplayName("Test build cnp all null")
	void testBuildCnpMade5()
	{
		cnp.buildCnp(null,null,null, null);

		String test = cnp.getCnp();
		char sex = test.charAt(0);
		assert ((sex-'0' == 1) || (sex-'0' == 2) || (sex-'0' == 3) || (sex-'0' == 4) || (sex-'0' == 5) || (sex-'0' == 6));

		int county = Integer.parseInt((test.charAt(7)+""+test.charAt(8)));
		if((county < 1 || county > 46) && (county != 51 && county != 52)) fail();
		System.out.println(test);
	}

	@RepeatedTest(1000)
	@DisplayName("Test sex with date interval")
	void testBuildCnpMadeDateInterval()
	{
		cnp.buildCnp(Integer.parseInt(cnp.randomGender()),1900,1999, null);
		String test = cnp.getCnp();
		char sex = test.charAt(0);
		assert ((sex-'0' == 1) || (sex-'0' == 2));
		System.out.println(test);
	}



}

