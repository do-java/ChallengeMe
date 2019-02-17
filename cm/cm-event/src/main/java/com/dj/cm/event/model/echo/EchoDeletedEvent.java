package com.dj.cm.event.model.echo;


import com.dj.cm.event.model.common.EventType;
import com.dj.cm.model.entity.Echo;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class EchoDeletedEvent extends EchoEvent {

	private final Echo value;

	public EchoDeletedEvent(Echo value) {
		super(EventType.DELETED);
		this.value = value;
	}
}
