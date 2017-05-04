package com.santanawilliams.lostandfound;

/**
 * Item class
 */

public class Item {
    private int id;
    private String name;
    private String type;
    private String contactInfo;
    private String description;

    public Item() {
        id = 0;
        name = "NULL";
        type = "NULL";
        contactInfo = "NULL";
        description = "NULL";
    }

    public Item(int i, String n, String t, String c, String d) {
        id = i;
        name = n;
        type = t;
        contactInfo = c;
        description = d;
    }

    // Setters
    public void setID(int i) { id = i; }
    public void setName(String s) { name = s;}
    public void setType(String t) { type = t;}
    public void setContactInfo(String s) { contactInfo = s;}
    public void setDescription(String s) { description = s;}

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getType() { return type; }
    public String getContactInfo() { return contactInfo; }
    public String getDescription() { return description; }
}
