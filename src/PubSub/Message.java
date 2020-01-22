package PubSub;

/**
 * Lital Shoshani
 * Uriah Ahrak
 * The interface handles the Message
 * for pub-sub system
 */
public interface Message {
    /**
     * Get type of message
     * @return
     */
    String getType();

    /**
     * Get the data
     * @return
     */
    Object getData();
}