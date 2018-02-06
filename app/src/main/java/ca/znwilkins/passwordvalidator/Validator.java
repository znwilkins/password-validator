package ca.znwilkins.passwordvalidator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

	static List<Rule> ruleList = new ArrayList<>();

	static {
		ruleList.add(new Rule("password", Pattern.CASE_INSENSITIVE));
		ruleList.add(new Rule("^.{0,7}$"));
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
