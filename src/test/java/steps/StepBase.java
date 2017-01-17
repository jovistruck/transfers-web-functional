package steps;

import config.Drivers;
import util.DataProvider;

/**
 * Created by jovianodias on 04/10/2016.
 */
class StepBase extends Drivers {

    static DataProvider dataProvider = new DataProvider();
    static String pickedManuscript;

    StepBase(){
        this.initializeWebdriver();
    }
}
