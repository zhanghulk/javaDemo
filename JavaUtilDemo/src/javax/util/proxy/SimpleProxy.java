package javax.util.proxy;

/**
 * 简单的静态代理
 * @author zhanghao
 *
 */
public class SimpleProxy implements Greet {

	private Greet greet = null;

	SimpleProxy(Greet greet) {
		this.greet = greet;
	}

	public void sayHello(String name) {
		System.out.println("SimpleProxy--before method sayHello");
		greet.sayHello(name);
		System.out.println("SimpleProxy--after method sayHello");
	}

	public void goodBye() {
		System.out.println("SimpleProxy--before method goodBye");
		greet.goodBye();
		System.out.println("SimpleProxy--after method goodBye");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Greet greet = new SimpleProxy(new GreetImpl());// 生成代理
		greet.sayHello("hulk");
		greet.goodBye();

	}
}
