package nive.soulcollector.Part2;

/**
 * Created by tj on 15. 9. 3..
 */
public class GhostLine{
    Ghost g1;
    Ghost g2;

    public GhostLine(Ghost g1Input, Ghost g2Input){
        g1 = g1Input;
        g2 = g2Input;
    }

    public Ghost getLeft(){return g1;}
    public Ghost getRight(){return g2;}


}
