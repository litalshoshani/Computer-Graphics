package App;

import Clipping.Clipping;
import FileHandlers.FileParser;
import FileHandlers.FileParserFactory;
import FileHandlers.FileParserFactoryData;
import Math.Edge;
import Math.Matrix;
import Math.Point;
import Math.Polygon;
import PubSub.DrawMessage;
import PubSub.Message;
import PubSub.ViewportMessage;
import Scene.Scene;
import Transformations.RotateOrientation;
import Transformations.TransformationCase;
import Transformations.TransformationFactory;
import Transformations.TransformationStrategy;
import View.View;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Flow.Publisher;
import java.util.concurrent.Flow.Subscriber;
import java.util.function.Function;

/**
 * Lital Shoshani
 * Uriah Ahrak
 * The Model class,
 * handles all the operations across
 * the application
 */
public class Model implements Publisher<Message> {

    private Point source;
    private Point center;

    private Matrix CT;
    private Matrix AT;
    private Matrix FT;

    private TransformationFactory factory;
    private Function<Point, TransformationCase> strategy;
    private RotateOrientation orientation;

    private Scene scene;
    private View view;
    private List<Subscriber<? super Message>> subscribers;

    private Clipping clipping;

    private boolean isClipping;

    private File viwDefaultFile;
    private File scnDefaultFile;
    private FileParserFactory fileFactory;

    /**
     * The constructor method,
     * creates all the entities 
     * and loads the default files
     */
    public Model() {
        // Create entities
        view = new View();
        scene = new Scene();

        // Create subscribers array
        subscribers = new ArrayList<>();

        // Define the strategy of the transformation decision
        strategy = new TransformationStrategy(view);

        // Clipping set to false by default
        isClipping = false;

        // Load default files
        viwDefaultFile = new File("src/cowExample.viw");
        scnDefaultFile = new File("src/cowExample.scn");

        // File factory
        fileFactory = new FileParserFactory();
        
        // Prepare clipping algorithm
        clipping = new Clipping(view.getViewport());

        AT = CT = Matrix.I(4);


        // Prepare default files
        uploadDefaultFiles();
    }

    /********************************
    *            GETTERS            *
    ********************************/

    /**
     * @return the strategy
     */
    public Function<Point, TransformationCase> getStrategy() {
        return strategy;
    }

    /**
     * @return the fT
     */
    public Matrix getFT() {
        return FT;
    }

    /**
     * @return the factory
     */
    public TransformationFactory getTransformationFactory() {
        return factory;
    }

    /**
     * @return the center
     */
    public Point getCenter() {
        return center;
    }

    /**
     * @return the source
     */
    public Point getSource() {
        return source;
    }

    /**
     * @return the aT
     */
    public Matrix getAT() {
        return AT;
    }

    /**
     * @return the cT
     */
    public Matrix getCT() {
        return CT;
    }

    /**
     * @return the orientation
     */
    public RotateOrientation getOrientation() {
        return orientation;
    }

    /**
     * @return the scene
     */
    public Scene getScene() {
        return scene;
    }

    /**
     * @return the view
     */
    public View getView() {
        return view;
    }

    /**
     * @return the clipping
     */
    public boolean isClippingOn() {
        return isClipping;
    }

    public Clipping getClipping() {
        return clipping;
    }

    /********************************
    *            SETTERS            *
    ********************************/

    /**
     * @param source the source to set
     */
    public void setSource(Point source) {
        this.source = source;
    }

    /**
     * @param factory the factory to set
     */
    public void setTransformationFactory(TransformationFactory factory) {
        this.factory = factory;
    }

    /**
     * @param cT the cT to set
     */
    public void setCT(Matrix cT) {
        CT = cT;
    }

    /**
     * @param aT the aT to set
     */
    public void setAT(Matrix aT) {
        AT = aT;
    }

    /**
     * @param fT the fT to set
     */
    public void setFT(Matrix fT) {
        FT = fT;
    }

    /**
     * @param orientation the orientation to set
     */
    public void setOrientation(RotateOrientation orientation) {
        this.orientation = orientation;
    }

    /**
     * @return the clipping
     */
    public void setClipping(boolean clip) {
        isClipping = clip;
    }

    /********************************
    *         BUSINESS LOGIC        *
    ********************************/

    /**
     * The method handles 1 framerate
     */
    public void tick() {
        center = new Point(view.getViewport().getWidth() / 2, 
                           view.getViewport().getHeight() / 2);

        // Calculate the current transformation matrix
        calcFT();

        // Transform the scene
        scene.transform(FT);

        // Prepare edges list for drawing
        List<Edge> edges = new ArrayList<>();

        for (Polygon p : scene.getPolygons()) {
            edges.addAll(p.getEdges());
        }
        
        // If the clipping flag is on, clip the edges
        if (isClipping) {
            edges = clipping.clip(edges);
        }
        
        // Edges frame lines to drawing
        edges.addAll(clipping.getInnerFrame());

        // Publish the edges to the drawing system
        publish(new DrawMessage(edges));
    }

    /**
     * The method resets the state
     * of the model
     */
    public void reset() {
        // Reset the main entities state
        scene.reset();
        view.reset();

        // Reset the center position
        center = new Point(view.getViewport().getWidth() / 2, 
                           view.getViewport().getHeight() / 2);

        // Reset the main matrices to I
        AT = Matrix.I(4);
        CT = Matrix.I(4);

        // Reset the orientation of the rotation
        orientation = RotateOrientation.X;

        // Reset the clipping flag
        isClipping = false;

        // publish to change frame size
        publish(new ViewportMessage(view.getViewport()));

        // Change 1 frame
        tick();  
    }

    /**
     * The method calculates the transformation matrix
     */
    public void calcFT() {
        // Get all the viewing matrices
        Matrix MV1 = view.getMV1();
        Matrix MV2 = view.getMV2();
        Matrix P = view.getP();

        // Calculate
        FT = MV2.mult(P).mult(CT).mult(AT).mult(MV1);
    }

    /**
     * The method returns if the model
     * is ready (Means two files loaded)
     * @return if two files loaded
     */
    public boolean ready() {
        return scene.ready() && view.ready();
    }

    /**
     * The method publishes the edges
     * to the pub-sub system (mainly for the GUI)
     * @param message
     */
    private void publish(Message message) {
        for (Subscriber<? super Message> subscriber : subscribers) {
            subscriber.onNext(message);
        }
    }

    /**
     * The method upload the default files
     * to the model
     */
    private void uploadDefaultFiles() {
        loadFile(viwDefaultFile);
        loadFile(scnDefaultFile);
    }

    /**
     * The method starts the program if 
     * the model is ready
     */
    public void startProgram() {
        if (ready()) {
            reset();
        }
    }

    public void loadFile(File file) {
        
        FileParserFactoryData fileParserFactoryData = fileFactory.create(file, this);

        FileParser fileParser = fileParserFactoryData.getFileParser();
        
        fileParser.parseFile(file);
        fileParser.sync(fileParserFactoryData.getSyncable());

        if (ready()) {
            reset();
        }
    }

    /********************************
    *           EXTERNAL            *
    ********************************/

    /**
     * The method subscribes a new subscriber
     * to the pub-sub system
     */
    
    @Override
    public void subscribe(Subscriber<? super Message> subscriber) {
        subscribers.add(subscriber);
    }
}