package com.example.wolf.land;

import java.io.Serializable;

/**
 * Create by CWbl 2018-07-10
 */
public class userseedandland implements Serializable
{
	private String fid;
	private long uid;
	private String sfid;
	private String validityPeriod;
	private String cameraUrl;
	private long remainM;
	private long myid;
	private long sid;
	private long growDate;
	private long swoingdate;
	private long lol;

	public userseedandland()
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

	public long getUid()
	{
		return uid;
	}

	public void setUid(long uid)
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

	public long getRemainM()
	{
		return remainM;
	}

	public void setRemainM(long remainM)
	{
		this.remainM = remainM;
	}

	public long getMyid()
	{
		return myid;
	}

	public void setMyid(long myid)
	{
		this.myid = myid;
	}

	public long getSid()
	{
		return sid;
	}

	public void setSid(long sid)
	{
		this.sid = sid;
	}

	public long getGrowDate()
	{
		return growDate;
	}

	public void setGrowDate(long growDate)
	{
		this.growDate = growDate;
	}

	public long getSwoingdate()
	{
		return swoingdate;
	}

	public void setSwoingdate(long swoingdate)
	{
		this.swoingdate = swoingdate;
	}

	public long getLol()
	{
		return lol;
	}

	public void setLol(long lol)
	{
		this.lol = lol;
	}
}
