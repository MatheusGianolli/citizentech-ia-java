package com.citizentech.api_java;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import java.net.URI;
import java.util.Map;
import java.util.List;

@Service
public class AnalisadorDeRiscoIA {

    private final String apiKey = "COLE_SUA_CHAVE_AQUI";
    // Via expressa da API: modelo Lite para evitar as filas de processamento
    private final String url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-flash-lite-latest:generateContent?key=";

    public String analisarRisco(String regiao, int ocorrencias) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("User-Agent", "CitizenTech-App/1.0"); // Disfarce de sistema real para o firewall do Google

        String promptFinal = "Atue como um analisador de dados de segurança do CitizenTech. " +
                "Região: " + regiao + " | Ocorrências: " + ocorrencias + ". " +
                "Calcule o risco para uma mulher circulando nesta região. " +
                "Responda EXATAMENTE neste formato, sem saudações, sem introduções e sem texto extra:\n" +
                "Nível de Risco: [Baixo, Moderado ou Alto]\n" +
                "Dica 1: [Dica prática e direta]\n" +
                "Dica 2: [Dica prática e direta]";

        Map<String, Object> body = Map.of(
                "contents", List.of(
                        Map.of("parts", List.of(
                                Map.of("text", promptFinal)
                        ))
                )
        );

        HttpEntity request = new HttpEntity<>(body, headers);
        URI uriBlindada = URI.create(url + apiKey);

        // Resiliência: Se o servidor do Google der 503, o código tenta automaticamente até 3 vezes
        for (int tentativa = 1; tentativa <= 3; tentativa++) {
            try {
                ResponseEntity response = restTemplate.postForEntity(uriBlindada, request, Map.class);
                Map corpoResposta = (Map) response.getBody();
                List candidates = (List) corpoResposta.get("candidates");
                Map primeiroCandidato = (Map) candidates.get(0);
                Map content = (Map) primeiroCandidato.get("content");
                List parts = (List) content.get("parts");
                Map primeiraParte = (Map) parts.get(0);

                return (String) primeiraParte.get("text");
            } catch (Exception e) {
                // Captura a recusa por excesso de tráfego e segura a requisição por 2 segundos antes de tentar de novo
                if (e.getMessage() != null && e.getMessage().contains("503")) {
                    System.out.println("Google deu 503. Tentativa " + tentativa + " falhou. Segurando 2 segundos...");
                    if (tentativa == 3) return "Erro 503: Os servidores do Google estão superlotados no momento.";
                    try { Thread.sleep(2000); } catch (InterruptedException ie) {}
                } else {
                    return "Erro real da IA: " + e.getMessage();
                }
            }
        }
        return "Falha ao analisar risco.";
    }
}