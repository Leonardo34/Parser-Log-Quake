import java.util.regex.Pattern;

public class Regex {
	public static boolean EstaPresente (String contem, String contida) {
			return createPatternToValue(contida).matcher(contem).matches();
	}
	
	private static Pattern createPatternToValue(final String value) {
		return Pattern.compile("([0-9]*[0-9]:[0-5][0-9])" + "\\s(" + value + ":)(.*?)");
	}
}
