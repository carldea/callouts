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

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class CustomCallout extends Callout {

    public void build() {
        // Create head
        //Circle head = new Circle(getHeadPoint().getX(), getHeadPoint().getY(), 5);
        Rectangle head = new Rectangle(getHeadPoint().getX() - 5, getHeadPoint().getY() -5, 2*5, 2*5);
        head.setFill(Color.WHITE);
        head.setStroke(Color.WHITE);

        // First leader line
        Line firstLeaderLine = new Line(getHeadPoint().getX(), getHeadPoint().getY(),
                getHeadPoint().getX(), getHeadPoint().getY());
        firstLeaderLine.setStroke(Color.WHITE);
        firstLeaderLine.setStrokeWidth(3);

        // Second part of the leader line
        Line secondLeaderLine = new Line(getLeaderLineToPoint().getX(),
                getLeaderLineToPoint().getY(),
                getLeaderLineToPoint().getX(),
                getLeaderLineToPoint().getY());

        secondLeaderLine.setStroke(Color.WHITE);
        secondLeaderLine.setStrokeWidth(3);

        // Main title Rectangle
        HBox mainTitle = new HBox();
        mainTitle.setBackground(
                new Background(
                        new BackgroundFill(Color.WHITE,
                                new CornerRadii(2),
                                new Insets(0)))
        );

        // Main title text
        Text mainTitleText = new Text(getMainTitleText());
        HBox.setMargin(mainTitleText, new Insets(8, 8, 8, 8));
        mainTitleText.setFont(Font.font(20));
        mainTitle.getChildren().add(mainTitleText);

        // Position sub tile rectangle under main title
        Rectangle subTitleRect = new Rectangle(2, 20);
        subTitleRect.setFill(Color.WHITE);

        // Create the sub title
        HBox subTitle = new HBox();
        subTitle.setBackground(
                new Background(
                        new BackgroundFill(Color.color(0, 0, 0, .20),
                                new CornerRadii(0),
                                new Insets(0)))
        );
        Text subTitleText = new Text(getSubTitleText());
        subTitleText.setVisible(true);
        subTitleText.setFill(Color.WHITE);
        subTitleText.setFont(Font.font(14));
        subTitle.getChildren().add(subTitleText);

        // Build the animation code.
        buildAnimation(head,
                firstLeaderLine,
                secondLeaderLine,
                mainTitle,
                subTitleRect,
                subTitle);

        // Must add nodes after buildAnimation.
        // Positioning calculations are done
        // outside of this Group.
        getChildren().addAll(head,
                firstLeaderLine,
                secondLeaderLine,
                mainTitle,
                subTitleRect,
                subTitle);
        getChildren().forEach(node -> node.setVisible(false));

    }

    protected Animation buildHeadAnim(Node head) {
        Rectangle headRect = (Rectangle) head;
        return new Timeline(
                new KeyFrame(Duration.millis(1),
                        new KeyValue(headRect.visibleProperty(), true),
                        new KeyValue(headRect.scaleXProperty(), 0),
                        new KeyValue(headRect.scaleYProperty(), 0)
                ), // show
                new KeyFrame(Duration.millis(200),
                        new KeyValue(headRect.scaleXProperty(), 1),
                        new KeyValue(headRect.scaleYProperty(), 1)
                ) // max value
        );
    }
}
