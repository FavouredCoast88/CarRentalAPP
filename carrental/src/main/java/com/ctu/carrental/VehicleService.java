//@author Knobs
package com.ctu.carrental;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {
    private final List<Vehicle> vehicles = new ArrayList<>();
    private int nextId = 4;

    public VehicleService(){
        vehicles.add(new Vehicle(1, "Toyota", "Corolla", 450.00, true));
        vehicles.add(new Vehicle(2, "VW", "Polo", 380.00, true));
        vehicles.add(new Vehicle(3, "Ford", "Ranger", 750.00, false));
    }//end constructor
    
    public List<Vehicle> findAll(){
        return vehicles;
    }//end findAll method
    
    public Vehicle findById(int id){
        for(Vehicle v : vehicles){
            if(v.getId() == id){
                return v;
            }//end if
        }//end for
        return null;
    }//end findById method
    
    public Vehicle add(Vehicle v){
        v.setId(nextId);
        nextId++;
        vehicles.add(v);
        return v;
    }//end add method
    
    public Vehicle update(int id, Vehicle updated){
        Vehicle existing = findById(id);

        if(existing == null){
            return null;
        }//end if

        existing.setMake(updated.getMake());
        existing.setModel(updated.getModel());
        existing.setDailyRate(updated.getDailyRate());
        existing.setAvailable(updated.isAvailable());
        return existing;
    }//end update method
    
    public boolean delete(int id){
        Vehicle v = findById(id);

        if(v == null){
            return false;
        }//end if
        
        vehicles.remove(v);
        return true;
    }//end delete method
    
    public Vehicle rent(int id){
        Vehicle v = findById(id);
        if(v == null){
            return null;
        }//end if
        
        if(!v.isAvailable()){
            throw new IllegalStateException("Vehicle" + id +
                    " is already rented.");
        }//end if
        
        v.setAvailable(false);
        return v;
    }//end rent method
    
    public Vehicle giveBack(int id){
        Vehicle v = findById(id);
        
        if(v == null){      
            return null;
        }//end if
        
        v.setAvailable(true);
        return v;
    }//end giveback method
    
    public List<Vehicle> searchByMake(String make){
        List<Vehicle> results = new ArrayList<>();
        
        for(Vehicle v : vehicles){
            if(v.getMake().toLowerCase().contains(make.toLowerCase())){
                results.add(v);
            }//end if
        }//end for
        return results;
    }//end searchByMake method
}//end class
