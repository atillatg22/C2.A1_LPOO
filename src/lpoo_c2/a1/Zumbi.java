package lpoo_c2.a1;

public class Zumbi extends Criatura {

	public Zumbi(String nome, int pontosVida, int poderAtaque) {
		super(nome, pontosVida, poderAtaque);
	}

	@Override
	public int atacar(Abrigo abrigo) {
		int dano = getPoderAtaque();
		abrigo.receberAtaque(dano);
		return dano;
	}
}
