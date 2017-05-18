/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IService;

/**
 *
 * @author slimu
 */
import java.util.List;

import Class.Game;
import javafx.collections.ObservableList;


/**
 *
 * @author slimu
 */

public interface IGameService {
  
    public void addgame(Game game);
    public void deletegame(int idGame);
    public Game findById(int idGame);
    public void updateGame(Game g );
    public List<Game> listerGame();
    public ObservableList<Game> lister();
    
    
}

