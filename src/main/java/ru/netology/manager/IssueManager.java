package ru.netology.manager;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ru.netology.domain.Issue;
import ru.netology.repository.IssueRepository;

public class IssueManager {
    private IssueRepository repository;

    public void saveIssue(Issue issue) {
        repository.addIssue(issue);
    }
}
