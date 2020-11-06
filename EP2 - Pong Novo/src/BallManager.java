import java.awt.Color;
import java.lang.reflect.*;
import java.util.*;

public class BallManager {

	private IBall theBall = null;

	private Class<?> ballClass = null;

	private long inicio;
	private double velocidade;
	private LinkedList<BolaExtra> bolasextras = new LinkedList<>();
	

	public BallManager(String className){

		try{
			ballClass = Class.forName(className);
		}
		catch(Exception e){

			System.out.println("Classe '" + className + "' não reconhecida... Usando 'Ball' como classe padrão.");
			ballClass = Ball.class;
		}
	}

	private double [] normalize(double x, double y){

		double length = Math.sqrt(x * x + y * y);

		return new double [] { x / length, y / length };
	}

	private IBall createBallInstance(double cx, double cy, double width, double height, Color color, double speed, double vx, double vy){

		IBall ball = null;
		double [] v = normalize(vx, vy);
		

		try{
			Constructor<?> constructor = ballClass.getConstructors()[0];
			ball = (IBall) constructor.newInstance(cx, cy, width, height, color, speed, v[0], v[1]);
		}
		catch(Exception e){

			System.out.println("Falha na instanciação da bola do tipo '" + ballClass.getName() + "' ... Instanciando bola do tipo 'Ball'");
			ball = new Ball(cx, cy, width, height, color, speed, v[0], v[1]);
		}

		return ball;
	} 

	public void initMainBall(double cx, double cy, double width, double height, Color color, double speed, double vx, double vy){

		theBall = createBallInstance(cx, cy, width, height, color, speed, vx, vy);
		this.velocidade = speed;
	}

	public void draw(){

		theBall.draw();
		for(BolaExtra b : bolasextras)	b.bola.draw();
		/*Nossa bb, linda de bonita, gostosa de cremosa,
		com todo respeito minha vida*/
	}

	public void update(long delta){
		
		if(theBall.getSpeed() != velocidade){
			
			if(System.currentTimeMillis() >= this.inicio + BoostTarget.BOOST_DURATION) 
				theBall.setSpeed(velocidade);
		}

		theBall.update(delta);

		for(Iterator<BolaExtra> it = bolasextras.iterator(); it.hasNext();) {

			BolaExtra atual = it.next();

			if(atual.bola.getSpeed() != velocidade){
			
				if(System.currentTimeMillis() >= atual.iniciov + BoostTarget.BOOST_DURATION) 
					atual.bola.setSpeed(velocidade);
			}			
		
			if(System.currentTimeMillis() >= atual.inicio + DuplicatorTarget.EXTRA_BALL_DURATION)
				it.remove();					
			else atual.bola.update(delta);
			
		}
		
		//Nossa, fascinou agora, encantou em??		
	}
	
	public int checkCollision(Wall wall){

		int hits = 0;

		if(theBall.checkCollision(wall)) hits++;
		for(BolaExtra b : bolasextras) {
			if(b.bola.checkCollision(wall)) hits++;
		}
		return hits;

		//Radicalizou geral, tu ta demais mulher!
	}

	public void checkCollision(Player player){

		theBall.checkCollision(player);
		for(BolaExtra b : bolasextras) b.bola.checkCollision(player);
		/*Qual seu nome princesa? Quantos aninhos vc tem?
		Onde tu moras?*/
	}

	public void checkCollision(Target target){

		if(theBall.checkCollision(target)) {

			if(target instanceof BoostTarget) {

				double vmult = velocidade * BoostTarget.BOOST_FACTOR * BoostTarget.BOOST_FACTOR;

				if(theBall.getSpeed() == velocidade){
					
					theBall.setSpeed(vmult);
					this.inicio = System.currentTimeMillis();				
				}	
			}

			else {

				Random gerador = new Random();
				double perturbacao = gerador.nextDouble();
	
				Long tempo = System.currentTimeMillis();
				IBall newball = createBallInstance(theBall.getCx(), theBall.getCy(), theBall.getWidth(), theBall.getHeight(), Color.RED, velocidade, theBall.getVx() + perturbacao, theBall.getVy() + perturbacao);
	
				BolaExtra bola = new BolaExtra(newball, tempo);
				bolasextras.add(bola);
			}
		}	

		BolaExtra bola = null;

		for(BolaExtra b : bolasextras){

			if(b.bola.checkCollision(target)) {

				if(target instanceof BoostTarget) {
	
					double vmult = velocidade * BoostTarget.BOOST_FACTOR * BoostTarget.BOOST_FACTOR;
	
					if(b.bola.getSpeed() == velocidade){
						
						b.bola.setSpeed(vmult);
						b.iniciov = System.currentTimeMillis();				
					}	
				}
	
				else {
	
					Random gerador = new Random();
					double perturbacao = gerador.nextDouble();
		
					Long tempo = System.currentTimeMillis();
					IBall newball = createBallInstance(theBall.getCx(), theBall.getCy(), theBall.getWidth(), theBall.getHeight(), Color.RED, velocidade, theBall.getVx() + perturbacao, theBall.getVy() + perturbacao);
		
					bola = new BolaExtra(newball, tempo);
				}
			}	
		}

		if(bola != null) bolasextras.add(bola);
		/*Que eu vou no resgate pra nois tomar
		um banho de Chandon AUUUUUUUUUU*/
	}
}


