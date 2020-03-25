package patel.jay.personal.universeweightapp;

import java.util.List;


public class CardData implements CardDataInterface {

    private String headTitle;
    private Integer headBackgroundResource;
    private Integer mainBackgroundResource;


    public String getHeadTitle() {
        return headTitle;
    }

    public void setHeadTitle(String headTitle) {
        this.headTitle = headTitle;
    }

    public Integer getHeadBackgroundResource() {
        return headBackgroundResource;
    }

    @Override
    public List getListItems() {
        return null;
    }

    public void setHeadBackgroundResource(Integer headBackgroundResource) {
        this.headBackgroundResource = headBackgroundResource;
    }

    public Integer getMainBackgroundResource() {
        return mainBackgroundResource;
    }

    public void setMainBackgroundResource(Integer mainBackgroundResource) {
        this.mainBackgroundResource = mainBackgroundResource;
    }
}





