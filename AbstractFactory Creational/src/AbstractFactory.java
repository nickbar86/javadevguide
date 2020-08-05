import java.util.Optional;

public abstract class AbstractFactory {
	abstract GeometricShape getShape(ShapeType name);
}
