/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviesystem.DAL;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;


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
    
    
    /**
     * 
     * @return filename
     * giver stien på den mp4 file man valger 
     * samt den åber et windue hvor man kan vælge en file
     */
    public String pickFile(){
        FileChooser fileChooser;
        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter(".mp4","*.mp4"));
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter(".mpeg4", "*.mpeg4"));
        file = fileChooser.showOpenDialog(null).getAbsoluteFile().getPath();
        fileName= file;
    return fileName;
    }
    
    
        
    }
