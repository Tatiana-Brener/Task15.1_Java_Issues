package ru.netology.comparator;

import ru.netology.domain.Issue;

import java.util.Comparator;
import java.util.List;

public class IssueByDataComparator implements Comparator<Issue> {

    @Override
    public int compare(Issue o1, Issue o2) {

        return o1.getIssueDate().compareTo(o2.getIssueDate());
    }

    @Override
    public Comparator<Issue> reversed() {
        return Comparator.comparing(Issue::getIssueDate).reversed();
    }
}
