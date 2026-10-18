package lpoo_c2.a1;

public class Mutante extends Criatura {

	public Mutante(String nome, int pontosVida, int poderAtaque) {
		super(nome, pontosVida, poderAtaque);
	}

	@Override
	public int atacar(Abrigo abrigo) {
		int dano = getPoderAtaque() * 2;
		abrigo.receberAtaque(dano);
		return dano;
	}
}
