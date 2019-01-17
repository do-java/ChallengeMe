package com.dj.cm.event.model.echo;


import com.dj.cm.event.model.common.EventType;
import com.dj.cm.model.entity.Echo;

public class EchoUpdatedEvent extends EchoEvent {

	private final Echo oldValue;
	private final Echo newValue;

	public EchoUpdatedEvent(Echo oldValue, Echo newValue) {
		super(EventType.UPDATED);
		this.oldValue = oldValue;
		this.newValue = newValue;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("EchoUpdatedEvent{");
		sb.append("oldValue=").append(oldValue);
		sb.append(", newValue=").append(newValue);
		sb.append('}');
		return sb.toString();
	}
}
