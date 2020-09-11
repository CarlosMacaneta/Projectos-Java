package gui;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class DrawSmile extends JPanel {

    @Override
    public void paintComponent(Graphics graph) {

        super.paintComponent(graph);

        //draw the face
        graph.setColor(Color.yellow);
        graph.fillOval(10, 10, 200, 200);

        //draw the eyes
        graph.setColor(Color.BLACK);
        graph.fillOval(55, 65, 30, 30);
        graph.fillOval(135, 65, 30, 30);

        //draw the mouth
        graph.fillOval(50, 110, 120, 60);

        //creating smile
        graph.setColor(Color.yellow);
        graph.fillRect(50, 110, 120, 30);
        graph.fillOval(50, 120, 120, 40);

    }
}
