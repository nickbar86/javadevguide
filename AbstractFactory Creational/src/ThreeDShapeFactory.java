public class ThreeDShapeFactory extends AbstractFactory {
	@Override
	GeometricShape getShape(ShapeType name) {
		switch (name) {
		case SPHERE:
			return new Sphere();
		default:
			throw new DrawApplicationException("Shape Does Not Exist.");
		}
	}

}
