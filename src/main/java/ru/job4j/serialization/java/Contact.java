package ru.job4j.serialization.java;

import java.io.*;
import java.nio.file.Files;

public class Contact implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final int zipCode;
    private final String phone;

    public Contact(int zipCode, String phone) {
        this.zipCode = zipCode;
        this.phone = phone;
    }

    public int getZipCode() {
        return zipCode;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "Contact{"
                + "zipCode=" + zipCode
                + ", phone='" + phone + '\''
                + '}';
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        final Contact contact = new Contact(123456, "+7 (111) 111-11-11");
        final Contact result;

        try (ObjectOutputStream outputStream = new ObjectOutputStream(
                new FileOutputStream("contact.xml")
        )) {
            outputStream.writeObject(contact);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (ObjectInputStream inputStream = new ObjectInputStream(
                new FileInputStream("contact.xml")
        )) {
           result = (Contact) inputStream.readObject();
            System.out.println(result);
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }
}