package bejeweled;

import bejeweled.model.Game;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.net.URL;
import java.util.ResourceBundle;

import static bejeweled.Renderer.TILE_SIZE;

public class Controller implements Initializable{
    private Game game = new Game();
    @FXML
    private Canvas canvas;
    private GraphicsContext gc;

    public void onBtnNewGameClicked(ActionEvent event) {
        this.game.prepareNewGame();
        renderAll();
    }

    private void renderAll() {
        Renderer.renderField(this.gc, this.game);
        Renderer.renderPlayersScore(this.gc, this.game);
    }

    public void onBtnExitClicked(ActionEvent event) {
        final Alert alert = new Alert(Alert.AlertType.WARNING, "Dou you really want to exit?",
                ButtonType.YES, ButtonType.NO);
        alert.setTitle("Confirm exit, please");
        alert.showAndWait();
        if(alert.getResult() == ButtonType.YES){
            Platform.exit();
            System.exit(0);
        } else {
            alert.close();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.gc = this.canvas.getGraphicsContext2D();
        this.canvas.setOnMouseClicked(event -> {
            final int x = (int)event.getX() / TILE_SIZE;
            final int y = (int)event.getY() / TILE_SIZE;
            System.out.println("x = " + x + " y = " + y);
            this.game.move(x, y);
            renderAll();
        });
        renderAll();
    }
}
