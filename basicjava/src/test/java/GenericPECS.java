import org.junit.Test;
import org.old.wang.Student;
import org.old.wang.impl.StudentImpl;

import java.util.ArrayList;

/**
 * @author wangtingting
 * @date 2021/4/26
 */
public class GenericPECS {

    @Test
    public void testPE() {
        ArrayList<StudentImpl> studentImplList = new ArrayList<>();
        studentImplList.add(new StudentImpl());

        ArrayList<? extends Student> studentList = studentImplList;
        for (Student student : studentList) {
            student.printIDE();
        }
    }

    @Test
    public void testCS() {
        ArrayList<? super Student> csList = new ArrayList<>();
        csList.add(new StudentImpl());

        for (Object student : csList) {
            ((Student)student).printIDE();
        }
    }

}
