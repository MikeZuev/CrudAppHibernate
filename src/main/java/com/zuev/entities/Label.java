package com.zuev.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "labels")
public class Label {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "label_id")
    private long id;

    @Column(name = "name")
    private String name;

    @ManyToMany( cascade = CascadeType.ALL)
    @JoinTable(
            name = "post_label",
            joinColumns = @JoinColumn(name = "label_id"),
            inverseJoinColumns = @JoinColumn(name = "post_id")
    )
    private List<Post> posts;

    public Label(){

    }

    public Label(String name) {
        this.name = name;
    }

    public Label(long id, String name){
        this.id = id;
        this.name = name;
    }
    public Label(String name, List<Post> posts){
        this.name = name;
        this.posts = posts;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    @Override
    public String toString() {
        return "Label{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
