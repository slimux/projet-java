/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

/**
 *
 * @author slimu
 */

public class Astuce {
    private int idAstuce;
    private int jeu_id;
    private String title_astuce;
    private String description_astuce;
    private String image_astuce;

    public Astuce() {
    }

    public Astuce(int idAstuce, int jeu_id,String title_game, String description_astuce, String image_astuce) {
        this.idAstuce = idAstuce;
        this.jeu_id= jeu_id;
        this.title_astuce = title_astuce;
        this.description_astuce = description_astuce;
        this.image_astuce = image_astuce;
        
    }

    public Astuce(String title_astuce,int jeu_id ,String description_astuce, String image_astuce) {
        this.jeu_id= jeu_id;
        this.title_astuce = title_astuce;
        this.description_astuce = description_astuce;
        this.image_astuce = image_astuce;
    }


    public int getIdAstuce() {
        return idAstuce;
    }

    public void setIdAstuce(int idAstuce) {
        this.idAstuce = idAstuce;
    }
    
    public int getJeuId() {
        return jeu_id;
    }

    public void setJeuId(int jeu_id) {
        this.jeu_id = jeu_id;
    }

    public String getTitle_astuce() {
        return title_astuce;
    }

    public void setTitle_astuce(String title_astuce) {
        this.title_astuce = title_astuce;
    }

    public String getDescription_astuce() {
        return description_astuce;
    }

    public void setDescription_astuce(String description_astuce) {
        this.description_astuce = description_astuce;
    }
    
        public String getImage_astuce() {
        return image_astuce;
    }

    public void setImage_astuce(String image_astuce) {
        this.image_astuce = image_astuce;
    }
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + this.idAstuce;
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
        final Astuce other = (Astuce) obj;
        if (this.idAstuce != other.idAstuce) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Astuce{" + "idAstuce=" + idAstuce + ", title_astuce=" + title_astuce + ", description_astuce=" + description_astuce + '}';
    }

    public void Title_astuce(String text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}

