package sid.roborally.game_mechanics;

import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;

public class IDComparator<T> implements Comparator<T> {

    @Override
    public int compare(T o1, T o2) {
        int v1, v2;
        try {
            v1 = (int) o1.getClass().getMethod("getID").invoke(o1);
            v2 = (int) o2.getClass().getMethod("getID").invoke(o2);
            if (v1 < v2) return -1;
            if (v1 > v2) return 1;
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    return 0;
    }
}
