package org.launchcode.controllers;

import org.launchcode.models.*;
import org.launchcode.models.forms.JobForm;
import org.launchcode.models.data.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping(value = "job")
public class JobController {

    private JobData jobData = JobData.getInstance();

    // The detail display for a given Job at URLs like /job?id=17
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model,  int id) {


        // TODO #1 - get the Job with the given ID and pass it into the view
        //pass data from the controller into the view
        //calling the method findByID within the JobData.java class and calling the parameter ID.  Set this to the variable retrieveJob
        //Essentially this is retrieving the job with the given ID, which is then passed into the view
        Job retrieveJob = jobData.findById(id);
        //two parameters, referencing "job" in the html file and connecting this with retrieveJob (which is displaying the job associated with the given ID)
        model.addAttribute("job",retrieveJob);
        //String ISTHISWORKING = "isthisworking";
        //model.addAttribute("ISTHISWORKING",ISTHISWORKING);
        //job-detail comes from template
        return "job-detail";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute(new JobForm());
        return "new-job";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model, @Valid JobForm jobForm, Errors errors) {

        // TODO #6 - Validate the JobForm model, and if valid, create a
        // new Job and add it to the jobData data store. Then
        // redirect to the job detail view for the new Job.

//        if String name = "";
//            return ("ERROR, PLEASE ENTER A VALUE");

        if (errors.hasErrors()) {
            model.addAttribute(new JobForm());

            else {

        //must be in same order as in Job (name, employer, location, positionType, coreCompetency
        String name = jobForm.getName();
        Employer employer = jobData.getEmployers().findById(jobForm.getEmployerId());
        Location location = jobData.getLocations().findById(jobForm.getlocationsId());
        PositionType positionType = jobData.getPositionTypes().findById(jobForm.getPositionTypesId());
        CoreCompetency coreCompetency = jobData.getCoreCompetencies().findById(jobForm.getcoreCompetencyId());

        //what makes an object Job requires the 5 attributes (name, employer, location, positionType, coreCompetency
        Job addedJob = new Job(name, employer, location, positionType, coreCompetency)
        //adding the new job to the session
        model.addAttribute(addedJob);

        return "";

    }
}
