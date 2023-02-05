package ro.sapientia.furniture.error;

import javassist.NotFoundException;

public class FurnitureItemNotFoundException extends NotFoundException {
    public FurnitureItemNotFoundException(String msg) {
        super(msg);
    }
}
