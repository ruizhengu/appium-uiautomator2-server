package io.appium.uiautomator2.translation;

import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SelectorParser {

    public static Map<String, String> parseSelector(String selector) {
        Map<String, String> element = new HashMap<>();
        String matcher = "matcher";
        String value = "value";
        if (parseText(selector) != null) {
            element.put(matcher, "TEXT");
            element.put(value, parseText(selector));
            return element;
        }
        return null;
    }

    private static String parseText(String selector) {
        Pattern textPattern = Pattern.compile("text\\((.*)\\)");
        Matcher textMatcher = textPattern.matcher(selector);

        if (textMatcher.matches()) {
            return textMatcher.group(1);
        }

        return null;
    }
}
