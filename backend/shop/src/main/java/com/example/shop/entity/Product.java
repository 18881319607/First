package com.example.shop.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;        // 商品标题
    private String image;        // 图片URL
    private Double price;        // 售价
    private Double oldPrice;     // 原价
    private String shopName;     // 店铺名
    private Integer commentCount;// 评论数
    private String goodRate;     // 好评率
    private String tags;         // 标签

    // ===== Getter / Setter =====
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public Double getOldPrice() { return oldPrice; }
    public void setOldPrice(Double oldPrice) { this.oldPrice = oldPrice; }

    public String getShopName() { return shopName; }
    public void setShopName(String shopName) { this.shopName = shopName; }

    public Integer getCommentCount() { return commentCount; }
    public void setCommentCount(Integer commentCount) { this.commentCount = commentCount; }

    public String getGoodRate() { return goodRate; }
    public void setGoodRate(String goodRate) { this.goodRate = goodRate; }

    public String getTags() { return tags; }
    public void setTags(String tags) { this.tags = tags; }
}
