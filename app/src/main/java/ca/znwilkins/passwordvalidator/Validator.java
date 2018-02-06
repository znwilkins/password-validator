package ca.znwilkins.passwordvalidator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

	static List<Rule> ruleList = new ArrayList<>();

	static {
		// Can't be password
		ruleList.add(new Rule("password", Pattern.CASE_INSENSITIVE));
		// At least 8 characters
		ruleList.add(new Rule("^.{0,7}$"));
		// At least one special character
		ruleList.add(new Rule("^[a-zA-Z0-9]+$"));
		// At least one digit
		ruleList.add(new Rule("^[^\\d]+$"));
		// At least one uppercase
		ruleList.add(new Rule("^[^A-Z]+$"));
	}

	public static int validate(String password) {

		List<Matcher> matchers = new ArrayList<>();
		for (Rule rule : ruleList) {
			matchers.add(rule.getMatcher(password));
		}

		int count = 0;

		for (Matcher matcher : matchers) {
			if (!matcher.matches())
				count++;
		}

		return count;
	}

}
