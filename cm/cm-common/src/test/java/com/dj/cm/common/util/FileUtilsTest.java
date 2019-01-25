package com.dj.cm.common.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class FileUtilsTest {

	@Test
	public void parseBase64DataUrl() {
		// Given
		String dataUrl = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAArwAAA";

		// When
		Base64DataUrl result = FileUtils.parseBase64DataUrl(dataUrl);

		// Then
		assertEquals("image", result.getType());
		assertEquals("png", result.getSubtype());
		assertEquals("iVBORw0KGgoAAAANSUhEUgAAArwAAA", result.getData());
	}

	@Test
	public void parseBase64DataUrl_WithDelimitersInData() {
		// Given
		String dataUrl = "data:image/png;base64,iVBORw0KGgoAAAAN:SUh/EUgAA,ArwAAA";

		// When
		Base64DataUrl result = FileUtils.parseBase64DataUrl(dataUrl);

		// Then
		assertEquals("image", result.getType());
		assertEquals("png", result.getSubtype());
		assertEquals("iVBORw0KGgoAAAAN:SUh/EUgAA,ArwAAA", result.getData());
	}

	@Test(expected = IllegalArgumentException.class)
	public void parseBase64DataUrl_IllegalArgumentException() {
		// Given
		String dataUrl = "xxx";

		// When
		FileUtils.parseBase64DataUrl(dataUrl);

		// Then
		// Exception expected
	}
}