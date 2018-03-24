package bejeweled;

import bejeweled.model.Game;
import bejeweled.model.Jewel;
import bejeweled.model.JewelType;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import static bejeweled.model.Game.FIELD_WIDTH;

/**
 * Created by igor on 24.03.18.
 */
public class Renderer {
    static final int TILE_SIZE = 100;
    private static final Font FONT = new Font("Arial", 40);
    static void renderField(final GraphicsContext gc, final Game game){
        gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
        game.getJewels().forEach(jewel -> renderJewel(gc, jewel));
    }

    private static void renderJewel(final GraphicsContext gc, final Jewel jewel){
        gc.setFill(getColorByJewelType(jewel.getJewelType()));
        gc.fillOval(jewel.getX() * TILE_SIZE, jewel.getY() * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }

    static void renderPlayersScore(final GraphicsContext gc, final Game game){
        gc.setFill(Color.BLUE);
        gc.setFont(FONT);
        gc.fillText(String.format("Score:\n%d", game.getScore()), FIELD_WIDTH * TILE_SIZE + 10, 30);
    }

    private static Color getColorByJewelType(final JewelType jewelType){
        switch (jewelType){
            case RED:
                return Color.RED;
            case BLUE:
                return Color.BLUE;
            case GREEN:
                return Color.GREEN.darker();
            case CYAN:
                return Color.CYAN;
            case YELLOW:
                return Color.YELLOW;
            default:
                return Color.MAGENTA;
        }
    }
}
