//board class is where the graphics are, paints the paddle, game, and ball
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Board extends JPanel implements ActionListener {
    final int WIDTH = 900;
    final int HEIGHT = 600;

    private final int EDGESPACE = 50;
    private final int DECORSIZE = 25;

    private int pScore = 0, cScore = 0;

    Paddle pPaddle;
    Paddle cPaddle;
    Ball bBall;
    Timer timer;
    Game game;


    public Board(Game game){
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);

        pPaddle = new Paddle(this, game);
        cPaddle = new Paddle(this, game);
        bBall = new Ball(this);
    }

    public void init(){
        bBall.setPosition(WIDTH/2, HEIGHT/2);
        pPaddle.setPosition(EDGESPACE,  HEIGHT/2);
        cPaddle.setPosition(WIDTH - EDGESPACE, HEIGHT/2 );
        timer = new Timer(1000/60, this);
        timer.start();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Serif", Font.BOLD, 72));
        printSimpleString(Integer.toString(pScore), getWidth()/2, 0, DECORSIZE *2, g);
        printSimpleString(Integer.toString(cScore), getWidth()/2, getWidth()/2, DECORSIZE *2, g);
        pPaddle.paint(g);
        cPaddle.paint(g);
        bBall.paint(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        bBall.checkCollisions(cPaddle);
        bBall.checkCollisions(pPaddle);
        bBall.move();
        cPaddle.move(bBall);
        pPaddle.move();
        repaint();
    }

    private void printSimpleString(String s, int width, int XPos, int YPos, Graphics g){
        int stringLen = (int)g.getFontMetrics().getStringBounds(s, g).getWidth();
        int start = width/2 - stringLen/2;
        g.drawString(s, start+XPos, YPos);

    }

    public int getpScore() {
        return pScore;
    }

    public void setpScore(int pScore) {
        this.pScore = pScore;
    }

    public int getcScore() {
        return cScore;
    }

    public void setcScore(int cScore) {
        this.cScore = cScore;
    }
}
