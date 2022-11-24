package com.coolw.code.redis.pojo;

import java.io.Serializable;

/**
 * @Classname Role
 * @Description TODO
 * @Author lw
 * @Date 2020-03-27 14:49
 */
public class Role implements Serializable {
    
    private static final long serialVersionUID = 3007624208967555508L;

    private long id;
    private String roleName;
    private String note;

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", note='" + note + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
