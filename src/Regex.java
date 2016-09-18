import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
	public static boolean EstaPresente (String contem, String contida) {
			return createPatternToValue(contida).matcher(contem).matches();
	}
	
	private static Pattern createPatternToValue(final String value) {
		return Pattern.compile("([0-9]*[0-9]:[0-5][0-9])" + "\\s(" + value + ":)(.*?)");
	}
	
	public static Matcher getMatcherForSentence(final String sentence, final String value) {
		return createPatternToValue(value).matcher(sentence);
	}
	
	public static Matcher createPatternForSetence(final String pattern, final String sentence) {
		return Pattern.compile(pattern).matcher(sentence);
	}
}
