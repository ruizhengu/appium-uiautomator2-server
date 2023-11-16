package io.appium.uiautomator2.translation;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ActionParser {

    public static Map<String, String> parseAction(String by) {
        Map<String, String> element = new HashMap<>();
        String matcher = "matcher";
        String value = "value";
        String action = "action";
        if (parseClick(by) != null) {
            element.put(matcher, Objects.requireNonNull(parseClick(by)).get("matcher"));
            element.put(value, Objects.requireNonNull(parseClick(by)).get("value"));
            element.put(action, "click");
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
