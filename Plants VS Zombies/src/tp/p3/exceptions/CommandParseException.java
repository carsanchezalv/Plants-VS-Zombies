package tp.p3.exceptions;

public class CommandParseException extends Exception{

	private static final long serialVersionUID = -3223640762907563123L;
	
	public CommandParseException()
	{
		super("Unknown command.");
	}
	public CommandParseException(String mensaje)
	{
		super(mensaje);
	}
}
