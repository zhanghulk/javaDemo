package javax.util.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 动态代理类
 * 
 * @author zhanghao
 *
 */
public class DynamicProxy implements InvocationHandler {
	//Greet接口的實現：GreetImpl
	private Object obj;

	public DynamicProxy(Object obj) {
		// Greet接口的實現：GreetImpl
		this.obj = obj;
	}

	/**
	 * 可以根据方法名和参数值, 只hook其中一个或几个函数
	 * // Method m：調用的方法,
	// Object[] args：方法要傳入的參數
	 */
	public Object invoke(Object proxy, Method m, Object[] args) throws Throwable {
		Object result;
		try {
			// 自定義的處理
			System.out.println(/*proxy +*/ "--before method " + m.getName());
			// 調用GreetImpl中方法
			result = m.invoke(obj, args);
		} catch (InvocationTargetException e) {
			throw e.getTargetException();
		} catch (Exception e) {
			throw new RuntimeException("unexpected invocation exception: " + e.getMessage());
		} finally {
			System.out.println("--after method " + m.getName());
		}
		return result;
	}
}
