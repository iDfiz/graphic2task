import javax.swing.*;
import java.awt.*;

public class DrawPanel extends JPanel {

    private final ColorArc arc;

    public DrawPanel(int width, int height) {
        setPreferredSize(new Dimension(width, height));
        setBackground(Color.WHITE);

        arc = new ColorArc(250, 250, 200, 0, 360, Color.GREEN, Color.BLUE);
    }

    @Override
    protected void paintComponent(Graphics gr) {
        super.paintComponent(gr);
        arc.draw(gr);
    }
}
