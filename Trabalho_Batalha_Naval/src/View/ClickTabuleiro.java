package View;

public class ClickTabuleiro {
	public static int[] VerificarClickTabuleiro(int x, int y, String armamentoSelecionado) {
		double xInicio = 575.0;
		double yInicio = 125.0;
		double tamanhoQuadrado = 20.0;

		int posX = -1;
		int posY = -1;

		for (int i = 0; i < 15; i++) {
		    for (int j = 0; j < 15; j++) {
		        double minX = xInicio + i * tamanhoQuadrado;
		        double maxX = minX + tamanhoQuadrado;
		        double minY = yInicio + j * tamanhoQuadrado;
		        double maxY = minY + tamanhoQuadrado;

		        if (x >= minX && x <= maxX && y >= minY && y <= maxY) {
		            posX = i;
		            posY = j;
		            break;
		        }
		    }
		    if (posX != -1 && posY != -1) {
		        break;
		    }
		}

		return new int[]{posX, posY};
	}
}
