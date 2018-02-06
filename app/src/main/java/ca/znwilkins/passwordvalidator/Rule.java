package ca.znwilkins.passwordvalidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Rule {

	Pattern rulePattern;

	public Rule(String regex) {
		rulePattern = Pattern.compile(regex);
	}

	public Rule(String regex, int flag) {
		rulePattern = Pattern.compile(regex, flag);
	}

	public Matcher getMatcher(String password) {
		return rulePattern.matcher(password);
	}

}