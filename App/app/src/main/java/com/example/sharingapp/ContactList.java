package com.example.sharingapp;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class ContactList {

    private ArrayList<Contact> contacts = new ArrayList<Contact>();
    private String FILENAME= "contacts.sav";;

    public ContactList() {

        contacts = new ArrayList<Contact>();
    }

    public void setContacts(ArrayList<Contact> contacts) {
        this.contacts = contacts;
    }

    public ArrayList<Contact> getContacts() {
        return contacts;
    }

    public ArrayList<String> getAllUsernames() {
        ArrayList<String> userNames = new ArrayList<String>();
        for (Contact contact : contacts) {
            userNames.add(contact.getUsername());
        }
        return userNames;
    }

    public void addContact(Contact contact){
        contacts.add(contact);
    }

    public void deleteContact(Contact contact){
        contacts.remove(contact);
    }

    public Contact getContact(int index){
        return contacts.get(index);
    }

    public int getSize(){
        return contacts.size();
    }

    public int getIndex(Contact contact){
        int pos = 0;
        for (Contact i : contacts) {
            if (contact.getId().equals(i.getId())) {
                return pos;
            }
            pos = pos+1;
        }
        return -1;
    }
    /* TODO */
    public boolean hasContact(Contact contact){

        /* The equals is overridden in the Contact.java to match the username, email and id. This can be changed according to the requirements */
        if(contacts.contains(contact)){
            return true;
        }else {
            return false;
        }
    }

    public Contact getContactByUsername(String username){

        for (Contact contact : contacts) {
            if (contact.getUsername().equals(username)) {
                return contact;
            }
        }
        return new Contact("","","");
    }

    public void loadContacts(Context context) {

        try {
            FileInputStream fis = context.openFileInput(FILENAME);
            InputStreamReader isr = new InputStreamReader(fis);
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Contact>>() {}.getType();
            contacts = gson.fromJson(isr, listType); // temporary
            fis.close();
        } catch (FileNotFoundException e) {
            contacts = new ArrayList<Contact>();
        } catch (IOException e) {
            contacts = new ArrayList<Contact>();
        }
    }

    public void saveContacts(Context context) {
        try {
            FileOutputStream fos = context.openFileOutput(FILENAME, 0);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(contacts, osw);
            osw.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isUsernameAvailable(String username){
        for (Contact contact : contacts) {
            if (contact.getUsername().equals(username)) {
                return false;
            }
        }
        /* not found, so available */
        return true;
    }
}
