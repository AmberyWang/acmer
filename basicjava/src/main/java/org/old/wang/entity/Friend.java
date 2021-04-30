package org.old.wang.entity;

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
public class Friend implements Cloneable, Serializable {

    private String name;

    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            log.error("Friend clone exception.", e);
        }
        return null;
    }

}
