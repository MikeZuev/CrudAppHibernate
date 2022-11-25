package com.zuev.entities;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "writers")
public class Writer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "FirstName")
    private String firstName;
    @Column(name = "LastName")
    private String lastName;
    @OneToMany(mappedBy = "writer", fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST, CascadeType.REMOVE} )
    private List<Post> posts;

    public Writer(){

    }



    public Writer(long id, String firstName, String lastName){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }







    public Writer( String firstName, String lastName) {

        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Writer( String firstName, String lastName, List<Post> posts) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.posts = posts;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Post> getPosts() {
        return posts;
    }
    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    @Override
    public String toString() {
        return "Writer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
