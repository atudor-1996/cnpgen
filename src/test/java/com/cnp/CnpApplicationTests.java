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
		cnp.randomGender();
		assert (cnp.formatGender() instanceof String);
	}

	@Test
	@DisplayName("Test build")
	void testBuildCnp()
	{
		cnp.buildCnp();
	}
}

