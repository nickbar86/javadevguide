import java.util.ArrayList;
import java.util.List;

public class Application {
	public static void main(String[] args){
		List<GeometricShape> shapes = new ArrayList();
		AbstractFactory twoDFactory = FactoryProvider.getFactory(FactoryType.TWO_D_SHAPE_FACTORY);
		AbstractFactory threeDFactory = FactoryProvider.getFactory(FactoryType.THREE_D_SHAPE_FACTORY);
		shapes.add(twoDFactory.getShape(ShapeType.LINE));
		shapes.add(threeDFactory.getShape(ShapeType.SPHERE));
		shapes.forEach(GeometricShape::draw);
	}
}
