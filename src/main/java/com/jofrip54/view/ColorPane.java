package com.jofrip54.view;

import java.util.stream.Stream;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class ColorPane extends Pane implements PressedColor {

	public ColorPane() {
		installDefaults();
		installBindings();
		installBehavior();
	}

	protected void installDefaults() {
		setPrefSize(150, 75);
	}

	protected void installBindings() {
		// Create binding for background
		final ObjectBinding<Background> binding =
		     Bindings.createObjectBinding(() -> {
			     final BackgroundFill bf = new BackgroundFill(
			                                                  getBackgroundColor(),
			                                                  CornerRadii.EMPTY,
			                                                  Insets.EMPTY);
			     return new Background(bf);
		     }, backgroundColorProperty());
		// Apply binding
		backgroundProperty().bind(binding);
	}

	protected void installBehavior() {
		// Create handler
		final EventHandler<? super MouseEvent> handler = e -> {
			final EventType<? extends MouseEvent> type = e.getEventType();
			final Paint color = MouseEvent.MOUSE_PRESSED == type
			     ? getPressedColor()
			     : getReleasedColor();
			setBackgroundColor(color);
		};

		// Add handler
		Stream.of(
		          MouseEvent.MOUSE_PRESSED,
		          MouseEvent.MOUSE_RELEASED)
		      .forEach(type -> addEventHandler(type, handler));
	}


	//===================================================
	///////////////////////////////////////////  PROPERTIES  //////////////////////////////////////////
	//===================================================

	protected ObjectProperty<Paint> backgroundColorProperty() {
		if (backgroundColor == null)
			backgroundColor = new SimpleObjectProperty<>(
			                                             this,
			                                             "backgroundColor",
			                                             getReleasedColor());
		return backgroundColor;
	}
	protected Paint getBackgroundColor() {
		return backgroundColorProperty().get();
	}
	protected void setBackgroundColor(Paint c) {
		backgroundColorProperty().set(c);
	}
	private ObjectProperty<Paint> backgroundColor;




	@Override
	public ObjectProperty<Paint> releasedColorProperty() {
		if (releasedColor == null)
			releasedColor = new SimpleObjectProperty<>(
			                                           this,
			                                           "releasedColor",
			                                           Color.GREEN);
		return releasedColor;
	}
	private ObjectProperty<Paint> releasedColor;
	//TODO Comment out the get/set methods after setting new value in Scene Builder
	@Override
	public Paint getReleasedColor() {
		return releasedColorProperty().get();
	}
	@Override
	public void setReleasedColor(Paint c) {
		releasedColorProperty().set(c);
	}




	@Override
	public ObjectProperty<Paint> pressedColorProperty() {
		if (pressedColor == null)
			pressedColor = new SimpleObjectProperty<>(
			                                          this,
			                                          "pressedColor",
			                                          Color.CHARTREUSE);
		return pressedColor;
	}
	private ObjectProperty<Paint> pressedColor;
	//TODO Comment out the get/set methods after setting new value in Scene Builder
	@Override
	public Paint getPressedColor() {
		return pressedColorProperty().get();
	}
	@Override
	public void setPressedColor(Paint c) {
		pressedColorProperty().set(c);
	}
}
