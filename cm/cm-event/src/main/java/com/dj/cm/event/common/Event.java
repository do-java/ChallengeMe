package com.dj.cm.event.common;

import java.io.Serializable;

/**
 * Basic Event.
 */
public class Event implements Serializable {

	private final EventType type;

	public Event(EventType type) {
		this.type = type;
	}

	public EventType getType() {
		return type;
	}

	public enum EventType {
		CREATED, UPDATED, DELETED
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("Event{");
		sb.append("type=").append(type);
		sb.append('}');
		return sb.toString();
	}

}
