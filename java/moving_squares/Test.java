import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.geom.Area;

import javax.swing.JFrame;

public class Test {

    private JFrame frame;
    private Polygon polygon1;
    private Polygon polygon2;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Test window = new Test();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public Test() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame(){
            private static final long serialVersionUID = 1L;

            @Override
            public void paint(Graphics g){

                super.paint(g);

                doDrawing(g);

            }
        };
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        int nShape1 = 4;
        int xPoly1[] = {30,50,50,30};
        int yPoly1[] = {30,30,50,50};
        polygon1 = new Polygon(xPoly1,yPoly1,nShape1);

        int nShape2 = 4;
        int xPoly2[] = {35,55,55,35};
        int yPoly2[] = {50,50,70,70};

        // uncomment next line to rise second polygon vertically by one pixel
        //yPoly2[] = {49,49,69,69};

        polygon2 = new Polygon(xPoly2,yPoly2,nShape2);
    }
    public synchronized void doDrawing(Graphics g){
        g.setColor(new Color(255,0,0));

        // if you draw the polygon, collision on the exact outline won't be detected.
        // uncomment draw or fill command to see what I mean.
        g.drawPolygon(polygon1);
        g.fillPolygon(polygon1);

        g.setColor(new Color(0,0,255));

        // if you draw the polygon, collision on the exact outline won't be detected.
        // uncomment draw or fill command to see what I mean.
        g.drawPolygon(polygon2);
        g.fillPolygon(polygon2);

        Area area = new Area(polygon1);
        area.intersect(new Area(polygon2));
        if(!area.isEmpty()){
            System.out.println("intersects: yes");
        }
        else{
            System.out.println("intersects: no");
        }
    }

}