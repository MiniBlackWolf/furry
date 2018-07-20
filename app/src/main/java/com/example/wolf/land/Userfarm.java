package com.example.wolf.land;

import com.example.wolf.seed.Seedfarm;

import java.io.Serializable;
import java.util.List;

public class Userfarm implements Serializable{

  private String fid;
  private Integer uid;
  private String sfid;
  private String validityPeriod;
  private String cameraUrl;
  private Integer remainM;
  private String mold;
  private Integer genre;
  private List<Seedfarm> seedfarm;

  public Userfarm()
  {
  }

  public String getFid()
  {
    return fid;
  }

  public void setFid(String fid)
  {
    this.fid = fid;
  }

  public Integer getUid()
  {
    return uid;
  }

  public void setUid(Integer uid)
  {
    this.uid = uid;
  }

  public String getSfid()
  {
    return sfid;
  }

  public void setSfid(String sfid)
  {
    this.sfid = sfid;
  }

  public String getValidityPeriod()
  {
    return validityPeriod;
  }

  public void setValidityPeriod(String validityPeriod)
  {
    this.validityPeriod = validityPeriod;
  }

  public String getCameraUrl()
  {
    return cameraUrl;
  }

  public void setCameraUrl(String cameraUrl)
  {
    this.cameraUrl = cameraUrl;
  }

  public Integer getRemainM()
  {
    return remainM;
  }

  public void setRemainM(Integer remainM)
  {
    this.remainM = remainM;
  }

  public String getMold()
  {
    return mold;
  }

  public void setMold(String mold)
  {
    this.mold = mold;
  }

  public Integer getGenre()
  {
    return genre;
  }

  public void setGenre(Integer genre)
  {
    this.genre = genre;
  }

  public List<Seedfarm> getSeedfarm()
  {
    return seedfarm;
  }

  public void setSeedfarm(List<Seedfarm> seedfarm)
  {
    this.seedfarm = seedfarm;
  }
}
