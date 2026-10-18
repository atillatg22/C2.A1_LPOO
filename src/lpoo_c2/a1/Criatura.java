package lpoo_c2.a1;

public abstract class Criatura {

	private String nome;
	private int pontosVida;
	private int poderAtaque;
	private boolean derrotada;

	private static int quantDerrotadas = 0;

	public Criatura(String nome, int pontosVida, int poderAtaque) {
		this.nome = nome;
		this.pontosVida = Math.max(0, pontosVida);
		this.poderAtaque = poderAtaque;
		this.derrotada = false;
	}

	public String getNome() {
		return nome;
	}

	public int getPontosVida() {
		return pontosVida;
	}

	public int getPoderAtaque() {
		return poderAtaque;
	}

	public boolean isDerrotada() {
		return derrotada;
	}

	public static int getQuantDerrotadas() {
		return quantDerrotadas;
	}

	public boolean receberDano(int dano) {
		if (derrotada) {
			return true;
		}

		pontosVida -= dano;
		if (pontosVida < 0) {
			pontosVida = 0;
		}

		// Contabiliza a criatura uma única vez
		if (pontosVida == 0) {
			derrotada = true;
			quantDerrotadas++;
		}

		return derrotada;
	}

	public abstract int atacar(Abrigo abrigo);
}
