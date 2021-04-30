package org.old.wang.entity;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * @author wangtingting
 * @date 2021/4/30
 */
@Slf4j
@Data
@AllArgsConstructor
@ToString
public class Person implements Cloneable, Serializable {

    private String name;

    private Integer age;

    private Friend friend;

    public void setFriendName(String friendName) {
        this.friend.setName(friendName);
    }

    @Override
    public Object clone() {
        try {
            Person person = (Person) super.clone();
            person.friend = (Friend) friend.clone();
            return person;
        } catch (CloneNotSupportedException e) {
            log.error("Person clone exception.", e);
        }

        return null;
    }

    public Object deepClone() throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(byteArrayOutputStream);
        oos.writeObject(this);

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(byteArrayInputStream);
        return ois.readObject();
    }

}
