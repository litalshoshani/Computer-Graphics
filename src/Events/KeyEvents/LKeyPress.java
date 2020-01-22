package Events.KeyEvents;

import App.Model;
import Events.Action;

import javax.swing.*;
import java.io.File;

/**
 * Lital Shoshani
 * Uriah Ahrak
 * This class is responsible for what happens when the L key is pressed.
 */

public class LKeyPress implements Action {
    public LKeyPress() {}

    @Override
    public void handle(Model model, Object event) {

        //check the user selection and reset the values accordingly
        JFileChooser fileChooser = new JFileChooser();
        //set current directory
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        //show the dialog
        int result = fileChooser.showOpenDialog(null);

        if (result != JFileChooser.APPROVE_OPTION) {
            return;
        }
        
        File selectedFile = fileChooser.getSelectedFile();

        model.loadFile(selectedFile);
    }

}