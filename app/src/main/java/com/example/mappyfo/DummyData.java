package com.example.mappyfo;

import java.util.ArrayList;
import java.util.List;

public final class DummyData {

    public static List<Location> getDefaultLocations() {
        List<Location> list = new ArrayList<>();
        list.add(new Location(R.string.title_indonesia, R.string.desc_indonesia, -7.1834508609048715, 108.47690659492922));
        list.add(new Location(R.string.title_australia, R.string.desc_australia, -21.164027412168338, 131.39623211847345));
        list.add(new Location(R.string.title_singapore, R.string.desc_singapore, 1.3143394, 103.7041639));
        list.add(new Location(R.string.title_malaysia, R.string.desc_malaysia, 3.316380719325731, 102.21981396032335));
        list.add(new Location(R.string.title_philippines, R.string.desc_philippines, 11.32869000261502, 122.49411687812682));
        return list;
    }
}
