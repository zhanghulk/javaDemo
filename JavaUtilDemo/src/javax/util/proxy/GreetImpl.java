package javax.util.proxy;

public class GreetImpl implements Greet {

	@Override
	public void sayHello(String name) {
		System.out.println("Hello " + name + " !! ");
	}

	@Override
	public void goodBye() {
		System.out.println("Good bye... ");
	}

}
