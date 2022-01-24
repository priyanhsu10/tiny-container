# tiny-container is IOC container Open source project 
This will small and easy to use  IOC container 

New   JAVA MVC framework will be launch . this framework use this 
IOC container 
This new framework heavily inspire by Aspnet core.
This framework will be exposed the ton of extension points for 
custom implementations just like Aspnet core .
without any magic like spring framework

Usage 

SingleTon


`ServiceCollection serviceCollection = new ServiceCollection();

   Registration of singleton object

serviceCollection.addSingleton(ITest.class,TestClass.class);

resolution of  object from container

ITest obj= serviceCollection.resolve(ITest.class);

`


Transient 


`ServiceCollection serviceCollection = new ServiceCollection();

Registration of Transient object

serviceCollection.addTransient(ITest.class,TestClass.class);

resolution of  object from container

ITest obj= serviceCollection.resolve(ITest.class);

`



Request Scope


`ServiceCollection serviceCollection = new ServiceCollection();

Registration of RequestScope object

serviceCollection.AddRequestScope(ITest.class,TestClass.class);

resolution of  object from container

ITest obj= serviceCollection.resolve(ITest.class);

`