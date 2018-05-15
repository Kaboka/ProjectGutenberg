/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author ehn19
 */
public class City {
    private String city_name;

    public City(String name) {
        this.city_name = name;
    }
    
    public String getName(){
        return city_name; 
    }
    
}
