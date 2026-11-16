# Sistema Automatizado de Pedágio – Sprint 3

Implementação parcial em Java do sistema especificado nas Sprints 1 e 2.

| | |
|---|---|
| **Aluno** | Gustavo Rodrigues Monteiro |
| **Matrícula** | 2024433167 |
| **Disciplina** | DCC603 – Engenharia de Software |
| **Professor** | Eduardo Figueiredo |
| **Instituição** | Universidade Federal de Minas Gerais (UFMG) |

## Pré-requisitos

- JDK 17 ou superior
- Apache Maven 3.8 ou superior

## Como executar

Na raiz do projeto (pasta que contém o `pom.xml`):

```bash
# Compilar e executar os testes automatizados
mvn clean test

# Compilar e executar a demonstração (classe Main)
mvn compile exec:java
```

A classe `Main` processa três passagens demonstrativas e, ao final, exibe o total de transações registradas:

| Veículo | Situação | Resultado esperado |
|---|---|---|
| Carro (ABC1D23) | Tag com saldo R$ 30,00 | Identificação `TAG`, status `PAGA`, saldo restante R$ 20,00 |
| Ônibus (BUS2E34) | Tag com saldo R$ 5,00 | Identificação `TAG`, status `PENDENTE`, saldo mantido em R$ 5,00 |
| Motocicleta (MOT3F45) | Sem tag | Identificação `PLACA_INFORMADA`, status `PENDENTE` |

Saída final: `Transacoes registradas: 3`.

## Funcionalidades implementadas

- **RF01** – Registrar a passagem do veículo (simulada).
- **RF02** – Identificar o veículo por tag (simulada).
- **RF06** – Classificar o veículo pela categoria informada.
- **RF07** – Calcular a tarifa de acordo com a categoria.
- **RF08** – Cobrar utilizando o saldo pré-pago da tag; com saldo insuficiente, a cobrança fica pendente.
- **RF09** – Gerar cobrança pendente para veículo sem tag, vinculada à placa.
- **RF10** – Registrar a transação em memória (data e hora, veículo, categoria, valor, forma de identificação e situação da cobrança).
- **RNF04** – Processar 20 passagens consecutivas sem perda de registros (verificado por teste).

Tarifas fictícias utilizadas:

| Categoria | Tarifa |
|---|---|
| Motocicleta | R$ 5,00 |
| Carro | R$ 10,00 |
| Ônibus | R$ 20,00 |
| Caminhão | R$ 30,00 |

## Limitações

- **RF03:** quando não há tag, a identificação por placa é **simulada** a partir da placa informada na entrada; não há captura de imagem nem OCR.
- **RF04, RF05, RF11 e RF12** não foram implementados: não há avaliação manual, consulta ao licenciamento, monitoramento de equipamentos nem emissão de alertas.
- **RF06:** a categoria é informada na entrada; não existe classificador automático (por isso o RNF02 não se aplica).
- **RNF03:** os registros ficam em memória e são perdidos ao encerrar o programa; não há persistência após falha.
- As cobranças pendentes não têm pagamento posterior, cobrança externa nem tratamento de inadimplência (fora do escopo definido na Sprint 1).

## Arquitetura

```
src/main/java/br/ufmg/pedagio/
├── Main.java        demonstração pelo terminal
├── model/           entidades e enums
│   ├── Veiculo, Tag, Passagem, Tarifa, Transacao
│   └── CategoriaVeiculo, FormaIdentificacao, StatusCobranca
└── service/         regras de negócio e orquestração
    ├── PassagemService        coordena o processamento da passagem
    ├── IdentificacaoService   identifica por tag ou placa informada
    ├── ClassificacaoService   obtém a categoria do veículo
    ├── CobrancaService        calcula a tarifa e processa a cobrança
    └── TransacaoService       armazena as transações em memória
```

Fluxo de uma passagem em `PassagemService.processar`: registrar passagem → identificar → classificar → calcular tarifa → cobrar → registrar transação. A ordem é a mesma do Diagrama de Atividades da Sprint 2.

## Simplificações em relação à Sprint 2

As diferenças abaixo decorrem da escolha por uma implementação parcial, focada no fluxo principal de registro e cobrança da passagem:

- **Diagrama de Componentes:** o `PassagemService` coordena diretamente os demais serviços, em vez da cadeia de dependências do diagrama. Assim cada serviço fica independente e pode ser testado isoladamente. O Serviço de Monitoramento não foi implementado.
- **Diagrama de Sequência:** a coordenação do fluxo, que no diagrama fica no objeto `Passagem`, é feita pelo `PassagemService`. O `BancoDados` foi substituído por uma lista em memória no `TransacaoService`.
- **Cobrança por tag:** segue o Diagrama de Atividades e o RF08. Com saldo insuficiente, a cobrança é registrada como pendente. O Diagrama de Sequência mostra apenas os casos "tag válida" e "tag não encontrada".
- **Diagrama de Classes:**
  - `Transacao` armazena o valor da tarifa e obtém data/hora e veículo pela `Passagem`.
  - Situação da cobrança e forma de identificação são enums (`StatusCobranca`, `FormaIdentificacao`), e não `String`.
  - Toda `Passagem` tem um veículo informado, já que a identificação manual não foi implementada.
- **Não implementados nesta sprint:** `PracaPedagio`, `Equipamento` e subclasses (`Camera`, `LeitorTag`, `SensorPassagem`), `Alerta`, `RevisaoManual` e `OperadorControle`, além do OCR e da consulta ao licenciamento.

## Testes automatizados (JUnit 5)

Os 12 testes estão em `src/test/java` e cobrem:

- `CobrancaServiceTest` (8): tarifa de cada categoria; cobrança com saldo suficiente, exato e insuficiente; cobrança sem tag.
- `PassagemServiceTest` (3): fluxo completo com tag, fluxo sem tag e 20 passagens consecutivas sem perda de registros (RNF04).
- `TransacaoServiceTest` (1): registro e consulta de transações, com lista protegida contra alteração externa.

## Documentação das sprints anteriores

A pasta `docs/` contém os documentos da Sprint 1 (planejamento e requisitos) e da Sprint 2 (arquitetura e modelagem UML).

## Uso de Inteligência Artificial

Ferramentas de IA generativa foram utilizadas como apoio na elaboração do código, dos testes automatizados e desta documentação. Todos os artefatos foram revisados, executados e validados pelo aluno.
