/*Factory pattern is one of most used design pattern in Java. This type of design pattern 
comes under creational pattern as this pattern provides one of the best ways to create an object.
In Factory pattern, we create object without exposing the creation logic to the client and 
refer to newly created object using a common interface.
Implementation

We're going to create a Shape interface and concrete classes implementing the Shape
interface. A factory class ShapeFactory is defined as a next step.
FactoryPatternDemo, our demo class will use ShapeFactory to get a Shape object. It will pass
information (CIRCLE / RECTANGLE / SQUARE) to ShapeFactory to get the type of object it
needs.*/

interface Shape {
    void draw();
}

class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Inside Rectangle::draw() method.");
    }
}

class Square implements Shape {

    @Override
    public void draw() {
        System.out.println("Inside Square::draw() method.");
    }
}

class Circle implements Shape {

    @Override
    public void draw() {
        System.out.println("Inside Circle::draw() method.");
    }
}

//Create a Factory to generate object of concrete class based on given information.

class ShapeFactory {

    //use getShape method to get object of type shape
    public static Shape getShape(String shapeType){
        if(shapeType == null){
            return null;
        }
        if(shapeType.equalsIgnoreCase("CIRCLE")){
            return new Circle();
        } else if(shapeType.equalsIgnoreCase("RECTANGLE")){
            return new Rectangle();
        } else if(shapeType.equalsIgnoreCase("SQUARE")){
            return new Square();
        }
        return null;
    }

}

//Use the Factory to get object of concrete class by passing an information such as type.

class FactoryPatternDemo {

    public static void main(String[] args) {
        //ShapeFactory shapeFactory = new ShapeFactory();

        //get an object of Circle and call its draw method.
        Shape shape1 = ShapeFactory.getShape("CIRCLE");

        //call draw method of Circle
        shape1.draw();

        //get an object of Rectangle and call its draw method.
        Shape shape2 = ShapeFactory.getShape("RECTANGLE");

        //call draw method of Rectangle
        shape2.draw();

        //get an object of Square and call its draw method.
        Shape shape3 = ShapeFactory.getShape("SQUARE");

        //call draw method of Square
        shape3.draw();
    }
}
