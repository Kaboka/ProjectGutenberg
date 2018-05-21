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
    private float longitude;
    private float latitude;

    public City(String name ,float longitude, float latitude) {
        this.city_name = name;
        this.longitude = longitude;
        this.latitude = latitude;
    }
    
    public String getName(){
        return city_name; 
    }

    public float getLongitude() {
        return longitude;
    }

    public float getLatitude() {
        return latitude;
    }
    
}
