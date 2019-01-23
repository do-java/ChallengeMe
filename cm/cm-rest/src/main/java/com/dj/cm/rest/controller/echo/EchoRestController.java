package com.dj.cm.rest.controller.echo;

import com.dj.cm.biz.service.echo.EchoService;
import com.dj.cm.model.entity.Echo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
 * fetch('/rest/echos', { method: 'POST', headers: { 'Content-Type': 'application/json' }, body: JSON.stringify({ value: 'ccc' , id: null}) }).then(result => result.json().then(console.log))
 * fetch('/rest/echos/1', { method: 'PUT', headers: { 'Content-Type': 'application/json' }, body: JSON.stringify({ value: 'aaa1' }) }).then(result => result.json().then(console.log))
 * fetch('/rest/echos/9', { method: 'DELETE' }).then(result => console.log(result))
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

    @PostMapping
    public Echo create(@RequestBody Echo echo) {
        return echoService.createEcho(echo);
    }

    @PutMapping("{id}")
    public Echo update(@PathVariable Long id, @RequestBody Echo echo) {
        echo.setId(id); // Update id from path
        return echoService.updateEcho(echo);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        echoService.deleteEcho(id);
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