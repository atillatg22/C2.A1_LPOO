package lpoo_c2.a1;

public class Abrigo {

	private int defesa;
	private int pontosVida;
	private final int maxDefesa;
	private final int maxPontosVida;

	public Abrigo(int pontosVida, int defesa) {
		this.pontosVida = Math.max(0, pontosVida);
		this.defesa = Math.max(0, defesa);
		this.maxDefesa = this.defesa;
		this.maxPontosVida = this.pontosVida;
	}

	public int getDefesa() {
		return defesa;
	}

	public int getPontosVida() {
		return pontosVida;
	}

	public boolean estaDestruido() {
		return defesa == 0 && pontosVida == 0;
	}

	public boolean receberAtaque(int dano) {
		if (estaDestruido()) {
			return true;
		}

		pontosVida -= dano;

		while (pontosVida <= 0 && defesa > 0) {
			defesa--;
			pontosVida += maxPontosVida;
		}

		if (pontosVida < 0) {
			pontosVida = 0;
		}

		return estaDestruido();
	}
}
