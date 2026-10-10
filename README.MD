# Cross-Stack E2E Test Automation

Projeto de automação de testes end-to-end para o site [Automation Exercise](https://www.automationexercise.com/), com implementações em Java/Selenium e TypeScript/Playwright em branches separadas.

## Implementações

| Branch | Tecnologias | Objetivo |
| --- | --- | --- |
| [`main`](https://github.com/marcoswrk/cross-stack-e2e-automation/tree/main) | Java, Selenium WebDriver, Cucumber, JUnit Platform e Maven | Executar cenários BDD de interface com Selenium. |
| [`feat/playwright-typescript`](https://github.com/marcoswrk/cross-stack-e2e-automation/tree/feat/playwright-typescript) | TypeScript, Playwright Test e Faker | Executar testes E2E com Playwright, fixtures e Page Objects. |

Cada branch contém a estrutura e as dependências da respectiva implementação.

### Pré-requisitos

- Node.js LTS e npm;
- Acesso à internet;
- Uma conta de teste já cadastrada no Automation Exercise para os cenários de login.

### Instalação e execução

Na raiz do repositório:

```powershell
npm ci
npx playwright install chromium
npm test
```

Os cenários de login e o teste de e-mail existente usam a conta indicada pelas variáveis `PLAYWRIGHT_USER` e `PLAYWRIGHT_PASS`. Configure-as no terminal antes de executar os testes:

```powershell
$env:PLAYWRIGHT_USER = "seu-email-de-teste@example.com"
$env:PLAYWRIGHT_PASS = "sua-senha-de-teste"
npm test
```

Para listar os testes sem executá-los:

```powershell
npx playwright test --list
```

Para executar somente um arquivo:

```powershell
npx playwright test tests/login.spec.ts
npx playwright test tests/registration.spec.ts
```

O relatório HTML é gerado em `playwright-report/`. Para abri-lo depois da execução:

```powershell
npx playwright show-report
```

### Estrutura do Playwright

```text
tests/
├── login.spec.ts
├── registration.spec.ts
├── pages/       # Page Objects e interações com a interface
└── utils/       # Credenciais e geração de dados de teste
package.json
package-lock.json
playwright.config.ts
tsconfig.json
```