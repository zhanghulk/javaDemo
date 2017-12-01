package javax.util.proxy;

import java.lang.reflect.Proxy;
import java.util.List;

public class GreetTest {
	public static void main(String[] args) {
		Greet myGreet = new GreetImpl();

		Greet greet = (Greet) newProxyInstance(myGreet);
		// 生成的greet和tmp有相同的hashCode
		greet.sayHello("zhanghao");
		greet.goodBye();
	}
	
	public static Object newProxyInstance(Object obj) {
		//要代理的类加载器
		ClassLoader classLoader = obj.getClass().getClassLoader();
		//需要被代理的类。如果
		Class[] interfaces = obj.getClass().getInterfaces();
		return Proxy.newProxyInstance(classLoader, interfaces, new DynamicProxy(obj));
	}
	
	static Class[] getClassInterfaces(Class<?> interface_class) {
		List<Class<?>> interfaces = ReflectUtil.getAllInterfaces(interface_class);
		Class[] ifs = interfaces != null && interfaces.size() > 0 ? interfaces.toArray(new Class[interfaces.size()]) : new Class[0];
		return ifs;
	}
}
