/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IService;

import java.util.List;
import Class.Astuce;

/**
 *
 * @author slimu
 */
public interface  IAstuceService {
     public void addAstuce(Astuce astuce);
    public void deleteAstuce(int idAstuce);
    public Astuce findByIdAs(int idAstuce);
    public void modifAstuce(Astuce astuce);
    public List<Astuce> getAll();
    
}

