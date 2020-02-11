package tp.p3.exceptions;


public class CommandExecuteException extends Exception {

	private static final long serialVersionUID = 142972923262089800L;
	
	public CommandExecuteException()
	{
		super("Command execution failed");
	}
	public CommandExecuteException(String mensaje)
	{
		super(mensaje);
	}
}
