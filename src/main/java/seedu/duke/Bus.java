package seedu.duke;

import java.util.ArrayList;
import java.util.Arrays;

public class Bus {

    String busNumber;
    ArrayList<BusStops> route = new ArrayList<>();

    public Bus(String busNo, BusStops[] stops) {
        busNumber = busNo;
        route.addAll(Arrays.asList(stops));
    }

    public Bus(String busNo, ArrayList<BusStops> stops) {
        busNumber = busNo;
        route.addAll(stops);
    }

    public ArrayList<String> getStopNames() {

        ArrayList<String> routeNames = new ArrayList<>();
        route.forEach(stop -> routeNames.add(stop.getName()));
        return routeNames;
    }

    public String getBusNumber() {
        return this.busNumber;
    }

    ArrayList<BusStops> getPossibleRoute(String startingLoc, String destination) {
        ArrayList<BusStops> allStopsFromStart = new ArrayList<>();
        ArrayList<BusStops> finalRoute = new ArrayList<>();
        ArrayList<String> allStopNamesFromStart = new ArrayList<>();
        ArrayList<String> routeNames = new ArrayList<>(getStopNames());
        routeNames.replaceAll(String::toLowerCase);

        if (routeNames.contains(startingLoc.toLowerCase())) {
            int startingIndex = routeNames.indexOf(startingLoc.toLowerCase()) + 1;
            int size = routeNames.size();
            allStopNamesFromStart.addAll(routeNames.subList(startingIndex, size));
            allStopsFromStart.addAll(route.subList(startingIndex, size));
            if (allStopNamesFromStart.contains(destination.toLowerCase())) {
                int endIndex = allStopNamesFromStart.indexOf(destination.toLowerCase());
                finalRoute.addAll(allStopsFromStart.subList(0, endIndex));
            }
        }
        return finalRoute;
    }

    @Override
    public String toString() {
        if (route.size() > 0) {
            String printableRoute = String.join("-> ", getStopNames());
            return busNumber + "\n\t" + printableRoute;
        }
        return null;
    }

}