package com.jofrip54.view;

import javafx.beans.property.ObjectProperty;
import javafx.scene.paint.Paint;

public interface PressedColor {
	
	ObjectProperty<Paint> releasedColorProperty();
	default Paint getReleasedColor() {
		return releasedColorProperty().get();
	}
	default void setReleasedColor(Paint c) {
		releasedColorProperty().set(c);
	}
	
	ObjectProperty<Paint> pressedColorProperty();
	default Paint getPressedColor() {
		return pressedColorProperty().get();
	}
	default void setPressedColor(Paint c) {
		pressedColorProperty().set(c);
	}
}
