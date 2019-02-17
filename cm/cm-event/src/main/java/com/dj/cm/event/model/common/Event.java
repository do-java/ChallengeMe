package com.dj.cm.event.model.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

/**
 * Basic Event.
 */
@AllArgsConstructor
@Getter
@ToString
public class Event implements Serializable {

	private final EventType type;
}
