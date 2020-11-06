import java.awt.Color;
import java.lang.reflect.*;
import java.util.*;

public class FxBall extends Ball implements IBall {

private LinkedList<Coordenada> lista = new LinkedList<>();
private LinkedList<Ball> bolinhas = new LinkedList<>();

    public FxBall(double cx, double cy, double width, double height, Color color, double speed, double vx, double vy){

        super(cx,cy,width,height,color,speed,vx,vy);
    }

    public void draw(){
        
        for(Ball b : bolinhas) b.draw();
        bolinhas.clear();

        super.draw();
    }

    public void update(long delta){

        super.update(delta);
        
        lista.addFirst(new Coordenada(getCx(),getCy()));
        if (lista.size() > 400) lista.removeLast();

        if (!lista.isEmpty()){

            Color cor = getColor().darker();
            double tam = 0.25;
            
            for(int i=0; i < lista.size(); i++){
                
                Coordenada c = lista.get(i);
                bolinhas.add(new Ball(c.cx,c.cy, getWidth()-tam, getHeight()-tam, cor, getSpeed(), getVx(), getVy()));     
                if(i%10 == 0) cor = cor.darker(); 
                tam+=0.25;
            }
        }
    }
}

class Coordenada {

    public double cx;
    public double cy;

    public Coordenada(double cx, double cy){

        this.cx = cx;
        this.cy = cy;
    }

}