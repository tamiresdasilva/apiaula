package br.com.ulbra.apiaula;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class Teste {

    @GetMapping
    public String hello (){
        return "<h1>TDE finalizada!</h1>";
    }
}
