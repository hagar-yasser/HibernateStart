package org.example.entities;


import org.hibernate.Session;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Entity
public class MyMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int myMemberId;
    private String name;
    @ManyToMany(mappedBy = "myMembers")
    private Set<MyGroup> myGroups =new HashSet<>();

    @OneToMany(mappedBy = "author",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Message> messages=new ArrayList<>();

    public MyMember(String name) {
        this.name = name;
    }
    public MyMember() {
    }


    public int getMyMemberId() {
        return myMemberId;
    }

    public void setMyMemberId(int myMemberId) {
        this.myMemberId = myMemberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<MyGroup> getMyGroups() {
        return myGroups;
    }

    public void setMyGroups(Set<MyGroup> myGroups) {
        this.myGroups = myGroups;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    @Override
    public String toString() {
        return "MyMember{" +
                "myMemberId=" + myMemberId +
                ", name='" + name ;
    }
}
