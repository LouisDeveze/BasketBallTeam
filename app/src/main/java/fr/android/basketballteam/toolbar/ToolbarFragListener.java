package fr.android.basketballteam.toolbar;

public interface ToolbarFragListener {

    /**This method is called by the toolbar fragment when the Gallery image button is pressed*/
    public void onFragmentGallerySelected();
    /**This method is called by the toolbar fragment when the Team image button is pressed*/
    public void onFragmentTeamSelected();
    /**This method is called by the toolbar fragment when the Home image button is pressed*/
    public void onFragmentHomeSelected();
    /**This method is called by the toolbar fragment when the Match image button is pressed*/
    public void onFragmentMatchSelected();
    /**This method is called by the toolbar fragment when the Option image button is pressed*/
    public void onFragmentMapSelected();
}
