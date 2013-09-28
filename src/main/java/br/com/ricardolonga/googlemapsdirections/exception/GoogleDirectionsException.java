package br.com.ricardolonga.googlemapsdirections.exception;

public class GoogleDirectionsException extends Exception {

    private static final long serialVersionUID = 1L;

    public GoogleDirectionsException(String message, Throwable cause) {
        super(message, cause);
    }

	public GoogleDirectionsException(String message) {
		super(message);
	}

	public GoogleDirectionsException(Throwable cause) {
		super(cause);
	}

}
