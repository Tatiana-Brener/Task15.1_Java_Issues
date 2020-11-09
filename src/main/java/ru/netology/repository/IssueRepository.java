package ru.netology.repository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ru.netology.domain.Issue;

import java.util.*;

@AllArgsConstructor
@NoArgsConstructor

public class IssueRepository {
    private List<Issue> issues = new ArrayList<>();

    public void addIssue(Issue issue) {
        issues.add(issue);
    }

    public List<Issue> getAll() {

        return issues;
    }

    public boolean addAll(Collection<? extends Issue> issues) {
        return this.issues.addAll(issues);
    }

    public List<Issue> getAllOpen() {
        List<Issue> openIssues = new ArrayList<>();
        for(Issue issue : getAll()) {
            if (issue.isOpen()) {
                openIssues.add(issue);
            }
        }
        return openIssues;
    }

    public List<Issue> getAllClosed() {
        List<Issue> closedIssues = new ArrayList<>();
        for(Issue issue : getAll()) {
            if (issue.isClosed()) {
                closedIssues.add(issue);
            }
        }
        return closedIssues;
    }
}
