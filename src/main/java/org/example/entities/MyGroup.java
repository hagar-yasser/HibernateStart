package org.example.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Entity
public class MyGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int myGroupId;
    private String name;
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    @JoinTable(name = "myGroup_myMember",
            joinColumns = { @JoinColumn(name = "fk_myGroup") },
            inverseJoinColumns = { @JoinColumn(name = "fk_myMember") })
    private Set<MyMember> myMembers =new HashSet<>();
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "myGroup",
            cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private List<Message> messages=new ArrayList<>();

    public MyGroup(String name) {
        this.name = name;
    }
    public MyGroup() {
    }

    public int getMyGroupId() {
        return myGroupId;
    }

    public void setMyGroupId(int myGroupId) {
        this.myGroupId = myGroupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<MyMember> getMyMembers() {
        return myMembers;
    }

    public void setMyMembers(Set<MyMember> myMembers) {
        this.myMembers = myMembers;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    @Override
    public String toString() {
        return "MyGroup{" +
                "myGroupId=" + myGroupId +
                ", name='" + name + '\'' +
                ", myMembers=" + myMembers +
                ", messages=" + messages +
                '}';
    }
}
