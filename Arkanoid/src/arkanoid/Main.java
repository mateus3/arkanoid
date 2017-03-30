
package arkanoid;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    private static MainFrame mainFrame;

    public static MainFrame getMainFrame() {
        return mainFrame;
    }
            
        // definiowanie wielkości okna gry
    
    @Override public void start(Stage stage) {
        Config.initialize();
        Group root = new Group();
        mainFrame = new MainFrame(root);
        stage.setTitle("ARKANOID ;)");
        stage.setResizable(false);
        stage.setWidth(Config.SCREEN_WIDTH + 2*Config.WINDOW_BORDER);
        stage.setHeight(Config.SCREEN_HEIGHT+ 2*Config.WINDOW_BORDER + Config.TITLE_BAR_HEIGHT);
        Scene scene = new Scene(root);
        scene.setFill(Color.BLACK);
        stage.setScene(scene);
        mainFrame.changeState(MainFrame.SPLASH);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

    public class MainFrame {
        
        // główna scena
        
        private Group root;

        
        // ekran powitalny
        
        private Splash splash;

        // ekran poziomu
        
        private Level level;

        // liczba żyć
        
        private int lifeCount;

        // aktualna punktacja
        
        private int score;

        private MainFrame(Group root) {
            this.root = root;
        }

        public int getState() {
            return state;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public int getLifeCount() {
            return lifeCount;
        }

        public void increaseLives() {
            lifeCount = Math.min(lifeCount + 1, Config.MAX_LIVES);
        }

        public void decreaseLives() {
            lifeCount--;
        }

        // startowe ustawienia gry - liczba żyć, punkty
        
        public void startGame() {
            lifeCount = 3;
            score = 0;
            changeState(1);
        }

        // aktualny stan gry
        
        // 0 - ekran powitalny
        
        public static final int SPLASH = 0;
        
        // 1..Level.LEVEL_COUNT - Level
        
        private int state = SPLASH;

        public void changeState(int newState) {
            this.state = newState;
            if (splash != null) {
                splash.stop();
            }
            if (level != null) {
                level.stop();
            }
            if (state < 1 || state > LevelData.getLevelsCount()) {
                root.getChildren().remove(level);
                level = null;
                splash = new Splash();
                root.getChildren().add(splash);
                splash.start();
            } else {
                root.getChildren().remove(splash);
                splash = null;
                level = new Level(state);
                root.getChildren().add(level);
                level.start();
            }
        }
    }

}

