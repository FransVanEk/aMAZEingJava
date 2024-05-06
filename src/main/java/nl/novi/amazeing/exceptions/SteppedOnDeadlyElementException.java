package nl.novi.amazeing.exceptions;

import jdk.jshell.spi.ExecutionControl;

public class SteppedOnDeadlyElementException extends RuntimeException {

    public SteppedOnDeadlyElementException(String message) {
       super(message);
    }
}
