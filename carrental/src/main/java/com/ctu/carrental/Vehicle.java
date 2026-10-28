//@author Knobs
package com.ctu.carrental;

public class Vehicle {
    private int id;
    private String make;
    private String model;
    private double dailyRate;
    private boolean available;
    
    public Vehicle(){
    }//end empty constructor
    
    public Vehicle(int id, String make, String model, double dailyRate, boolean available){
        this.id = id;
        this.make = make;
        this.model = model;
        this.dailyRate = dailyRate;
        this.available = available;
    }//end constructor
    
    public int getId(){
        return id;
    }//end getId method
    public void setId(int id){
        this.id = id;
    }//end setId method
    
    public String getMake(){
        return make;
    }//end getMake method
    public void setMake(String make){
        this.make = make;
    }//end setMake method
    
    public String getModel(){
        return model;
    }//end getModel method
    public void setModel(String model){
        this.model = model;
    }//end setModel method
    
    public double getDailyRate(){
        return dailyRate;
    }//end getDailyRate method
    public void setDailyRate(double dailyRate){
        this.dailyRate = dailyRate; 
    }//end setDailyRate method

    public boolean isAvailable(){
        return available;
    }//end isAvailable method  
    public void setAvailable(boolean available){
        this.available = available;
    }//end setAvailable method
}//end class
