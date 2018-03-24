package bejeweled.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Collections;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by igor on 24.03.18.
 */
public class Game {
    public static final int FIELD_WIDTH = 6;
    public static final int FIELD_HEIGHT = 6;
    private static final int CHAIN = 4;
    private static final Random random = new Random();
    private static final int MAX_NEIGHBOUR_COUNT = 4;
    private static final int[] DX = { 1, 0, -1, 0};
    private static final int[] DY = { 0, 1, 0, -1};
    private List<Jewel> jewels;
    private Jewel selectedJewel = null;
    private int score;

    public Game(){
        prepareNewGame();
    }

    public void prepareNewGame(){
        this.score = 0;
        this.jewels = IntStream.range(0, FIELD_WIDTH * FIELD_HEIGHT)
                .mapToObj(index -> new Jewel(getJewelXByIndex(index), getJewelYByIndex(index), getRandomJewelType()))
                .collect(Collectors.toList());
    }

    public void move(final int x, final int y){
        if(!isLegalCoordinates(x, y)) return;
        System.out.println("Trying to move");
        if(this.selectedJewel == null){
            this.selectedJewel = this.jewels.get(getJewelIndexByCoordinates(x, y));
            System.out.println("The jewel was selected");
        } else {
            final Jewel target = this.jewels.get(getJewelIndexByCoordinates(x, y));
            if(areJewelsAdjacent(this.selectedJewel, target)){
                System.out.println("Jewels are adjacent");
                this.selectedJewel.swap(target);
                checkFilledLines();
                this.selectedJewel = null;
                System.out.println("The jewels were swapped");
            } else {
                System.out.println("The jewels are not adjacent. Could not swap them");
            }
        }
    }

    public int getScore(){
        return this.score;
    }

    public List<Jewel> getJewels(){
        return Collections.unmodifiableList(this.jewels);
    }

    private void increasePlayersScore(final JewelType jewelType){
        this.score += jewelType.getValue();
    }

    private void checkFilledLines() {
        final Map<Integer, List<Jewel>> rows = this.jewels.stream().collect(Collectors.groupingBy(Jewel::getY));
        rows.values().forEach(this::checkLine);
        final Map<Integer, List<Jewel>> columns = this.jewels.stream().collect(Collectors.groupingBy(Jewel::getX));
        columns.values().forEach(this::checkLine);
        final List<Jewel> mainDiagonal = this.jewels.stream().filter(jewel -> jewel.getX() == jewel.getY())
                .collect(Collectors.toList());
        checkLine(mainDiagonal);
        final List<Jewel> secondaryDiagonal = this.jewels.stream()
                .filter(jewel -> jewel.getX() + jewel.getY() == FIELD_WIDTH - 1).collect(Collectors.toList());
        checkLine(secondaryDiagonal);
    }

    private void checkLine(final List<Jewel> line){
        final JewelType jewelType = line.get(0).getJewelType();
        final boolean isLineFilled = line.stream().allMatch(jewel -> jewel.getJewelType().equals(jewelType));
        if(isLineFilled){
            increasePlayersScore(jewelType);
            boolean hasNewLineOnlyOneTypeOfJewels = true;
            while (hasNewLineOnlyOneTypeOfJewels) {
                line.forEach(jewel -> jewel.setJewelType(getRandomJewelType()));
                final JewelType firstJewelType = line.get(0).getJewelType();
                hasNewLineOnlyOneTypeOfJewels = line.stream()
                        .filter(jewel -> !jewel.getJewelType().equals(firstJewelType)).count() == 0;
            }
        }
    }

    private static JewelType getRandomJewelType(){
        return JewelType.values()[random.nextInt(JewelType.values().length)];
    }

    public boolean areJewelsAdjacent(final Jewel first, final Jewel second){
        return getNeighboursVonNeumann(first).contains(second);
    }

    private static boolean isLegalCoordinates(final int x, final int y){
        return x >= 0 && x < FIELD_WIDTH && y >= 0 && y < FIELD_HEIGHT;
    }

    private List<Jewel> getNeighboursVonNeumann(final Jewel jewel){
        final List<Jewel> neighbours = new ArrayList<>();
        for(int dir = 0; dir < MAX_NEIGHBOUR_COUNT; ++dir){
            int nx = jewel.getX() + DX[dir];
            int ny = jewel.getY() + DY[dir];
            if(isLegalCoordinates(nx, ny)){
                final int index = getJewelIndexByCoordinates(nx, ny);
                neighbours.add(this.jewels.get(index));
            }
        }
        return neighbours;
    }

    public int getJewelIndexByCoordinates(final int x, final int y){
        return y * FIELD_HEIGHT + x;
    }

    private int getJewelXByIndex(final int index){
        return index % FIELD_WIDTH;
    }

    private int getJewelYByIndex(final int index){
        return index / FIELD_HEIGHT;
    }

}
