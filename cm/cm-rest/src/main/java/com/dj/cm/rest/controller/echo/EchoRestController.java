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
 * GET http://localhost:8080/rest/echo/do?s=bbb&n=9
 * GET http://localhost:8080/rest/echo/do/1?n=9
 * GET http://localhost:8080/rest/echo/1
 * GET http://localhost:8080/rest/echo/all
 *
 */
@RestController
@RequestMapping("rest/echo")
public class EchoRestController {

	@Autowired
	private EchoService echoService;

	@GetMapping("/do")
	public String doEcho(@RequestParam(defaultValue = "aaa") String s, @RequestParam(defaultValue = "3") int n) {
		return echoService.doEcho(s, n);
	}

	@GetMapping("/do/{id}")
	public String doEcho(@RequestParam(defaultValue = "3") int n, @PathVariable Long id) {
		return echoService.doEcho(id, n);
	}

	@GetMapping("{id}")
	public Echo getById(@PathVariable Long id) {
		return echoService.getEchoById(id);
	}

	@GetMapping("/all")
	public Iterable<Echo> getAll() {
		return echoService.getAllEchos();
	}

}