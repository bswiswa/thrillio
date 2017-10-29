#Implementing managers

* Managers have business logic
We need to write some methods that will instantiate the entities we just created.
The methods we write will be used in the data store which will be written later.

*We will write the User manager and Bookmark manager.
User manager will have business logic related to users. While Bookmark manager will do the same for Bookmarks.

Manager classes are stateless and maintain no instance variables. We can maintain them as static utility classes.
However for managers it is recommended to instead create just one single instance of each of the manager classes. The reason is that managers can need to implement interfaces and in those instances, you cannot use a static utility class as static methods in that class cannot hide or overwite instance methods in an interface. So the recommended way is to create a single instance of the manager class.
This is achieved by using a design pattern called a Singleton pattern

##Singleton pattern 

Is a design pattern that ensures that only one object is created and no more. Singleton pattern also provides a global point to access the object. So manager classes are basically Singletons