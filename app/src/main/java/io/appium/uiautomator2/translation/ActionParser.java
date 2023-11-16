package io.appium.uiautomator2.translation;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ActionParser {
    private static final String MATCHER_KEY = "matcher";
    private static final String VALUE_KEY = "value";
    private static final String ACTION_KEY = "action";

    public static Map<String, String> parseAction(String by) {
        Map<String, String> element = new HashMap<>();
        if (parseClick(by) != null) {
            element.put(MATCHER_KEY, Objects.requireNonNull(parseClick(by)).get("matcher"));
            element.put(VALUE_KEY, Objects.requireNonNull(parseClick(by)).get("value"));
            element.put(ACTION_KEY, "CLICK");
            return element;
        }
        return null;
    }

    private static Map<String, String> parseClick(String by) {
        Pattern clickPattern = Pattern.compile("By.*:\\s(.*)");
        Matcher clickMatcher = clickPattern.matcher(by);

        if (clickMatcher.matches()) {
            return SelectorParser.parseSelector(clickMatcher.group(1));
        }

        return null;
    }
}
