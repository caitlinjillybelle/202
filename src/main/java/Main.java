import dao.JdbiDaoFactory;
import dao.ProductDAO;
import gui.MainMenu;
 
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 
/**
 *
 * @author dyaca941
 */
public class Main {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ProductDAO dao = JdbiDaoFactory.getProductDAO();
        // create the frame instance
        MainMenu frame = new MainMenu(dao);
 
        // centre the frame on the screen
        frame.setLocationRelativeTo(null);
 
        // show the frame
        frame.setVisible(true);
    }
    
}