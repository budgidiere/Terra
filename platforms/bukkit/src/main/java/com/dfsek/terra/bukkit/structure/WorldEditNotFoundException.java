package com.dfsek.terra.bukkit.structure;

public class WorldEditNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1899999999;
    
    public WorldEditNotFoundException() {
    }

    public WorldEditNotFoundException(String message) {
        super(message);
    }

    public WorldEditNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public WorldEditNotFoundException(Throwable cause) {
        super(cause);
    }

    public WorldEditNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
