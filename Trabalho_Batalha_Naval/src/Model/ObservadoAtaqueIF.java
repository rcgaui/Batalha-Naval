package Model;

import View.ObservadorAtaqueIF;

public interface ObservadoAtaqueIF {
	public void add(ObservadorAtaqueIF observador);
	public String get(int n);
}
