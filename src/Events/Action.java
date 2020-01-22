package Events;

import App.Model;

/**
 * Lital Shoshani
 * Uriah Ahrak
 * The Action interface, which is given to the 
 * events system as a workflow for a specific
 * event
 */
public interface Action {
    /**
     * Each specific implementation needs
     * to implement this method, and that's how
     * it performs tasks when a specific event
     * occurres
     * 
     * @param model model of the system
     * @param event specific event details
     */
    void handle(Model model, Object event);
}