package com.example.utils;

public class UserDatapojo {
	private String name;
    private String job;
    
    //No-args constructor (Jackson needs this)
    public UserDatapojo() {
    	
    }
    
    public UserDatapojo(String name, String job) {
        this.name = name;
        this.job = job;
    }
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}

}
