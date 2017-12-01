package javax.util.proxy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

public class ReflectUtil {
	
	public static void field_set(Class<?> c_class, Object o_object, String field_name, Object value){
		try {
			Field field = c_class.getDeclaredField(field_name);
			field.setAccessible(true);
			field.set(o_object, value);
		} catch (Exception e) {
			e.printStackTrace();
		} 	
	}
	
	public static void field_set(Class<?> c_class, String field_name, Object value){
		try {
			Field field = c_class.getDeclaredField(field_name);
			field.setAccessible(true);
			field.set(null, value);
		} catch (Exception e) {
			e.printStackTrace();
		} 	
	}
	
	public static Object field_get(Class<?> c_class, Object o_object, String field_name){
		try {
			Field field = c_class.getDeclaredField(field_name);
			field.setAccessible(true);
			return field.get(o_object);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 	
	}
	
	public static Object field_get(Class<?> c_class, String field_name){
		try {
			Field field = c_class.getDeclaredField(field_name);
			field.setAccessible(true);
			return field.get(null);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 	
	}	
	
// full	
	public static Object method_invoke(Class<?> c_class, Object o_object, String method_name, Class[] param_class, Object[] args){
		
		try {
			Method m = c_class.getMethod(method_name, param_class);
			m.setAccessible(true);
			return m.invoke(o_object, args);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

// shortcut 
	public static Object method_invoke(Object o_object, String method_name, Class[] param_class, Object[] args){
		return method_invoke(o_object.getClass(), o_object, method_name, param_class, args);
	}
	
	public static Object method_invoke(Object o_object, String method_name){
		return method_invoke(o_object.getClass(), o_object, method_name, null, null);
	}	
	
// for static method	
	public static Object method_invoke(Class<?> c_class, String method_name, Class[] param_class, Object[] args){
		return method_invoke(c_class, null, method_name, param_class, args);
	}
	
	public static Object method_invoke(Class<?> c_class, String method_name){
		return method_invoke(c_class, null, method_name, null, null);
	}	


   public static List<Class<?>> getAllInterfaces(final Class<?> cls) {
        if (cls == null) {
            return null;
        }
        final LinkedHashSet<Class<?>> interfacesFound = new LinkedHashSet<Class<?>>();
        getAllInterfaces(cls, interfacesFound);
        return new ArrayList<Class<?>>(interfacesFound);
    }

    private static void getAllInterfaces(Class<?> cls, final HashSet<Class<?>> interfacesFound) {
        while (cls != null) {
            final Class<?>[] interfaces = cls.getInterfaces();

            for (final Class<?> i : interfaces) {
                if (interfacesFound.add(i)) {
                    getAllInterfaces(i, interfacesFound);
                }
            }
            cls = cls.getSuperclass();
        }
    }	
    
	public static String mv_to_share_dir(String org){
		
		String name = org.substring(org.lastIndexOf("/")+1, org.length());
		String share_path = "/sdcard/.tianji_share/"+name;
		
		try{
			File src = new File(org);
			File dst = new File(share_path);
		
		    InputStream in = new FileInputStream(src);
		    OutputStream out = new FileOutputStream(dst);
			    // Transfer bytes from in to out
			byte[] buf = new byte[8192];
		    int len;
		    while ((len = in.read(buf)) > 0) {
		        out.write(buf, 0, len);
		    }
		    in.close();
		    out.close();
	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return share_path;	
	}
	
	public static void log_obj(Object o){
		
		if(o==null)
			return;
		
		Class c=o.getClass();
		Field[] o_fields = c.getDeclaredFields();
		try{
			for (Field f : o_fields){
				f.setAccessible(true);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}				
	}
	
	public static void log_class(Class c){
		
		if(c==null)
			return;

		Field[] o_fields = c.getDeclaredFields();
		try{
			for (Field f : o_fields){
				f.setAccessible(true);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		Method[] ms = c.getMethods();
		try{
			for (Method m : ms){
				m.setAccessible(true);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void log_class_1(Class<?> clazz) {
	    while (clazz != null) {
	    	log_class(clazz);
	    	clazz = clazz.getSuperclass();
	    }
	}
	
	static boolean d2o_end_proc_flag=false;
	
}

