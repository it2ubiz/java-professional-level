import annotations.AfterSuite;
import annotations.BeforeSuite;
import annotations.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Java Core. Professional level. Lesson 7
 *
 * @author Nika Zurbaevi
 * @version dated January 26, 2021
 */
class TestingClass {

    public static <T> void start(Class<T> cls) {
        performTests(cls);
    }

    private static <T> void performTests(Class<T> cls) throws RuntimeException {
        TestingClass testingObject;
        try {
            testingObject = (TestingClass) cls.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        Method beforeMethod = null;
        Method afterMethod = null;
        Method[] methods = cls.getMethods();
        List<MethodsPriority> testingMethods = new ArrayList<>();

        for (Method method : methods)
            if (method.getAnnotation(BeforeSuite.class) != null) {
                if (beforeMethod != null) {
                    throw new RuntimeException("Missing method with @BeforeSuite annotation");
                }
                beforeMethod = method;
            } else if (method.getAnnotation(AfterSuite.class) != null) {
                if (afterMethod != null) {
                    throw new RuntimeException("Missing method with @AfterSuite annotation");
                }
                afterMethod = method;
            } else if (method.getAnnotation(Test.class) != null) {
                Test annotationTst = method.getAnnotation(Test.class);
                testingMethods.add(new MethodsPriority(method, annotationTst.priority()));
            }

        testingMethods.sort(Comparator.comparing(MethodsPriority::getPriority));

        try {
            if (beforeMethod != null) {
                beforeMethod.invoke(testingObject);
            }
            for (MethodsPriority methodsPriority : testingMethods) {
                methodsPriority.method.invoke(testingObject);
            }
            if (afterMethod != null) {
                afterMethod.invoke(testingObject);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(priority = 3)
    public void thirdTest() {
        System.out.println("Call test №3");
    }

    @Test(priority = 2)
    public void secondTest() {
        System.out.println("Call test №2");
    }

    @Test(priority = 5)
    public void firstTest() {
        System.out.println("Call test №1");
    }

    @BeforeSuite
    public void beforeAll() {
        System.out.println("The method is executed before all tests");
    }

    @AfterSuite
    public void afterAll() {
        System.out.println("The method is executed after all tests");
    }
}