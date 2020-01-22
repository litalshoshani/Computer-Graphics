package UI;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

import App.Model;
import Events.KeyboardEventsManager;
import Events.Mouse;
import Events.MouseEventsManager;
import Events.KeyEvents.CKeyPress;
import Events.KeyEvents.LKeyPress;
import Events.KeyEvents.QKeyPress;
import Events.KeyEvents.RKeyPress;
import Events.KeyEvents.XKeyPress;
import Events.KeyEvents.YKeyPress;
import Events.KeyEvents.ZKeyPress;
import Events.MouseEvents.MouseMove;
import Events.MouseEvents.MousePress;
import Events.MouseEvents.MouseRelease;
import Math.Edge;
import PubSub.DrawMessage;
import PubSub.Message;
import PubSub.ViewportMessage;
import View.View;
import View.Viewport;

/**
 * Lital Shoshani
 * Uriah Ahrak
 * The GUI class represents the GUI window
 * inside the app
 */
public class GUI extends Canvas implements Subscriber<Message> {

    private static final long serialVersionUID = 1L;
    private KeyboardEventsManager keyboardEvents;
    private MouseEventsManager mouseEvents;
    private List<Drawable> drawables;

    /**
     * The constructor method which
     * initializes the GUI
     * @param model
     */
    public GUI(Model model) {
        // Init the events classes
        keyboardEvents = new KeyboardEventsManager(model);
        mouseEvents = new MouseEventsManager(model);

        // Init drawables list
        drawables = new ArrayList<>();
        
        // Get the view
        View view = model.getView();

        // Set canvas size according to the viewport
        setSize(view.getViewport().getWidth(), view.getViewport().getHeight());

        // Add listeners
        addKeyboardEvents();
        addMouseEvents();
        addListeners();
        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent componentEvent) {
                model.getView().setViewport(
                    new Viewport(getWidth(), getHeight()));

                if (model.ready()) {
                    model.tick();
                }
            }
        });

        view.subscribe(this);

    }

    /********************************
    *        BUSINESS LOGIC         *
    ********************************/

    /**
     * The method adds all listeners
     * to the GUI
     */
    private void addListeners() {
        addMouseListener(mouseEvents);
        addMouseMotionListener(mouseEvents);
        addKeyListener(keyboardEvents);

    }
    
    /**
     * The method adds keyboard
     * event listeners
     */
    private void addKeyboardEvents() {
        keyboardEvents.addKeyPressListener('c', new CKeyPress());
        keyboardEvents.addKeyPressListener('r', new RKeyPress());
        keyboardEvents.addKeyPressListener('l', new LKeyPress());
        keyboardEvents.addKeyPressListener('x', new XKeyPress());
        keyboardEvents.addKeyPressListener('y', new YKeyPress());
        keyboardEvents.addKeyPressListener('z', new ZKeyPress());
        keyboardEvents.addKeyPressListener('q', new QKeyPress());
    }
    
    /**
     * The method adds mouse
     * event listeners
     */
    private void addMouseEvents() {
        mouseEvents.addListener(Mouse.PRESS, new MousePress());
        mouseEvents.addListener(Mouse.MOVE, new MouseMove());
        mouseEvents.addListener(Mouse.RELEASE, new MouseRelease());
    }
    
    /********************************
    *           EXTERNAL            *
    ********************************/

    public void paint(Graphics g) {
        for (Drawable drawable : drawables) {
            drawable.draw(g);
        }
    }
    
    @Override
    public void onNext(Message message) {
        if (message instanceof DrawMessage) {
            List<Edge> edges = (List<Edge>)(message.getData());
            drawables.clear();
            drawables.addAll(edges);
        } else if (message instanceof ViewportMessage) {
            Viewport viewport = (Viewport)(message.getData());
            setSize(viewport.getWidth(), viewport.getHeight());
        }

        repaint();
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