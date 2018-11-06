package com.carag.dataserver.model;

import com.carag.dataserver.config.AllowedTypes;
import com.carag.dataserver.config.CredentialStore;
import com.carag.dataserver.connectors.AlarmConnector;
import com.carag.dataserver.connectors.WebConnector;

import java.util.LinkedList;
import java.util.List;

public class Camera {

    private List<Image> imageList = new LinkedList<>();
    private WebConnector connector;

    private String name;
    private String target;
    private AllowedTypes type;
    private int sleepTime;
    private int id;

    public Camera(AllowedTypes type, CredentialStore store) throws Exception{



        this.connector = setConnector(type, store);



        System.out.println();

    }


    private WebConnector setConnector(AllowedTypes type, CredentialStore store){
        switch (type){
            case Alarm: return new AlarmConnector(store);
            default: throw new IllegalArgumentException("Connectore type " + type.toString() + " is not valid..." );
        }
    }


    public Image getNextFrame(){
        return imageList.get(0);
    }

    public void update(){

        Image i = new Image(connector.getImage(target));
        imageList.add(i);

        if (imageList.size() > 10) {
            imageList.remove(0);
        }

    }

    public List<Image> getImageList() {
        return imageList;
    }

    public WebConnector getConnector() {
        return connector;
    }

    public void setConnector(WebConnector connector) {
        this.connector = connector;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public int getSleepTime() {
        return sleepTime;
    }

    public void setSleepTime(int sleepTime) {
        this.sleepTime = sleepTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Camera camera = (Camera) o;

        if (sleepTime != camera.sleepTime) return false;
        if (id != camera.id) return false;
        if (imageList != null ? !imageList.equals(camera.imageList) : camera.imageList != null) return false;
        if (connector != null ? !connector.equals(camera.connector) : camera.connector != null) return false;
        if (name != null ? !name.equals(camera.name) : camera.name != null) return false;
        if (target != null ? !target.equals(camera.target) : camera.target != null) return false;
        return type != null ? type.equals(camera.type) : camera.type == null;
    }

    @Override
    public int hashCode() {
        int result = imageList != null ? imageList.hashCode() : 0;
        result = 31 * result + (connector != null ? connector.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (target != null ? target.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + sleepTime;
        result = 31 * result + id;
        return result;
    }
}
