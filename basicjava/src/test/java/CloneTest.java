import org.junit.Assert;
import org.junit.Test;
import org.old.wang.entity.Friend;
import org.old.wang.entity.Person;

/**
 * @author wangtingting
 * @date 2021/4/30
 */
public class CloneTest {

    /**
     * 基本类型赋值
     */
    @Test
    public void testBasicAssignment() {
        int a = 10;
        int b = a;

        Assert.assertEquals(a, b);

        a = 20;
        Assert.assertNotEquals(a, b);
        Assert.assertEquals(a, 20);
        Assert.assertEquals(b, 10);
    }

    /**
     * 对象赋值
     */
    @Test
    public void testClassAssignment() {
        Person person1 = new Person("person1", 20, new Friend("friend1"));

        /**
         * 对象赋值
         */
        Person person2 = person1;

        Assert.assertEquals(person1.getName(), person2.getName());
        Assert.assertEquals(person1.getAge(), person2.getAge());
        Assert.assertEquals(person1.getFriend(), person2.getFriend());


        person1.setName("person1-updated");
        person1.setAge(21);
        person1.setFriendName("friend2");

        Assert.assertEquals(person1, person2);
        Assert.assertEquals(person1.getName(), person2.getName());
        Assert.assertEquals(person2.getAge(), person2.getAge());
        Assert.assertEquals(person1.getFriend(), person2.getFriend());

    }

    /**
     * 浅拷贝：属性未实现 cloneable 接口 深拷贝：属性实现 cloneable 接口，并且在 clone 方法中调用属性的 clone 方法进行赋值
     */
    @Test
    public void testShallowClone() {
        Friend friend = new Friend("friend1");
        Person person1 = new Person("person1", 20, friend);
        Person person2 = (Person) person1.clone();

        Assert.assertTrue(person1 != person2);
        Assert.assertEquals(person1.getName(), person2.getName());
        Assert.assertEquals(person2.getAge(), person2.getAge());
        Assert.assertTrue(person1.getFriend() == person2.getFriend());

        person1.setName("person1-updated");
        person1.setAge(21);
        person1.setFriendName("friend2");

        Assert.assertNotEquals(person1.getName(), person2.getName());
        Assert.assertNotEquals(person1.getAge(), person2.getAge());
        Assert.assertEquals(person1.getFriend().getName(), person2.getFriend().getName());
        Assert.assertEquals(person1.getFriend(), person2.getFriend());
        Assert.assertTrue(person1.getFriend()==person2.getFriend());
    }

    /**
     * 深拷贝：属性实现 cloneable 接口，并且在 clone 方法中调用属性的 clone 方法进行赋值
     */
    @Test
    public void testDeepCloneWithClone() {
        Friend friend = new Friend("friend1");
        Person person1 = new Person("person1", 20, friend);
        Person person2 = (Person) person1.clone();

        Assert.assertTrue(person1 != person2);
        Assert.assertEquals(person1.getName(), person2.getName());
        Assert.assertEquals(person2.getAge(), person2.getAge());
        Assert.assertTrue(person1.getFriend() != person2.getFriend());

        person1.setName("person1-updated");
        person1.setAge(21);
        person1.setFriendName("friend2");

        Assert.assertNotEquals(person1.getName(), person2.getName());
        Assert.assertNotEquals(person1.getAge(), person2.getAge());
        Assert.assertNotEquals(person1.getFriend().getName(), person2.getFriend().getName());
    }

    /**
     * 深拷贝：新创建对象并属性赋值
     */
    @Test
    public void testDeepClone() {
        Friend friend = new Friend("friend1");
        Person person1 = new Person("person1", 20, friend);
        Person person2 = new Person(person1.getName(), person1.getAge(), new Friend(person1.getFriend().getName()));

        Assert.assertTrue(person1 != person2);
        Assert.assertTrue(person1.getFriend() != person2.getFriend());

        person1.setName("person1-updated");
        person1.setAge(21);
        person1.setFriend(new Friend("friend2"));

        Assert.assertNotEquals(person1.getName(), person2.getName());
        Assert.assertNotEquals(person1.getAge(), person2.getAge());
        Assert.assertNotEquals(person1.getFriend().getName(), person2.getFriend().getName());
    }

    /**
     * 深拷贝：使用 Serializable
     */
    @Test
    public void testDeepCloneWithSerializable() throws Exception {
        Friend friend = new Friend("friend1");
        Person person1 = new Person("person1", 20, friend);
        Person person2 = (Person) person1.deepClone();

        Assert.assertTrue(person1 != person2);
        Assert.assertTrue(person1.getFriend() != person2.getFriend());


        person1.setName("person1-updated");
        person1.setAge(21);
        person1.setFriendName("friend2");

        Assert.assertNotEquals(person1.getName(), person2.getName());
        Assert.assertNotEquals(person1.getAge(), person2.getAge());
        Assert.assertNotEquals(person1.getFriend().getName(), person2.getFriend().getName());
    }

}
