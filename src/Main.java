import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello Java Annotations!");
        MyClass mC = new MyClass();
        mC.method();

        Class clazz = MyClass.class;
        Annotation[] annotations = clazz.getAnnotations();
        Arrays.stream(annotations).forEach(System.out::println);
        Arrays.stream(annotations)
                .filter(a -> a instanceof MyAnnotation)
                .map(a -> (MyAnnotation)a)
                .map(a -> "[name:"+a.name()
                        +";age:"+a.age()
                        +";other names:"+Arrays.toString(a.otherNames())
                        +"]")
                .forEach(System.out::println);
        // easier version
        for (Annotation a : annotations) {
            if (a instanceof MyAnnotation) { // type check
                MyAnnotation myA = (MyAnnotation)a; // class cast
                System.out.println("[name:"+myA.name()
                        +";age:"+myA.age()
                        +";other names:"+Arrays.toString(myA.otherNames())
                        +"]");
            }
        }
        // reading of annotations from method
        Method[] methods = clazz.getMethods();
        Arrays.stream(methods).forEach(System.out::println);
        for (Method m : methods) {
            if ("method".equals(m.getName())) {
                for(Annotation a : m.getAnnotations()) {
                    System.out.println(a.toString());
                }
            }
        }
    }
}
