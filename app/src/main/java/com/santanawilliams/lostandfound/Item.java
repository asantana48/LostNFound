package com.santanawilliams.lostandfound;

/**
 * Created by andre on 4/29/2017.
 */

public class Item {

    public enum ItemType {
        SUPPLIES("Office Supplies"),
        ELECTRONICS ("Electronics"),
        CLOTHING("Clothing"),
        JEWELRY("Jewelry"),
        MISC("Miscellaneous");

        private final String strValue;
        ItemType(String t) {
            strValue = t;
        }
        @Override
        public String toString() {
            return strValue;
        }
    }

    private int id;
    private String name;
    private ItemType type;
    private String contactInfo;
    private String description;

    public Item() {
        id = 0;
        name = "NULL";
        type = ItemType.MISC;
        contactInfo = "NULL";
        description = "NULL";
    }

    public Item(int i, String n, ItemType t, String c, String d) {
        id = i;
        name = n;
        type = t;
        contactInfo = c;
        description = d;
    }

    public void setID(int i) { id = i; }
    public void setName(String s) { name = s;}
    public void setType(ItemType t) { type = t;}
    public void setContactInfo(String s) { contactInfo = s;}
    public void setDescription(String s) { description = s;}

    public int getId() { return id; }
    public String getName() { return name; }
    public ItemType getType() { return type; }
    public String getContactInfo() { return contactInfo; }
    public String getDescription() { return description; }
}
