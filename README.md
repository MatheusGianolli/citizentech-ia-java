# 🛡️ CitizenTech - Inteligência Artificial para Segurança Urbana

> **Plataforma de análise de risco situacional utilizando IA Generativa (Google Gemini) para emissão de alertas e dicas de segurança em tempo real.**

---

## 👥 Equipe Desenvolvedora
| RM | Nome |
| :--- | :--- |
| **565258** | Matheus Gianolli |
| **563379** | Enzo Xavier Coelho |
| **564995** | Gustavo Ribeiro Permagnani |
| **566457** | Larissa Juvenal de Magalhães |
| **565568** | Julia Menezes |

---

## 🎯 Sobre o Projeto (Critério 01 - Aplicação ao Problema)
O **CitizenTech** é uma solução tecnológica focada na segurança pública preventiva, com ênfase na proteção de mulheres em vias urbanas. O sistema recebe parâmetros dinâmicos (como a região da cidade e o número histórico de ocorrências) e utiliza Inteligência Artificial para calcular o nível de risco local, gerando diretrizes e dicas de segurança hiper-personalizadas para o cenário informado.

---

## 🧠 Integração com IA Generativa (Critérios 02 e 03)
Este projeto **não utiliza dados simulados ou condicionais estáticas (`if/else`)**. O motor de análise é 100% integrado à API oficial do Google Gemini.

* **Modelo Utilizado:** `gemini-flash-lite-latest` (Escolhido via auditoria de endpoints pela sua alta velocidade e menor latência em sistemas de produção).
* **Conexão Nativa:** Implementada através do `HttpClient` nativo do Java 17, garantindo uma comunicação direta e à prova de interferências de cabeçalhos.
* **Engenharia de Prompt Avançada:** A IA foi configurada com limites estritos de formatação (System Prompting). Ela está instruída a atuar como uma central de segurança analítica, processando o contexto dinâmico e retornando exclusivamente a classificação do risco (Baixo, Moderado, Alto) e dicas práticas, sem alucinações ou diálogos genéricos.
* **Resiliência (Tolerância a Falhas):** O backend possui um mecanismo de *retry* automático que tenta reconectar até 3 vezes caso os servidores do Google apresentem instabilidade (Erro 503).

---

## 📸 Demonstração Visual
*(Arraste e solte os prints da aplicação rodando nestes espaços abaixo)*

**1. Interface do Sistema (Frontend)**
> <img width="1855" height="953" alt="print1" src="https://github.com/user-attachments/assets/050ddcc8-ec79-4526-a893-52beb45165f5" />
`

**2. IA Processando um Cenário Real (Ex: Centro)**
> <img width="1917" height="962" alt="print2" src="https://github.com/user-attachments/assets/991a3239-74cb-4914-84c6-e82fee42b7d4" />


**3. IA Adaptando-se a Cenários Extremos/Incomuns**  PODE SER USADO QUALQUER LOCALIZAÇÃO DO MUNDO(OU NÃO) QUE A IA IRA RESPONDER E SE ADAPTAR
> <img width="1895" height="966" alt="rint3" src="https://github.com/user-attachments/assets/0f6898ba-0659-423d-8209-2507dd303839" />


---

## ⚙️ Arquitetura e Tecnologias (Critérios 04 e 05)
O projeto foi desenvolvido sob uma arquitetura robusta, separando claramente as responsabilidades de visualização e processamento:
* **Backend:** Java 17 com Spring Boot (Servidor Tomcat embutido na porta 8080).
* **Integração:** `java.net.http.HttpClient` e `Jackson (ObjectMapper)` para *parsing* seguro do JSON.
* **Frontend:** HTML5, CSS3 e JavaScript assíncrono (Fetch API), servido de forma nativa a partir da pasta `/resources/static`.
* **Segurança de Rota:** Uso de `@CrossOrigin` para garantir a comunicação impecável entre a interface e os endpoints da API.

---

## 🚨 GUIA DO PROFESSOR: Como Executar o Projeto (Critério 06)

> **ATENÇÃO - PRÁTICAS RESPONSÁVEIS (CRITÉRIO 06):** 
> Seguindo as diretrizes de Segurança da Informação e Governança de Dados em nuvem, a nossa **API Key verdadeira foi propositalmente removida** deste repositório público para evitar varreduras de *bots*, vazamentos e bloqueios na Google Cloud.

Para testar o projeto e comprovar a funcionalidade da IA em sua máquina, siga este passo a passo:

**Passo 1: Inserir a Chave Privada**
1. Copie a nossa chave da API oficial do Google Gemini (enviada de forma privada nos **comentários da entrega oficial do trabalho** no arquivo txt enviado).
2. No código-fonte baixado, abra o arquivo:
   `src/main/java/com/citizentech/api_java/AnalisadorDeRiscoIA.java`
3. Localize a linha 16 e substitua a string de placeholder pela chave verdadeira:
   `private final String apiKey = "COLE_A_CHAVE_AQUI";`

**Passo 2: Rodar a Aplicação**
1. Abra o projeto na sua IDE (recomendamos o IntelliJ IDEA).
2. Execute o arquivo principal `ApiJavaApplication.java` para iniciar o servidor Spring Boot.
3. Aguarde o terminal indicar que o Tomcat iniciou na porta `8080`.

**Passo 3: Acessar o Sistema**
1. Abra o navegador de sua preferência.
2. Acesse: `http://localhost:8080/`
3. A interface do CitizenTech carregará automaticamente. Insira uma região, defina um número de ocorrências e clique em "Analisar Risco" para ver a IA Generativa operando em tempo real.
