package org.example.entities;

import javax.persistence.*;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int messageId;
    private String content;
    @ManyToOne
    @JoinColumn(name = "fk_author")
    private MyMember author;
    @ManyToOne
    @JoinColumn(name = "fk_myGroup")
    private MyGroup myGroup;

    public Message(String content) {
        this.content = content;
    }
    public Message() {

    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MyMember getAuthor() {
        return author;
    }

    public void setAuthor(MyMember author) {
        this.author = author;
    }

    public MyGroup getMyGroup() {
        return myGroup;
    }

    public void setMyGroup(MyGroup myGroup) {
        this.myGroup = myGroup;
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageId=" + messageId +
                ", content='" + content ;
    }
}
