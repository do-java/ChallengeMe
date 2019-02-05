package com.dj.cm.common.util;

import org.springframework.util.StringUtils;

/**
 * File Utils.
 */
public final class FileUtils {


	/**
	 * Parse base64 data url.
	 *
	 * @param dataUrl data url in format https://tools.ietf.org/html/rfc2397, e.g "data:image/png;base64,iVBORw0KGgoAAAANSUh..."
	 * @return parsed {@link Base64DataUrl} object.
	 * @throws IllegalArgumentException on invalid dataUrl format
	 */
	public static final Base64DataUrl parseBase64DataUrl(String dataUrl) {
		// Validate
		if (StringUtils.isEmpty(dataUrl)) {
			throw new IllegalArgumentException("dataUrl is empty");
		}

		// Split
		String[] dataUrlSplit = dataUrl.split("[:,;/]", 5);

		if (dataUrlSplit.length < 5) {
			final String dataSubstring = dataUrl.substring(0, dataUrl.length() < 45 ? dataUrl.length() : 45);
			throw new IllegalArgumentException(
					String.format("Invalid data format, Expected: \"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgA...\", Was: \"%s...\"", dataSubstring));
		}

		// Return result
		return new Base64DataUrl(dataUrlSplit[1], dataUrlSplit[2], dataUrlSplit[4]);
	}
}
