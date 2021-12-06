package Effect;

import Utils.MyPoint;
import View.View;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class EffectTemplate extends Effect{
    public EffectTemplate(View view, MyPoint point) {
        super(view, point);
    }

    @Override
    public void apply(Graphics graphics) {
        try {
            BufferedImage template = ImageIO.read(new File("src/data/template.png"));
            tint(template, new Color(144, 1, 1));
            graphics.drawImage(template, point.x, point.y, null);
        } catch (Exception e){
            System.err.println("Image not found");
            System.exit(1);
        }
    }

    private static void tint(BufferedImage image, Color color) {
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                Color pixelColor = new Color(image.getRGB(x, y), true);
                int r = (pixelColor.getRed() * color.getRed()) / 255;
                int g = (pixelColor.getGreen() * color.getGreen()) / 255;
                int b = (pixelColor.getBlue() * color.getBlue()) / 255;
                int a = pixelColor.getAlpha();
                int rgba = (a << 24) | (r << 16) | (g << 8) | b;
                image.setRGB(x, y, rgba);
            }
        }
    }
}
