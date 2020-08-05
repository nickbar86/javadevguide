import java.util.Optional;

public class FactoryProvider {
	public static AbstractFactory getFactory(FactoryType factoryType){
		switch (factoryType) {
		case TWO_D_SHAPE_FACTORY:
			return new TwoDShapeFactory();
		case THREE_D_SHAPE_FACTORY:
			return new ThreeDShapeFactory();
		default:
			throw new DrawApplicationException("ShapeFactory Does Not Exist.");
		}
	}
}
