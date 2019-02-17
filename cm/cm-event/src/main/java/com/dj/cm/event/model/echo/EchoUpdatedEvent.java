package com.dj.cm.event.model.echo;


import com.dj.cm.event.model.common.EventType;
import com.dj.cm.model.entity.Echo;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class EchoUpdatedEvent extends EchoEvent {

	private final Echo oldValue;
	private final Echo newValue;

	public EchoUpdatedEvent(Echo oldValue, Echo newValue) {
		super(EventType.UPDATED);
		this.oldValue = oldValue;
		this.newValue = newValue;
	}
}
