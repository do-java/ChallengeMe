package com.dj.cm.rest.controller.echo;

import com.dj.cm.biz.service.echo.EchoService;
import com.dj.cm.model.entity.Echo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * Usage:
 * <p>
 * GET http://localhost:8080/rest/echos/do?s=bbb&n=9
 * GET http://localhost:8080/rest/echos/1/do?n=9
 * GET http://localhost:8080/rest/echos
 * GET http://localhost:8080/rest/echos/1
 *
 */
@RestController
@RequestMapping("rest/echos")
public class EchoRestController {

    @Autowired
    private EchoService echoService;

    @GetMapping
    public Iterable<Echo> getAll() {
        return echoService.getAllEchos();
    }

    @GetMapping("{id}")
    public Echo getById(@PathVariable Long id) {
        return echoService.getEchoById(id);
    }

    @GetMapping("/do")
    public String doEcho(@RequestParam(defaultValue = "aaa") String s, @RequestParam(defaultValue = "3") int n) {
        return echoService.doEcho(s, n);
    }

    @GetMapping("{id}/do")
    public String doEcho(@RequestParam(defaultValue = "3") int n, @PathVariable Long id) {
        return echoService.doEcho(id, n);
    }

}