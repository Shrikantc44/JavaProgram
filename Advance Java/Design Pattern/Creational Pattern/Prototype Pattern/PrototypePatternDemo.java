import java.util.Hashtable;

//A prototype is a template of any object before the actual object is constructed. In java
//also, it holds the same meaning. Prototype design pattern is used in scenarios where
//application needs to create a number of instances of a class, which has almost same state or
//differs very little.

//In this design pattern, an instance of actual object (i.e. prototype) is created on
//starting, and thereafter whenever a new instance is required, this prototype is cloned to
//have another instance. The main advantage of this pattern is to have minimal instance
//creation process which is much costly than cloning process.

//Prototype pattern refers to creating duplicate object while keeping performance in mind.
//This type of design pattern comes under creational pattern as this pattern provides one of
//the best way to create an object.

//This pattern involves implementing a prototype interface which tells to create a clone of
//the current object. This pattern is used when creation of object directly is costly.

//We're going to create an abstract class Shape and concrete classes extending the Shape
//class. A class ShapeCache is defined as a next step which stores shape objects in a
//Hashtable and returns their clone when requested.

//Create an abstract class implements Clonable interface
abstract class Shape implements Cloneable {
    private String id;
    protected String type;
    
    abstract void draw();
    
    public String getType() {
        return type;
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public Object clone() {
        Object clone = null;
        try {
            clone = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;
    }
}

//Create concrete classes extending the above class.
class Rectangle extends Shape {
    public Rectangle() {
        type = "Rectangle";
    }
    
    @Override
    public void draw() {
        System.out.println("Inside Rectangle::draw() method.");
    }
}

class Square extends Shape {
    public Square() {
        type = "Square";
    }
    
    @Override
    public void draw() {
        System.out.println("Inside Square::draw() method.");
    }
}

class Circle extends Shape {
    public Circle() {
        type = "Circle";
    }
    
    @Override
    public void draw() {
        System.out.println("Inside Circle::draw() method.");
    }
}

class ShapeCache {
    private static Hashtable<String, Shape> shapeMap = new Hashtable<String, Shape>();
    
    public static Shape getShape(String shapeId) {
        Shape cachedShape = shapeMap.get(shapeId);
        return (Shape) cachedShape.clone();
    }
    
    public static void loadCache() {
        Circle circle = new Circle();
        circle.setId("1");
        shapeMap.put(circle.getId(), circle);
        
        Square square = new Square();
        square.setId("2");
        shapeMap.put(square.getId(), square);
        
        Rectangle rectangle = new Rectangle();
        rectangle.setId("3");
        shapeMap.put(rectangle.getId(), rectangle);
    }
}

//PrototypePatternDemo uses ShapeCache class to get clones of shapes stored in a Hashtable.
class PrototypePatternDemo {
    public static void main(String[] args) {
        ShapeCache.loadCache();
        
        Shape clonedShape = (Shape) ShapeCache.getShape("1");
        System.out.println("Shape : " + clonedShape.getType());
        
        Shape clonedShape1 = (Shape) ShapeCache.getShape("1");
        System.out.println("Shape : " + clonedShape1.getType());
        
        Shape clonedShape2 = (Shape) ShapeCache.getShape("2");
        System.out.println("Shape : " + clonedShape2.getType());
        
        Shape clonedShape3 = (Shape) ShapeCache.getShape("3");
        System.out.println("Shape : " + clonedShape3.getType());
    }
}