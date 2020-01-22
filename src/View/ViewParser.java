package View;

/**
 * Lital Shoshani
 * Uriah Ahrak
 * This class is responsible for parsing the viw file
 */

import FileHandlers.FileParser;
import Math.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class ViewParser implements FileParser {

    private Point position;
    private Point lookAt;
    private Point up;

    private double left;
    private double right;
    private double top;
    private double bottom;

    private int viewPortWidth;
    private int viewPortHeight;


    public ViewParser() {
    }

    /**
     * parse over a viw file, and creates the viewing objects accordingly.
     * @throws Exception
     */
    @Override
    public void parseFile(File file) {
        try {
            BufferedReader buff = new BufferedReader(new FileReader(file));
            String str;
            String splitStr[];

            while((str = buff.readLine()) != null){
                //check str length
                splitStr = str.split(" ");
                List<Double> values = new ArrayList<>();
                for(int i = 1; i < splitStr.length; i++){
                    values.add(Double.parseDouble(splitStr[i]));
                }
                switch (splitStr[0]){
                    case "Position":
                        position = new Point(values.get(0), values.get(1), values.get(2));
                        break;
                    case "LookAt":
                        lookAt = new Point(values.get(0), values.get(1), values.get(2));
                        break;
                    case "Up":
                        up = new Point(values.get(0), values.get(1), values.get(2));
                        break;
                    case "Window":
                        //the order of parameters is: left, right, bottom, top
                        left = values.get(0);
                        right = values.get(1);
                        bottom = values.get(2);
                        top = values.get(3);
                        break;
                    case "Viewport":
                        viewPortWidth = values.get(0).intValue();
                        viewPortHeight = values.get(1).intValue();
                        break;
                    default:
                        break;
                }
            }

            buff.close();
        } catch (Exception e){
            System.out.println("error in file reading");
        }
    }

    /**
     * sync a syncable object with the created objects of the viw file
     * @param syncable
     */
    @Override
    public void sync(Syncable syncable) {
        View view = (View)syncable;

        view.setLookAt(lookAt);
        view.setPosition(position);
        view.setUp(up);

        view.setLeft(left);
        view.setRight(right);
        view.setTop(top);
        view.setBottom(bottom);

        view.setViewport(new Viewport(viewPortWidth, viewPortHeight));
        view.setViewportOriginalSize(viewPortWidth, viewPortHeight);

        view.setReady(true);
    }
}
