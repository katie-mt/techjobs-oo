package org.launchcode.models.data;
//packaged code placed in this file for organizational reasons to package code surrounding the data together

import javafx.geometry.Pos;
import org.launchcode.models.*;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
public class JobData {

    private ArrayList<Job> jobs = new ArrayList<>();
    private static JobData instance;

    private JobFieldData<Employer> employers = new JobFieldData<>();
    private JobFieldData<Location> locations = new JobFieldData<>();
    private JobFieldData<CoreCompetency> coreCompetencies = new JobFieldData<>();
    private JobFieldData<PositionType> positionTypes = new JobFieldData<>();

    //constructor filling in an instance (loadData method is in JobDataImporter)
    private JobData() {
        JobDataImporter.loadData(this);
    }


    public static JobData getInstance() {
        if (instance == null) {
            instance = new JobData();
        }
        return instance;
    }

    //find a job by its ID
    public Job findById(int id) {
        for (Job job : jobs) {
            if (job.getId() == id)
                return job;
        }

        return null;
    }

    public ArrayList<Job> findAll() {
        return jobs;
    }


    //calling JobFieldType which enables the ask of data related to a specific field or all fields
    //Find all jobs matching the given string in the given column/property
    public ArrayList<Job> findByColumnAndValue(JobFieldType column, String value) {

        ArrayList<Job> matchingJobs = new ArrayList<>();

        for (Job job : jobs) {
            if (getFieldByType(job, column).contains(value))
                matchingJobs.add(job);
        }

        return matchingJobs;
    }

    //Find all jobs matching the given string in any fields
    public ArrayList<Job> findByValue(String value) {

        ArrayList<Job> matchingJobs = new ArrayList<>();

        for (Job job : jobs) {

            if (job.getName().toLowerCase().contains(value)) {
                matchingJobs.add(job);
                continue;
            }

            for (JobFieldType column : JobFieldType.values()) {
                if (column != JobFieldType.ALL && getFieldByType(job, column).contains(value)) {
                    matchingJobs.add(job);
                    break;
                }
            }
        }

        return matchingJobs;
    }


    public void add(Job job) {
        jobs.add(job);
    }


    private static JobField getFieldByType(Job job, JobFieldType type) {
        switch(type) {
            case EMPLOYER:
                return job.getEmployer();
            case LOCATION:
                return job.getLocation();
            case CORE_COMPETENCY:
                return job.getCoreCompetency();
            case POSITION_TYPE:
                return job.getPositionType();
        }

        throw new IllegalArgumentException("Cannot get field of type " + type);
    }

    public JobFieldData<Employer> getEmployers() {
        return employers;
    }

    public JobFieldData<Location> getLocations() {
        return locations;
    }

    public JobFieldData<CoreCompetency> getCoreCompetencies() {
        return coreCompetencies;
    }

    public JobFieldData<PositionType> getPositionTypes() {
        return positionTypes;
    }
}
