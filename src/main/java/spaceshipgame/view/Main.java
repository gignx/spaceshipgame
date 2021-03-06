package spaceshipgame.view;
//CHECKSTYLE:OFF
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import spaceshipgame.model.PlayerManager;
import spaceshipgame.model.dao.HibernateDAO;



public class Main extends Application {
	private static Logger logger = LoggerFactory.getLogger(Main.class);
	
	public static Stage primaryStage;
	public static Stage secondaryStage;
	public static PlayerManager PM = new PlayerManager();
	private MenuController mc;
	private InGameMenuController imc;
	private GameOverController goc;
	private LoginController lc;
	private RegistrationController rc;
	private GameController gc;
	private ScoreBoardController sbc;

	
	
	public void createInGameMenu(){
		logger.info("create ingame menu");
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("/fxml/ingamemenu.fxml"));
		
		try {
			AnchorPane menuPane = (AnchorPane) loader.load();
			imc = loader.getController();
			imc.setMain(this);
			imc.setPlayer(PM.getLoggedInPlayer());
			secondaryStage = new Stage();
			secondaryStage.setTitle("Menu");
			secondaryStage.initModality(Modality.WINDOW_MODAL);
			secondaryStage.initOwner(primaryStage);
			
			Scene scene = new Scene(menuPane);
			secondaryStage.setScene(scene);
			secondaryStage.show();
			
			
		} catch (IOException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		
		
	}
	public void createGameOverView(){
		logger.info("create game over view");
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("/fxml/gameover.fxml"));
		
		try {
			AnchorPane gameoverview = (AnchorPane) loader.load();
			goc = loader.getController();
			goc.setMain(this);
			goc.setPlayer(PM.getLoggedInPlayer());
			goc.setText();
			Scene scene = new Scene(gameoverview);
			
			secondaryStage = new Stage();
			secondaryStage.initModality(Modality.WINDOW_MODAL);
			secondaryStage.initOwner(primaryStage);
			secondaryStage.setScene(scene);
			secondaryStage.show();
			
		} catch (IOException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void createScoreBoardView(){
		logger.info("scoreboard");
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("/fxml/scoreboardview.fxml"));
		try {
			AnchorPane scoreboard = (AnchorPane) loader.load();
			sbc = loader.getController();
			sbc.setMain(this);
			sbc.getData();
			sbc.setTable();
			
			Scene scene = new Scene(scoreboard);
			
			secondaryStage = new Stage();
			secondaryStage.initModality(Modality.WINDOW_MODAL);
			secondaryStage.initOwner(primaryStage);
			secondaryStage.setScene(scene);
			secondaryStage.show();
			
		} catch (IOException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void createGameView(){
		logger.info("gameview");
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/fxml/gameview.fxml"));
		
		try {
			Pane game = (Pane) loader.load();
			Scene gamescene = new Scene(game);
			gc = loader.getController();
			gc.setPlayer(PM.getLoggedInPlayer());
			gc.setPane(game);
			gc.setMain(this);
			gc.startGame();
			gc.setKeyPresses(gamescene);
			primaryStage.setScene(gamescene);
			primaryStage.setTitle("GAME");
			primaryStage.show();
		} catch (IOException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		
		
	}
	
	public void createMenu(Stage stage){
		logger.info("menu");
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/fxml/gamemenu.fxml"));
		
		try {
			AnchorPane menuPane = (AnchorPane) loader.load();
			mc = loader.getController();
			mc.setMain(this);
			Scene scene = new Scene(menuPane);
	        primaryStage = stage;
	        primaryStage.setTitle("Menu");
	        primaryStage.setScene(scene);
	        primaryStage.show();
		} catch (IOException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void createLoginView(Stage stage){
		logger.info("login view");
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/fxml/loginview.fxml"));
		
		try {
			AnchorPane loginPane = (AnchorPane) loader.load();
			lc = loader.getController();
			lc.setMain(this);
			Scene scene = new Scene(loginPane);
	        primaryStage = stage;
	        primaryStage.setTitle("Login");
	        primaryStage.setScene(scene);
	        primaryStage.show();
			
	        	        
		} catch (IOException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	public void createRegistrationView(Stage stage){
		logger.info("reg view");
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/fxml/registrationview.fxml"));
	
		try {
			AnchorPane regPane = (AnchorPane) loader.load();
			rc = loader.getController();
			rc.setMain(this);
			Scene scene = new Scene(regPane);
	        primaryStage = stage;
	        primaryStage.setTitle("Registration");
	        primaryStage.setScene(scene);
	        primaryStage.show();
		} catch (IOException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		
        
	}
	

	
	@Override
	public void start(Stage stage) throws IOException {
		createLoginView(stage);
    }
	
	public static void main(String[] args) {
		launch(args);
		HibernateDAO.close();
		logger.info("end");
	}

}
