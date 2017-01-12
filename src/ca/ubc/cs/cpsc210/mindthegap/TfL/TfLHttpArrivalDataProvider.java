package ca.ubc.cs.cpsc210.mindthegap.TfL;

/*
 * Copyright 2015-2016 Department of Computer Science UBC
 */

import ca.ubc.cs.cpsc210.mindthegap.model.Line;
import ca.ubc.cs.cpsc210.mindthegap.model.Station;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

/**
 * Wrapper for TfL Arrival Data Provider
 */
public class TfLHttpArrivalDataProvider extends AbstractHttpDataProvider {
    //private static final String ARRIVALS_API_BASE = "https://api.tfl.gov.uk";              // for TfL data
    private static final String ARRIVALS_API_BASE = "http://kunghit.ugrad.cs.ubc.ca:6060";   // for simulated data (3pm to midnight)
    private Station stn;

    public TfLHttpArrivalDataProvider(Station stn) {
        super();
        this.stn = stn;
    }

    @Override
    protected URL getURL() throws MalformedURLException {
        String naptanId = stn.getID();
        String storedLineId = "";
        for (Line line : stn.getLines()) {
            if (line.getId() != null) {
                if (storedLineId.equals("")) {
                    storedLineId = storedLineId + line.getId();
                } else {
                    storedLineId = storedLineId + "," + line.getId();
                }
            }
        }
        return new URL(ARRIVALS_API_BASE + "/Line/" + storedLineId + "/Arrivals?stopPointId=" + naptanId + "&app_id=&app_key=");
    }
}
