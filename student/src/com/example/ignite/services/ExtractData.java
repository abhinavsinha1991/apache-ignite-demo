package com.example.ignite.services;

import com.example.ignite.StudentData;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.IgniteCompute;
import org.apache.ignite.Ignition;
import org.apache.ignite.cluster.ClusterGroup;

import java.util.ArrayList;
import java.util.List;

public class ExtractData implements StudentData {

    private static Ignite ignite = null;

    public static void main(String[] args) {

        // Enable client mode locally.
        Ignition.setClientMode(true);

        // Start Ignite in client mode.
        ignite = Ignition.start("/home/akshay/Documents/apache-ignite-fabric-1.9.0-bin/examples/config/example-ignite.xml");

        // Create cache on all the existing and future server nodes.
        IgniteCache<Integer, String> cache = ignite.getOrCreateCache("demoCache");

        putStudentData(cache);

        List<String> firstNameList = getStudentData(cache);

        displayStudentData(firstNameList);

    }

    /**
     * To put tha data in the cache.
     * @param cache
     */
    private static void putStudentData(IgniteCache<Integer, String> cache) {

        cache.put(student1.getRollNumber(), student1.getFirstName());
        cache.put(student2.getRollNumber(), student2.getFirstName());
        cache.put(student3.getRollNumber(), student3.getFirstName());

    }

    /**
     * To get the data from teh cache.
     * @param cache
     * @return
     */
    private static List<String> getStudentData(IgniteCache<Integer, String> cache) {

        List<String> firstNameList = new ArrayList<String>();
        ClusterGroup clientGroup = ignite.cluster().forClients();

        IgniteCompute clientCompute = ignite.compute(clientGroup);

        // Execute computation on the client nodes.
        clientCompute.broadcast(() ->
        {
            firstNameList.add(cache.get(student1.getRollNumber()));
            firstNameList.add(cache.get(student2.getRollNumber()));
            firstNameList.add(cache.get(student3.getRollNumber()));

        });

        return firstNameList;
    }

    /**
     * To display that data.
     * @param firstNameList
     */
    private static void displayStudentData(List<String> firstNameList){

        System.out.println("First Name of students in ascending order of roll number : \n");
        System.out.println(firstNameList.get(0));
        System.out.println(firstNameList.get(1));
        System.out.println(firstNameList.get(2));
    }

}
