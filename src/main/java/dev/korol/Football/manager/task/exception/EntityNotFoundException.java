package dev.korol.Football.manager.task.exception;

/**
 * @author Korol Artur
 * 01.08.2025
 */
public class EntityNotFoundException extends RuntimeException{

    public EntityNotFoundException(String message) {
        super(message);
    }

}
