package com.dj.cm.common.util;

import java.util.StringJoiner;

public final class Base64DataUrl {

	private final String type;
	private final String subtype;
	private final String data;

	public Base64DataUrl(String type, String subtype, String data) {
		this.type = type;
		this.subtype = subtype;
		this.data = data;
	}

	public String getType() {
		return type;
	}

	public String getSubtype() {
		return subtype;
	}

	public String getData() {
		return data;
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", Base64DataUrl.class.getSimpleName() + "[", "]")
				.add("type='" + type + "'")
				.add("subtype='" + subtype + "'")
				.toString();
	}
}
