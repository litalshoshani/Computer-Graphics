package Events;

import App.Model;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.HashMap;
import java.util.Map;

/**
 * Lital Shoshani
 * Uriah Ahrak
 * The class handles all the mouse 
 * events
 */
public class MouseEventsManager implements MouseMotionListener, MouseListener {

    private Map<Mouse, Action> mouseEvents;
    private Model model;

    /**
     * The constructor method, saves the hashmap
     * of all listened keys, and the model
     * @param model
     */
    public MouseEventsManager(Model model) {
        mouseEvents = new HashMap<Mouse, Action>();
        this.model = model;
    }

    /********************************
    *        BUSINESS LOGIC         *
    ********************************/

    /**
     * Add a mouse listener to the hashmap
     * @param event
     * @param action
     */
    public void addListener(Mouse event, Action action) {
        if (mouseEvents.containsKey(event)) {
            return;
        }
        
        mouseEvents.put(event, action);
    }
    
    /**
     * Remove a listener from the hashmap
     * @param event
     */
    public void removeListener(Mouse event) {
        if (!mouseEvents.containsKey(event)) {
            return;
        }
        
        mouseEvents.remove(event);
    }
    
    /**
     * A convenient method for running a specific
     * mouse event
     * @param e
     * @param type
     */
    private void run(MouseEvent e, Mouse type) {
        // If there is no listener, return
        if (!mouseEvents.containsKey(type)) {
            return;
        }

        // If the model is not ready, dont run the event
        if (!model.ready()) {
            return;
        }

        // Run it
        mouseEvents.get(type).handle(model, e);
    }

    /********************************
    *            EXTERNAL           *
    ********************************/
    
    /**
     * Run a specific mousePressed event
     */
    @Override
    public void mousePressed(MouseEvent e) {
        run(e, Mouse.PRESS);
    }

    /**
     * Run a specific mouseDragged event
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        run(e, Mouse.MOVE);
    }
    
    /**
     * Run a specific mouseReleased event
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        run(e, Mouse.RELEASE);
    }

    /********************************
    *     UNIMPLEMENTED EVENTS      *
    ********************************/

    @Override
    public void mouseMoved(MouseEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {}


    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

}