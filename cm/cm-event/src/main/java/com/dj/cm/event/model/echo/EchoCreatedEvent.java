package com.dj.cm.event.model.echo;


import com.dj.cm.event.model.common.EventType;
import com.dj.cm.model.entity.Echo;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class EchoCreatedEvent extends EchoEvent {

	private final Echo value;

	public EchoCreatedEvent(Echo value) {
		super(EventType.CREATED);
		this.value = value;
	}
}
