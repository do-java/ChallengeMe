package com.dj.cm.event.echo;


import com.dj.cm.model.entity.Echo;

public class EchoDeletedEvent extends EchoEvent {

	private final Echo value;

	public EchoDeletedEvent(Echo value) {
		super(EventType.DELETED);
		this.value = value;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("EchoDeletedEvent{");
		sb.append("value=").append(value);
		sb.append('}');
		return sb.toString();
	}
}
