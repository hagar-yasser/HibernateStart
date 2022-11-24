package org.example;

import org.example.entities.CommandMenu;
import org.example.entities.MyMember;
import org.example.entities.Message;
import org.example.entities.MyGroup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        CommandMenu.callEnteredMethod();

    }

}