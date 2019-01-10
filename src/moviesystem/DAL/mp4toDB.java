/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviesystem.DAL;

import javafx.stage.FileChooser;


/**
 *
 * @author andre
 */
public class mp4toDB
{

    private DBConnectionProvider dbConnect;
    private String fileName;
    private String file;
    

    public mp4toDB()
    {
        dbConnect = new DBConnectionProvider();
    }
    
    public String pickFile(){
        FileChooser fileChooser;
        fileChooser = new FileChooser();
        file = fileChooser.showOpenDialog(null).getAbsoluteFile().getPath();
        fileName= file;
    return fileName;
    }
    
    
        
    }
