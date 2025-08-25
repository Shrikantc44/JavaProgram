

/*This pattern involves a single class which is responsible to creates own object while making sure 
that only single object get created.
 This class provides a way to access its only object which can be accessed directly
 without need to instantiate the object of the class.  */

class SingleObject
{
    private static SingleObject instance = null;
    private SingleObject() {}
    
    //Get the only object available
    public static SingleObject getInstance()
    {
        if(instance == null)
            instance = new SingleObject();
        return instance;
    }

    public void showMessage()
    {
        System.out.println("Hello World!");
    }
}

class SingletonPatternDemo
{
    public static void main(String[] args)
    {
        SingleObject object = SingleObject.getInstance();
        object.showMessage();
        SingleObject object1 = SingleObject.getInstance();
        object1.showMessage();
        if(object == object1)
            System.out.println("same");
    }
}