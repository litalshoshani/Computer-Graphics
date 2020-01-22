package FileHandlers;

import View.Syncable;

import java.io.File;

/**
 * Lital Shoshani
 * Uriah Ahrak
 * The FileParser interface,
 * mainly for View and Scene parsers
 */
public interface FileParser {
    /**
     * The parseFile gets a file
     * and parses it according to its
     * rules
     * @param file
     */
    void parseFile(File file);

    /**
     * The method gets a syncable object, 
     * and syncs it according to a specific
     * logic
     * @param syncable
     */
    void sync(Syncable syncable);
}
