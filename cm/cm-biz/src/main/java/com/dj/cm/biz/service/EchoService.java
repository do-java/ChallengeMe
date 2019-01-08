package com.dj.cm.biz.service;

/**
 * Echo Service.
 */
public interface EchoService {

	/**
	 * Do echo of string.
	 *
	 * @param s value for string
	 * @param n times to echo
	 * @return echo string
	 */
	String doEcho(String s, int n);
}
