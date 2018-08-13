package com.example.wolf.userbean;


import java.util.List;

public class Userorder {

  private Integer oid;
  private long uid;
  private String name;
  private long type;
  private long status;
  private String orderDate;
  private String description;
  private List<Userorderitem> Userorderitem;

  public List<Userorderitem> getUserorderitem() {
    return Userorderitem;
  }

  public void setUserorderitem(List<Userorderitem> userorderitem) {
    Userorderitem = userorderitem;
  }

  public Integer getOid()
  {
    return oid;
  }

  public void setOid(Integer oid)
  {
    this.oid = oid;
  }

  public long getUid() {
    return uid;
  }

  public void setUid(long uid) {
    this.uid = uid;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public long getType() {
    return type;
  }

  public void setType(long type) {
    this.type = type;
  }


  public long getStatus() {
    return status;
  }

  public void setStatus(long status) {
    this.status = status;
  }


  public String getOrderDate() {
    return orderDate;
  }

  public void setOrderDate(String orderDate) {
    this.orderDate = orderDate;
  }


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

}
