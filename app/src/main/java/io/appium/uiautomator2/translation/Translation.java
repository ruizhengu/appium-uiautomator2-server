package io.appium.uiautomator2.translation;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;
import java.util.Objects;

import io.appium.uiautomator2.model.By;

public class Translation {
    private final String TAG_ESPRESSO = "Espresso";
    private final String TAG_UIAUTOMATOR = "UI Automator";
    private String framework;
    private String matcher;
    private String value;
    private String action;
    private String statement;


    public Translation(String selector) {
        Map<String, String> map = SelectorParser.parseSelector(selector);
        assert map != null;
        this.matcher = map.get("matcher");
        if (Objects.equals(this.matcher, "text")) {
            this.framework = TAG_ESPRESSO;
            this.value = map.get("value");
        }
    }

    public Translation(By by) {
        Map<String, String> map = ActionParser.parseAction(String.valueOf(by));
        assert map != null;
        this.matcher = map.get("matcher");
        if (Objects.equals(this.matcher, "text")) {
            this.framework = TAG_ESPRESSO;
            this.value = map.get("value");
            this.action = map.get("action");
        }
    }

    public String getStatement() {
        if (Objects.equals(this.framework, TAG_ESPRESSO)) {
            switch (this.matcher) {
                case "text":
                    statement = String.format("onView(withText(%s))", this.value);
                    break;
                case "ID":
                    statement = String.format("onView(withId(%s))", this.value);
                    break;
            }
            if (this.action != null) {
                switch (this.action) {
                    case "click":
                        statement += ".click()";
                        break;
                    case "longClick":
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
