package org.launchcode.models.forms;

import org.launchcode.models.JobFieldType;

/**
 * Created by LaunchCode
 */
public class SearchForm {

    //these fields represent the data associated with the search form (each is necessary to display and process the form)

    // The search options
    private JobFieldType[] fields = JobFieldType.values();

    // The selected search options
    private JobFieldType searchField = JobFieldType.ALL;

    // The search string
    private String keyword;

    public JobFieldType getSearchField() {
        return searchField;
    }

    public void setSearchField(JobFieldType searchField) {
        this.searchField = searchField;
    }

    public JobFieldType[] getFields() {
        return fields;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
