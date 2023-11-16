package io.appium.uiautomator2.translation;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;
import java.util.Objects;

import io.appium.uiautomator2.model.By;

public class Translation {
    private final String TAG_ESPRESSO = "Espresso";
    private final String TAG_UIAUTOMATOR = "UI Automator";

    private enum MatcherType {
        TEXT, ID
    }

    private enum ActionType {
        CLICK, LONG_CLICK
    }

    private String framework;
    private MatcherType matcher;
    private String value;
    private ActionType action;
    private String statement;


    public Translation(String selector) {
        Map<String, String> map = SelectorParser.parseSelector(selector);
        if (map != null) {
            initializeFields(map);
        }
    }

    public Translation(By by) {
        Map<String, String> map = ActionParser.parseAction(String.valueOf(by));
        if (map != null) {
            initializeFields(map);
        }
    }

    private void initializeFields(Map<String, String> map) {
        this.matcher = MatcherType.valueOf(map.get("matcher"));
        if (Objects.equals(this.matcher, MatcherType.TEXT)) {
            this.framework = TAG_ESPRESSO;
            this.value = map.get("value");
            if (map.get("action") != null) {
                this.action = ActionType.valueOf(map.get("action"));
            }
        }
    }

    public String getStatement() {
        if (Objects.equals(this.framework, TAG_ESPRESSO)) {
            switch (this.matcher) {
                case TEXT:
                    statement = String.format("onView(withText(%s))", this.value);
                    break;
                case ID:
                    statement = String.format("onView(withId(%s))", this.value);
                    break;
            }
            if (this.action != null) {
                switch (this.action) {
                    case CLICK:
                        statement += ".click()";
                        break;
                    case LONG_CLICK:
                        statement += ".longClick()";
                        break;
                }
            }
        }
        return statement;
    }

    public String getFramework() {
        return framework;
    }
}
