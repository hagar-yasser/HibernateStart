package org.example.entities;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.lang.reflect.Member;
import java.util.List;
import java.util.Scanner;

public class CommandMenu {
    public static void printOperations(){
        System.out.println("Please enter the number of the operation you want to perform");
        System.out.println("1-Add Group");
        System.out.println("2-Add Member");
        System.out.println("3-Add Message");
        System.out.println("4-Show All Groups");
        System.out.println("5-Show All Members");
        System.out.println("6-Add member to group");
        System.out.println("0-EXIT");
    }
    public static void callEnteredMethod(){
        Scanner sc = new Scanner(System.in);
        while (true){
            printOperations();
            String command=sc.nextLine();
            switch (command){
                case "0":
                    return;
                case "1":
                    addGroupMenu();
                    break;
                case "2":
                    addMemberMenu();
                    break;
                case "3":
                    addMessageMenu();
                    break;
                case "4":
                    showAllGroups();
                    break;
                case "5":
                    showAllMembers();
                    break;
                case "6":
                    addMemberToGroupMenu();
                    break;
                default:
                    System.out.println("Please enter a valid option");


            }
        }
    }
    public static void addGroupMenu(){
        System.out.println("Enter the group's name");
        Scanner sc=new Scanner(System.in);
        addGroupToDb(sc.nextLine());

    }
    private static void addGroupToDb(String name){
        MyGroup group=new MyGroup(name);
        StandardServiceRegistry registry=new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sessionFactory=new MetadataSources(registry).buildMetadata().buildSessionFactory();
        try(Session s=sessionFactory.openSession()){
            Transaction transaction=s.beginTransaction();
            s.persist(group);
            transaction.commit();
        }
    }
    public static void addMemberMenu(){
        System.out.println("Enter the member's name");
        Scanner sc=new Scanner(System.in);
        addMemberToDb(sc.nextLine());

    }
    private static void addMemberToDb(String name){
        MyMember member=new MyMember(name);
        StandardServiceRegistry registry=new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sessionFactory=new MetadataSources(registry).buildMetadata().buildSessionFactory();
        try(Session s=sessionFactory.openSession()){
            Transaction transaction=s.beginTransaction();
            s.persist(member);
            transaction.commit();
        }
    }
    public static void addMemberToGroupMenu(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the member's id");
        int memberId=sc.nextInt();
        System.out.println("Enter the group's id");
        int groupId=sc.nextInt();
       boolean valid= addMemberToGroup(memberId,groupId);
        if(!valid){
            System.out.println("please enter a valid group id and a valid author id ");
        }
    }
    private static boolean addMemberToGroup(int memberId,int groupId){
        StandardServiceRegistry registry=new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sessionFactory=new MetadataSources(registry).buildMetadata().buildSessionFactory();
        try(Session s=sessionFactory.openSession()){
            Transaction transaction=s.beginTransaction();
            MyMember member=s.load(MyMember.class,memberId);
            MyGroup group=s.load(MyGroup.class,groupId);
            if(group==null||member==null){
                return false;
            }
            group.getMyMembers().add(member);
            member.getMyGroups().add(group);
            transaction.commit();
        }
        return true;
    }
    public static void addMessageMenu(){
        Scanner sc =new Scanner(System.in);
        System.out.println("Enter the message's content");
        String content=sc.nextLine();
        System.out.println("Enter the message's author id");
        int authorId=sc.nextInt();
        System.out.println("Enter the message's group id");
        int groupId=sc.nextInt();
        boolean valid =addMessageToDb(content,authorId,groupId);
        if(!valid){
            System.out.println("please enter a valid group id and an author id that is in this group");
        }



    }
    private static boolean addMessageToDb(String content,int authorId,int groupId){
        Message m = new Message(content);

        StandardServiceRegistry registry=new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sessionFactory=new MetadataSources(registry).buildMetadata().buildSessionFactory();
        try(Session s=sessionFactory.openSession()){
            Transaction transaction=s.beginTransaction();
            MyMember author=s.load(MyMember.class,authorId);
            MyGroup group=s.load(MyGroup.class,groupId);
            if(group==null||!group.getMyMembers().contains(author)){
                return false;
            }
            m.setAuthor(author);
            m.setMyGroup(group);
            s.persist(m);
            author.getMessages().add(m);
            group.getMessages().add(m);
            transaction.commit();
        }
        return true;
    }
    private static void showAllGroups(){
        StandardServiceRegistry registry=new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sessionFactory=new MetadataSources(registry).buildMetadata().buildSessionFactory();
        List<MyGroup> groups;
        try(Session s=sessionFactory.openSession()){
           groups= s.createQuery("from MyGroup", MyGroup.class).list();
        }
        System.out.println(groups);
    }
    private static void showAllMembers(){
        StandardServiceRegistry registry=new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sessionFactory=new MetadataSources(registry).buildMetadata().buildSessionFactory();
        List<MyMember> members;
        try(Session s=sessionFactory.openSession()){
            members= s.createQuery("from MyMember", MyMember.class).list();
        }
        System.out.println(members);
    }


}
