/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.appium.uiautomator2.handler;

import android.util.Log;

import androidx.test.uiautomator.UiObjectNotFoundException;

import java.util.Objects;

import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.model.AndroidElement;
import io.appium.uiautomator2.model.AppiumUIA2Driver;
import io.appium.uiautomator2.model.Session;
import io.appium.uiautomator2.translation.ActionParser;
import io.appium.uiautomator2.translation.SelectorParser;
import io.appium.uiautomator2.translation.Translation;
import io.appium.uiautomator2.utils.Device;
import io.appium.uiautomator2.utils.Logger;

public class Click extends SafeRequestHandler {
    private String TAG = "Action: Click";

    public Click(String mappedUri) {
        super(mappedUri);
    }

    @Override
    protected AppiumResponse safeHandle(IHttpRequest request) {
        Session session = AppiumUIA2Driver.getInstance().getSessionOrThrow();
        AndroidElement element = session.getElementsCache().get(getElementId(request));
        Logger.translation(TAG, "action: " + element.getBy());
        Translation statement = new Translation(element.getBy());
        Logger.translation(statement.getFramework(), statement.getStatement());
        element.click();
        Device.waitForIdle();
        return new AppiumResponse(getSessionId(request));
    }
}
