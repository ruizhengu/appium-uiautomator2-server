package io.appium.uiautomator2.translation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SelectorParser {

    public static String parseSelector(String selector) {
        Pattern textPattern = Pattern.compile("text\\((.*)\\)");
        Matcher textMatcher = textPattern.matcher(selector);

        if (textMatcher.matches()) {
            return String.format("Espresso: onView(withText(%s))", textMatcher.group(1));
        }

        return String.format("No matcher for selector %s found", selector);
    }
}
