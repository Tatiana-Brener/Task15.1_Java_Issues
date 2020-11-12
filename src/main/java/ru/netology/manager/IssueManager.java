package ru.netology.manager;

import ru.netology.domain.Issue;
import ru.netology.repository.IssueRepository;

import java.util.*;
import java.util.function.Predicate;


public class IssueManager {
    private IssueRepository repository;

    public void saveIssue(Issue issue) {

        repository.addIssue(issue);
    }

    public List<Issue> filterByAuthor(String author) {
        List<Issue> issuesByAuthor = new ArrayList<>();
        Predicate<Issue> predicate = issue -> issue.getAuthor().equalsIgnoreCase(author);

        for (Issue issue : repository.getAll()) {
            if (predicate.test(issue)) {
                issuesByAuthor.add(issue);
            }
        }
        return issuesByAuthor;
    }

    public List<Issue> filterByLabel(Set<String> label) {
        List<Issue> issuesByLabel = new ArrayList<>();
        Predicate<Issue> predicate = issue -> issue.getLabel().equals(label);

        for (Issue issue : repository.getAll()) {
            if (predicate.test(issue)) {
                issuesByLabel.add(issue);
            }
        }
        return issuesByLabel;
    }

    public List<Issue> filterByAssignee(Set<String> assignee) {
        List<Issue> issuesByAssignee = new ArrayList<>();
        Predicate<Issue> predicate = issue -> issue.getAssignee().equals(assignee);

        for (Issue issue : repository.getAll()) {
            if (predicate.test(issue)) {
                issuesByAssignee.add(issue);
            }
        }
        return issuesByAssignee;
    }

    public List<Issue> sortByOldest(Comparator<Issue> comparator) {
        List<Issue> oldestIssues = new ArrayList<>();

        oldestIssues.addAll(repository.getAll());
        oldestIssues.sort(comparator);
        return oldestIssues;
    }

    public List<Issue> sortByNewest(Comparator<Issue> comparator) {
        List<Issue> newestIssues = new ArrayList<>();

        newestIssues.addAll(repository.getAll());
        newestIssues.sort(comparator.reversed());
        return newestIssues;
    }

    public boolean openIssueById(int id) {
        for (Issue issue : repository.getAll()) {
            if (issue.getId() == id) {
               issue.setOpen(true);
               return issue.isOpen();
            }
        }
        return false;
    }

    public boolean closedIssueById(int id) {
        for (Issue issue : repository.getAll()) {
            if (issue.getId() == id) {
                issue.setClosed(true);
                return issue.isClosed();
            }
        }
        return false;
    }
}
