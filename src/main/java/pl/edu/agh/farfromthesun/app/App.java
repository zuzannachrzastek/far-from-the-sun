<<<<<<< HEAD
package pl.edu.agh.farfromthesun.app;
=======
package main.java.pl.edu.agh.farfromthesun.app;
>>>>>>> c05e2760a2e0697102474cce23f5b0ee3bee9c4a

import java.awt.Dimension;

import javax.swing.JFrame;

<<<<<<< HEAD
import pl.edu.agh.farfromthesun.algorithm.Algorithm;
import pl.edu.agh.farfromthesun.forecast.Forecast;
import pl.edu.agh.farfromthesun.map.Map;
=======
import main.java.pl.edu.agh.farfromthesun.algorithm.Algorithm;
import main.java.pl.edu.agh.farfromthesun.forecast.Forecast;
import main.java.pl.edu.agh.farfromthesun.map.Map;
>>>>>>> c05e2760a2e0697102474cce23f5b0ee3bee9c4a

public class App extends JFrame {

    private static final long serialVersionUID = 3066593449554111970L;

    public App(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 600));
        pack();
        setLocationRelativeTo(null);
        setTitle("Far From The Sun");
        setVisible(true);
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        App app = new App();
        Map map = new Map();
        Forecast forecast = new Forecast();
        Algorithm algorithm = new Algorithm();

        map.initialize(app);
        forecast.initialize(app);
        algorithm.initialize(app);
    }
}
