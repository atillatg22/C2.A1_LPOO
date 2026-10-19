package lpoo_c2.a1;

import java.util.ArrayList;
import java.util.Random;

public class Main {

	public static void main(String[] args) {
		Random random = new Random();

		Abrigo abrigo = new Abrigo(100, 5);

		ArrayList<Criatura> criaturas = new ArrayList<>();
		criaturas.add(new Zumbi("Zumbi 1", 30, 10));
		criaturas.add(new Zumbi("Zumbi 2", 30, 10));
		criaturas.add(new Zumbi("Zumbi 3", 30, 10));
		criaturas.add(new Mutante("Mutante 1", 20, 15));
		criaturas.add(new Mutante("Mutante 2", 20, 15));

		int rodada = 1;

		while (!criaturas.isEmpty() && !abrigo.estaDestruido()) {
			System.out.println("========== RODADA " + rodada + " ==========\n");

			int dano = random.nextInt(16) + 5;
			Criatura alvo = criaturas.get(random.nextInt(criaturas.size()));

			boolean derrotada = alvo.receberDano(dano);

			System.out.println("Os sobreviventes atacaram!");
			System.out.println("Alvo: " + alvo.getClass().getSimpleName() + " " + alvo.getNome());
			System.out.println("Dano causado: " + dano);
			System.out.println();
			System.out.println("--- SITUACAO DA CRIATURA ---");
			System.out.println("Nome: " + alvo.getNome());
			System.out.println("Pontos de vida: " + alvo.getPontosVida());
			System.out.println("Poder de ataque: " + alvo.getPoderAtaque());
			System.out.println("Total de criaturas derrotadas: " + Criatura.getQuantDerrotadas());
			System.out.println();

			if (derrotada) {
				criaturas.remove(alvo);
			}

			if (!criaturas.isEmpty()) {
				Criatura atacante = criaturas.get(random.nextInt(criaturas.size()));

				int danoAtaque = atacante.atacar(abrigo);

				System.out.println(atacante.getClass().getSimpleName() + ": " + atacante.getNome() + " atacou o abrigo!");
				System.out.println("Dano do ataque: " + danoAtaque);
				System.out.println(abrigo.estaDestruido() ? "O abrigo foi destruído" : "O abrigo bloqueou o ataque!");
				System.out.println();
				System.out.println("--- SITUACAO DO ABRIGO ---");
				System.out.println("Pontos de vida: " + abrigo.getPontosVida());
				System.out.println("Defesas restantes: " + abrigo.getDefesa());
				System.out.println();
			}

			rodada++;
		}

		if (abrigo.estaDestruido()) {
			System.out.println("ABRIGO DESTRUÍDO — AS CRIATURAS VENCERAM!");
		} else {
			System.out.println("ABRIGO DEFENDIDO — OS SOBREVIVENTES VENCERAM!");
		}

		System.out.println("Total de criaturas derrotadas: " + Criatura.getQuantDerrotadas());

		System.out.println();
		System.out.println("-".repeat(30));
		System.out.println("FIM DO PROGRAMA");
		System.out.println("-".repeat(30));
		System.out.println("Disciplina: Linguagem de Programação Orientada a Objetos");
		System.out.println("Professora: Cinthia Cristina Lucio Caliari");
		System.out.println("Turma: 4SC1");
		System.out.println();
		System.out.println("Desenvolvido por:");
		System.out.println("1. Atilla Tayller Gama da Rocha - 25110254");
		System.out.println("2. Luiz Henrique Dominicini Becalli Almeida de Faria - 25110553");
		System.out.println();
		System.out.println("FAESA - 2026");
		System.out.println("-".repeat(30));
	}
}
