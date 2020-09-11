package gui;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        DrawSmile panel = new DrawSmile();
        JFrame application = new JFrame();

        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        application.add(panel);
        application.setSize(230, 250);
        application.setVisible(true);
    }
}
