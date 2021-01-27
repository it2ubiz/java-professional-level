import java.lang.reflect.Method;

/**
 * Java Core. Professional level. Lesson 7
 *
 * @author Nika Zurbaevi
 * @version dated January 26, 2021
 */
public class MethodsPriority {
    public Method method;
    public Integer priority;

    public MethodsPriority(Method method, int priority) {
        this.method = method;
        this.priority = priority;
    }

    public Integer getPriority() {
        return priority;
    }
}
