package com.dj.cm.biz.service.echo;

import com.dj.cm.biz.service.exception.NotFoundBizException;
import com.dj.cm.model.entity.Echo;

/**
 * Echo Service.
 */
public interface EchoService {

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
     * @return echo
     * @throws NotFoundBizException if not found
     */
    Echo getEchoById(Long id);

    /**
     * Create echo.
     *
     * @param echo echo to create
     * @return created echo
     */
    Echo createEcho(Echo echo);

    /**
     * Update echo.
     *
     * @param echo echo to update
     * @return updated echo
     * @throws NotFoundBizException if not found
     */
    Echo updateEcho(Echo echo);

    /**
     * Delete echo.
     *
     * @param id echo id to delete
     */
    void deleteEcho(Long id);

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
     * @throws NotFoundBizException if not found
     */
    String doEcho(Long id, int n);
}
