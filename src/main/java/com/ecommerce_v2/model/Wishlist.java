package com.ecommerce_v2.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "wishlist")
public class Wishlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "created_date")
    private Date createddate;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Wishlist() {
    }

    public Wishlist(Integer id, User user, Date createddate, Product product) {
        this.id = id;
        this.user = user;
        this.createddate = createddate;
        this.product = product;
    }

    public Wishlist(User user, Product product) {
        this.user = user;
        this.product = product;
        this.createddate=new Date();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreateddate() {
        return createddate;
    }

    public void setCreateddate(Date createddate) {
        this.createddate = createddate;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
