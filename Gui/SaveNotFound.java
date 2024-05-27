package Gui;

public class SaveNotFound extends IndexOutOfBoundsException {

    public SaveNotFound(String message) {
        super(message);
    }

    public static void throwItemSaveNotFound(String message) {
        throw new SaveNotFound("Item not found exception: " + message);
    }
}