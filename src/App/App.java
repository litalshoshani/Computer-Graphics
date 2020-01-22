package App;

/**
 * Lital Shoshani
 * Uriah Ahrak
 */

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

import PubSub.Message;
import PubSub.ViewportMessage;
import UI.GUI;
import View.Viewport;

/**
 * The class handles the main App
 */

public class App implements Subscriber<Message> {

    private Frame frame;
    private GUI gui;
    private Model model;

    public App() {
        frame = new Frame("Exercise1");
    }

    /**
     * The run method
     * runs the app
     */
    public void run() {
        // Create the Model and the GUI
        model = new Model();
        gui = new GUI(model);

        // Subscribe to paint events
        model.subscribe(gui);
        model.subscribe(this);

        // Setup the frame
        
        frame.add(gui);

        WindowAdapter myWindowAdapter = new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        };

        frame.addWindowListener(myWindowAdapter);
        frame.pack();
        frame.setVisible(true);
        frame.requestFocus();

        // Start the program
        model.startProgram();
    }

    @Override
    public void onNext(Message message) {
        if (message instanceof ViewportMessage) {
            Viewport viewport = (Viewport)(message.getData());
            frame.setSize(viewport.getWidth(), viewport.getHeight());
            frame.setVisible(false);
            frame.pack();
            frame.setVisible(true);
        }
    }

    /********************************
    *     UNIMPLEMENTED EVENTS      *
    ********************************/

    @Override
    public void onSubscribe(Subscription subscription) {

    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onComplete() {

    }

}