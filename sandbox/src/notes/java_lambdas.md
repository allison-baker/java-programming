# Interfaces and Abstract classes

[jdoodle](https://www.jdoodle.com/online-java-compiler)

## Interfaces

Java interfaces define a potential future class, outlining all of the methods that it will contain. However, it does **NOT** include implementation for those methods! (these are called abstract methods). The implementation deatails are left to the implementing class to determine. for example, `List<E>` is an interface which declares methods like `get()` and `add(E e)`. `ArrayList` and `LinkedList` both implement `List`, and thereforemust also implement the `get()` and `add()` methods. And we know that the details of these implementations are very different under the hood, even though they accept identical inputs and produce identical outputs.

```
Interface List<E> {

    void add(E e);

    E get();

    boolean isEmpty();

    ...

}
```

If you want to implement an interface, you can do so like this:

```
class ArrayList<E> extends AbstractList<E> implements List<E> {

    ArrayList<>(/*params here*/) {
        super(/*some, but probably not all, params here*/)
    }

    void add(E e) {
        // code here
    }

    E get() {
        // code here
    }

    boolean isEmpty() {
        //code here
    }

    ...

}
```

- Interfaces are extremely powerful because of polymorphism! It's a great idea to have your datastructures and methods accept interfaces instead of concrete implementations to allow for more flexibility.
- A class can implement any number of interfaces
- all methods declared in an interface are public by default
- an interface **CAN** have static methods declared **AND** defined.
- any variables defined in an interface are public, static, and final.
- an interface can extend another interface (or more than one)

## Abstract classes

- Similar to an interface, an abstract class can declare abstract methods that must be implemented by all children
- An abstract class **CAN ALSO** include defined methods (concrete methods with a specific implementation).
- Abstract classes are extended, not implemented
- Any class can only extend a maximum of **ONE** abstract class.
- An abstract class can declare its own non-public, non-static data members
- an abstract class can have a defined constructor which the children can reference using `super()`



# Lambdas, Functional Interfaces, and Streams

## [Functional Interfaces](https://www.geeksforgeeks.org/functional-interfaces-java/)

In Java, everything is a class except for the following 8 data types:

### Java Primitives

- boolean
- byte
- char
- short
- int
- long
- float
- double

However, occasionally we only need a single function, and building an entire class just to hold this one function is overkill. In this case, we can use a functional interface. A functional Interface looks just like any other Java interface, exccept that it only contains ONE method. It might look like this:

```
@functionalInterface
interface calculate {
    int doCalculate(int x, int y);
}
```

Now of course, we can't use this by itself because it's an interface and the method has no body definition. So why is this useful? Well, we can use this as a parameter in the definition of another method! This is a very convenient way to get pass _functionality_ around your program, where normally you would have to pass objects. (and of course, the implementation of one of these functional interfaces is an object, but we can treat it as if it's not).

Lets look at an example, the `List.forEach()` method

### Built in Java functional interfaces

- `Consumer` - accepts one parameter and returns nothing. Useful for producing side-effects (like printing or logging).

```
@FunctionalInterface
interface Consumer<T> {
    void accept(T t);
}
```

- `Function` - accepts one parameter and returns one result. Useful for performing conversions

```
@FunctionalInterface
public interface Function<T, R> {
    R apply(T t);
}
```

- `Predicate` - accepts one parameter and returns one boolean. Can be thought of as a special version of Function. Intended for creating custom comparisons.

```
@FunctionalInterface
public interface Predicate<T> {
    boolean test(T t);
}
```

- `Supplier` - accepts zero parameters and produces one result. useful for producing random values from an encoded set of possibilities or for creating a collection

```
@FunctionalInterface
public interface Supplier<T> {
    T get();
}
```

There are also exist versions which accept two parameters:

- `BiConsumer`
- `BiFunction`
- `BiPredicate`

If you need a functional interface that accepts more than two parameters, you will have to create your own.

### Why use functional interfaces?

Normally in order to use an interface, we have to first create our own class that implements it, then we have to create an object of that type, then we have to pass that object as a parameter wherever we want to use it,

```
class MyPrinter implements Consumer<String> {
    void accept(String str) {
        System.out.println(str);
    }
}

...

    MyPrinter printer = new MyPrinter();
    previouslyDefinedArray.forEach(printer)
```

However, functional interfaces allow us to skip the first two steps and go straight to the 'passing a parameter' step by using a lambda!

```
    previouslyDefinedArray.forEach( (str) -> {System.out.println(str)} )
```

What normally would have taken 7 lines of code can now be done on just one, and it's much clearer too!
