package com.baeldung.jira;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.JiraRestClientFactory;
import com.atlassian.jira.rest.client.api.domain.Comment;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.input.IssueInput;
import com.atlassian.jira.rest.client.api.domain.input.IssueInputBuilder;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class MyJiraClient {

    private String username;
    private String password;
    private String jiraUrl;
    private JiraRestClient restClient;

    public MyJiraClient(String username, String password, String jiraUrl) {
        this.username = username;
        this.password = password;
        this.jiraUrl = jiraUrl;
        this.restClient = getJiraRestClient();
    }

    public static void main(String[] args) {

        MyJiraClient myJiraClient = new MyJiraClient("yasin.bhojawala", "jira@P455", "http://jira.baeldung.com");

        final String issueKey = "BAEL-1139";
        myJiraClient.updateIssueDescription(issueKey, "This is description from my Jira Client");
        Issue issue = myJiraClient.getIssue(issueKey);
        System.out.println(issue.getDescription());

        myJiraClient.voteForAnIssue(issue);

        System.out.println(myJiraClient.getTotalVotesCount(issueKey));

        myJiraClient.addComment(issue, "This is comment from my Jira Client");

        List<Comment> comments = myJiraClient.getAllComments(issueKey);
        comments.forEach(c -> System.out.println(c.getBody()));

        myJiraClient.close();
    }

    public Issue getIssue(String issueKey) {
        Issue issue = restClient.getIssueClient().getIssue(issueKey).claim();
        return issue;
    }

    public void voteForAnIssue(Issue issue) {
        restClient.getIssueClient().vote(issue.getVotesUri()).claim();
    }

    public int getTotalVotesCount(String issueKey) {
        Issue updatedIssue = getIssue(issueKey);
        return updatedIssue.getVotes().getVotes();
    }

    public void addComment(Issue issue, String commentBody) {
        restClient.getIssueClient().addComment(issue.getCommentsUri(), Comment.valueOf(commentBody));
    }

    public List<Comment> getAllComments(String issueKey) {
        Issue issue = getIssue(issueKey);
        List<Comment> comments = new ArrayList<>();
        issue.getComments().forEach(c -> comments.add(c));
        return comments;
    }

    public void updateIssueDescription(String issueKey, String newDescription) {
        IssueInput input = new IssueInputBuilder().setDescription(newDescription).build();
        restClient.getIssueClient().updateIssue(issueKey, input).claim();
    }

    private JiraRestClient getJiraRestClient() {
        JiraRestClientFactory factory = new AsynchronousJiraRestClientFactory();

        URI jiraServerUri = getJiraUri();
        return factory
          .createWithBasicHttpAuthentication(jiraServerUri, this.username, this.password);
    }

    private URI getJiraUri() {
        URI jiraServerUri = null;
        try {
            jiraServerUri = new URI(this.jiraUrl);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return jiraServerUri;
    }

    private void closeRestClient(JiraRestClient restClient) {
        try {
            restClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void close() {
        closeRestClient(restClient);
    }
}
