package software.kalender.soruuygulamasi.Helpers;

import java.util.HashMap;
import java.util.Map;

import software.kalender.soruuygulamasi.Enums.JokerEnum;
import software.kalender.soruuygulamasi.Enums.StoreEnum;
import software.kalender.soruuygulamasi.MainActivity;
import software.kalender.soruuygulamasi.Statics;

public class StoreHelper {

    HashMap<Integer, Long> mapPrice = new HashMap<>();

    public StoreHelper(){
        mapPrice.put(1, Long.valueOf(12000));
    }

    public int buy(int id){
        //TODO

        if(Statics.player.getPoint() < mapPrice.get(id)){
            return 1;
        }

        switch (id){
            case StoreEnum.JOKER_DOUBLE:
                Statics.player.incrementJokerDouble();
                Statics.player.decrementPoint(mapPrice.get(id));
            break;
        }

        MainActivity.updateUI();

        return 0;
    }
}
