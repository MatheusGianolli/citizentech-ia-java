package com.citizentech.api_java;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class RiscoController {

    @Autowired
    private AnalisadorDeRiscoIA analisadorDeRiscoIA;

    // Essa é a URL local que vamos usar para testar a sua IA
    @GetMapping("/testar-ia")
    public String testarMinhaIA(@RequestParam String regiao, @RequestParam int ocorrencias) {

        // Aqui ele chama o arquivo que criamos no Passo 2
        return analisadorDeRiscoIA.analisarRisco(regiao, ocorrencias);
    }
}
