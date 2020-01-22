package FileHandlers;

import View.Syncable;

/**
 * Lital Shoshani
 * Uriah Ahrak
 * The class organizes the data from the 
 * FileParserFactory in one data structure
 */
public class FileParserFactoryData {

    private Syncable syncable;
    private FileParser fileParser;

    /**
     * The constructor gets a Syncable object,
     * and a FileParser object and stores them
     * @param syncable
     * @param fileParser
     */
    public FileParserFactoryData(Syncable syncable, FileParser fileParser) {
        this.syncable = syncable;
        this.fileParser = fileParser;
    }

    /********************************
    *            GETTERS            *
    ********************************/
    
    /**
     * @return the fileParser
     */
    public FileParser getFileParser() {
        return fileParser;
    }

    /**
     * @return the syncable
     */
    public Syncable getSyncable() {
        return syncable;
    }
}