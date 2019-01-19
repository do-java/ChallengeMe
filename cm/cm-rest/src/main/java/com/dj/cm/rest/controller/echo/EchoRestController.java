package com.dj.cm.rest.controller.echo;

import com.dj.cm.biz.service.echo.EchoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * Usage:
 *
 * http://localhost:8080/rest/echo?s=bbb&n=9
 */
@RestController
@RequestMapping("rest/echo")
public class EchoRestController {

    @Autowired
    private EchoService echoService;

    @GetMapping
    public String doEcho(@RequestParam(defaultValue = "aaa") String s, @RequestParam(defaultValue = "3") int n) {
        return echoService.doEcho(s, n);
    }

    @GetMapping("{id}")
    public String getEcho(@RequestParam(defaultValue = "3") int n, @PathVariable Long id) {
        return echoService.getEcho(id, n);
    }

}