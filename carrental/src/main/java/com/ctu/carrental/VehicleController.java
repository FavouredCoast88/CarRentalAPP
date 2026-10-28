//@author Knobs
package com.ctu.carrental;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {
    
    private final VehicleService service;
    public VehicleController(VehicleService s){
        this.service = s;
    }//end constructor
    
    //http://localhost:8080/api/vehicles
    @GetMapping
    public List<Vehicle> getAll(){
        return service.findAll();
    }//end getAll method
    
    //http://localhost:8080/api/vehicles/search?make=
    @GetMapping("/search")
    public List<Vehicle> search(@RequestParam String make){
        return service.searchByMake(make);
    }//end search method
    
    //http://localhost:8080/api/vehicles/1
    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getOne(@PathVariable int id){
        Vehicle v = service.findById(id);
        
        if(v == null){
            return ResponseEntity.notFound().build();
        }//end if
        return ResponseEntity.ok(v);
    }//end getOne method
    
    //http://localhost:8080/api/vehicles
    @PostMapping
    public ResponseEntity<Vehicle> create(@RequestBody Vehicle v){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.add(v));
    }//end create method
    
    //http://localhost:8080/api/vehicles/1
    @PutMapping("/{id}")
    public ResponseEntity<Vehicle> update(@PathVariable int id, @RequestBody Vehicle updated){
        Vehicle v = service.update(id, updated);
        
        if(v == null){
            return ResponseEntity.notFound().build();
        }//end if
        return ResponseEntity.ok(v);
    }//end update method
    
    //http://localhost:8080/api/vehicles/1
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id){
        
        if(service.delete(id)){
            return ResponseEntity.noContent().build();
        }//end if
        return ResponseEntity.notFound().build();
    }//end delete method
    
    //http://localhost:8080/api/vehicles/1/rent
    @PutMapping("/{id}/rent")
    public ResponseEntity<Vehicle> rent(@PathVariable int id){
        try{
            Vehicle v = service.rent(id);
            
            if(v == null){
                return ResponseEntity.notFound().build();
            }//end if
            return ResponseEntity.ok(v);
        }catch(IllegalStateException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }//end catch
    }//end rent method
    
    //http://localhost:8080/api/vehicles/1/return
    @PutMapping("/{id}/return")
    public ResponseEntity<Vehicle> giveBack(@PathVariable int id){
        Vehicle v = service.giveBack(id);
        
        if(v == null){
            return ResponseEntity.notFound().build();
        }//end if
        return ResponseEntity.ok(v);
    }//end giveBack method
}//end class
