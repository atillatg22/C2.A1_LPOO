# Invasão ao Abrigo — Exercício Avaliativo da C2

Simulação em Java de uma invasão de criaturas a um abrigo de sobreviventes, desenvolvida para a disciplina **Linguagem de Programação Orientada a Objetos** (FAESA, turma 4SC1).

O programa roda sozinho, sem interação do usuário: a cada rodada os sobreviventes atacam uma criatura e uma criatura contra-ataca o abrigo, até que um dos lados vença.

## Como executar

**Eclipse:** importe a pasta como projeto Java existente e execute `Main.java`.

**Terminal** (a partir da raiz do repositório):

```bash
javac -encoding UTF-8 -d bin $(find src -name "*.java")
java -p bin -m lpoo_c2.a1/lpoo_c2.a1.Main
```

## Estrutura

```
src/
├── module-info.java
└── lpoo_c2/a1/
    ├── Abrigo.java      Abrigo dos sobreviventes
    ├── Criatura.java    Classe abstrata base das criaturas
    ├── Zumbi.java       Criatura que causa dano igual ao poder de ataque
    ├── Mutante.java     Criatura que causa o dobro do poder de ataque
    └── Main.java        Montagem do cenário e loop do jogo
```

```
            Criatura (abstrata)
            - nome, pontosVida, poderAtaque, derrotada
            - static quantDerrotadas
            + receberDano(dano)
            + atacar(Abrigo)  «abstrato»
                  ▲          ▲
                  │          │
               Zumbi      Mutante
```

## Regras do jogo

| Elemento | Configuração inicial |
|---|---|
| Abrigo | 100 pontos de vida, 5 defesas |
| Zumbis (3) | 30 pontos de vida, poder de ataque 10 |
| Mutantes (2) | 20 pontos de vida, poder de ataque 15 |

1. **Ataque dos sobreviventes:** sorteia um dano entre 5 e 20 e aplica a uma criatura aleatória. Se a vida dela chegar a zero, ela é removida da lista e o contador de derrotadas é atualizado.
2. **Contra-ataque:** uma criatura ainda ativa é sorteada e ataca o abrigo.
3. O jogo segue enquanto houver criaturas e o abrigo não estiver destruído.

### Como o abrigo absorve dano

As defesas funcionam como "vidas extras". Quando os pontos de vida chegam a zero ou ficam negativos e ainda há defesa, o abrigo perde uma defesa e recupera a vida inicial, somando o excesso de dano. Exemplo com vida inicial 20 e 2 defesas:

| Antes (vida / defesa) | Dano | Depois (vida / defesa) |
|---|---|---|
| 20 / 2 | 10 | 10 / 2 |
| 10 / 2 | 10 | 20 / 1 |
| 3 / 2 | 10 | 13 / 1 (−7 + 20) |
| 5 / 0 | 10 | 0 / 0 — destruído |

## Conceitos de POO aplicados

**Classe abstrata** — `Criatura` não pode ser instanciada: ela define o que toda criatura tem (nome, vida, ataque) e declara `atacar(Abrigo)` sem implementá-lo, obrigando cada subclasse a definir seu próprio ataque.

**Herança** — `Zumbi` e `Mutante` estendem `Criatura` e reaproveitam atributos, construtor e `receberDano`, escrevendo apenas o que as diferencia.

**Polimorfismo** — a `Main` guarda todas as criaturas em um `ArrayList<Criatura>` e chama `criatura.atacar(abrigo)` sem saber o tipo concreto. Em tempo de execução, o Java executa a versão do `Zumbi` (dano = ataque) ou do `Mutante` (dano = 2 × ataque).

**Encapsulamento** — todos os atributos são `private` e só há métodos `get`. O estado muda apenas pelos métodos de regra de negócio (`receberDano`, `receberAtaque`), que garantem invariantes como "pontos de vida nunca ficam negativos".

**Atributo de classe (`static`)** — `quantDerrotadas` pertence à classe `Criatura`, e não a cada objeto: é um único contador compartilhado. Ele é incrementado somente na primeira vez que uma criatura é derrotada, garantindo que cada uma seja contada uma única vez.

**Sobrescrita de métodos (`@Override`)** — cada subclasse sobrescreve `atacar`, e a anotação faz o compilador verificar que a assinatura corresponde ao método da superclasse.

**Coleções** — `ArrayList<Criatura>` armazena as criaturas ativas, permitindo sorteio por índice e remoção das derrotadas.

## Autores

1. Atilla Tayller Gama da Rocha — 25110254
2. Luiz Henrique Dominicini Becalli Almeida de Faria — 25110553

Professora: Cinthia Cristina Lucio Caliari · FAESA 2026
