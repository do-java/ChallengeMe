package com.dj.cm.biz.service.echo;

/**
 * com.dj.cm.model.entity.Echo Service
 */
public interface EchoService {

    /**
     * Do echo of string
     *
     * @param s value for string
     * @param n times to echo
     * @return echo string
     */
    String doEcho(String s, int n);

    String getEcho(Long id, int i);
}