package bejeweled.tests;

import bejeweled.model.Game;
import bejeweled.model.Jewel;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by igor on 24.03.18.
 */
public class GameTest {
    @Test
    public void testOdJewelsAdjacency(){
        final Game game = new Game();
        final List<Jewel> jewelList = game.getJewels();
        for(int y = 0; y < Game.FIELD_HEIGHT; ++y) {
            for(int x = 0; x < Game.FIELD_WIDTH - 1; ++x) {
                assertTrue(game.areJewelsAdjacent(jewelList.get(game.getJewelIndexByCoordinates(x, y)),
                        jewelList.get(game.getJewelIndexByCoordinates(x + 1, y))));
            }
        }

        for(int x = 0; x < Game.FIELD_WIDTH; ++x) {
            for(int y = 0; y < Game.FIELD_HEIGHT - 1; ++y) {
                assertTrue(game.areJewelsAdjacent(jewelList.get(game.getJewelIndexByCoordinates(x, y)),
                        jewelList.get(game.getJewelIndexByCoordinates(x, y + 1))));
            }
        }
    }
}
