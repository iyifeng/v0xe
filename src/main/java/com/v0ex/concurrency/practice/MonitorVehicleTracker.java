package com.v0ex.concurrency.practice;

import org.apache.commons.collections.map.HashedMap;

import java.util.Collections;
import java.util.Map;

/**
 * MonitorVehicleTracker
 * <p/>
 * Monitor-based vehicle tracker implementation
 *
 * @author Brian Goetz and Tim Peierls
 * ThreadSafe
 */
public class MonitorVehicleTracker {
    private final Map<String,MutablePoint> locations;
    public MonitorVehicleTracker(Map<String,MutablePoint> locations){
        this.locations = deepCopy(locations);
    }

    public synchronized Map<String,MutablePoint> getLocations(){
        return deepCopy(locations);
    }

    public synchronized MutablePoint getLacation(String id){
        MutablePoint loc = locations.get(id);
        return loc == null ? null : new MutablePoint(loc);
    }

    public synchronized void setLocation(String id,int x,int y){
        MutablePoint loc = locations.get(id);
        if (loc == null){
            throw new IllegalArgumentException("No such ID :"+id);
        }
        loc.x = x;
        loc.y = y;
    }

    private static Map<String,MutablePoint> deepCopy(Map<String,MutablePoint> m){
        Map<String,MutablePoint> result = new HashedMap();
        for (String id :m.keySet()){
            result.put(id,new MutablePoint(m.get(id)));
        }
        return Collections.unmodifiableMap(result);
    }
}
