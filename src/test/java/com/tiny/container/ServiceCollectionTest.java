package com.tiny.container;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServiceCollectionTest {

    @Test
    void getContainer() {
    }

    @Test
    void addTransient() {
        ServiceCollection serviceCollection = new ServiceCollection();
        serviceCollection.addTransient(ITest.class,TestClass.class);
        //act
        ITest obj= serviceCollection.resolve(ITest.class);
        ITest obj2= serviceCollection.resolve(ITest.class);
        //assert
        assertNotEquals(null,obj);
        assertNotEquals(null,obj2);
        //validated different  object will get as transient scope
        assertNotEquals(obj,obj2);
        obj.display();
    }

    @Test
    void addSingleton() {
        //arrange
        ServiceCollection serviceCollection = new ServiceCollection();
        serviceCollection.addSingleton(ITest.class,TestClass.class);
        //act
        ITest obj= serviceCollection.resolve(ITest.class);
        ITest obj2= serviceCollection.resolve(ITest.class);
        //assert
        assertNotEquals(null,obj);
        assertNotEquals(null,obj2);
        //validated same object will get
        assertEquals(obj,obj2);
        obj.display();

    }

    @Test
    void resolve() {
        //arrange
        ServiceCollection serviceCollection = new ServiceCollection();
        serviceCollection.addSingleton(ITest.class,TestClass.class);
        //act
        ITest obj= serviceCollection.resolve(ITest.class);
        //assert
        //assert correct type is reterning from resolve
        assertEquals(TestClass.class,obj.getClass());

    }

    @Test
    void addRequestScope() {
        ServiceCollection serviceCollection = new ServiceCollection();
        serviceCollection.AddRequestScope(ITest.class,TestClass.class);
        //act
        ITest obj= serviceCollection.resolve(ITest.class);
        ITest obj2= serviceCollection.resolve(ITest.class);
        //assert
        assertNotEquals(null,obj);
        assertNotEquals(null,obj2);
        //scope validation is hard, so we are assumed in this scenario it acts as singleton
        // so execution of test will be the scope
        //thi will be bound to the request object and at request end the scope map will deallocate
        assertEquals(obj,obj2);
        obj.display();
    }

    @Test
    void register_with_SingleTone() {
        //arrange
        ServiceCollection serviceCollection = new ServiceCollection();

        //act
        serviceCollection.register(TestClass.class,Scope.Singleton);
        //this will add the object in singletonTank Map
        ITest obj= serviceCollection.resolve(TestClass.class);
        //assert

        assertEquals(serviceCollection.getContainer().singletonTank.size(),1);
        assertEquals(obj,serviceCollection.getContainer().singletonTank.get(TestClass.class));
    }
    @Test
    void register_with_RequestScope() {
        //arrange
        ServiceCollection serviceCollection = new ServiceCollection();

        //act
        serviceCollection.register(TestClass.class,Scope.RequestScope);
        //this will add the object in scopeTank Map
        ITest obj= serviceCollection.resolve(TestClass.class);
        //assert

        assertEquals(serviceCollection.getContainer().scopeTank.size(),1);
        assertEquals(obj,serviceCollection.getContainer().scopeTank.get(TestClass.class));
    }
    @Test
    void register_with_Transient() {
        //arrange
        ServiceCollection serviceCollection = new ServiceCollection();

        //act
        serviceCollection.register(TestClass.class,Scope.Transient);

        //assert

      Descriptor descriptor=  serviceCollection.getContainer().tank.get(TestClass.class);

        assertEquals(descriptor.getImplementer(),TestClass.class);
    }

}
interface  ITest{
    String display();
}
class TestClass implements ITest{

    @Override
    public String display() {
        return "display test class";
    }
}