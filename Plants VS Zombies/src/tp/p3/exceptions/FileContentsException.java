package tp.p3.exceptions;

public class FileContentsException extends Exception{

	private static final long serialVersionUID = 6445724603461727969L;
	
	public FileContentsException()
	{
		super("Wrong file content");
	}
	public FileContentsException(String mensaje)
	{
		super(mensaje);
	}
}