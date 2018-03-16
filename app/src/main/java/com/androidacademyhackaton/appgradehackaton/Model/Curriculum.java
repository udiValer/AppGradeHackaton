package com.androidacademyhackaton.appgradehackaton.Model;
import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Curriculum {

    public String title;

    public Curriculum(){}

    public Curriculum(String title){
        this.title = title;
    }

    @Override
    public boolean equals(Object object){
        if (this == object) {
            return true;
        }
        if (object == null) {
            return false;
        }
        if(!(object instanceof Curriculum)){
            return false;
        }
        Curriculum curriculum = (Curriculum) object;
        return title.equals(curriculum.title);
    }

}
