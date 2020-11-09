package ru.netology.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Issue;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class IssueRepositoryTest {
    IssueRepository repository = new IssueRepository();

    private Issue firstIssue = new Issue(1, "Issue_1", "Tatiana", Set.of("label1"), Set.of("Tatiana, Galina"),
            true, false, LocalDate.of(2020, 12, 3));
    private Issue secondIssue = new Issue(2, "Issue_2", "Anna", Set.of("label1"), Set.of("Tatiana, Galina"),
            true, false, LocalDate.of(2020, 6, 15));
    private Issue thirdIssue = new Issue(3, "Issue_3", "Tatiana", Set.of("label2, label3"), Set.of("Anna"),
            false, true, LocalDate.of(2019, 9, 22));
    private Issue fourthIssue = new Issue(4, "Issue_4", "Tatiana", Set.of("label2, label3"), Set.of("Ivan"),
            true, false, LocalDate.of(2020, 1, 15));
    private Issue fifthIssue = new Issue(5, "Issue_5", "Galina", Set.of("Label5"), Set.of("Ivan"),
            false, true , LocalDate.of(2019, 12, 12));

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