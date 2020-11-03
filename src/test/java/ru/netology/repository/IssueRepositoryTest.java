package ru.netology.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Issue;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class IssueRepositoryTest {
    IssueRepository repository = new IssueRepository();

    private Issue firstIssue = new Issue(1, "Issue_1", "Tatiana",
            Set.of("label1"), Set.of("Galina B"), true, false );
    private Issue secondIssue = new Issue(2, "Issue_2", "Anna",
            Set.of("Label2, label1"), Set.of("Tatiana"), true, false );
    private Issue thirdIssue = new Issue(3, "Issue_3", "Tatiana",
            Set.of("Label_3"), Set.of("Anna"), false, true );
    private Issue fourthIssue = new Issue(4, "Issue_4", "Tatiana",
            Set.of("Label_4"), Set.of("Ivan"), true, false );
    private Issue fifthIssue = new Issue(5, "Issue_5", "Galina",
            Set.of("Label_5"), Set.of("Ivan"), false, true );

    @BeforeEach
    public void setUp() {
        List<Issue> issues = List.of(firstIssue, secondIssue, thirdIssue, fourthIssue, fifthIssue);
        repository.addAll(issues);
    }


    @Test
    public void shouldGetAllOpenIssue() {

        List<Issue> expected = List.of(firstIssue, secondIssue, fourthIssue);

        List<Issue> actual = repository.getAllOpen();
        assertEquals(expected, actual);
    }

        @Test
    public void shouldGetAllClosedIssue() {

        List<Issue> expected = List.of(thirdIssue, fifthIssue);

        List<Issue> actual = repository.getAllClosed();
        assertEquals(expected, actual);
    }
}