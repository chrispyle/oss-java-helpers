package com.clearcapital.oss.java.exceptions;

public class ReflectionHelperSetFieldValueException extends ReflectiveOperationException {

	private static final long serialVersionUID = -4314794768866477949L;

	public ReflectionHelperSetFieldValueException(String message) {
        super(message);
    }

    public ReflectionHelperSetFieldValueException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReflectionHelperSetFieldValueException(Throwable cause) {
        super(cause);
    }


}
