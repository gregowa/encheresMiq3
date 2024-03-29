/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.waille.encheresmiq3.GUIFX;

import fr.insa.encheresmiq3.modele.Objet;
import static fr.insa.waille.encheresmiq3.GUIFX.Accueil.recupererLogo;
import static fr.insa.waille.encheresmiq3.bdd.GestionBdD.creeEnchere;
import static fr.insa.waille.encheresmiq3.bdd.GestionBdD.getEmailUtilisateur;
import static fr.insa.waille.encheresmiq3.bdd.GestionBdD.getEmailUtilisateurEnCours;
import static fr.insa.waille.encheresmiq3.bdd.GestionBdD.getIdUtilisateur;
import static fr.insa.waille.encheresmiq3.bdd.GestionBdD.getNomCategorie;
import static fr.insa.waille.encheresmiq3.bdd.GestionBdD.getPrixMaxSurObjet;
import static fr.insa.waille.encheresmiq3.bdd.GestionBdD.getUtilisateur;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import static java.lang.Integer.max;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import static java.time.LocalDate.now;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
         



/**
 *
 * @author valen
 */
public class ObjetPlus extends GridPane{

    public ObjetPlus(Stage stage, Connection con, Objet obj) throws FileNotFoundException, SQLException, IOException{
        
    Label logo = recupererLogo();
        
    String titre = obj.getTitre();
    String description = obj.getDescription();
    Timestamp debut = obj.getDebut();
    Timestamp fin = obj.getFin();
    int prix_base = obj.getPrix_base();
    int prix_actuel = obj.getPrix_actuel();
    int categorie = obj.getCategorie();
    int propose_par = obj.getPropose_par();
    
    //affichage de l'image correspondante
    ImageView imageView = recupererImage(obj);
    //dimensions de l'image :
    imageView.setFitHeight(150);
    imageView.setFitWidth(300);
    Label imageObjet = new Label("",imageView);
    
    Label Ltitre = new Label("Titre : ");
    Ltitre.setStyle("-fx-max-width: 50");
    Ltitre.setStyle("-fx-font-weight: bold");
    Label Ldescription = new Label("Description : ");
    Ldescription.setStyle("-fx-max-width: 50");
    Ldescription.setStyle("-fx-font-weight: bold");
    Label Ldebut = new Label("Debut : ");
    Ldebut.setStyle("-fx-max-width: 50");
    Ldebut.setStyle("-fx-font-weight: bold");
    Label Lfin = new Label("Fin : ");
    Lfin.setStyle("-fx-max-width: 50");
    Lfin.setStyle("-fx-font-weight: bold");
    Label Lprix_base = new Label("Prix de base : ");
    Lprix_base.setStyle("-fx-max-width: 50");
    Lprix_base.setStyle("-fx-font-weight: bold");
    Label Lprix_actuel = new Label("Prix actuel : ");
    Lprix_actuel.setStyle("-fx-max-width: 50");
    Lprix_actuel.setStyle("-fx-font-weight: bold");
    Label Lcategorie = new Label("Catégorie : ");
    Lcategorie.setStyle("-fx-max-width: 50");
    Lcategorie.setStyle("-fx-font-weight: bold");
    Label Lpropose_par = new Label("Proposé par : ");
    Lpropose_par.setStyle("-fx-max-width: 50");
    Lpropose_par.setStyle("-fx-font-weight: bold");
    Button Bprop = new Button("Proposer une enchère");
    Label panneau = new Label();
    TextField TnouvPrix = new TextField();
    Button Bencherir = new Button("Enchérir");
    Button Bretour = new Button("Retour");
    
    Label ShowTitre = new Label(titre);
    Label ShowDescription = new Label(description);
    Label ShowDebut = new Label(debut.toString());
    Label ShowFin = new Label(fin.toString());
    String Sprix_base = Integer.toString(prix_base);
    Label ShowPrix_base = new Label(Sprix_base+" €");
    String Sprix_actuel = Integer.toString(prix_actuel);
    Label ShowPrix_actuel = new Label(Sprix_actuel+" €");
    String Scategorie = getNomCategorie(con,categorie);
    Label ShowCategorie = new Label(Scategorie);
    String Spropose_par = getUtilisateur(con,propose_par);
    Label ShowPropose_par = new Label(Spropose_par);
    
    this.add(logo,1,0);
    this.add(Ltitre,0,1);
    this.add(ShowTitre,0,2);
    this.add(Ldescription,0,3);
    this.add(ShowDescription,0,4);
    this.add(Ldebut,0,5);
    this.add(ShowDebut,1,5);
    this.add(Lfin,0,6);
    this.add(ShowFin,1,6);
    this.add(Lprix_base,0,7);
    this.add(ShowPrix_base,1,7);
    this.add(Lprix_actuel,0,8);
    this.add(ShowPrix_actuel,1,8);
    this.add(Lcategorie,0,9);
    this.add(ShowCategorie,1,9);
    this.add(Lpropose_par,0,10);
    this.add(ShowPropose_par,1,10);
    if(!(getEmailUtilisateur(con,propose_par).equals(getEmailUtilisateurEnCours(con)))){
        this.add(Bprop,3,1);
    }
    this.add(Bretour,4,1);
    this.add(panneau,3,2);
    this.add(imageObjet, 0, 11,4,1); //affichage de l'image sur 4 colonnes pour éviter décalage labels
    
    
    //action de l'appuie sur le bouton enchere
    Bprop.setOnAction((t) ->{

        panneau.setText("Proposer un nouveau prix d'enchère");
        this.add(TnouvPrix,3,3);
        this.add(Bencherir,3,4);

    });
    
    //action de l'appuie sur le bouton enchere
    Bencherir.setOnAction((t) ->{
        
        Timestamp quand = Timestamp.valueOf(LocalDate.now().atTime(LocalTime.now()).toString().replace("T", " "));
        System.out.println(quand);
        int nouvprix = Integer.parseInt(TnouvPrix.getText());
        String email;
        int idUser=0;
        try {
            email = getEmailUtilisateurEnCours(con);
            idUser = getIdUtilisateur(con,email);
        } catch (SQLException ex) {
            Logger.getLogger(ObjetPlus.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            if(nouvprix<max(obj.getPrix_base(),getPrixMaxSurObjet(con,obj.getId()) ) ){
                panneau.setText("Le nouveau prix doit être plus grand que "+max(obj.getPrix_base(),getPrixMaxSurObjet(con,obj.getId()))+"€");
                TnouvPrix.setText("");
            }
            else{
                int idObj = obj.getId();
                
                try {
                    creeEnchere(con, quand, nouvprix, idUser, idObj);
                } catch (SQLException ex) {
                    Logger.getLogger(ObjetPlus.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                panneau.setText("Proposition d'enchère effectuée");
                TnouvPrix.setText("");
                this.getChildren().remove(Bencherir);
                this.getChildren().remove(TnouvPrix);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ObjetPlus.class.getName()).log(Level.SEVERE, null, ex);
        }
    });
    
        //action de l'appui sur le bouton retour
    Bretour.setOnAction((var t) ->{
        //ferme la fenêtre ouverte : retour à la fenêtre principale (accueil)
        stage.close();
    });
    
    }
    
    public static ImageView recupererImage(Objet obj) throws IOException{
        //récupère l'image associée à l'objet
        BufferedImage bfimage = obj.getImage();
        //conversion dans le bon type :
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(bfimage, "png", bos );
        byte [] data = bos.toByteArray();
        InputStream is = new ByteArrayInputStream(data);
        Image img = new Image(is);
        return (new ImageView(img));
    }
}
