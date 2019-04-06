package com.tcg.bean;

import java.io.Serializable;

/**
 * @author Administrator
 * @Title: Goods
 * @ProjectName redisLearning
 * @Description: TODO
 * @date 2019/4/7 000700:26
 */
public class Goods implements Serializable,Comparable<Goods> {
    private static final long serialVersionUID = 5820943087732837L;
    private String id;
    private String name;
    private String price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                '}';
    }

    public Goods(String id, String name, String price) {
        super();
        this.id = id;
        this.name = name;
        this.price = price;
    }


    @Override
    public int compareTo(Goods o) {
        return 0;
    }
}
