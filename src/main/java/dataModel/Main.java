package dataModel;

import graphicalUserInterface.Controller;
import graphicalUserInterface.View;

public class Main {

    public static void main(String[] args) {

        View view = new View();
        Controller controller = new Controller(view);
        view.setVisible(true);
    }
}
