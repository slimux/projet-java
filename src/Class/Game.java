/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

import java.util.Date;
import IService.IGameService;
import Connection.DataSource;
import Class.Game;
import Service.GameService;
import javafx.JavaFX;
import javafx.FXMLDocumentController;

/**
 *
 * @author slimu
 */
public class Game {

    static String setGenre;

 
    private int id_Jeu;
    private String titre;
    private String genre;
    private Date date_sortie;
    private int note_presse ;
    private int note_joueur;
    private String description;
    private int prix;
    private String console;
    private String image;
    private String developpeur;
      

    public Game() {
    }

    public Game(int id_Jeu,String titre, String genre, Date date_sortie, int note_presse, int note_joueur, String description, int prix, String console, String image, String developpeur) {
       this.id_Jeu=id_Jeu;
        this.titre = titre;
        this.genre = genre;
        this.date_sortie = date_sortie;
        this.note_presse = note_presse;
        this.note_joueur = note_joueur;
        this.description = description;
        this.prix = prix;
        this.console = console;
        this.image = image;
        this.developpeur=developpeur;
   
    }
    public Game(String titre, String genre, Date date_sortie, int note_presse, int note_joueur, String description, int prix, String console, String image, String developpeur) {
       //this.idGame=idGame;
       
        this.titre = titre;
        this.genre = genre;
        this.date_sortie = date_sortie;
        this.note_presse = note_presse;
        this.note_joueur = note_joueur;
        this.description = description;
        this.prix = prix;
        this.console = console;
        this.image = image;
        this.developpeur=developpeur;
       
    }

    public int getId_Jeu() {
        return id_Jeu;
    }

    public void setId_Jeu(int id_Jeu) {
        this.id_Jeu = id_Jeu;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Date getDate_sortie() {
        return date_sortie;
    }

    public void setDate_sortie(Date date_sortie) {
        this.date_sortie = date_sortie;
    }

    public int getNote_presse() {
        return note_presse;
    }

    public void setNote_presse(int note_presse) {
        this.note_presse = note_presse;
    }

    public int getNote_joueur() {
        return note_joueur;
    }

    public void setNote_joueur(int note_joueur) {
        this.note_joueur = note_joueur;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getConsole() {
        return console;
    }

    public void setConsole(String console) {
        this.console = console;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
                public String getDeveloppeur() {
        return developpeur;
    }

    public void setDeveloppeur(String developpeur) {
        this.developpeur = developpeur;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.id_Jeu;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Game other = (Game) obj;
        if (this.id_Jeu != other.id_Jeu) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Game{" + "id_Jeu=" + id_Jeu + ", titre=" + titre + ", genre=" + genre + ", date_sortie=" + date_sortie + ", note_presse=" + note_presse + ", note_joueur=" + note_joueur + ", description=" + description + ", prix=" + prix + ", console=" + console + ", image=" + image + '}';
    }
    

    
}
