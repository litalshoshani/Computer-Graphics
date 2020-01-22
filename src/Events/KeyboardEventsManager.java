package Events;

import App.Model;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

/**
 * Lital Shoshani
 * Uriah Ahrak
 * The class handles all the keyboard 
 * events
 */
public class KeyboardEventsManager implements KeyListener {

    private Map<Character, Action> keyPressed;
    private Model model;

    /**
     * The constructor method, saves the hashmap
     * of all listened keys, and the model
     * @param model
     */
    public KeyboardEventsManager(Model model) {
        this.keyPressed = new HashMap<Character,Action>();
        this.model = model;
    }

    /********************************
    *            EXTERNAL           *
    ********************************/

    /**
     * When a key is pressed, we look at a hashmap
     * to look for an appropriate listener,
     * and if found one - we run its Action interface's
     * handle method
     */
    @Override
    public void keyPressed(KeyEvent e) {
        char key = Character.toLowerCase(e.getKeyChar());

        if (!keyPressed.containsKey(key)) {
            return;
        }

        // Run the action
        keyPressed.get(key).handle(model, null);
    }
    
    /**
     * Add a listener to the hashmap
     * @param key
     * @param action
     */
    public void addKeyPressListener(char key, Action action) {
        if (keyPressed.containsKey(key)) {
            return;
        }
        
        keyPressed.put(key, action);
    }
    
    /**
     * Remove a listener from the hashmap
     * @param key
     */
    public void removeKeyPressListener(char key) {
        if (!keyPressed.containsKey(key)) {
            return;
        }
        
        keyPressed.remove(key);
    }
    
    /********************************
    *     UNIMPLEMENTED EVENTS      *
    ********************************/
    
    @Override
    public void keyTyped(KeyEvent e) {}
    
    @Override
    public void keyReleased(KeyEvent e) {}
}