package interface_adapter.mainmenu;

import view.LoginAndSignupView;

public class MainMenuBackButtonController implements ButtonController {
    public void click() {
        new LoginAndSignupView();
    }
}
