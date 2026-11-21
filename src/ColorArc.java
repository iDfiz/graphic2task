import java.awt.*;

public class ColorArc {
    private final int cx;
    private final int cy;
    private final int radius;
    private final double startAngle;
    private final double arcAngle;
    private final Color startColor;
    private final Color endColor;

    public ColorArc(int cx, int cy, int radius, double startAngle, double arcAngle, Color startColor, Color endColor) {
        this.cx = cx;
        this.cy = cy;
        this.radius = radius;
        this.startAngle = startAngle;
        this.arcAngle = arcAngle;
        this.startColor = startColor;
        this.endColor = endColor;
    }

    public void draw(Graphics gr) {
        Graphics2D g2 = (Graphics2D) gr;


        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        int steps = Math.max(2, (int) Math.abs(arcAngle)) ;

        double prevX = 0, prevY = 0;
        for (int i = 0; i <= steps; i++) {
            double t = (double) i / steps;

            double angleDeg = startAngle + arcAngle * t;
            double angleRad = Math.toRadians(angleDeg);

            double x = cx + radius * Math.cos(angleRad);
            double y = cy + radius * Math.sin(angleRad);

            if (i > 0) {
                Color c = interpolate(startColor, endColor, (float) t);
                g2.setColor(c);
                g2.setStroke(new BasicStroke(5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                g2.drawLine((int) prevX, (int) prevY, (int) x, (int) y);
            }
            prevX = x;
            prevY = y;
        }
    }

    private Color interpolate(Color a, Color b, float t) {
        int r = (int)(a.getRed()  + (b.getRed()  - a.getRed())  * t);
        int g = (int)(a.getGreen()+ (b.getGreen()- a.getGreen())* t);
        int bl= (int)(a.getBlue() + (b.getBlue() - a.getBlue()) * t);
        return new Color(r, g, bl);
    }
}
