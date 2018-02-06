package ca.znwilkins.passwordvalidator;


import org.junit.Test;

import java.util.regex.Matcher;

import static org.junit.Assert.*;

public class PasswordValidatorUnitTests {

	private final int PASSWORD_RULE = 0;
	private final int LENGTH_RULE = 1;

	private Validator validator;

	@Test
	public void checkCompileRegex() {
		for (Rule rule : Validator.ruleList) {
			assertNotNull(rule.rulePattern);
		}
	}

	@Test
	public void passwordIsNotPasswordLowerCase() {
		Matcher matcher = Validator.ruleList.get(PASSWORD_RULE).getMatcher("password");
		assertTrue(matcher.matches());
	}

	@Test
	public void passwordIsNotPasswordMixedCase() {
		Matcher matcher = Validator.ruleList.get(PASSWORD_RULE).getMatcher("PaSsWoRd");
		assertTrue(matcher.matches());
	}

	@Test
	public void passwordIsNotPasswordUpperCase() {
		Matcher matcher = Validator.ruleList.get(PASSWORD_RULE).getMatcher("PASSWORD");
		assertTrue(matcher.matches());
	}

	@Test
	public void passwordIsTooShort() {
		Matcher matcher = Validator.ruleList.get(LENGTH_RULE).getMatcher("short");
		assertTrue(matcher.matches());
	}

	@Test
	public void passwordIsLongEnough() {
		Matcher matcher = Validator.ruleList.get(LENGTH_RULE).getMatcher("ninechars");
		assertFalse(matcher.matches());
	}

	@Test
	public void passwordIsStrong() {
		// If this password doesn't count as strong, what does?
		assertEquals(Validator.ruleList.size(), validator.validate("N7Zd:5bfuM'?bW:@"));
	}

}
