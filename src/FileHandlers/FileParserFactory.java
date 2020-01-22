package FileHandlers;

import App.Model;
import Scene.SceneParser;
import View.ViewParser;

import java.io.File;

/**
 * Lital Shoshani
 * Uriah Ahrak
 * The class handles a factory
 * of fileparsers and syncables
 */
public class FileParserFactory {

    /**
     * a method to create the needed file parser
     * @param file
     * @param model
     */
    public FileParserFactoryData create(File file, Model model) {
        String filePathStr = file.getAbsolutePath();
        String fileEnding = filePathStr.substring(filePathStr.lastIndexOf('.') + 1);

        FileParserFactoryData fileParserFactoryData = null;

        switch (fileEnding) {
            case "viw":
                fileParserFactoryData = new FileParserFactoryData(model.getView(), new ViewParser());
                break;
            case "scn":
                fileParserFactoryData = new FileParserFactoryData(model.getScene(), new SceneParser());
                break;
            default:
                break;
        }

        return fileParserFactoryData;
    }
}
