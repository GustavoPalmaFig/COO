import java.awt.*;

/**
	Esta classe representa um placar no jogo. A classe princial do jogo (Pong)
	instancia dois objeto deste tipo, cada um responsável por gerenciar a pontuação
	de um player, quando a execução é iniciada.
*/

public class Score {

	/**
		Construtor da classe Score.

		@param playerId uma string que identifica o player ao qual este placar está associado.
	*/

	protected String playerID;
	protected int score;

	public Score(String playerId){
		this.playerID = playerId;
		score = 0;
	}

	/**
		Método de desenho do placar.
	*/

	public void draw(){

		if(playerID.equals(Pong.PLAYER1)) GameLib.drawText(playerID + " - " + getScore() , 70, GameLib.ALIGN_LEFT);
		else GameLib.drawText(playerID + " - " + getScore(), 70, GameLib.ALIGN_RIGHT);	

	}

	/**
		Método que incrementa em 1 unidade a contagem de pontos.
	*/

	public void inc(){

		score++;
		draw();

	}

	/**
		Método que devolve a contagem de pontos mantida pelo placar.

		@return o valor inteiro referente ao total de pontos.
	*/

	public int getScore(){

		return score;
	}
}
