package ca.znwilkins.passwordvalidator;


import org.junit.Test;

import java.util.regex.Matcher;

import static org.junit.Assert.*;

public class PasswordValidatorUnitTests {

	private static final int PASSWORD_RULE = 0;
	private static final int LENGTH_RULE = 1;
	private static final int SPECIAL_CHAR_RULE = 2;
	private static final int DIGIT_RULE = 3;
	private static final int MIXED_CASE_RULE = 4;

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
	public void passwordHasNoSpecialChars() {
		Matcher matcher = Validator.ruleList.get(SPECIAL_CHAR_RULE).getMatcher("abc");
		assertTrue(matcher.matches());
	}

	@Test
	public void passwordHasSpecialChars() {
		Matcher matcher = Validator.ruleList.get(SPECIAL_CHAR_RULE).getMatcher("!@#");
		assertFalse(matcher.matches());
	}

	@Test
	public void passwordHasNoDigit() {
		Matcher matcher = Validator.ruleList.get(DIGIT_RULE).getMatcher("abc");
		assertTrue(matcher.matches());
	}

	@Test
	public void passwordHasDigit() {
		Matcher matcher = Validator.ruleList.get(DIGIT_RULE).getMatcher("abc123");
		assertFalse(matcher.matches());
	}

	@Test
	public void passwordHasNoUpperCase() {
		Matcher matcher = Validator.ruleList.get(MIXED_CASE_RULE).getMatcher("abc");
		assertTrue(matcher.matches());
	}

	@Test
	public void passwordHasUpperCase() {
		Matcher matcher = Validator.ruleList.get(MIXED_CASE_RULE).getMatcher("abcABC");
		assertFalse(matcher.matches());
	}

	@Test
	public void passwordIsStrong() {
		// If this password doesn't count as strong, what does?
		assertEquals(Validator.ruleList.size(), validator.validate("N7Zd:5bfuM'?bW:@"));
	}

}
