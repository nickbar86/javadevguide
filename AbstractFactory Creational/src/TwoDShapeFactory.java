
public class TwoDShapeFactory extends AbstractFactory {

	@Override
	GeometricShape getShape(ShapeType name) {
		switch (name) {
		case LINE:
			return new Line();
		case CIRCLE:
			return new Circle();
		default:
			throw new DrawApplicationException("Shape Does Not Exist.");
		}
	}

}
