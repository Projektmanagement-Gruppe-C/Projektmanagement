package gui;

import gui.kunde.KundeControl;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
			KundeControl kundeControl = new KundeControl(primaryStage);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}	
	
	public static void main(String[] args)
	{
		launch(args);
	}
}