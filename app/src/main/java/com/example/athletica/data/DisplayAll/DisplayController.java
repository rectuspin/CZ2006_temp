package com.example.athletica.data.DisplayAll;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.example.athletica.data.event.Event;
import com.example.athletica.data.facility.Facility;
import com.example.athletica.data.search.Filter;
import com.example.athletica.data.user.DataManager;
import com.example.athletica.ui.home.HomeActivity;
import com.example.athletica.ui.search.DisplayAll;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Map;


public class DisplayController {
    Context context;
    DataManager dataManager;
    Filter filter;
    private String value;

    
    private ArrayList<String> facilities = new ArrayList<>();
    private ArrayList<String> index = new ArrayList<>();


    private ArrayList<String> eventsName = new ArrayList<String>();// unique indexes of all the records are stored in this list
    private ArrayList<String> eventIds = new ArrayList<>();

    private ArrayList<String> userNames = new ArrayList<String>();
    private ArrayList<String> userIds = new ArrayList<>();


    private ArrayList<Map> eventMap;
    private ArrayList<Facility> facilityMap;
    private ArrayList<Map> userMap;

    private int state;


    public DisplayController(Context context, int state) {
        this.context = context;
        this.state = state;
        dataManager = new DataManager();
        filter=new Filter();
    }

    public void getFacilities(final DisplayAll displayAll) {
        facilityMap = (ArrayList<Facility>) dataManager.readDataAll(context, "");
        for (Facility facility : facilityMap) {
            String str2 = facility.getName();  //parsing facilities and index's in separate lists
            String i = facility.getFacilityIndex();
            facilities.add(str2);
            index.add(i);
        }
        displayAll.initRecyclerView(state, facilities, index);
    }


    public void getEvents(final DisplayAll displayAll) {
        dataManager.getEventKeys(new DataManager.DataStatus() {
            @Override
            public void dataLoaded(Object object) {
                eventMap = ((ArrayList<Map>) object);
                filter.endEventCheck(eventMap);
                for (Map<String, String> map : eventMap) {
                    String str1 = map.get("key");
                    String str2 = map.get("name");
                    eventIds.add(str1);
                    eventsName.add(str2);
                }
                displayAll.initRecyclerView(state, eventsName, eventIds);

            }
        },"");
    }

    public void getEvents(final HomeActivity homeActivity) {
        dataManager.getEventKeys(new DataManager.DataStatus() {
            @Override
            public void dataLoaded(Object object) {
                eventMap = ((ArrayList<Map>) object);
                filter.endEventCheck(eventMap);
                filter.sortEvents(eventMap);
                filter.truncateEvents(eventMap,5);
                for (Map<String, String> map : eventMap) {

                    String str1 = map.get("key");
                    String str2 = map.get("name");
                    eventIds.add(str1);
                    eventsName.add(str2);
                }

                homeActivity.initRecyclerView(1, eventsName, eventIds);
            }
        },  "");
    }


    public void getUsers(final DisplayAll displayAll) {
        dataManager.getAllUsers(new DataManager.DataStatus() {
            @Override
            public void dataLoaded(Object object) {
                userMap = ((ArrayList<Map>) object);
                for (Map<String, String> map : userMap) {
                    String str1 = map.get("key");
                    String str2 = map.get("name");
                    userIds.add(str1);
                    userNames.add(str2);
                }
                displayAll.initRecyclerView(state, userNames, userIds);

            }
        }, "");
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public ArrayList<Facility> sortFacilityByName() {
        ArrayList<Facility> facilities = dataManager.readDataAll(context, "");
        facilities.sort(Comparator.comparing(Facility::getName));
        return facilities;
    }

}
