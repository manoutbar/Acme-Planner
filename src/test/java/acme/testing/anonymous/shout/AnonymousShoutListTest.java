package acme.testing.anonymous.shout;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.framework.helpers.StringHelper;
import acme.testing.AcmePlannerTest;

public class AnonymousShoutListTest extends AcmePlannerTest {

	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/list-all.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void listAll(final int recordIndex, final String moment, final String author, final String text, final String info) {
		super.clickOnMenu("Anonymous", "List shouts not older than one month");
		
		super.checkColumnHasValue(recordIndex, 0, moment);
		super.checkColumnHasValue(recordIndex, 1, author);
		super.checkColumnHasValue(recordIndex, 2, text);
		
		if (info != null && !StringHelper.isBlank(info)) {
			super.checkColumnHasValue(recordIndex, 3, info);
		}
	}
	
	@Test
	@Order(20)
	public void testAllNegative() {
		// check restricted anonymous access
		super.signIn("manager1", "manager1");
		super.navigate("/anonymous/shout/list", "");
		super.checkErrorsExist();

		super.signOut();

		super.signIn("administrator", "administrator");
		super.navigate("/anonymous/shout/list", "");
		super.checkErrorsExist();

		super.signOut();
	}
	
}
