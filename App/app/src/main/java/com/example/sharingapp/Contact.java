package com.example.sharingapp;
import java.util.UUID;
public class Contact extends Observable{

    private String username;
    private String email;
    private String id;

    public Contact(String username, String email, String id) {
        this.username = username;
        this.email = email;
        if (id == null ){
            setId();
        } else {
            updateId(id);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!Contact.class.isAssignableFrom(obj.getClass())) {
            return false;
        }

        Contact c = (Contact) obj;

        if(this.username == c.username && this.email == c.email && this.id == c.id ){
            return true;
        }
        else{
            return false;
        }
    }

    public void setId() {
        this.id = UUID.randomUUID().toString();
        notifyObservers();
    }
    public void updateId(String id){
        this.id = id;
        notifyObservers();
    }

    public void setUsername(String username) {
        this.username = username;
        notifyObservers();
    }

    public void setEmail(String email) {
        this.email = email;
        notifyObservers();
    }

    public void setId(String id) {
        this.id = id;
        notifyObservers();
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getId() {
        return id;
    }
}
