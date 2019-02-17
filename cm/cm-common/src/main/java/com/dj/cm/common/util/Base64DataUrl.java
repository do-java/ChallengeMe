package com.dj.cm.common.util;

import lombok.Value;

@Value
public final class Base64DataUrl {

	private final String type;
	private final String subtype;
	private final String data;
}
