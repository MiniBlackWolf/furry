package com.example.wolf.seed;


import java.io.Serializable;

public class Seedfarm implements Serializable{

  private long myid;
  private String sfid;
  private long sid;
  private long growDate;
  private long swoingdate;
  private long lol;


  public Seedfarm()
  {
  }


  public long getMyid() {
    return myid;
  }

  public void setMyid(long myid) {
    this.myid = myid;
  }


  public String getSfid() {
    return sfid;
  }

  public void setSfid(String sfid) {
    this.sfid = sfid;
  }


  public long getSid() {
    return sid;
  }

  public void setSid(long sid) {
    this.sid = sid;
  }


  public long getGrowDate() {
    return growDate;
  }

  public void setGrowDate(long growDate) {
    this.growDate = growDate;
  }


  public long getSwoingdate() {
    return swoingdate;
  }

  public void setSwoingdate(long swoingdate) {
    this.swoingdate = swoingdate;
  }


  public long getLol() {
    return lol;
  }

  public void setLol(long lol) {
    this.lol = lol;
  }

}
