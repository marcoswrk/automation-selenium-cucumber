# Automacao Selenium com Cucumber

Projeto de automacao de testes E2E com Selenium WebDriver, Cucumber, JUnit
Platform e Maven. Os cenarios validam o cadastro, login, logout e mensagens de
erro do site [Automation Exercise](https://www.automationexercise.com/).

## Pre-requisitos

- Java 26 ou uma versao compativel com o `pom.xml`;
- Maven 3.9 ou superior;
- Google Chrome;
- acesso a internet.

O WebDriverManager configura o ChromeDriver automaticamente.

## Credenciais de teste

O teste de login usa uma conta previamente cadastrada no Automation Exercise.
As credenciais nao ficam no repositorio: sao lidas das variaveis de ambiente
`TEST_EMAIL` e `TEST_PASSWORD`.

No PowerShell, configure-as para a sessao atual:

```powershell
$env:TEST_EMAIL = "seu-email-de-teste@example.com"
$env:TEST_PASSWORD = "sua-senha-de-teste"
```

Para tornar a configuracao persistente para novos terminais:

```powershell
[Environment]::SetEnvironmentVariable("TEST_EMAIL", "seu-email-de-teste@example.com", "User")
[Environment]::SetEnvironmentVariable("TEST_PASSWORD", "sua-senha-de-teste", "User")
```

Abra um novo terminal depois de usar `SetEnvironmentVariable`.

## Executando os testes

Na raiz do projeto, execute todos os cenarios:

```powershell
mvn clean test
```

Para executar apenas o cenario de cadastro:

```powershell
mvn "-Dtest=runner.RunCucumberTest" "-Dcucumber.filter.name=Register User" test
```
## Estrutura

```text
src/test/java/
	core/       # DriverFactory e acoes comuns do BasePage
	hooks/      # Inicializacao e encerramento do navegador
	pages/      # Page Objects
	runner/     # Runner JUnit Platform do Cucumber
	steps/      # Step Definitions
	utils/      # Dados de teste e credenciais
src/test/resources/
	registration.feature
```

## Observacoes

- O cenario de cadastro usa dados gerados pelo DataFaker.
- A conta usada nos cenarios de login precisa existir no ambiente de teste.