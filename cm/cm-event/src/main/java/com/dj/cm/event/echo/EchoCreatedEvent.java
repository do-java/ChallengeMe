package com.dj.cm.event.echo;


import com.dj.cm.model.entity.Echo;

public class EchoCreatedEvent extends EchoEvent {

	private final Echo value;

	public EchoCreatedEvent(Echo value) {
		super(EventType.CREATED);
		this.value = value;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("EchoCreatedEvent{");
		sb.append("value=").append(value);
		sb.append('}');
		return sb.toString();
	}
}
