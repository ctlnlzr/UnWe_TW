package edu.tw;

import edu.tw.database.entity.Varsta;
import edu.tw.database.repository.VarstaController;

public class Main {
    public static void main(String[] args) {

        Varsta varsta=new Varsta();
        VarstaController varstaController=new VarstaController();
        System.out.println(varstaController.findByLuna(2).toString());
    }
}
