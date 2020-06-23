import java.security.AccessControlContext;

public interface Prototype extends Cloneable {
	public AccessControl clone() throws CloneNotSupportedException;
}
