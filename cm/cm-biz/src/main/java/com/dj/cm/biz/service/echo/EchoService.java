package com.dj.cm.biz.service.echo;

import com.dj.cm.model.entity.Echo;

import java.util.List;

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

    /**
     * Do echo by id.
     *
     * @param id echo id
     * @param n  times to echo
     * @return echo string
     */
    String doEcho(Long id, int n);

    /**
     * Get all echos;
     *
     * @return @{@link Echo} list
     */
    Iterable<Echo> getAllEchos();

    /**
     * Get echo by id;
     *
     * @param id get by id
     * @return echo or null if not found
     */
    Echo getEchoById(Long id);
}
