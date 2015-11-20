package nsga;

import zdt.zdt1.ZDT1MasterController;

/**
 * Created by Delth on 17.11.2015.
 */
public class Main {


    public static void main(String[] args){
        System.out.println("We're live!");
        MasterController masterController = new ZDT1MasterController();
        masterController.start();
    }
}
