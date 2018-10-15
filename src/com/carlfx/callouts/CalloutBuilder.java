/*
 * Copyright (c) 2018. Carl Dea
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.carlfx.callouts;

import java.lang.reflect.InvocationTargetException;

/**
 * A callout builder to create a Callout instance.
 * An example of building a Callout using the CalloutBuilder.:
 * <pre>
 *     Callout callout = CalloutBuilder.create()
 *                 .headPoint(600, 550)
 *                 .leaderLineToPoint(400, 300)
 *                 .endLeaderLineLeft()
 *                 .mainTitle("STONEY CREEK")
 *                 .subTitle("Pasadena, MD")
 *                 .pause(5000)
 *                 .build();
 * </pre>
 */
public class CalloutBuilder {

    private com.carlfx.callouts.Callout callout = new com.carlfx.callouts.Callout();

    private CalloutBuilder() {}

    private CalloutBuilder(com.carlfx.callouts.Callout callout) {
        this.callout = callout;
    }

    public static CalloutBuilder create() {
        return new CalloutBuilder();
    }
    public static CalloutBuilder create(Class<? extends com.carlfx.callouts.Callout> clazz) {
        try {
            return new CalloutBuilder(clazz.getDeclaredConstructor().newInstance());
        } catch (InstantiationException |
                IllegalAccessException |
                InvocationTargetException |
                NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public CalloutBuilder headPoint(double x, double y) {
        callout.setHeadPoint(x, y);
        return this;
    }

    public CalloutBuilder leaderLineToPoint(double x, double y) {
        callout.setLeaderLineToPoint(x, y);
        return this;
    }

    public CalloutBuilder endLeaderLineRight(double length) {
        callout.setEndLeaderLineLength(length);
        callout.setEndLeaderLineDirection(com.carlfx.callouts.Callout.RIGHT);
        return this;
    }
    public CalloutBuilder endLeaderLineRight() {
        callout.setEndLeaderLineLength(75);
        callout.setEndLeaderLineDirection(com.carlfx.callouts.Callout.RIGHT);
        return this;
    }

    public CalloutBuilder endLeaderLineLeft(double length) {
        callout.setEndLeaderLineLength(length);
        callout.setEndLeaderLineDirection(com.carlfx.callouts.Callout.LEFT);
        return this;
    }

    public CalloutBuilder endLeaderLineLeft() {
        callout.setEndLeaderLineLength(75);
        callout.setEndLeaderLineDirection(com.carlfx.callouts.Callout.LEFT);
        return this;
    }

    public CalloutBuilder endLeaderLineLength(double length) {
        callout.setEndLeaderLineLength(length);
        return this;
    }

    public CalloutBuilder endLeaderLineDirection(int direction) {
        callout.setEndLeaderLineDirection(direction);
        return this;
    }

    public CalloutBuilder mainTitle(String title) {
        callout.setMainTitleText(title);
        return this;
    }

    public CalloutBuilder subTitle(String title) {
        callout.setSubTitleText(title);
        return this;
    }
    public CalloutBuilder pause(long inMillis) {
        callout.setPauseTime(inMillis);
        return this;
    }


    public com.carlfx.callouts.Callout build() {
        callout.build();
        return callout;
    }
}
