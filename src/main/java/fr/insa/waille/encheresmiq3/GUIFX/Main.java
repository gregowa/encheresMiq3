/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.waille.encheresmiq3.GUIFX;


import static fr.insa.waille.encheresmiq3.bdd.GestionBdD.creeSchemaDeBase;
import static fr.insa.waille.encheresmiq3.bdd.GestionBdD.defautConnect;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 *
 * @author francois
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) throws SQLException, IOException {
        Connection con = null;
        try {
            con = defautConnect();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GridPaneAuthentification.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(GridPaneAuthentification.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Scene sc = new Scene(new GridPaneAuthentification(stage,con));
        stage.setWidth(700);
        stage.setHeight(600);
        stage.setScene(sc);
        stage.setTitle("Encheres");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}