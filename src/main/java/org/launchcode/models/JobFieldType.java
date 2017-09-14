package org.launchcode.models;

/**
 * Created by LaunchCode
 */
//enables the view and controller layers to ask for data related to a specific job field or all fields
public enum JobFieldType {
    //values render search and list options in the view and collect these options in the control layer.
    EMPLOYER ("Employer"),
    LOCATION ("Location"),
    CORE_COMPETENCY ("Skill"),
    POSITION_TYPE ("Position Type"),
    ALL ("All");

    private final String name;

    JobFieldType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
