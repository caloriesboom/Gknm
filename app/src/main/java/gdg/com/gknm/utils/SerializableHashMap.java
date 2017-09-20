package gdg.com.gknm.utils;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by GUO.DG on 2017-9-13.
 */

public class SerializableHashMap implements Serializable {
    private HashMap<String,String> map;

    public HashMap<String, String> getMap() {
        return map;
    }

    public void setMap(HashMap<String, String> map) {
        this.map = map;
    }
}
